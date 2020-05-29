-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema produccion_industrial
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `produccion_industrial` ;

-- -----------------------------------------------------
-- Schema produccion_industrial
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `produccion_industrial` ;
USE `produccion_industrial` ;

-- -----------------------------------------------------
-- Table `produccion_industrial`.`career`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`career` (
  `id_career` INT NOT NULL AUTO_INCREMENT,
  `name_career` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_career`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`product` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TINYTEXT NOT NULL,
  PRIMARY KEY (`id_product`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`design_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`design_data` (
  `iddesign_data` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TINYTEXT NULL DEFAULT NULL,
  `picture` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`iddesign_data`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`design`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`design` (
  `id_design` INT NOT NULL AUTO_INCREMENT,
  `design_data` INT NULL DEFAULT NULL,
  `product_id_product` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_design`),
  INDEX `fk_design_design_data1_idx` (`design_data` ASC),
  INDEX `fk_design_product1_idx` (`product_id_product` ASC),
  CONSTRAINT `fk_design_design_data1`
    FOREIGN KEY (`design_data`)
    REFERENCES `produccion_industrial`.`design_data` (`iddesign_data`),
  CONSTRAINT `fk_design_product1`
    FOREIGN KEY (`product_id_product`)
    REFERENCES `produccion_industrial`.`product` (`id_product`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`group` (
  `id_group` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `information` MEDIUMTEXT NULL DEFAULT NULL,
  `section` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_group`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`production`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`production` (
  `id_production` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `state` TINYINT(1) NOT NULL,
  `qualification` DOUBLE NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `init_cost` DOUBLE NULL DEFAULT NULL,
  `final_cost` DOUBLE NULL DEFAULT NULL,
  `product_id` INT NULL,
  `group_id` INT NOT NULL,
  `design_id` INT NOT NULL,
  `post_design` INT NULL,
  PRIMARY KEY (`id_production`),
  INDEX `fk_LINEA_DE_PRODUCCION_PRODUCTO1_idx` (`product_id` ASC),
  INDEX `fk_production_group1_idx` (`group_id` ASC),
  INDEX `fk_production_design1_idx` (`design_id` ASC),
  INDEX `fk_production_design2_idx` (`post_design` ASC),
  CONSTRAINT `fk_LINEA_DE_PRODUCCION_PRODUCTO1`
    FOREIGN KEY (`product_id`)
    REFERENCES `produccion_industrial`.`product` (`id_product`),
  CONSTRAINT `fk_production_design1`
    FOREIGN KEY (`design_id`)
    REFERENCES `produccion_industrial`.`design` (`id_design`),
  CONSTRAINT `fk_production_design2`
    FOREIGN KEY (`post_design`)
    REFERENCES `produccion_industrial`.`design` (`id_design`),
  CONSTRAINT `fk_production_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `produccion_industrial`.`group` (`id_group`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`stage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`stage` (
  `id_stage` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TINYTEXT NOT NULL,
  `production_id` INT NOT NULL,
  PRIMARY KEY (`id_stage`),
  INDEX `fk_ETAPA_LINEA_DE_PRODUCCION1_idx` (`production_id` ASC),
  CONSTRAINT `fk_ETAPA_LINEA_DE_PRODUCCION1`
    FOREIGN KEY (`production_id`)
    REFERENCES `produccion_industrial`.`production` (`id_production`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`step`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`step` (
  `id_step` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TINYTEXT NOT NULL,
  `stage_id` INT NOT NULL,
  PRIMARY KEY (`id_step`),
  INDEX `fk_PASO_ETAPA1_idx` (`stage_id` ASC),
  CONSTRAINT `fk_PASO_ETAPA1`
    FOREIGN KEY (`stage_id`)
    REFERENCES `produccion_industrial`.`stage` (`id_stage`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`commentary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`commentary` (
  `id_commentary` INT NOT NULL AUTO_INCREMENT,
  `commentary` TINYTEXT NOT NULL,
  `id_step` INT NOT NULL,
  PRIMARY KEY (`id_commentary`),
  INDEX `fk_COMMENTARY_step1_idx` (`id_step` ASC),
  CONSTRAINT `fk_COMMENTARY_step1`
    FOREIGN KEY (`id_step`)
    REFERENCES `produccion_industrial`.`step` (`id_step`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`extra_cost`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`extra_cost` (
  `id_extra_cost` INT NOT NULL AUTO_INCREMENT,
  `description` TINYTEXT NOT NULL,
  `cost` DOUBLE NOT NULL,
  `id_production` INT NOT NULL,
  PRIMARY KEY (`id_extra_cost`),
  INDEX `fk_extra_cost_production_idx` (`id_production` ASC),
  CONSTRAINT `fk_extra_cost_production`
    FOREIGN KEY (`id_production`)
    REFERENCES `produccion_industrial`.`production` (`id_production`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`rol_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`rol_user` (
  `id_rol` INT NOT NULL,
  `name_rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_rol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`user` (
  `carnet` INT NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `phone` INT NOT NULL,
  `password` VARCHAR(400) NOT NULL,
  `state` TINYINT(1) NULL DEFAULT NULL,
  `id_rol` INT NOT NULL,
  `id_career` INT NOT NULL,
  PRIMARY KEY (`carnet`),
  INDEX `fk_USUARIO_ROL_USUARIO1_idx` (`id_rol` ASC),
  INDEX `fk_USUARIO_CARRERA1_idx` (`id_career` ASC),
  CONSTRAINT `fk_USUARIO_CARRERA1`
    FOREIGN KEY (`id_career`)
    REFERENCES `produccion_industrial`.`career` (`id_career`),
  CONSTRAINT `fk_USUARIO_ROL_USUARIO1`
    FOREIGN KEY (`id_rol`)
    REFERENCES `produccion_industrial`.`rol_user` (`id_rol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`group_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`group_user` (
  `id_gruop_user` INT NOT NULL AUTO_INCREMENT,
  `admission_date` DATE NOT NULL,
  `group_id` INT NOT NULL,
  `user_carnet` INT NOT NULL,
  PRIMARY KEY (`id_gruop_user`),
  INDEX `fk_GRUPO_USUARIO_GRUPO1_idx` (`group_id` ASC),
  INDEX `fk_group_user_user1_idx` (`user_carnet` ASC),
  CONSTRAINT `fk_group_user_user1`
    FOREIGN KEY (`user_carnet`)
    REFERENCES `produccion_industrial`.`user` (`carnet`),
  CONSTRAINT `fk_GRUPO_USUARIO_GRUPO1`
    FOREIGN KEY (`group_id`)
    REFERENCES `produccion_industrial`.`group` (`id_group`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`measure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`measure` (
  `id_measure` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id_measure`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`supply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`supply` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `internal_code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `expiration_date` DATE NULL,
  `date_of_admission` DATE NOT NULL,
  `cost` DOUBLE NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `availability` TINYINT(1) NOT NULL,
  `description` TINYTEXT NULL DEFAULT NULL,
  `id_measure` INT NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `fk_SUPPLY_MEASURE1_idx` (`id_measure` ASC),
  CONSTRAINT `fk_SUPPLY_MEASURE1`
    FOREIGN KEY (`id_measure`)
    REFERENCES `produccion_industrial`.`measure` (`id_measure`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`modify_supply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`modify_supply` (
  `id_modify_supply` INT NOT NULL AUTO_INCREMENT,
  `carnet_user` INT NOT NULL,
  `supply_code` INT NOT NULL,
  `modify_type` ENUM('CANTIDAD', 'ATRIBUTOS', 'CONSUMIDO') NOT NULL,
  `cuantity` INT NOT NULL,
  `date` DATE NOT NULL,
  `note` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id_modify_supply`),
  INDEX `fk_INSUMO_USUARIO_INSUMO1_idx` (`supply_code` ASC),
  INDEX `fk_modify_supply_user1_idx` (`carnet_user` ASC),
  CONSTRAINT `fk_INSUMO_USUARIO_INSUMO1`
    FOREIGN KEY (`supply_code`)
    REFERENCES `produccion_industrial`.`supply` (`code`),
  CONSTRAINT `fk_modify_supply_user1`
    FOREIGN KEY (`carnet_user`)
    REFERENCES `produccion_industrial`.`user` (`carnet`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `produccion_industrial`.`necessary_supply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produccion_industrial`.`necessary_supply` (
  `id_necessary_supply` INT NOT NULL AUTO_INCREMENT,
  `quantity` DOUBLE NOT NULL,
  `supply_code` INT NOT NULL,
  `design_id` INT NOT NULL,
  PRIMARY KEY (`id_necessary_supply`),
  INDEX `fk_necessary_supply_design1_idx` (`design_id` ASC),
  INDEX `fk_necessary_supply_supply1_idx` (`supply_code` ASC),
  CONSTRAINT `fk_necessary_supply_design1`
    FOREIGN KEY (`design_id`)
    REFERENCES `produccion_industrial`.`design` (`id_design`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_necessary_supply_supply1`
    FOREIGN KEY (`supply_code`)
    REFERENCES `produccion_industrial`.`supply` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


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
INSERT INTO `rol_user` (`id_rol`, `name_rol`) VALUES ('1', 'Estudiante');
INSERT INTO `rol_user` (`id_rol`, `name_rol`) VALUES ('2', 'Docente');
INSERT INTO `rol_user` (`id_rol`, `name_rol`) VALUES ('3', 'Administrador'); 

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (carnet, `name`, email, phone, `password`, state, id_rol, id_career) VALUES
(1, 'admin', 'admin@cunoc.edu.gt', 12345678, 'PBKDF2WithHmacSHA256:3072:b4iXBXxi5t0hCgR8hp0tq32Nk0heMA7hphPuK0BWyIDuMwgZbDVtplgbEuwKI1xXIV39GoGcvDEGI9Tl3Hzllw==:1bLDMjf/8Kx7pL4KlcvJ9uDVqNyG3ejvBIgw2sIBBow=', 1, 3, 1);

INSERT INTO `measure` (`id_measure`, `name`) VALUES ('1', 'mililitros'), ('2', 'milimetros'), ('3', 'milimetros cuadrados'), ('4', 'gramos');



--
-- Volcado de datos para la tabla supply
--

INSERT INTO supply (`code`, internal_code, `name`, expiration_date, date_of_admission, cost, quantity, availability, description, id_measure) VALUES
(1, 'afds', 'Pintura Amarilla', '2020-05-14', '2020-03-03', 15.6, 13.2, 1, 'jdgjdgh', 1),
(2, 'dsfa56', 'Pintura Roja', '2019-12-10', '2020-03-05', 19.5, 45, 1, 'fsdadasf', 1),
(3, 'dhg46', 'Maisena', '2020-06-12', '2020-03-19', 15.2, 12, 1, 'fsda', 4),
(4, 'sdaf', 'Otro insumo', '2020-08-13', '2020-03-04', 14.2, 16.4, 0, 'gfds', 3);

--
-- Volcado de datos para la tabla product
--

INSERT INTO product (id_product, `name`, description) VALUES
(1, 'Playeras', 'playeras descripcion'),
(3, 'Tazas', 'tazas coleccionables'),
(2, 'LLaveros', '');
