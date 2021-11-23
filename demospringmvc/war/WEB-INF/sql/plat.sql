-- phpMyAdmin SQL Dump
-- version 3.3.2
-- http://www.phpmyadmin.net
--
-- Serveur: 127.0.0.1
-- Généré le : Mer 15 Septembre 2010 à 01:43
-- Version du serveur: 5.1.45
-- Version de PHP: 5.3.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `restaurant`
--

-- --------------------------------------------------------

--
-- Structure de la table `plat`
--

CREATE TABLE IF NOT EXISTS `plat` (
  `id` int(50) NOT NULL,
  `NomPlat` varchar(255) NOT NULL,
  `Description` longtext NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Prix` float(5,2) NOT NULL,
  `MiseJour` date NOT NULL,
  `Image` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `plat`
--

INSERT INTO `plat` (`id`, `NomPlat`, `Description`, `Type`, `Prix`, `MiseJour`, `Image`) VALUES
(1, 'laping', 'LAPIN à la moutarde !!', 'ezez', 10.00, '2010-09-23', 0x30);
