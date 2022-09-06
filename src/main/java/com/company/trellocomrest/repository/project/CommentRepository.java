package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "from Comment where isDeleted = false and id = :in_id")
    Optional<Comment> getByIdNotDeleted(@Param(value = "in_id") Long id);

    @Query(value = "from Comment where isDeleted = false and cart.id = :in_id")
    List<Comment> getAllNotDeleted(@Param(value = "in_id") Long id);
}
