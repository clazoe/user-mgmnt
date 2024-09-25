
package com.canvia.usermgmnt.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Table(name = "roles")
@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RolEnum name;

    @Column(nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public RolEnum getName() {
        return name;
    }

    public Rol setName(RolEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Rol setDescription(String description) {
        this.description = description;
        return this;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

