-- phpMyAdmin SQL Dump
-- version 2.6.1-pl3
-- http://www.phpmyadmin.net
-- 
-- Serveur: localhost
-- Version du serveur: 4.1.9
-- Version de PHP: 5.0.4
-- 
-- Base de données: `formation`
-- 

-- --------------------------------------------------------

-- 
-- Structure de la table `employe`
-- 

CREATE TABLE `employe` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `login` varchar(25) NOT NULL default '',
  `password` varchar(10) NOT NULL default '',
  `nom` varchar(30) NOT NULL default '',
  `prenom` varchar(30) NOT NULL default '',
  `email` varchar(30) default NULL,
  `role` varchar(10) NOT NULL default '',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- 
-- Contenu de la table `employe`
-- 

INSERT INTO `employe` VALUES (1, 'douglas', 'objis', 'Mbiandou', 'Douglas', 'douglas.mbiandou@objis.com', 'employe');
INSERT INTO `employe` VALUES (2, 'mass', 'objis', 'Samb', 'Mass', 'mass.samb@objis.com', 'employe');
INSERT INTO `employe` VALUES (3, 'nassur', 'objis', 'Mhoumadi', 'Nassur', 'nassur.mhoumadi@objis.com', 'manager');
        