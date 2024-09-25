package com.canvia.usermgmnt.dto;

import com.canvia.usermgmnt.entity.Usuario;
import com.canvia.usermgmnt.entity.UsuarioRol;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    //UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);
    UsuarioDto toDto(Usuario usuario);
    Usuario toEntity(UsuarioDto usuarioDto);
}
