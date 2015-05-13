/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : ofertapp_es

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-05-13 11:24:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for archivo
-- ----------------------------
DROP TABLE IF EXISTS `archivo`;
CREATE TABLE `archivo` (
  `identificador` int(11) NOT NULL,
  `asunto` varchar(500) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `secretaria_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `archivoSecretaria` (`secretaria_id`),
  CONSTRAINT `archivoSecretaria` FOREIGN KEY (`secretaria_id`) REFERENCES `secretaria` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of archivo
-- ----------------------------

-- ----------------------------
-- Table structure for cartoncuotas
-- ----------------------------
DROP TABLE IF EXISTS `cartoncuotas`;
CREATE TABLE `cartoncuotas` (
  `identificador` int(11) NOT NULL DEFAULT '0',
  `enero` tinyint(1) DEFAULT NULL,
  `febrero` tinyint(1) DEFAULT NULL,
  `marzo` tinyint(1) DEFAULT NULL,
  `abril` tinyint(1) DEFAULT NULL,
  `mayo` tinyint(1) DEFAULT NULL,
  `junio` tinyint(1) DEFAULT NULL,
  `julio` tinyint(1) DEFAULT NULL,
  `agosto` tinyint(1) DEFAULT NULL,
  `septiembre` tinyint(1) DEFAULT NULL,
  `octubre` tinyint(1) DEFAULT NULL,
  `noviembre` tinyint(1) DEFAULT NULL,
  `diciembre` tinyint(1) DEFAULT NULL,
  `año` int(4) DEFAULT NULL,
  `numero_hermano_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `numero_hermano_id` (`numero_hermano_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cartoncuotas
-- ----------------------------
INSERT INTO `cartoncuotas` VALUES ('1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2015', '2');
INSERT INTO `cartoncuotas` VALUES ('2', '1', '1', '1', '1', '1', '1', '1', null, null, null, null, null, '2016', '2');
INSERT INTO `cartoncuotas` VALUES ('3', '1', '1', '1', '1', '1', '1', null, '1', '1', '1', null, null, '2015', '1');

-- ----------------------------
-- Table structure for cuentabancaria
-- ----------------------------
DROP TABLE IF EXISTS `cuentabancaria`;
CREATE TABLE `cuentabancaria` (
  `identificador` int(11) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `titulo` varchar(500) DEFAULT NULL,
  `banco` varchar(500) DEFAULT NULL,
  `direccion` varchar(500) DEFAULT NULL,
  `municipio` varchar(500) DEFAULT NULL,
  `codigo_postal` varchar(500) DEFAULT NULL,
  `provincia` varchar(500) DEFAULT NULL,
  `pais` varchar(500) DEFAULT NULL,
  `num_cuenta` varchar(500) DEFAULT NULL,
  `iban` varchar(500) DEFAULT NULL,
  `bic` varchar(500) DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cuentabancaria
-- ----------------------------
INSERT INTO `cuentabancaria` VALUES ('1', 'Banco BBVA', null, 'BBVA', 'C/ Corredera 32', 'Sevilla', '41000', 'Sevilla', 'España', '12345678', '23', '212', '2100');
INSERT INTO `cuentabancaria` VALUES ('2', 'sas', 'ss', 'ss', 'ss', 'ss', '555', 'ss', 'ss', '55555', '55', '55', '33');

-- ----------------------------
-- Table structure for entidadesconocidas
-- ----------------------------
DROP TABLE IF EXISTS `entidadesconocidas`;
CREATE TABLE `entidadesconocidas` (
  `identificador` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `localidad` varchar(50) DEFAULT NULL,
  `domicilio` varchar(50) DEFAULT NULL,
  `telf1` int(10) DEFAULT NULL,
  `telf2` int(10) DEFAULT NULL,
  `secretaria_id` int(11) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `provincia` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `entidadesSecretaria` (`secretaria_id`),
  CONSTRAINT `entidadesSecretaria` FOREIGN KEY (`secretaria_id`) REFERENCES `secretaria` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of entidadesconocidas
-- ----------------------------
INSERT INTO `entidadesconocidas` VALUES ('1', 'hilario', 'dasdas', 'dasdsa', '3', '3', '1', '3', 'dasdas', '3');
INSERT INTO `entidadesconocidas` VALUES ('2', 'roosa', '6', '6', '6', '6', '1', '6', '6', '6');
INSERT INTO `entidadesconocidas` VALUES ('4', 'lñlñlñ', 'lñlñ', 'lñlñ', '444', '44', '1', '45', 'lñlñ', '44');
INSERT INTO `entidadesconocidas` VALUES ('7', 'a', 'a', 'a', '0', '4', '1', '4', 'a', '4');
INSERT INTO `entidadesconocidas` VALUES ('8', 'a', 'a', 'a', '0', '0', '1', '5', 'a', '');
INSERT INTO `entidadesconocidas` VALUES ('9', 'a', 'a', 'a', '0', '0', '1', '5', 'a', '');
INSERT INTO `entidadesconocidas` VALUES ('10', 's', 's', 's', '7', '7', '1', '7', 's', '7');
INSERT INTO `entidadesconocidas` VALUES ('11', '', '', '', '0', '0', '1', '0', '', '');

-- ----------------------------
-- Table structure for fichareparto
-- ----------------------------
DROP TABLE IF EXISTS `fichareparto`;
CREATE TABLE `fichareparto` (
  `identificador` int(11) NOT NULL,
  `protocolo_id` int(11) DEFAULT NULL,
  `papeleta_sitio_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  UNIQUE KEY `fichaRepartoPapeleta` (`papeleta_sitio_id`) USING BTREE,
  KEY `fichaRepartoProtocolo` (`protocolo_id`),
  CONSTRAINT `fichaRepartoPapeleta` FOREIGN KEY (`papeleta_sitio_id`) REFERENCES `papeletasitio` (`identificador`),
  CONSTRAINT `fichaRepartoProtocolo` FOREIGN KEY (`protocolo_id`) REFERENCES `protocolo` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fichareparto
-- ----------------------------
INSERT INTO `fichareparto` VALUES ('1', '1', '1');
INSERT INTO `fichareparto` VALUES ('2', '2', '122');

-- ----------------------------
-- Table structure for formapago
-- ----------------------------
DROP TABLE IF EXISTS `formapago`;
CREATE TABLE `formapago` (
  `identificador` int(11) NOT NULL,
  `forma_pago` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of formapago
-- ----------------------------
INSERT INTO `formapago` VALUES ('1', 'Domiciliario');
INSERT INTO `formapago` VALUES ('2', 'Banco');
INSERT INTO `formapago` VALUES ('11', 'Domiciliario');

-- ----------------------------
-- Table structure for hermandad
-- ----------------------------
DROP TABLE IF EXISTS `hermandad`;
CREATE TABLE `hermandad` (
  `identificador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `año_fundacion` int(4) DEFAULT NULL,
  `domicilio` varchar(50) DEFAULT NULL,
  `municipio` varchar(50) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `telf1` varchar(50) DEFAULT NULL,
  `telf2` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `numero_hermanos` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hermandad
-- ----------------------------
INSERT INTO `hermandad` VALUES ('1', 'esperanza', '200', 'huerto queri', 'el viso del alcor', 'sevilla', '645556390', '', '', '', '0');

-- ----------------------------
-- Table structure for hermandadeshermanadas
-- ----------------------------
DROP TABLE IF EXISTS `hermandadeshermanadas`;
CREATE TABLE `hermandadeshermanadas` (
  `identificador` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `parroquia` varchar(50) DEFAULT NULL,
  `localidad` varchar(50) DEFAULT NULL,
  `domicilio` varchar(50) DEFAULT NULL,
  `telf1` varchar(50) DEFAULT NULL,
  `telf2` varchar(50) DEFAULT NULL,
  `secretaria_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `hermanadasSecretaria` (`secretaria_id`),
  CONSTRAINT `hermanadasSecretaria` FOREIGN KEY (`secretaria_id`) REFERENCES `secretaria` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hermandadeshermanadas
-- ----------------------------
INSERT INTO `hermandadeshermanadas` VALUES ('1', 'Redencion', 'Parroquia del Santisimo Redentor', 'Sevilla', null, '659896326', null, '1');

-- ----------------------------
-- Table structure for hermanos
-- ----------------------------
DROP TABLE IF EXISTS `hermanos`;
CREATE TABLE `hermanos` (
  `numero_hermano` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `municipio` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `tfno` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `banco` varchar(255) DEFAULT NULL,
  `cuenta_bancaria` varchar(255) DEFAULT NULL,
  `tipo_pago_id` int(11) DEFAULT NULL,
  `forma_pago_id` int(11) DEFAULT NULL,
  `id_hermandad` int(11) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `codigo_postal` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero_hermano`),
  KEY `forma_pago_id` (`forma_pago_id`),
  KEY `tipo_pago_id` (`tipo_pago_id`),
  KEY `id_hermandad` (`id_hermandad`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hermanos
-- ----------------------------
INSERT INTO `hermanos` VALUES ('1', 'hilario', 'roldan bonilla', '15414578r', 'el viso del alcor', 'españa', 'sevilla', '659698659', 'hilario_ds@hotmail.com', '55555555555', '5555555', '1', '1', '1', '2015-05-12', 'dasdasdasdasdasd', '45645');
INSERT INTO `hermanos` VALUES ('2', 'sssssss', 'ssssssssssssssss', '585899898', 'sssssssssssss', 'ssssssssss', 'ssssssssss', '898989898', 'sssssssssssssssssss', '88888888', '888888', '1', '1', '1', '2015-05-12', 'ssssssssss', '55656');
INSERT INTO `hermanos` VALUES ('3', 'aaaaaaaaaaa', 'aaaaaaaaaa', '565656565656', 'aaaaaaaaaaa', 'aaaaaaaaa', 'aaaaaaaaa', '56565656556', 'aaaaaaaaa56565a', '565656', '5656565656', '1', '1', '1', '2015-05-12', 'aaaaaaaaa', '454545');

-- ----------------------------
-- Table structure for inventario
-- ----------------------------
DROP TABLE IF EXISTS `inventario`;
CREATE TABLE `inventario` (
  `identificador` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `autor` varchar(50) DEFAULT NULL,
  `estilo` varchar(50) DEFAULT NULL,
  `fecha_realizacion` date DEFAULT NULL,
  `procedencia` varchar(50) DEFAULT NULL,
  `valoracion_economica` double DEFAULT NULL,
  `mejora` varchar(500) DEFAULT NULL,
  `restauracion` varchar(500) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `secretaria_id` int(11) DEFAULT NULL,
  `adquisicion` varchar(500) DEFAULT NULL,
  `imagen` longblob,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `inventarioSecretaria` (`secretaria_id`),
  CONSTRAINT `inventarioSecretaria` FOREIGN KEY (`secretaria_id`) REFERENCES `secretaria` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of inventario
-- ----------------------------
INSERT INTO `inventario` VALUES ('1', 'hhhhhhhhhhh', 'hhhhhhhhhhh', 'hhhhhhhhh', '2015-05-12', 'sssssssssss', '55555555', 'sssssssss', 'sssssssssss', '555555', 'sssssssss', '1', 'sssssssssssss', null, '2015-05-12');
INSERT INTO `inventario` VALUES ('2', 'sssssssssssssssssssss', 'ssssssssssssss', 'sssssssss', '2015-05-01', 'ssssssssssss', '55555555', 'sssssssssssss', 'ssssssssssss', '555555', 'ssssssssssss', '1', 'sssssssss', null, null);
INSERT INTO `inventario` VALUES ('3', 'ss', 's', 's', '2015-04-12', '', '55', '', '', '55', '', '1', 's', null, null);

-- ----------------------------
-- Table structure for juntagobierno
-- ----------------------------
DROP TABLE IF EXISTS `juntagobierno`;
CREATE TABLE `juntagobierno` (
  `identificador` int(20) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellidos` varchar(35) NOT NULL,
  `cargo` varchar(35) NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  `numero_hermano_id` int(25) NOT NULL,
  PRIMARY KEY (`identificador`,`cargo`),
  KEY `juntag` (`numero_hermano_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of juntagobierno
-- ----------------------------
INSERT INTO `juntagobierno` VALUES ('1', 'alej', 'asd', 'hermano mayor', 'aaa', '1');

-- ----------------------------
-- Table structure for librodeasientos
-- ----------------------------
DROP TABLE IF EXISTS `librodeasientos`;
CREATE TABLE `librodeasientos` (
  `identificador` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `concepto` varchar(500) DEFAULT NULL,
  `ingresar` double DEFAULT NULL,
  `debe` double DEFAULT NULL,
  `mayordomia_id` int(11) DEFAULT NULL,
  `cuenta_bancaria_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `LibroAsientoMayordomia` (`mayordomia_id`),
  KEY `LibroAsientoCuenta` (`cuenta_bancaria_id`),
  CONSTRAINT `LibroAsientoCuenta` FOREIGN KEY (`cuenta_bancaria_id`) REFERENCES `cuentabancaria` (`identificador`),
  CONSTRAINT `LibroAsientoMayordomia` FOREIGN KEY (`mayordomia_id`) REFERENCES `mayordomia` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of librodeasientos
-- ----------------------------
INSERT INTO `librodeasientos` VALUES ('1', '2015-03-22', 'Cuotas', '500', null, '1', '2');
INSERT INTO `librodeasientos` VALUES ('2', '2015-03-24', 'Flores', null, '47', '1', '1');
INSERT INTO `librodeasientos` VALUES ('3', '2015-12-01', 'cuots', '500', '0', '1', '2');
INSERT INTO `librodeasientos` VALUES ('66', '2015-12-01', 'cuots', '0', '70', '1', '1');
INSERT INTO `librodeasientos` VALUES ('333', '2015-12-01', 'cuots', '0', '70', '1', '1');

-- ----------------------------
-- Table structure for loterias
-- ----------------------------
DROP TABLE IF EXISTS `loterias`;
CREATE TABLE `loterias` (
  `identificador` int(11) NOT NULL,
  `sorteo` varchar(50) DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unidad` double DEFAULT NULL,
  `ganancia_unidad` double DEFAULT NULL,
  `mayordomia_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `loteriasMayordomia` (`mayordomia_id`),
  CONSTRAINT `loteriasMayordomia` FOREIGN KEY (`mayordomia_id`) REFERENCES `mayordomia` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of loterias
-- ----------------------------
INSERT INTO `loterias` VALUES ('2', 'Euromillones', '2015-04-01', '1000', '3', '2.5', '1');

-- ----------------------------
-- Table structure for mayordomia
-- ----------------------------
DROP TABLE IF EXISTS `mayordomia`;
CREATE TABLE `mayordomia` (
  `identificador` int(11) NOT NULL,
  `numero_hermano_id` int(11) DEFAULT NULL,
  `hermandad_id` int(11) DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  UNIQUE KEY `mayordomiaHermano` (`numero_hermano_id`) USING BTREE,
  UNIQUE KEY `mayordomiaHermandad` (`hermandad_id`) USING BTREE,
  CONSTRAINT `mayordomiaHermandad` FOREIGN KEY (`hermandad_id`) REFERENCES `hermandad` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mayordomia
-- ----------------------------
INSERT INTO `mayordomia` VALUES ('1', '6', '1', 'Mayordomo Primero');

-- ----------------------------
-- Table structure for pagocuotas
-- ----------------------------
DROP TABLE IF EXISTS `pagocuotas`;
CREATE TABLE `pagocuotas` (
  `identificador` int(11) NOT NULL,
  `numero_hermano_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `mayordomia_id` int(11) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `pagado` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `pagoCuotasMayordomia` (`mayordomia_id`),
  KEY `pagoCuotasHermano` (`numero_hermano_id`),
  CONSTRAINT `pagoCuotasMayordomia` FOREIGN KEY (`mayordomia_id`) REFERENCES `mayordomia` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pagocuotas
-- ----------------------------
INSERT INTO `pagocuotas` VALUES ('1', '1', '2015-03-22', '1', '25', null);
INSERT INTO `pagocuotas` VALUES ('2', '2', '2015-05-10', '1', '3', null);

-- ----------------------------
-- Table structure for papeletasitio
-- ----------------------------
DROP TABLE IF EXISTS `papeletasitio`;
CREATE TABLE `papeletasitio` (
  `identificador` int(11) NOT NULL,
  `numero_hermano_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `sale` tinyint(1) DEFAULT NULL,
  `donativo` double DEFAULT NULL,
  `salida_procesional_id` int(11) DEFAULT NULL,
  `numero_papeleta` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `papeletaSalidaProcesional` (`salida_procesional_id`),
  KEY `papeletaHermano` (`numero_hermano_id`),
  CONSTRAINT `papeletaSalidaProcesional` FOREIGN KEY (`salida_procesional_id`) REFERENCES `salidaprocesional` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of papeletasitio
-- ----------------------------
INSERT INTO `papeletasitio` VALUES ('1', '1', '2015-03-22', '1', '30', '1', '10');
INSERT INTO `papeletasitio` VALUES ('122', '1', '0000-00-00', '1', '15', '1', '3');

-- ----------------------------
-- Table structure for planingmayordomia
-- ----------------------------
DROP TABLE IF EXISTS `planingmayordomia`;
CREATE TABLE `planingmayordomia` (
  `identificador` int(11) NOT NULL,
  `hora` time DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `labor` varchar(500) DEFAULT NULL,
  `mayordomia_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `planingMayordomia` (`mayordomia_id`),
  CONSTRAINT `planingMayordomia` FOREIGN KEY (`mayordomia_id`) REFERENCES `mayordomia` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of planingmayordomia
-- ----------------------------
INSERT INTO `planingmayordomia` VALUES ('1', '09:45:33', '2015-03-26', 'Ingresar Dinero Cuotas', null);

-- ----------------------------
-- Table structure for planingsecretaria
-- ----------------------------
DROP TABLE IF EXISTS `planingsecretaria`;
CREATE TABLE `planingsecretaria` (
  `identificador` int(11) NOT NULL,
  `hora` time DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `secretaria_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `planingSecretaria` (`secretaria_id`),
  CONSTRAINT `planingSecretaria` FOREIGN KEY (`secretaria_id`) REFERENCES `secretaria` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of planingsecretaria
-- ----------------------------
INSERT INTO `planingsecretaria` VALUES ('1', '17:40:55', '2015-03-22', 'Repasar Ultimas Actas', '1');

-- ----------------------------
-- Table structure for protocolo
-- ----------------------------
DROP TABLE IF EXISTS `protocolo`;
CREATE TABLE `protocolo` (
  `identificador` int(11) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `salida_procesional_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `protocoloSalida` (`salida_procesional_id`),
  CONSTRAINT `protocoloSalida` FOREIGN KEY (`salida_procesional_id`) REFERENCES `salidaprocesional` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of protocolo
-- ----------------------------
INSERT INTO `protocolo` VALUES ('1', 'cruz de guia', '1', '1');
INSERT INTO `protocolo` VALUES ('2', 'varas', '10', '1');

-- ----------------------------
-- Table structure for repartoloteria
-- ----------------------------
DROP TABLE IF EXISTS `repartoloteria`;
CREATE TABLE `repartoloteria` (
  `identificador` int(11) NOT NULL,
  `num_desde` int(11) DEFAULT NULL,
  `num_hasta` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `loterias_id` int(11) DEFAULT NULL,
  `numero_hermano_id` int(25) NOT NULL,
  PRIMARY KEY (`identificador`),
  KEY `repartoLoterias` (`loterias_id`),
  CONSTRAINT `repartoLoterias` FOREIGN KEY (`loterias_id`) REFERENCES `loterias` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of repartoloteria
-- ----------------------------
INSERT INTO `repartoloteria` VALUES ('2', '1', '1', '1', '2', '1');

-- ----------------------------
-- Table structure for salidaprocesional
-- ----------------------------
DROP TABLE IF EXISTS `salidaprocesional`;
CREATE TABLE `salidaprocesional` (
  `identificador` int(11) NOT NULL,
  `año` date DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of salidaprocesional
-- ----------------------------
INSERT INTO `salidaprocesional` VALUES ('1', '2015-03-22', 'semana santa 2015');

-- ----------------------------
-- Table structure for secretaria
-- ----------------------------
DROP TABLE IF EXISTS `secretaria`;
CREATE TABLE `secretaria` (
  `identificador` int(11) NOT NULL,
  `numero_hermano_id` int(11) DEFAULT NULL,
  `hermandad_id` int(11) DEFAULT NULL,
  `observaciones` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  UNIQUE KEY `secretariaHermano` (`numero_hermano_id`) USING BTREE,
  UNIQUE KEY `secretariaHermandad` (`hermandad_id`) USING BTREE,
  CONSTRAINT `secretariaHermandad` FOREIGN KEY (`hermandad_id`) REFERENCES `hermandad` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of secretaria
-- ----------------------------
INSERT INTO `secretaria` VALUES ('1', '4', '1', 'Secretaria Primera');

-- ----------------------------
-- Table structure for tipopago
-- ----------------------------
DROP TABLE IF EXISTS `tipopago`;
CREATE TABLE `tipopago` (
  `identificador` int(11) NOT NULL,
  `tipo_pago` varchar(200) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tipopago
-- ----------------------------
INSERT INTO `tipopago` VALUES ('1', 'Domiciliario', null);
INSERT INTO `tipopago` VALUES ('2', 'Banco', null);
DROP TRIGGER IF EXISTS `actualizar`;
DELIMITER ;;
CREATE TRIGGER `actualizar` BEFORE INSERT ON `librodeasientos` FOR EACH ROW update cuentabancaria set cantidad = cantidad + NEW.ingresar
;
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `actu12`;
DELIMITER ;;
CREATE TRIGGER `actu12` AFTER INSERT ON `librodeasientos` FOR EACH ROW update cuentabancaria set cantidad = cantidad - NEW.debe
;
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `actualiza_saldos`;
DELIMITER ;;
CREATE TRIGGER `actualiza_saldos` AFTER INSERT ON `papeletasitio` FOR EACH ROW update cuentabancaria set cantidad = cantidad + NEW.donativo
;
;;
DELIMITER ;
