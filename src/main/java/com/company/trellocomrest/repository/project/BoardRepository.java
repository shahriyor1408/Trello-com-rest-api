package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(nativeQuery = true, value = "select * from board where id in (select board_id from board_auth_users where auth_user_id = :in_id)")
    List<Board> findAllNotDeleted(@Param(value = "in_id") Long id);
}
