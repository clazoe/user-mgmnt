package com.canvia.usermgmnt.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String correo;
    private String password;
    private String nombreUsuario;
    private String rol;


}
