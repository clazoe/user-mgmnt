package com.canvia.usermgmnt.dto;

import com.canvia.usermgmnt.entity.Rol;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioDto {
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;
    @JsonProperty("correo")
    private String correo;
    @JsonProperty("password")
    private String password;
    @JsonProperty("rol")
    private Rol rol;
}
