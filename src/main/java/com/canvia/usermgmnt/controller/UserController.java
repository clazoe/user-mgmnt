package com.canvia.usermgmnt.controller;


import com.canvia.usermgmnt.dto.UsuarioDto;
import com.canvia.usermgmnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/usuariosList")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDto>> usuariosList() {
        List <UsuarioDto> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}
