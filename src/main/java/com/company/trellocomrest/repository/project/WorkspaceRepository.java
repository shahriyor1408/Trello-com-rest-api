package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    @Query("select b from Workspace b where b.id in (select t.id from Workspace t join t.authUsers u where u.id = :in_id) and b.isDeleted = false")
    List<Workspace> findAllNotDeleted(@Param(value = "in_id") Long id);

    @Query(value = "from Workspace where isDeleted = false and id = :in_id")
    Optional<Workspace> getByIdAndIsDeleted(@Param(value = "in_id") Long id);
}
