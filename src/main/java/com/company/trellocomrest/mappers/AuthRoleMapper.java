package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.auth.AuthRole;
import com.company.trellocomrest.dtos.auth.AuthRoleCreateDTO;
import com.company.trellocomrest.dtos.auth.AuthRoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthRoleMapper {
    AuthRoleDTO toDTO(AuthRole entity);

    List<AuthRoleDTO> toDTO(List<AuthRole> entities);

    AuthRole fromCreateDTO(AuthRoleCreateDTO dto);
}
