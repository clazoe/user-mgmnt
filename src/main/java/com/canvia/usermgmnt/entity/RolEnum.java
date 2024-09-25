package com.canvia.usermgmnt.entity;


import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RolEnum {
    ADMINISTRADOR,
    USUARIO;

    private static final Map<String, RolEnum> map = Stream.of(values())
            .collect(Collectors.toMap(RolEnum::toString, Function.identity()));

    public static RolEnum obtenerRol(final String name) {
        RolEnum rolEnum = map.get(name);
        if (null == rolEnum) {
            throw new IllegalArgumentException(String.format("'%s' no es un rol valido. Roles validos: %s", name, Arrays.asList(values())));
        }
        return rolEnum;
    }
}
