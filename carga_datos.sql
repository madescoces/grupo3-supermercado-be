-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
SET GLOBAL time_zone = '+00:00';

-- -----------------------------------------------------
-- Schema supermercado
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema supermercado
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `supermercado`;

CREATE SCHEMA IF NOT EXISTS `supermercado` DEFAULT CHARACTER SET utf8 ;
USE `supermercado`;

-- -----------------------------------------------------
-- Table `supermercado`.`empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`empresa`;

CREATE TABLE IF NOT EXISTS `supermercado`.`empresa` (
  `id_empresa` INT NOT NULL,
  `razon_social` VARCHAR(80) NOT NULL,
  `domicilio` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_empresa`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`empresa` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`empresa` (id_empresa, razon_social, domicilio) VALUES
(1, 'Supermercado La Buena Elección', 'Calle 123, Colonia Siempre Viva #567'),
(2, 'Compra Express Plus', 'Avenida de la Alegría #789'),
(3, 'Supermercado Rápido y Fácil', 'Calle Sobremonte #101'),
(4, 'Tienda Viva Selecta', 'Avenida Los Fresnos #234'),
(5, 'Mercado Delicioso', 'Calle República #345'),
(6, 'Mini Market Deluxe', 'Avenida de las Pampas #678'),
(7, 'Tienda Express Premium', 'Pasaje La Gloria, Condominio Plaza #456'),
(8, 'Mercadito Gandalf', 'Avenida Mordor #789'),
(9, 'Compras Ya', 'Calle Viva #890'),
(10, 'Super Vivo MegaStore', 'Avenida Solano López #1234') as new_empresa
ON DUPLICATE KEY UPDATE
    id_empresa=new_empresa.id_empresa,
    razon_social=new_empresa.razon_social,
    domicilio=new_empresa.domicilio;

-- -----------------------------------------------------
-- Table `supermercado`.`repositor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`repositor`;

CREATE TABLE IF NOT EXISTS `supermercado`.`repositor` (
  `id_repositor` INT NOT NULL AUTO_INCREMENT,
  `nombre_repositor` VARCHAR(80) NOT NULL,
  `id_empresa` INT NOT NULL,
  PRIMARY KEY (`id_repositor`),
  INDEX `fk_repositor_empresa_idx` (`id_empresa` ASC) VISIBLE,
  CONSTRAINT `fk_repositor_empresa`
    FOREIGN KEY (`id_empresa`)
    REFERENCES `supermercado`.`empresa` (`id_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`repositor` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`repositor` (id_repositor, nombre_repositor, id_empresa) VALUES 
  (1, 'Esteban Lopez', 5),
  (2, 'Ana Mendez', 3),
  (3, 'Carlos Perez', 8),
  (4, 'Marta Gonzalez', 1),
  (5, 'Jorge Martinez', 6),
  (6, 'Lucía Ramirez', 9),
  (7, 'Diego Hernandez', 2),
  (8, 'Sofía Diaz', 10),
  (9, 'Pedro Jimenez', 4),
  (10, 'Isabel Torres', 7),
  (11, 'Esteban Moreno', 6),
  (12, 'Ana Fernandez', 2),
  (13, 'Carlos Garcia', 8),
  (14, 'Marta Castillo', 4),
  (15, 'Jorge Lopez', 1),
  (16, 'Lucía Molina', 9),
  (17, 'Diego Romero', 3),
  (18, 'Sofía Alvarez', 10),
  (19, 'Pedro Suarez', 7),
  (20, 'Isabel Gomez', 5) as new_repositor
ON DUPLICATE KEY UPDATE
    id_repositor=new_repositor.id_repositor,
    nombre_repositor=new_repositor.nombre_repositor,
    id_empresa=new_repositor.id_empresa;

-- -----------------------------------------------------
-- Table `supermercado`.`sector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`sector`;

CREATE TABLE IF NOT EXISTS `supermercado`.`sector` (
  `id_sector` INT NOT NULL,
  `desc_sector` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_sector`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`sector` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`sector` (id_sector, desc_sector) VALUES
  (1, 'Lácteos'),
  (2, 'Verduras y frutas'),
  (3, 'Carnes'),
  (4, 'Bebidas'),
  (5, 'Panadería'),  
  (6, 'Limpieza'),
  (7, 'Cuidado personal'),
  (8, 'Hogar'),
  (9, 'Farmacia'),
  (10, 'Galletitas'),
  (11, 'Cajas'),
  (12, 'Congelados'),
  (13, 'Almacén') AS new_sector
