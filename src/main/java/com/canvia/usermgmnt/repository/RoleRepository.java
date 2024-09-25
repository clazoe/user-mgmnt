package com.canvia.usermgmnt.repository;


import com.canvia.usermgmnt.entity.Rol;
import com.canvia.usermgmnt.entity.RolEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Rol, Integer> {
    Optional<Rol> findByName(RolEnum name);
}
