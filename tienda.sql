-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2016 a las 23:54:50
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`user`@`localhost` PROCEDURE `insertarCliente_en` (IN `nombre` VARCHAR(30), IN `apellidos` VARCHAR(65), IN `dni` VARCHAR(9), IN `cod_postal` VARCHAR(5), IN `telefono` VARCHAR(11))  NO SQL
INSERT INTO clientes VALUES (cod_cliente,nombre,apellidos,dni,cod_postal,telefono)$$

CREATE DEFINER=`user`@`localhost` PROCEDURE `insertarFactura_en` (IN `fecha_fact` DATE, IN `cod_rep` VARCHAR(9), IN `importe` FLOAT, IN `cod_cliente` INT)  NO SQL
INSERT INTO facturas VALUES (num_factura,fecha_fact,cod_rep,importe,cod_cliente)$$

CREATE DEFINER=`user`@`localhost` PROCEDURE `insertarUsuario_en` (IN `nombre` VARCHAR(25), IN `pass` VARCHAR(25), IN `tipo` VARCHAR(11))  NO SQL
INSERT INTO usuarios VALUES (id,nombre,pass,tipo)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cod_cliente` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(65) COLLATE utf8_spanish2_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `cod_postal` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` varchar(11) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`cod_cliente`, `nombre`, `apellidos`, `dni`, `cod_postal`, `telefono`) VALUES
(1, 'Maria', 'Sanchez García', '7305211M', '50005', '976565656'),
(2, 'Tamara', 'Gascon', '73000633J', '50010', '608453426'),
(3, 'Juanje', 'García Pérez', '73000633J', '50005', '608453426'),
(4, 'Jamon', 'Serrano', '73000633J', '50005', '608453426');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `num_factura` int(9) NOT NULL,
  `fecha_fact` date NOT NULL,
  `cod_rep` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `importe` float NOT NULL,
  `cod_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`num_factura`, `fecha_fact`, `cod_rep`, `importe`, `cod_cliente`) VALUES
(12, '2016-05-22', 'R20160001', 1200, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparaciones`
--

CREATE TABLE `reparaciones` (
  `cod_rep` int(9) NOT NULL,
  `problema` varchar(600) COLLATE utf8_spanish2_ci NOT NULL,
  `solucion` varchar(600) COLLATE utf8_spanish2_ci NOT NULL,
  `f_recogida` date NOT NULL,
  `f_entrega` date NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `facturado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `reparaciones`
--

INSERT INTO `reparaciones` (`cod_rep`, `problema`, `solucion`, `f_recogida`, `f_entrega`, `cod_cliente`, `id`, `facturado`) VALUES
(1, 'No funciona la gráfica, huele a quemado', 'Sustitución.', '2016-05-10', '2016-05-19', 1, 3, 1),
(2, 'Fuego', 'Extintor', '2016-05-09', '2016-05-18', 1, 3, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `pass` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` varchar(11) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `pass`, `tipo`) VALUES
(1, 'maria', '12345', 'tecnico'),
(2, 'manuel', '654321', 'comercial'),
(3, 'Tamara', 'AAgato123$', 'tecnico'),
(4, 'Juanje', 'BBgato123$', 'tecnico'),
(5, 'Tamy', 'AAgato123a$', 'comercial'),
(6, 'Carmen', 'AAgato123$', 'comercial');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cod_cliente`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`num_factura`);

--
-- Indices de la tabla `reparaciones`
--
ALTER TABLE `reparaciones`
  ADD PRIMARY KEY (`cod_rep`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `num_factura` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `reparaciones`
--
ALTER TABLE `reparaciones`
  MODIFY `cod_rep` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
