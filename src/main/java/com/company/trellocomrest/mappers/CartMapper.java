package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.Cart;
import com.company.trellocomrest.dtos.project.cart.CartCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart fromCreateDto(CartCreateDto dto);
}
