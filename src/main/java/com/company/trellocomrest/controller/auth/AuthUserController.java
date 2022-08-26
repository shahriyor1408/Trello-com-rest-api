package com.company.trellocomrest.controller.auth;

import com.company.trellocomrest.controller.ApiController;
import com.company.trellocomrest.domains.auth.AuthUser;
import com.company.trellocomrest.dtos.JwtResponse;
import com.company.trellocomrest.dtos.LoginRequest;
import com.company.trellocomrest.dtos.RefreshTokenRequest;
import com.company.trellocomrest.dtos.UserRegisterDTO;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.AuthUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthUserController extends ApiController<AuthUserService> {
    protected AuthUserController(AuthUserService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/auth/login", produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ApiResponse<>(service.login(loginRequest));
    }

    @GetMapping(value = PATH + "/auth/refresh", produces = "application/json")
    public ApiResponse<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ApiResponse<>(service.refreshToken(refreshTokenRequest));
    }

    @PostMapping(PATH + "/auth/register")
    public ApiResponse<AuthUser> register(@Valid @RequestBody UserRegisterDTO dto) {
        return new ApiResponse<>(service.register(dto));
    }

    @GetMapping(PATH + "/auth/activate")
    public ApiResponse<Boolean> activate(@RequestParam(name = "activation_code") String activationCode) {
        return new ApiResponse<>(service.activateUser(activationCode));
    }

    @GetMapping(PATH + "/auth/me")
    public AuthUser me() {
        return null;
    }
}
