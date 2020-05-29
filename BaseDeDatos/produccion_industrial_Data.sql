-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-03-2020 a las 06:06:57
-- Versión del servidor: 10.4.12-MariaDB
-- Versión de PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: produccion_industrial
--
USE produccion_industrial;
--
-- Volcado de datos para la tabla career
--

INSERT INTO career (id_career, name_career) VALUES
(1, 'Ingenieria en Ciencias y Sistemas'),
(2, 'Ingenieria Industrial'),
(3, 'Ingenieria Mecanica'),
(4, 'Ingenieria Civil'),
(5, 'Ingenieria Mecanica Industrial');

--
-- Volcado de datos para la tabla rol_user
--

INSERT INTO rol_user (id_rol, name_rol) VALUES
(1, 'Estudiante'),
(2, 'Docente'),
(3, 'Administrador');

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (carnet, `name`, email, phone, `password`, state, id_rol, id_career) VALUES
(1, 'admin', 'admin@cunoc.edu.gt', 12345678, 'PBKDF2WithHmacSHA256:3072:b4iXBXxi5t0hCgR8hp0tq32Nk0heMA7hphPuK0BWyIDuMwgZbDVtplgbEuwKI1xXIV39GoGcvDEGI9Tl3Hzllw==:1bLDMjf/8Kx7pL4KlcvJ9uDVqNyG3ejvBIgw2sIBBow=', 1, 3, 1),
(14567891, 'Estudiante Dos', 'Correo@eschool', 12345678, 'PBKDF2WithHmacSHA256:3072:ANd9UdVHNuZOSyhEAydBIBvxly7J8jd598a64N6TyAMgi6TNqPS7BESqEsl6E5+BKBV+KLAikJDGoHzQhZk+yg==:Pl2janKvI12jYMIjjDkAwpufvMOc8WSGyluZwcMI4q4=', 1, 1, 2),
(20630963, 'Estudiante Tres', 'Correo@gmail.com', 12345678, 'PBKDF2WithHmacSHA256:3072:Mmk6qIn2k7jkWmUKnZZxEIBVh/aBm+Sa9f9irdNCoHxPHJgdZQYy/niaB/LjvoXIy8u8Id2+9W8u/2I06z9CuQ==:89dgKfT2AlvgBQZ5ng2+wcm1sG9S+kl3LThkqJy1CBU=', 1, 1, 2),
(201630851, 'Estudiante Uno', 'correoooooo@es.com', 12345678, 'PBKDF2WithHmacSHA256:3072:iSG5Us4Tx758hZLKK2psqQrsHUNfdSMsTqi6lmi0lKyxtyt0l0Wp5OZ7I9MRUjyLT5LTdQHAVRjenQAARkGMmA==:eiVRkGlLo1JTn6iQ3qhUlBmZ6126Ui1wDSMseKZATXc=', 1, 1, 3),
(201630962, 'Daniel Gonzalez', 'danielgonzalezdagg@gmail.com', 46144415, 'PBKDF2WithHmacSHA256:3072:raEknd7p10X78+krykGK6XIgeiAT5Rox5dolNm+LHZLoMud7hUzoruGJ9l+KyyA5/UivqP2yndZKgcUDWgqfww==:Xc3nMDrA+IvXhcK11+Ny8h1BzcL2M/4TIod4c4ceY5w=', 1, 1, 1),
(201630978, 'Estefani Rodas', 'emailaaaaa@cunoc.edu.gt', 12345678, 'PBKDF2WithHmacSHA256:3072:IDPcr5NcA+yB5l+EsH9jTKDzAcxq3vjp7eB1xi5lj645R2KN9gE/RFXDjM0MA7bxJE0YuYqEwf/hRF1MrkLoDg==:TM/3Ay+69WASUEDcjSQ2+Au5VGnQ727kM/QkzJSjowI=', 1, 1, 2),
(201692468, 'Gabriela Maldonado', 'correoooooo@cunoc.edu.gt', 45781236, 'PBKDF2WithHmacSHA256:3072:R7TabfctND34V4zzRHO/2Mt6En1Fd9zZukRuBa/P7WTZUSgl1tmDrfAXTD3bbqQHJ1AfGTMxonvK2Dk1w2pPdg==:ZAlPdM5bDKWeOdmeiPIHpPXEK6l9sAcHvZ1+UdJ5dWg=', 1, 1, 5);

--
-- Volcado de datos para la tabla measure
--

INSERT INTO measure (id_measure, `name`) VALUES
(1, 'mililitros'),
(2, 'milimetros'),
(3, 'milimetros cuadrados'),
(4, 'gramos');

--
-- Volcado de datos para la tabla supply
--

INSERT INTO supply (`code`, internal_code, `name`, expiration_date, date_of_admission, cost, quantity, availability, description, id_measure) VALUES
(1, 'afds', 'Pintura Amarilla', '2020-05-14', '2020-03-03', 15.6, 13.2, 1, 'jdgjdgh', 1),
(2, 'dsfa56', 'Pintura Roja', '2019-12-10', '2020-03-05', 19.5, 45, 1, 'fsdadasf', 1),
(3, 'dhg46', 'Maisena', '2020-06-12', '2020-03-19', 15.2, 12, 1, 'fsda', 4),
(4, 'sdaf', 'nose', '2020-08-13', '2020-03-04', 14.2, 16.4, 0, 'gfds', 3);

--
-- Volcado de datos para la tabla product
--

INSERT INTO product (id_product, `name`, description) VALUES
(1, 'Playeras', 'playeras descripcion'),
(3, 'Tazas', 'tazas coleccionables'),
(2, 'LLaveros', '');

--
-- Volcado de datos para la tabla design
--



--
-- Volcado de datos para la tabla design_data
--



--
-- Volcado de datos para la tabla `group`
--

INSERT INTO `group` (id_group, `name`, information, section) VALUES
(1, 'Equipo dinamita', 'Solo estudiantes de sistemas', 'A'),
(2, 'Grupo #2', 'El Grupo que hara playeras', 'B'),
(3, 'Solo Mujeres', 'Grupo de solo mujeres', 'A');

--
-- Volcado de datos para la tabla group_user
--

INSERT INTO group_user (id_gruop_user, admission_date, group_id, user_carnet) VALUES
(1, '2020-03-20', 1, 201630962),
(2, '2020-03-20', 2, 201692468),
(3, '2020-03-20', 2, 201630978),
(4, '2020-03-20', 3, 201630851),
(5, '2020-03-20', 3, 20630963),
(6, '2020-03-20', 3, 14567891);

--
-- Volcado de datos para la tabla necessary_supply
--


--
-- Volcado de datos para la tabla produccion
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
