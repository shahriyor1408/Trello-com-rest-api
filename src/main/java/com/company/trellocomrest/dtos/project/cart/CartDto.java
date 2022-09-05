package com.company.trellocomrest.dtos.project.cart;

import com.company.trellocomrest.dtos.AuditableDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto extends AuditableDto {
    private String title;
    private int cartOrder;
    private String description;
    private Timestamp startingTime;
    private Timestamp deadline;

    @Builder(builderMethodName = "childBuilder")
    public CartDto(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean isDeleted, String title, int cartOrder, String description, Timestamp startingTime, Timestamp deadline) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.title = title;
        this.cartOrder = cartOrder;
        this.description = description;
        this.startingTime = startingTime;
        this.deadline = deadline;
    }
}
