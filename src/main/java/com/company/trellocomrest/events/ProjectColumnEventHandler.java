package com.company.trellocomrest.events;

import com.company.trellocomrest.domains.project.Board;
import com.company.trellocomrest.domains.project.ProjectColumn;
import com.company.trellocomrest.domains.project.Workspace;
import com.company.trellocomrest.events.project.ProjectColumnMoveEvent;
import com.company.trellocomrest.exceptions.GenericNotFoundException;
import com.company.trellocomrest.repository.project.BoardRepository;
import com.company.trellocomrest.repository.project.ProjectColumnRepository;
import com.company.trellocomrest.repository.project.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectColumnEventHandler {
    private final ProjectColumnRepository projectColumnRepository;
    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;

    @EventListener
    public void move(@NotNull ProjectColumnMoveEvent projectColumnMoveEvent) {
        ProjectColumn projectColumn = projectColumnRepository.getByIdAndIsDeleted(projectColumnMoveEvent.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Column not found!", 404);
        });

        Board board = boardRepository.getByIdAndIsDeleted(projectColumnMoveEvent.getBoardId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        });

        Workspace workspace = workspaceRepository.getByIdAndIsDeleted(board.getWorkspace().getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Workspace not found!", 404);
        });

        if (projectColumn.getBoard().getWorkspace().getId().equals(workspace.getId())) {
            moveThisWorkSpace(projectColumn, projectColumnMoveEvent);
        } else {
            moveAnotherWorkSpace(projectColumn, projectColumnMoveEvent);
        }

    }

    private void moveAnotherWorkSpace(ProjectColumn projectColumn, ProjectColumnMoveEvent projectColumnMoveEvent) {

    }

    private void moveThisWorkSpace(ProjectColumn projectColumn, @NotNull ProjectColumnMoveEvent projectColumnMoveEvent) {
        if (projectColumn.getBoard().getId().equals(projectColumnMoveEvent.getBoardId())) {
            if (projectColumn.getColumnOrder() > projectColumnMoveEvent.getNewOrder()) {
                moveLeft(projectColumn, projectColumnMoveEvent.getNewOrder());
            } else {
                moveRight(projectColumn, projectColumnMoveEvent.getNewOrder());
            }
        } else {

        }
    }

    private void moveRight(ProjectColumn projectColumn, int newOrder) {
        projectColumnRepository.moveThisWorkSpace(projectColumn.getColumnOrder(), newOrder, projectColumn.getBoard().getId());
        projectColumn.setColumnOrder(newOrder);
        projectColumnRepository.save(projectColumn);
    }

    private void moveLeft(ProjectColumn projectColumn, int newOrder) {
        projectColumnRepository.moveThisWorkSpace(newOrder, projectColumn.getColumnOrder(), projectColumn.getBoard().getId());
        projectColumn.setColumnOrder(newOrder);
        projectColumnRepository.save(projectColumn);
    }
}
