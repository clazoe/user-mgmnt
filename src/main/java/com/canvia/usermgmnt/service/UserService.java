package com.canvia.usermgmnt.service;


import com.canvia.usermgmnt.dto.RegisterUserDto;
import com.canvia.usermgmnt.dto.UsuarioDto;
import com.canvia.usermgmnt.dto.UsuarioMapper;
import com.canvia.usermgmnt.entity.Rol;
import com.canvia.usermgmnt.entity.RolEnum;
import com.canvia.usermgmnt.entity.Usuario;
import com.canvia.usermgmnt.repository.RoleRepository;
import com.canvia.usermgmnt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encriptaClave;
    private final UsuarioMapper usuarioMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encriptaClave,UsuarioMapper usuarioMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encriptaClave = encriptaClave;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDto> allUsers() {
        List<Usuario> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
       return users
               .stream()
               .map(usuarioMapper::toDto)
               .collect(Collectors.toList());
    }

    public Usuario createAdministrator(RegisterUserDto input) {
        Optional<Rol> optionalRole = roleRepository.findByName(RolEnum.obtenerRol(input.getRol()));
        if (optionalRole.isEmpty()) {
            return null;
        }
        var user = new Usuario()
                .setNombreUsuario(input.getNombreUsuario())
                .setCorreo(input.getCorreo())
                .setPassword(encriptaClave.encode(input.getPassword()))
                .setRol(optionalRole.get());

        return userRepository.save(user);
    }

    public Optional<Usuario> getUsuario(RegisterUserDto input) {
        return userRepository.findByNombreUsuario(input.getNombreUsuario());
    }

    @Transactional
    public void eliminarUsuario(String nombreUsuario) {
        Usuario user = userRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userRepository.delete(user);
    }

    @Transactional
    public void actualizarUsuario(String nombreUsuario, RegisterUserDto registerUserDto) {
        Usuario user = userRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Optional<Rol> optionalRole = roleRepository.findByName(RolEnum.obtenerRol(registerUserDto.getRol()));
        if (optionalRole.isEmpty()) {
            return;
        }
        user.setCorreo(registerUserDto.getCorreo());
        user.setNombreUsuario(registerUserDto.getNombreUsuario());
        user.setPassword(encriptaClave.encode(registerUserDto.getPassword()));
        user.setRol(optionalRole.get());
        userRepository.save(user);
    }
}
