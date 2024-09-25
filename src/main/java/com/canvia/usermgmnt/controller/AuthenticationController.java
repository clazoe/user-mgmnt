package com.canvia.usermgmnt.controller;


import com.canvia.usermgmnt.dto.LoginUserDto;
import com.canvia.usermgmnt.dto.RegisterUserDto;
import com.canvia.usermgmnt.entity.Usuario;
import com.canvia.usermgmnt.responses.LoginResponse;
import com.canvia.usermgmnt.service.AuthenticationService;
import com.canvia.usermgmnt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/creacionUsuario")
    public Map<String, Boolean> creacionUsuario(@RequestBody RegisterUserDto registerUserDto) {
        authenticationService.crearUsuarioRol(registerUserDto);
        return Collections.singletonMap("creacionUsuario", true);
    }

    @PostMapping("/loginUsuario")
    public ResponseEntity<LoginResponse> autentication(@RequestBody LoginUserDto loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}