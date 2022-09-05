package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "from Cart where isDeleted = true")
    List<Cart> findAllNotDeleted();
}
