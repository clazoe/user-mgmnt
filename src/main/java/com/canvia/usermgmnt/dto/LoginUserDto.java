package com.canvia.usermgmnt.dto;

public class LoginUserDto {
    private String nombreUsuario;
    private String password;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public LoginUserDto setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
