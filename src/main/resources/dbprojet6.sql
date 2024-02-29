-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 29 fév. 2024 à 17:42
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `dbprojet6`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id_compte` int(11) NOT NULL,
  `numero_compte_bancaire` varchar(255) DEFAULT NULL,
  `solde` double DEFAULT NULL,
  `id_client` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id_compte`, `numero_compte_bancaire`, `solde`, `id_client`) VALUES
(1, 'ABBBCD', 620, 1),
(3, 'BBACDD', 200, 2),
(4, 'DCFFGS', 885, 4),
(5, 'FDBCSM', 27, 5),
(6, 'ODSGFK', 60, 6),
(7, 'PASDPB', 30, 10),
(8, 'JFAPBF', 100, 14),
(9, 'NDFAAD', 130, 15),
(11, 'AFFDQR', 20, 3),
(12, 'GDGTDD', 300, 24),
(13, 'OPLDTS', 240, 25);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` enum('ROLE_USER','ROLE_ADMIN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id_transaction` int(11) NOT NULL,
  `date_transaction` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `compte_destination_id` int(11) DEFAULT NULL,
  `compte_source_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`id_transaction`, `date_transaction`, `description`, `montant`, `compte_destination_id`, `compte_source_id`) VALUES
(1, '2024-01-12 11:11:03.000000', 'virement', 50, 3, 1),
(2, '2024-02-16 09:31:30.000000', 'virement', 50, 4, 13),
(6, '2024-02-22 14:48:08.000000', 'Paiement d\'achat', 1000, 4, 1),
(7, '2024-02-21 14:49:47.000000', 'Transfert vers un amis', 1000, 3, 4),
(9, '2024-02-01 14:51:11.000000', 'virement pour rembourser un emprunt', 250, 4, 4),
(10, '2024-01-26 15:27:08.000000', 'Restaurant grilles ', 57, 8, 11),
(13, '2024-01-25 15:28:37.000000', 'Transfert d\'argent à un ami', 189, 9, 4),
(14, '2024-02-22 15:32:18.000000', 'Paiement location véhicule', 100, 11, 4),
(15, '2024-02-14 22:06:51.000000', 'Ticket film', 80, 4, 9),
(21, '2024-02-23 14:55:07.000000', 'Virement loyer', 50, 3, 4),
(22, '2024-02-23 14:55:48.000000', 'Virement', 20, 9, 4),
(23, '2024-02-23 14:57:07.000000', 'Virement loyer', 20, 9, 4),
(24, '2024-02-28 17:39:04.000000', 'Virement loyer', 349, 1, 4),
(25, '2024-02-29 10:05:47.000000', 'Virement loyer', 250, 6, 13),
(26, '2024-02-29 10:15:28.000000', 'Virement', 200, 12, 5),
(27, '2024-02-29 12:22:03.000000', 'payer', 20, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `friend` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `username`, `parent`, `friend`) VALUES
(1, 'ndeyehoudia@gmail.com', 'ndeyeaTest123@@', 'Ndeye', NULL, 4),
(2, 'jean22@gmail.com', 'jeanTesttt12', 'Jean', NULL, 4),
(3, 'modou20@gmail.com', 'modou2003@@', 'Modou', NULL, 1),
(4, 'modouSV12@gmail.com', '$2a$10$hrxJ4jjj.3uMx3t9icmh5OaV4uIAPIW3eJ8todmG0ZZKNPUhKHCUe', 'Moustapha', NULL, 3),
(5, 'marie22@gmail.com', '$2a$10$WIh3dnR8QAim5MZ3NZ2nW.AFThj48DzUb7fl.FttUpRJ5ZdqUB4xC', 'Marie', NULL, 14),
(6, 'OlivierTheule10@gmail.com', '$2a$10$S3H3P3EkddSgDOChkzeL7O44yQp//03R/zFjTTwd6v5NV6C1gDPUG', 'Olivier', NULL, 25),
(10, 'PascalDip10@gmail.com', '$2a$10$QdR5eqPtRxsFrp3f/WkqKOb6hYvQmm0JrkKhcfwaSo/E7s2KpdqmK', 'Pascal', NULL, NULL),
(14, 'julefauvel02@gmail.com', '$2a$10$4Zb39JlkG9NFHKeIKCx38.miqaUFpkjOzgFuIVCmNZnEp9glDkN2K', 'Jule', NULL, NULL),
(15, 'ndiayefatou12@gmail.com', '$2a$10$Oogd9Xk8.qy8HjEMIMFKpe/6RktsDBT7xYvXyH9Cumwc0RbDOJh1S', 'Fatou', NULL, 4),
(24, 'armandCoding20gmail.com', '$2a$10$FfB7py985PyIwct5Zdmge.PxipY1ZrsaW3l8U/o2WcZimD90bOur6', 'Armand', NULL, 5),
(25, 'saerDiagne2021gmail.com', '$2a$10$BsSIBkaiKRuPhVEwqvnn6u23t/qaJW0yRW17m4pN4Y/lMRFi8T82q', 'Saer', NULL, 4);

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(4, 1),
(5, 1),
(6, 1),
(10, 1),
(14, 1),
(15, 1),
(24, 1),
(25, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id_compte`),
  ADD UNIQUE KEY `UK_nhtf6th4jg4q67noifff5puk4` (`id_client`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id_transaction`),
  ADD KEY `FK73s7qa9u0pudjkp3fiawobsnw` (`compte_destination_id`),
  ADD KEY `FKiepcip15pje3m0dkrx1gvwk6y` (`compte_source_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD KEY `FKnr0j8dvsurq7r25eqrl6p1gn4` (`parent`),
  ADD KEY `FKteae7moi8qg0nh0ig5dm17uxg` (`friend`);

--
-- Index pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id_compte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id_transaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FKe59w6hc7y2kprv79uq7iwfe8s` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK73s7qa9u0pudjkp3fiawobsnw` FOREIGN KEY (`compte_destination_id`) REFERENCES `compte` (`id_compte`),
  ADD CONSTRAINT `FKiepcip15pje3m0dkrx1gvwk6y` FOREIGN KEY (`compte_source_id`) REFERENCES `compte` (`id_compte`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKnr0j8dvsurq7r25eqrl6p1gn4` FOREIGN KEY (`parent`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKteae7moi8qg0nh0ig5dm17uxg` FOREIGN KEY (`friend`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
