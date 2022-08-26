package com.company.trellocomrest.dtos.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRoleCreateDTO {
    private final String code;
    private final String name;
}
