package com.canvia.usermgmnt.dto;

import com.canvia.usermgmnt.entity.UsuarioRol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioRolMapper {
    UsuarioRolDto toUsuarioRolDto(UsuarioRol usuarioRol);
}
