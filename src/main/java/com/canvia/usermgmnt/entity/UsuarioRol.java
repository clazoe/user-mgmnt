package com.canvia.usermgmnt.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name = "usuario_rol")
@Entity
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
            @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roles_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rol rol;

    public Rol getRol() {
        return rol;
    }

    public UsuarioRol setRol(Rol rol) {
        this.rol = rol;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioRol setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }
}
