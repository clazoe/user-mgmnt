package com.canvia.usermgmnt.repository;

import com.canvia.usermgmnt.entity.UsuarioRol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends CrudRepository<UsuarioRol, Integer> {

}