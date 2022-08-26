package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.auth.AuthUser;
import com.company.trellocomrest.dtos.UserRegisterDTO;
import com.company.trellocomrest.dtos.auth.AuthUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {
    AuthUser fromRegisterDTO(UserRegisterDTO dto);

    AuthUserDTO toDTO(AuthUser domain);
}
