package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.Cart;
import com.company.trellocomrest.dtos.project.cart.CartCreateDto;
import com.company.trellocomrest.dtos.project.cart.CartDto;
import com.company.trellocomrest.dtos.project.cart.CartUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart fromCreateDto(CartCreateDto dto);

    CartDto toDto(Cart cart);

    List<CartDto> toDto(List<Cart> carts);

    void fromUpdateDto(CartUpdateDto cartUpdateDto, @MappingTarget Cart cart);
}
