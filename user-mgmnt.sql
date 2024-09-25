DROP DATABASE usermgmnt;

CREATE DATABASE IF NOT EXISTS usermgmnt;

USE usermgmnt;

CREATE TABLE `usuario_rol_seq` (
  `next_val` bigint DEFAULT NULL
);

CREATE TABLE `usuario_seq` (
  `next_val` bigint DEFAULT NULL
);

CREATE TABLE `roles_seq` (
  `next_val` bigint DEFAULT NULL
);

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` enum('ADMINISTRADOR','USUARIO') NOT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `usermgmnt`.`roles`(`id`,`description`,`name`)
VALUES(1,'Rol de Administrador','ADMINISTRADOR');

INSERT INTO `usermgmnt`.`roles`(`id`,`description`,`name`)
VALUES(2,'Rol de Usuario','USUARIO');

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `correo` varchar(100) NOT NULL UNIQUE,
  `nombre_usuario` varchar(50) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `roles_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ROLES_ID` (`roles_id`),
  CONSTRAINT `FK_ROLES_ID` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE
);

CREATE TABLE `usuario_rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roles_id` int NOT NULL,
  `usuario_id` int NOT NULL UNIQUE,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE
);