package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.ProjectColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectColumnRepository extends JpaRepository<ProjectColumn, Long> {

    @Query("from ProjectColumn where isDeleted = false")
    List<ProjectColumn> findAllNotDelete();
}
