package com.company.trellocomrest.controller.project;

import com.company.trellocomrest.controller.ApiController;
import com.company.trellocomrest.dtos.project.cart.CartCreateDto;
import com.company.trellocomrest.dtos.project.cart.CartDto;
import com.company.trellocomrest.dtos.project.cart.CartUpdateDto;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.project.CartService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CartController extends ApiController<CartService> {
    protected CartController(CartService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/cart/create", produces = "application/json")
    public ApiResponse<Long> create(@Valid @RequestBody CartCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @GetMapping(value = PATH + "/cart/get/{id}", produces = "application/json")
    public ApiResponse<CartDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @GetMapping(value = PATH + "/cart/getAll", produces = "application/json")
    public List<CartDto> getAll() {
        return service.getAll();
    }

    @PutMapping(value = PATH + "/cart/update", produces = "application/json")
    public ApiResponse<CartDto> update(@Valid @RequestBody CartUpdateDto cartUpdateDto) {
        return service.update(cartUpdateDto);
    }

    @DeleteMapping(value = PATH + "cart/delete/{id}", produces = "application/json")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
