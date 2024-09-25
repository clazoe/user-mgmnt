package com.canvia.usermgmnt.dto;

import com.canvia.usermgmnt.entity.Rol;
import com.canvia.usermgmnt.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioRolDto {
    private Usuario user;
    private Rol rol;
    private String password;
}
