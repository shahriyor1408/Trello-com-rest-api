package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.ProjectColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectColumnRepository extends JpaRepository<ProjectColumn, Long> {

    @Query("from ProjectColumn where isDeleted = false and board.id = :in_id")
    List<ProjectColumn> findAllNotDelete(@Param(value = "in_id") Long id);

    @Query(value = "from ProjectColumn where isDeleted = false and id = :in_id")
    Optional<ProjectColumn> getByIdAndIsDeleted(@Param(value = "in_id") Long id);
}
