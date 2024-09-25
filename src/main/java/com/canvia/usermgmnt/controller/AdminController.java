package com.canvia.usermgmnt.controller;


import com.canvia.usermgmnt.dto.RegisterUserDto;
import com.canvia.usermgmnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequestMapping("/admins")
@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @DeleteMapping("/eliminarUsuario/{nombreUsuario}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public Map<String, Boolean> eliminarUsuario(@PathVariable String nombreUsuario) {
        userService.eliminarUsuario(nombreUsuario);
        return Collections.singletonMap("eliminaUsuarioStatus", true);
    }

    @PutMapping("/actualizaUsuario/{nombreUsuario}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public Map<String, Boolean> actualizaUsuario(@PathVariable String nombreUsuario,@RequestBody RegisterUserDto registerUserDto) {
        userService.actualizarUsuario(nombreUsuario,registerUserDto);
        return Collections.singletonMap("actualizacionStatus", true);
    }
}