ON DUPLICATE KEY UPDATE
    id_sector=new_sector.id_sector,
    desc_sector=new_sector.desc_sector;

-- -----------------------------------------------------
-- Table `supermercado`.`gondola`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`gondola`;

CREATE TABLE IF NOT EXISTS `supermercado`.`gondola` (
  `id_gondola` INT NOT NULL AUTO_INCREMENT,
  `nombre_gondola` VARCHAR(80) NOT NULL,
  `id_sector` INT NOT NULL,
  PRIMARY KEY (`id_gondola`),
  INDEX `fk_gondola_sector_idx` (`id_sector` ASC) VISIBLE,
  CONSTRAINT `fk_gondola_sector`
    FOREIGN KEY (`id_sector`)
    REFERENCES `supermercado`.`sector` (`id_sector`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`gondola` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`gondola` (id_gondola, nombre_gondola, id_sector) VALUES 
  (1,'Lácteos refrigerados',1),
  (2,'Lácteos al vacío',1),
  (3,'Gaseosas cola',4),
  (4,'Vinos',4),
  (5,'Bebidas blancas',4),
  (6,'Galletitas dulces',10),
  (7,'Galletitas saladas',10),
  (8,'Venta Libre',9),
  (9,'Golosinas',11),
  (10,'Facturas',5),
  (11,'Vacunos',3),
  (12,'Chacinados y aves',3),
  (13,'Frutas estación',2),
  (14,'Verduras de hoja',2),
  (15,'Lavado ropa',6),
  (16,'Aguas',4),
  (17,'Panes',5),
  (18,'Snacks', 11),
  (19,'Detergentes', 6),
  (20,'Cremas y Shampoo', 7), 
  (21,'Prod. vegetarianos', 12),
  (22,'Papeles, servilletas', 6),
  (23,'Endulzantes', 13) AS new_gondola
ON DUPLICATE KEY UPDATE
    id_gondola=new_gondola.id_gondola,
    nombre_gondola=new_gondola.nombre_gondola,
    id_sector=new_gondola.id_sector;
    
-- -----------------------------------------------------
-- Table `supermercado`.`fila_producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`fila_producto`;

CREATE TABLE IF NOT EXISTS `supermercado`.`fila_producto` (
  `id_fila` INT NOT NULL AUTO_INCREMENT,
  `desc_fila` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_fila`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`fila_producto` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`fila_producto` (id_fila, desc_fila) VALUES 
  (1,'Superior'),
  (2,'Inferior'),
  (3,'Principal'),
  (4,'Inferior Secundaria'),
  (5,'Ofertas'),
  (6,'Destacados') AS new_fila
ON DUPLICATE KEY UPDATE
    id_fila=new_fila.id_fila,
    desc_fila=new_fila.desc_fila;
    
-- -----------------------------------------------------
-- Table `supermercado`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`producto`;

CREATE TABLE IF NOT EXISTS `supermercado`.`producto` (
  `id_producto` INT NOT NULL,
  `nombre_producto` VARCHAR(80) NOT NULL,
  `desc_producto` VARCHAR(80) NULL,
  `id_fila` INT NULL,
  `id_producto_reemplazo` INT NOT NULL,
  PRIMARY KEY (`id_producto`),
  INDEX `fk_producto_fila_producto_idx` (`id_fila` ASC) VISIBLE,
  INDEX `fk_producto_producto_idx` (`id_producto_reemplazo` ASC) VISIBLE,
  CONSTRAINT `fk_producto_fila_producto`
    FOREIGN KEY (`id_fila`)
    REFERENCES `supermercado`.`fila_producto` (`id_fila`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_producto`
    FOREIGN KEY (`id_producto_reemplazo`)
    REFERENCES `supermercado`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`producto` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`producto` (id_producto, nombre_producto, desc_producto, id_fila, id_producto_reemplazo) VALUES 
  (1,'Tortuguita', 'Corte de carne', 2, 1),
  (2,'Paleta', 'Corte de carne', 2, 2),
  (3,'Leche', 'La Martona', 5, 3),
  (4,'Manzanas', 'Roja la Frutería', 2, 4),
  (5,'Pollo', 'Envasado por cuarto', 1, 5),
  (6,'Agua Mineral', 'Villavicencio', 2, 6),
  (7,'Pan Integral', 'De la Panadería', 2, 7),
  (8,'Papas Fritas', 'Snakito', 5, 8),
  (9,'Detergente Vajilla', 'Singapour 500cc', 2, 9),
  (10,'Shampoo Plusbelle', 'Ceramidas', 2, 10),
  (11,'Papel Higiénico', 'Higienol 50mts', 2, 11),
  (12,'Aspirina', 'De la Farmacia', 2, 12),
  (13,'Milanesas soja', 'Granja iris pack familiar', 2, 13),
  (14,'Servilletas Absorventes', 'Por 3 rollos S&P', 2, 14),
  (15,'Azúcar', 'Ledesma Premium', 2, 15),
  (16,'Palitos de maiz y queso', 'Chetos', 2, 16),
  (17,'Alfajor', 'Triple de dulce de leche', 2, 17),
  (18,'Durazno', 'De temporada', 2, 18),
  (19,'Lechuga', 'Pack de 3 unidades', 2, 19) AS new_producto
ON DUPLICATE KEY UPDATE
    id_producto=new_producto.id_producto,
    nombre_producto=new_producto.nombre_producto,
    desc_producto=new_producto.desc_producto,
    id_fila=new_producto.id_fila,
    id_producto_reemplazo=new_producto.id_producto_reemplazo;
    
-- -----------------------------------------------------
-- Table `supermercado`.`presentacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`presentacion`;

CREATE TABLE IF NOT EXISTS `supermercado`.`presentacion` (
  `id_presentacion` INT NOT NULL AUTO_INCREMENT,
  `desc_presentacion` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_presentacion`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`presentacion` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`presentacion` (id_presentacion, desc_presentacion) VALUES 
(1, 'Caja'),
(2, 'Sobre'),
(3, 'Blister'),
(4, 'Botella 1L'),
(5, 'Botella 1.5L'),
(6, 'Bulto 50u'),
(7, 'Kg'),
(8, 'Suelto'),
(9, 'Paquete'),
(10, 'Al Vacio'),
(11, 'Unidad') AS new_presentacion
ON DUPLICATE KEY UPDATE
    id_presentacion = new_presentacion.id_presentacion,
    desc_presentacion = new_presentacion.desc_presentacion;   
    
-- -----------------------------------------------------
-- Table `supermercado`.`gondola_producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`gondola_producto`;

CREATE TABLE IF NOT EXISTS `supermercado`.`gondola_producto` (
  `id_gondola` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `id_presentacion` INT NOT NULL,
  PRIMARY KEY (`id_gondola`, `id_producto`),
  INDEX `fk_gondola_producto_presentacion_idx` (`id_presentacion` ASC) VISIBLE,
  INDEX `fk_gondola_producto_gondola_idx` (`id_gondola` ASC) VISIBLE,
  INDEX `fk_gondola_producto_producto_idx` (`id_producto` ASC) VISIBLE,  
  CONSTRAINT `fk_gondola_producto_gondola`
    FOREIGN KEY (`id_gondola`)
    REFERENCES `supermercado`.`gondola` (`id_gondola`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gondola_producto_producto`
    FOREIGN KEY (`id_producto`)
    REFERENCES `supermercado`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gondola_producto_presentacion`
    FOREIGN KEY (`id_presentacion`)
    REFERENCES `supermercado`.`presentacion` (`id_presentacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `supermercado`.`gondola_producto` INITIAL DATA
-- -----------------------------------------------------
INSERT INTO `supermercado`.`gondola_producto` (id_gondola, id_producto, id_presentacion) VALUES 
(11,1,7),
(11,2,7),
(1,3,1),
(13,4,7),
(12,5,10),
(16,6,5),
(17,7,9),
(18,8,9),
(19,9,4),
(20,10,5),
(22,11,11),
(8,12,3),
(21,13,9),
(22,14,9),
(23,15,7),
(18,16,9),
(9,17,1),
(13,18,7),
(14,19,11) AS new_gondola_producto
ON DUPLICATE KEY UPDATE
    id_gondola = new_gondola_producto.id_gondola,
    id_producto = new_gondola_producto.id_producto,
    id_presentacion = new_gondola_producto.id_presentacion;
    
-- -----------------------------------------------------
-- Table `supermercado`.`gondola_producto_repositor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `supermercado`.`gondola_producto_repositor`;

CREATE TABLE IF NOT EXISTS `supermercado`.`gondola_producto_repositor` (
  `id_gondola` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `id_repositor` INT NOT NULL,
  `fecha` DATETIME NOT NULL,
  `cantidad` DECIMAL(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_gondola`, `id_producto`, `id_repositor`, `fecha`),
  INDEX `fk_gondola_producto_repositor_repositor_idx` (`id_repositor` ASC) VISIBLE,
  INDEX `fk_gondola_producto_repositor_gondola_producto_idx` (`id_gondola` ASC, `id_producto` ASC) VISIBLE,
  CONSTRAINT `fk_gondola_producto_repositor_repositor`
    FOREIGN KEY (`id_repositor`)
    REFERENCES `supermercado`.`repositor` (`id_repositor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gondola_producto_repositor_gondola_producto`
    FOREIGN KEY (`id_gondola` , `id_producto`)
    REFERENCES `supermercado`.`gondola_producto` (`id_gondola` , `id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- ----------------------------------------------------------------
-- Table `supermercado`.`gondola_producto_repositor` INITIAL DATA
-- ----------------------------------------------------------------
INSERT INTO `supermercado`.`gondola_producto_repositor` ( id_producto, id_gondola, id_repositor, fecha, cantidad) VALUES 
(1,11,1,'2023-11-11 09:45:00',12),
(1,11,2,'2023-11-21 17:22:00',31),
(1,11,3,'2023-03-10 14:37:00',13),
(2,11,4,'2023-10-11 11:05:00',3),
(2,11,5,'2023-11-12 13:42:00',7),
(3,1,6,'2023-11-11 14:18:00',3),
(4,13,7,'2023-10-28 09:53:00',12),
(5,12,8,'2023-11-15 15:11:00',9),
(5,12,8,'2023-10-15 12:27:00',9),
(5,12,8,'2023-09-25 10:36:00',9),
(6,16,9,'2023-11-10 16:29:00',16),
(7,11,10,'2023-10-09 14:53:00',18),
(8,17,11,'2023-11-02 11:30:00',23),
(8,17,11,'2023-11-06 14:17:00',23),
(8,17,11,'2023-11-22 16:40:00',23),
(9,18,12,'2023-10-12 10:24:00',12),
(10,19,13,'2023-10-11 20:12:00',24),
(10,19,13,'2023-11-22 10:58:00',14),
(10,19,9,'2023-10-23 13:45:00',34),
(11,19,8,'2023-10-06 09:37:00',26),
(12,8,20,'2023-10-26 14:02:00',4),
(13,20,14,'2023-11-16 22:07:00',14),
(14,21,15,'2023-11-08 16:34:00',15),
(14,21,13,'2023-11-18 10:55:00',13),
(14,21,12,'2023-10-18 19:42:00',10),
(15,22,1,'2023-11-14 09:28:00',5),
(16,17,3,'2023-10-05 12:44:00',2),
(17,9,4,'2023-10-30 18:23:00',19),
(17,9,5,'2023-10-20 16:50:00',29),
(17,9,14,'2023-10-30 14:38:00',39),
(18,13,5,'2023-11-17 09:12:00',23),
(18,13,19,'2023-11-22 11:57:00',29),
(18,13,5,'2023-11-17 08:43:00',22),
(19,14,7,'2023-11-11 09:01:00',3) AS new_gondola_producto_repositor
ON DUPLICATE KEY UPDATE
  id_producto = new_gondola_producto_repositor.id_producto,
  id_gondola = new_gondola_producto_repositor.id_gondola,
  id_repositor = new_gondola_producto_repositor.id_repositor,
  fecha = new_gondola_producto_repositor.fecha,
  cantidad = new_gondola_producto_repositor.cantidad;
  
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
