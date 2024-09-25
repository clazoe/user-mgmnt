package com.canvia.usermgmnt.util;


import com.canvia.usermgmnt.dto.RegisterUserDto;
import com.canvia.usermgmnt.entity.Rol;
import com.canvia.usermgmnt.entity.RolEnum;
import com.canvia.usermgmnt.entity.Usuario;
import com.canvia.usermgmnt.repository.RoleRepository;
import com.canvia.usermgmnt.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminLoader(
        RoleRepository roleRepository,
        UserRepository  userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createAdministrator();
    }

    private void createAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setNombreUsuario("Admin");
        userDto.setCorreo("admin@email.com");
        userDto.setPassword("123456");

        Optional<Rol> optionalRole = roleRepository.findByName(RolEnum.ADMINISTRADOR);
        Optional<Usuario> optionalUser = userRepository.findByNombreUsuario(userDto.getNombreUsuario());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new Usuario()
            .setNombreUsuario(userDto.getNombreUsuario())
            .setCorreo(userDto.getCorreo())
            .setPassword(passwordEncoder.encode(userDto.getPassword()))
            .setRol(optionalRole.get());

        userRepository.save(user);
    }
}
