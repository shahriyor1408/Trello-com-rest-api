package com.company.trellocomrest.services.project;

import com.company.trellocomrest.domains.project.Cart;
import com.company.trellocomrest.domains.project.ProjectColumn;
import com.company.trellocomrest.dtos.project.cart.CartCreateDto;
import com.company.trellocomrest.dtos.project.cart.CartDto;
import com.company.trellocomrest.dtos.project.cart.CartUpdateDto;
import com.company.trellocomrest.exceptions.GenericNotFoundException;
import com.company.trellocomrest.mappers.CartMapper;
import com.company.trellocomrest.repository.project.CartRepository;
import com.company.trellocomrest.repository.project.ProjectColumnRepository;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.auth.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final AuthUserService authUserService;
    private final ProjectColumnRepository projectColumnRepository;

    public Long create(CartCreateDto dto) {
        Cart cart = cartMapper.fromCreateDto(dto);
        cart.setCartOrder(cartRepository.findAllNotDeleted().size() + 1);
        cart.setCreatedBy(authUserService.getCurrentAuthUser().getId());
        cart.setAuthUsers(new ArrayList<>());
        ProjectColumn projectColumn = projectColumnRepository.findById(dto.getProjectColumnId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Project column not found!", 404);
        });
        cart.setProjectColumn(projectColumn);
        return cartRepository.save(cart).getId();
    }

    public CartDto get(Long id) {
        return null;
    }

    public List<CartDto> getAll() {
        return null;
    }

    public ApiResponse<CartDto> update(CartUpdateDto cartUpdateDto) {
        return null;
    }

    public ApiResponse<Void> delete(Long id) {
        return null;
    }
}
