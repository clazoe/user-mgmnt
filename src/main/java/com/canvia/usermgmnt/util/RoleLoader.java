package com.canvia.usermgmnt.util;


import com.canvia.usermgmnt.entity.Rol;
import com.canvia.usermgmnt.entity.RolEnum;
import com.canvia.usermgmnt.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;


    public RoleLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RolEnum[] roleNames = new RolEnum[] { RolEnum.ADMINISTRADOR, RolEnum.USUARIO};
        Map<RolEnum, String> roleDescriptionMap = Map.of(
            RolEnum.ADMINISTRADOR, "Rol de Administrador",
            RolEnum.USUARIO, "Rol de Usuario"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Rol> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Rol roleToCreate = new Rol();

                roleToCreate.setName(roleName)
                        .setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
}
