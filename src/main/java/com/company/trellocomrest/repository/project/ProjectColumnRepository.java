package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.ProjectColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectColumnRepository extends JpaRepository<ProjectColumn, Long> {

    @Query("from ProjectColumn where isDeleted = false and board.id = :in_id order by columnOrder")
    List<ProjectColumn> findAllNotDelete(@Param(value = "in_id") Long id);

    @Query(value = "from ProjectColumn where isDeleted = false and id = :in_id")
    Optional<ProjectColumn> getByIdAndIsDeleted(@Param(value = "in_id") Long id);

    @Modifying
    @Query(value = "update ProjectColumn set columnOrder = columnOrder + 1" +
            " where columnOrder >= :start_id and columnOrder <= :end_id and board.id = :in_id and isDeleted = false")
    @Transactional
    void moveThisWorkSpace(@Param(value = "start_id") int start, @Param(value = "end_id") int end, @Param(value = "in_id") Long id);

    @Modifying
    @Query(value = "update ProjectColumn set columnOrder = columnOrder - 1" +
            " where columnOrder >= :start_id and columnOrder <= :end_id and board.id = :in_id and isDeleted = false")
    @Transactional
    void move(@Param(value = "start_id") int start, @Param(value = "end_id") int end, @Param(value = "in_id") Long id);
}
