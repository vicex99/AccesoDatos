-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-10-2018 a las 19:10:55
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bbdd_1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mitologias`
--

CREATE TABLE `mitologias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(244) NOT NULL,
  `descripcion` varchar(244) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `mitologias`
--

INSERT INTO `mitologias` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Griega', 'La mitología griega es el conjunto de mitos y leyendas pertenecientes a los antiguos griegos que tratan de sus dioses y héroes, la naturaleza del mundo, los orígenes y el significado de sus propios cultos y prácticas rituales. '),
(2, 'ESDLA', 'Mejor mundo de fantasia creado'),
(3, 'Nordica', 'Los términos mitología nórdica, mitología germánica y mitología escandinava comprenden todo lo relativo a la religión, creencias y leyendas de los pueblos escandinavos germanos, incluidos aquellos que se asentaron en Islandia, Britania, Galia e');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mitologias`
--
ALTER TABLE `mitologias`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
