package com.canvia.usermgmnt.service;


import com.canvia.usermgmnt.dto.*;
import com.canvia.usermgmnt.entity.Rol;
import com.canvia.usermgmnt.entity.RolEnum;
import com.canvia.usermgmnt.entity.Usuario;
import com.canvia.usermgmnt.entity.UsuarioRol;
import com.canvia.usermgmnt.repository.RoleRepository;
import com.canvia.usermgmnt.repository.UserRepository;
import com.canvia.usermgmnt.repository.UsuarioRolRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encriptaClave;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRolRepository usuarioRolRepository;
    private final UsuarioRolMapper usuarioRolMapper;



    public AuthenticationService(
        UserRepository userRepository,
        RoleRepository roleRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder encriptaClave,
        UsuarioRolRepository usuarioRolRepository,
        UsuarioRolMapper usuarioRolMapper
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encriptaClave = encriptaClave;
        this.usuarioRolRepository = usuarioRolRepository;
        this.usuarioRolMapper = usuarioRolMapper;
    }

    private Usuario crearUsuario(RegisterUserDto input) {
        Optional<Rol> optionalRole = roleRepository.findByName(RolEnum.obtenerRol(input.getRol()));

        var user = new Usuario()
            .setNombreUsuario(input.getNombreUsuario())
            .setCorreo(input.getCorreo())
            .setPassword(encriptaClave.encode(input.getPassword()))
            .setRol(optionalRole.orElseThrow(() -> new RuntimeException("Rol invalido")));
        return userRepository.save(user);
    }


    @Transactional
    public UsuarioRolDto crearUsuarioRol(RegisterUserDto input) {
        Usuario user = crearUsuario(input);
        UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(user);
            usuarioRol.setRol(user.getRol());
            usuarioRol = usuarioRolRepository.save(usuarioRol);
        return usuarioRolMapper.toUsuarioRolDto(usuarioRol);
    }

    public Usuario authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getNombreUsuario(),
                input.getPassword()
            )
        );

        return userRepository.findByNombreUsuario(input.getNombreUsuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<Usuario> allUsers() {
        List<Usuario> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
