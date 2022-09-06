package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "from Cart where isDeleted = false")
    List<Cart> findAllNotDeleted();

    @Query(value = "from Cart where isDeleted = false and id = :in_id")
    Optional<Cart> getByIdNotDeleted(@Param(value = "in_id") Long id);

    @Query(value = "from Cart where isDeleted = false and projectColumn.id = :in_id")
    List<Cart> findAllNotDeletedById(@Param(value = "in_id") Long id);
}
