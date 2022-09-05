package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select b from Board b where b.id " +
            "in (select t.id from Board t join t.authUsers u where u.id = :in_id) and b.isDeleted = false and b.workspace.id = :workspace_id")
    List<Board> findAllNotDeleted(@Param(value = "in_id") Long id, @Param(value = "workspace_id") Long workspaceId);

    @Query(value = "from Board where isDeleted = false and id = :in_id")
    Optional<Board> getByIdAndIsDeleted(@Param(value = "in_id") Long id);
}
