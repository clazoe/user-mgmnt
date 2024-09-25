
package com.canvia.usermgmnt.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, length = 50, nullable = false)
    private String nombreUsuario;

    @Column(unique = true, length = 100, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(
            name = "roles_id",
            nullable = false,
            foreignKey = @ForeignKey(
            name = "FK_ROLES_ID",
            foreignKeyDefinition = "FOREIGN KEY (roles_id) REFERENCES roles(id) ON DELETE CASCADE"
            ))
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + rol.getName().toString());

        return List.of(authority);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public Usuario setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Usuario setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public Usuario setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public Usuario setPassword(String password) {
        this.password = password;
        return this;
    }



    public Rol getRol() {
        return rol;
    }

    public Usuario setRol(Rol rol) {
        this.rol = rol;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + nombreUsuario + '\'' +
                ", email='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

