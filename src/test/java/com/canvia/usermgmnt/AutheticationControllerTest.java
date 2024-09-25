package com.canvia.usermgmnt;

import com.canvia.usermgmnt.controller.AuthenticationController;
import com.canvia.usermgmnt.dto.LoginUserDto;
import com.canvia.usermgmnt.dto.RegisterUserDto;
import com.canvia.usermgmnt.service.AuthenticationService;
import com.canvia.usermgmnt.service.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class AutheticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;

    @Test
    @DisplayName("Return creacionUsuario status when user is created")
    void returnCreacionUsuarioStatusWhenUserIsCreated(){
        RegisterUserDto dto = new RegisterUserDto();
        dto.setNombreUsuario("test");
        dto.setCorreo("test");
        dto.setPassword("test");
        dto.setRol("ADMINISTRADOR");
        Map<String, Boolean> retrunMap = authenticationController.creacionUsuario(dto);
        assertTrue(retrunMap.containsKey("creacionUsuario"));
    }

    @Test
    @DisplayName("Return user response  when user is created")
    void returnUserResponseWhenUserIsCreated(){
        LoginUserDto dto = new LoginUserDto();
        dto.setNombreUsuario("test");
        dto.setPassword("test");
        assertNotNull(authenticationController.autentication(dto));
    }
}
