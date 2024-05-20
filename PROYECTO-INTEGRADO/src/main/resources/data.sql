

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE DATABASE IF NOT EXISTS proyecto_integrado;

USE proyecto_integrado;

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
                            `id` int(20) NOT NULL AUTO_INCREMENT,
                            `nombre_usuario` varchar(200) NOT NULL,
                            `nombre` varchar(200) NOT NULL,
                            `apellidos` varchar(200) NOT NULL,
                            `sexo` enum('H','M') NOT NULL,
                            `correo_electronico` varchar(200) NOT NULL,
                            `contrasena` varchar(200) NOT NULL,
                            `tipo_usuario` enum('admin','ofertante','consumidor') NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


# LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Manolosfc7','Manuel','del Cuvillo Porrua','H','delcuvilloev@gmail.com','Sevillafc','admin');
INSERT INTO `usuario` VALUES (2,'ElBicho7','Cristiano','Ronaldo dos Santos Aveiro','H','realladronesfc@gmail.com','PEROMADREMIAHIJOMIO','consumidor');
INSERT INTO `usuario` VALUES (3,'ElGOAT10','Lionel Andrés','Messi Cuccittini','H','negreirafc@gmail.com','QUEMIRAHBOBO','ofertante');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
                           `id_evento` int(20) NOT NULL AUTO_INCREMENT,
                           `titulo` varchar(200) NOT NULL,
                           `fecha_evento` date NOT NULL,
                           `hora_evento` time NOT NULL,
                           `lugar` varchar(200) NOT NULL,
                           `descripcion` varchar(400) NOT NULL,
                           `invitados` varchar(200) NOT NULL,
                           `precio` double(7,2) NOT NULL,
                           `creador_id` int(20) NOT NULL,
                           PRIMARY KEY (`id_evento`),
                           FOREIGN KEY (`creador_id`)REFERENCES usuario(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES (1,'Quedada de Bicholovers','2024-05-25','19:07:07','Parque María Luisa','Se realizará un evento centrado exclusivamente de El Comandante, en el evento veremos sus Highlights, cantaremos sus canciones y haremos muchos abdominales','Will (de Los Futbolitos), DjMaRiiO y Manolo Lama',7.77,3);
INSERT INTO `eventos` VALUES (2,'Quedada de Messistas','2024-05-24','10:10:10','Parque María Luisa','Se hará una ceremonia donde comprometeremos nuestro destino con La Pulga, en este evento aprenderemos a hacer mate, veremos sus Highlights e insultaremos a holandeses y franceses','La Cobra, Vicent (de Los Futbolitos) y Josep Guardiola',10.10,3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
