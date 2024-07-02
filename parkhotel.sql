-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 30. Jun 2024 um 23:57
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `parkhotel`
--

DELIMITER $$
--
-- Prozeduren
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `dailyRoomCheck` ()   BEGIN
	UPDATE raum
    JOIN buchung ON buchung.raum_ID = raum.raum_ID
    SET belegt = 0
    WHERE date_OUT = CURDATE();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `proc` ()   BEGIN
	DECLARE i INT DEFAULT 701;
	DECLARE counter INT DEFAULT 1;
	WHILE i <= 732 DO
		INSERT INTO raum VALUES (i, 1, FALSE);
		IF counter % 3 = 0 THEN
			UPDATE raum SET groesse = 0 WHERE raum_ID = i;
        END IF;
		SET i = i + 1;
		SET counter = counter + 1;
	END WHILE;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `buchung`
--

CREATE TABLE `buchung` (
  `buchung_ID` int(11) NOT NULL,
  `kunde_ID` int(11) NOT NULL,
  `raum_ID` int(11) NOT NULL,
  `date_IN` timestamp NOT NULL DEFAULT current_timestamp(),
  `date_OUT` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Daten für Tabelle `buchung`
--

INSERT INTO `buchung` (`buchung_ID`, `kunde_ID`, `raum_ID`, `date_IN`, `date_OUT`) VALUES
(1, 14, 101, '2024-06-28 09:13:26', '2024-07-10 09:13:26'),
(2, 14, 103, '2024-06-28 09:13:26', '2024-06-29 09:13:26'),
(3, 6, 201, '2024-07-09 09:15:54', '2024-07-27 09:15:54'),
(64, 14, 116, '2024-07-12 18:11:47', '2024-07-19 18:11:47'),
(65, 14, 509, '2024-07-12 18:11:47', '2024-07-19 18:11:47'),
(66, 14, 512, '2024-07-12 18:11:47', '2024-07-19 18:11:47'),
(67, 14, 117, '2024-08-16 18:14:51', '2024-08-24 18:14:51'),
(68, 14, 119, '2024-07-19 18:19:35', '2024-07-21 18:19:35'),
(69, 14, 515, '2024-07-19 18:19:35', '2024-07-21 18:19:35'),
(70, 14, 518, '2024-07-19 18:19:35', '2024-07-21 18:19:35'),
(71, 14, 120, '2024-07-19 18:39:51', '2024-07-26 18:39:51'),
(72, 14, 521, '2024-07-19 18:39:51', '2024-07-26 18:39:51'),
(73, 14, 524, '2024-07-19 18:39:51', '2024-07-26 18:39:51'),
(74, 14, 122, '2024-07-19 18:41:22', '2024-07-26 18:41:22'),
(75, 14, 527, '2024-07-19 18:41:22', '2024-07-26 18:41:22'),
(76, 14, 530, '2024-07-19 18:41:22', '2024-07-26 18:41:22'),
(77, 14, 213, '2024-07-19 18:42:24', '2024-07-25 18:42:24'),
(78, 14, 214, '2024-07-19 18:42:24', '2024-07-25 18:42:24'),
(79, 14, 216, '2024-07-19 18:42:25', '2024-07-25 18:42:25'),
(80, 18, 351, '2024-07-18 20:25:11', '2024-07-26 20:25:11'),
(81, 14, 533, '2024-06-30 20:59:29', '2024-07-01 20:59:29'),
(82, 6, 123, '2024-06-30 21:53:53', '2024-07-27 21:53:53'),
(83, 6, 536, '2024-06-30 21:53:53', '2024-07-27 21:53:53'),
(84, 6, 539, '2024-06-30 21:53:53', '2024-07-27 21:53:53');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `buchungextras`
--

CREATE TABLE `buchungextras` (
  `buchung_ID` int(11) NOT NULL,
  `extras_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Daten für Tabelle `buchungextras`
--

INSERT INTO `buchungextras` (`buchung_ID`, `extras_ID`) VALUES
(1, 4),
(1, 5),
(2, 1),
(2, 2),
(2, 6),
(3, 6),
(3, 1),
(64, 1),
(65, 1),
(66, 1),
(67, 1),
(68, 1),
(68, 5),
(68, 6),
(69, 1),
(69, 5),
(69, 6),
(70, 1),
(70, 5),
(70, 6),
(71, 2),
(71, 5),
(72, 2),
(72, 5),
(73, 2),
(73, 5),
(74, 2),
(74, 5),
(75, 2),
(75, 5),
(76, 2),
(76, 5),
(77, 2),
(77, 3),
(78, 2),
(78, 3),
(79, 2),
(79, 3),
(80, 2),
(80, 4),
(81, 6),
(82, 6),
(83, 6),
(84, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `extras`
--

CREATE TABLE `extras` (
  `extras_ID` int(11) NOT NULL,
  `name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Daten für Tabelle `extras`
--

INSERT INTO `extras` (`extras_ID`, `name`) VALUES
(1, 'Kinderbett'),
(2, 'Pay-TV'),
(3, 'Young Floor'),
(4, 'Flussblick'),
(5, 'Durchgangszimmer'),
(6, 'Vollpension');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kunde`
--

CREATE TABLE `kunde` (
  `kunde_ID` int(11) NOT NULL,
  `login_ID` int(11) NOT NULL,
  `vorname` varchar(128) NOT NULL,
  `nachname` varchar(128) NOT NULL,
  `telefon` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Daten für Tabelle `kunde`
--

INSERT INTO `kunde` (`kunde_ID`, `login_ID`, `vorname`, `nachname`, `telefon`) VALUES
(1, 3, 'Ha', 'Fg', 'dfg'),
(2, 4, 'Hans', 'Horst', '0163 - 55555'),
(6, 8, 'ffwwewaaa', 'w33', '2323eee'),
(7, 9, 'rr', 'rrrr', 'rrrrrrrrrrr'),
(8, 10, 'fde', 'eeeeeee', '33333333333'),
(9, 11, 'Alter', 'Schwede', '0123 / 462746'),
(10, 12, 'zz', 'zzz', '888888888888888'),
(11, 13, 'Herbert', 'Hahn', '0224 / 556459'),
(12, 14, 'cfef', 'wefwefwe', 'dwfdfdfdfdfdfdff'),
(13, 15, 'fjf', 'djdjdjjjj', '44455455454'),
(14, 16, 'Henning', 'Kaßner', '01637769231'),
(15, 17, 'Erster', 'Kunde', '0123 / 456758'),
(16, 18, 'Bitte', 'Laufe', '3949494949494'),
(17, 20, '3333333', '3333333', '3333333333333'),
(18, 22, 'Max', 'Mustermann', '0221 / 55546');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `login`
--

CREATE TABLE `login` (
  `login_ID` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Daten für Tabelle `login`
--

INSERT INTO `login` (`login_ID`, `email`, `password`) VALUES
(1, 'testsubject@test.de', 'passwort1234'),
(3, 'ddg', 'Gruntz'),
(4, 'hans-horst@test.de', 'haho'),
(8, 'ddrr', ''),
(9, 'rrrrrrr@', '4'),
(10, '@@@@@@@', '12345678'),
(11, 'alter@schwede.de', 'alterschwede'),
(12, '@@@@@@@', '88888888'),
(13, 'hahn@name.de', 'hähnchen'),
(14, 'hahn@name.de', '22222222'),
(15, 'hund@name.de', '33333333'),
(16, 'henning_kassner@web.de', '12345678'),
(17, 'erster@kunde.de', '00000000'),
(18, 'bitte.laufe@test.de', 'aaaaaaaa'),
(19, 'hunger@ist.da', '12121212'),
(20, '33333@33333', '33333333'),
(21, 'kjnkcnkvjn@dlfefn', '1010101010101'),
(22, 'max.mustermann@muster.de', 'maxmuster');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `raum`
--

CREATE TABLE `raum` (
  `raum_ID` int(11) NOT NULL,
  `groesse` int(1) NOT NULL,
  `belegt` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Daten für Tabelle `raum`
--

INSERT INTO `raum` (`raum_ID`, `groesse`, `belegt`) VALUES
(101, 1, 1),
(102, 1, 1),
(103, 0, 1),
(104, 1, 1),
(105, 1, 1),
(106, 0, 1),
(107, 1, 1),
(108, 1, 1),
(109, 0, 1),
(110, 1, 1),
(111, 1, 1),
(112, 0, 1),
(113, 1, 1),
(114, 1, 1),
(115, 0, 1),
(116, 1, 1),
(117, 1, 1),
(118, 0, 1),
(119, 1, 1),
(120, 1, 1),
(121, 0, 1),
(122, 1, 1),
(123, 1, 1),
(124, 0, 1),
(125, 1, 0),
(126, 1, 0),
(127, 0, 1),
(128, 1, 0),
(129, 1, 0),
(130, 0, 1),
(131, 1, 0),
(132, 1, 0),
(133, 0, 1),
(134, 1, 0),
(135, 1, 0),
(136, 0, 1),
(137, 1, 0),
(138, 1, 0),
(139, 0, 1),
(140, 1, 0),
(141, 1, 0),
(142, 0, 1),
(143, 1, 0),
(144, 1, 0),
(145, 0, 1),
(146, 1, 0),
(147, 1, 0),
(148, 0, 1),
(149, 1, 0),
(150, 1, 0),
(151, 0, 1),
(201, 1, 1),
(202, 1, 1),
(203, 0, 0),
(204, 1, 1),
(205, 1, 1),
(206, 0, 0),
(207, 1, 1),
(208, 1, 1),
(209, 0, 0),
(210, 1, 1),
(211, 1, 1),
(212, 0, 0),
(213, 1, 1),
(214, 1, 1),
(215, 0, 0),
(216, 1, 1),
(217, 1, 0),
(218, 0, 0),
(219, 1, 0),
(220, 1, 0),
(221, 0, 0),
(222, 1, 0),
(223, 1, 0),
(224, 0, 0),
(225, 1, 0),
(226, 1, 0),
(227, 0, 0),
(228, 1, 0),
(229, 1, 0),
(230, 0, 0),
(231, 1, 0),
(232, 1, 0),
(233, 0, 0),
(234, 1, 0),
(235, 1, 0),
(236, 0, 0),
(237, 1, 0),
(238, 1, 0),
(239, 0, 0),
(240, 1, 0),
(241, 1, 0),
(242, 0, 1),
(243, 1, 0),
(244, 1, 0),
(245, 0, 1),
(246, 1, 0),
(247, 1, 0),
(248, 0, 1),
(249, 1, 0),
(250, 1, 0),
(251, 0, 1),
(301, 1, 0),
(302, 1, 0),
(303, 0, 1),
(304, 1, 0),
(305, 1, 0),
(306, 0, 1),
(307, 1, 0),
(308, 1, 0),
(309, 0, 1),
(310, 1, 0),
(311, 1, 0),
(312, 0, 1),
(313, 1, 0),
(314, 1, 0),
(315, 0, 1),
(316, 1, 0),
(317, 1, 0),
(318, 0, 1),
(319, 1, 0),
(320, 1, 0),
(321, 0, 1),
(322, 1, 0),
(323, 1, 0),
(324, 0, 1),
(325, 1, 0),
(326, 1, 0),
(327, 0, 1),
(328, 1, 0),
(329, 1, 0),
(330, 0, 1),
(331, 1, 0),
(332, 1, 0),
(333, 0, 1),
(334, 1, 0),
(335, 1, 0),
(336, 0, 1),
(337, 1, 0),
(338, 1, 0),
(339, 0, 1),
(340, 1, 0),
(341, 1, 0),
(342, 0, 1),
(343, 1, 0),
(344, 1, 0),
(345, 0, 1),
(346, 1, 0),
(347, 1, 0),
(348, 0, 1),
(349, 1, 0),
(350, 1, 0),
(351, 0, 1),
(401, 1, 0),
(402, 1, 0),
(403, 0, 1),
(404, 1, 0),
(405, 1, 0),
(406, 0, 1),
(407, 1, 0),
(408, 1, 0),
(409, 0, 1),
(410, 1, 0),
(411, 1, 0),
(412, 0, 1),
(413, 1, 0),
(414, 1, 0),
(415, 0, 1),
(416, 1, 0),
(417, 1, 0),
(418, 0, 1),
(419, 1, 0),
(420, 1, 0),
(421, 0, 1),
(422, 1, 0),
(423, 1, 0),
(424, 0, 1),
(425, 1, 0),
(426, 1, 0),
(427, 0, 1),
(428, 1, 0),
(429, 1, 0),
(430, 0, 1),
(431, 1, 0),
(432, 1, 0),
(433, 0, 1),
(434, 1, 0),
(435, 1, 0),
(436, 0, 1),
(437, 1, 0),
(438, 1, 0),
(439, 0, 1),
(440, 1, 0),
(441, 1, 0),
(442, 0, 0),
(443, 1, 0),
(444, 1, 0),
(445, 0, 0),
(446, 1, 0),
(447, 1, 0),
(448, 0, 0),
(449, 1, 0),
(450, 1, 0),
(451, 0, 0),
(501, 1, 0),
(502, 1, 0),
(503, 0, 1),
(504, 1, 0),
(505, 1, 0),
(506, 0, 1),
(507, 1, 0),
(508, 1, 0),
(509, 0, 1),
(510, 1, 0),
(511, 1, 0),
(512, 0, 1),
(513, 1, 0),
(514, 1, 0),
(515, 0, 1),
(516, 1, 0),
(517, 1, 0),
(518, 0, 1),
(519, 1, 0),
(520, 1, 0),
(521, 0, 1),
(522, 1, 0),
(523, 1, 0),
(524, 0, 1),
(525, 1, 0),
(526, 1, 0),
(527, 0, 1),
(528, 1, 0),
(529, 1, 0),
(530, 0, 1),
(531, 1, 0),
(532, 1, 0),
(533, 0, 1),
(534, 1, 0),
(535, 1, 0),
(536, 0, 1),
(537, 1, 0),
(538, 1, 0),
(539, 0, 1),
(540, 1, 0),
(541, 1, 0),
(542, 0, 0),
(543, 1, 0),
(544, 1, 0),
(545, 0, 0),
(546, 1, 0),
(547, 1, 0),
(548, 0, 0),
(549, 1, 0),
(550, 1, 0),
(551, 0, 0),
(601, 1, 0),
(602, 1, 0),
(603, 0, 0),
(604, 1, 0),
(605, 1, 0),
(606, 0, 0),
(607, 1, 0),
(608, 1, 0),
(609, 0, 0),
(610, 1, 0),
(611, 1, 0),
(612, 0, 0),
(613, 1, 0),
(614, 1, 0),
(615, 0, 0),
(616, 1, 0),
(617, 1, 0),
(618, 0, 0),
(619, 1, 0),
(620, 1, 0),
(621, 0, 0),
(622, 1, 0),
(623, 1, 0),
(624, 0, 0),
(625, 1, 0),
(626, 1, 0),
(627, 0, 0),
(628, 1, 0),
(629, 1, 0),
(630, 0, 0),
(631, 1, 0),
(632, 1, 0),
(633, 0, 0),
(634, 1, 0),
(635, 1, 0),
(636, 0, 0),
(637, 1, 0),
(638, 1, 0),
(639, 0, 0),
(640, 1, 0),
(641, 1, 0),
(642, 0, 0),
(643, 1, 0),
(644, 1, 0),
(645, 0, 0),
(646, 1, 0),
(647, 1, 0),
(648, 0, 0),
(649, 1, 0),
(650, 1, 0),
(651, 0, 0),
(701, 1, 0),
(702, 1, 0),
(703, 0, 0),
(704, 1, 0),
(705, 1, 0),
(706, 0, 0),
(707, 1, 0),
(708, 1, 0),
(709, 0, 0),
(710, 1, 0),
(711, 1, 0),
(712, 0, 0),
(713, 1, 0),
(714, 1, 0),
(715, 0, 0),
(716, 1, 0),
(717, 1, 0),
(718, 0, 0),
(719, 1, 0),
(720, 1, 0),
(721, 0, 0),
(722, 1, 0),
(723, 1, 0),
(724, 0, 0),
(725, 1, 0),
(726, 1, 0),
(727, 0, 0),
(728, 1, 0),
(729, 1, 0),
(730, 0, 0);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `buchung`
--
ALTER TABLE `buchung`
  ADD PRIMARY KEY (`buchung_ID`),
  ADD KEY `constraint_kunde` (`kunde_ID`),
  ADD KEY `constraint_raum` (`raum_ID`);

--
-- Indizes für die Tabelle `buchungextras`
--
ALTER TABLE `buchungextras`
  ADD KEY `constraint_buchung` (`buchung_ID`),
  ADD KEY `constraint_extras` (`extras_ID`);

--
-- Indizes für die Tabelle `extras`
--
ALTER TABLE `extras`
  ADD PRIMARY KEY (`extras_ID`);

--
-- Indizes für die Tabelle `kunde`
--
ALTER TABLE `kunde`
  ADD PRIMARY KEY (`kunde_ID`),
  ADD KEY `constraint_login` (`login_ID`);

--
-- Indizes für die Tabelle `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`login_ID`);

--
-- Indizes für die Tabelle `raum`
--
ALTER TABLE `raum`
  ADD PRIMARY KEY (`raum_ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `buchung`
--
ALTER TABLE `buchung`
  MODIFY `buchung_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT für Tabelle `extras`
--
ALTER TABLE `extras`
  MODIFY `extras_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `kunde`
--
ALTER TABLE `kunde`
  MODIFY `kunde_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT für Tabelle `login`
--
ALTER TABLE `login`
  MODIFY `login_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT für Tabelle `raum`
--
ALTER TABLE `raum`
  MODIFY `raum_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=733;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `buchung`
--
ALTER TABLE `buchung`
  ADD CONSTRAINT `constraint_kunde` FOREIGN KEY (`kunde_ID`) REFERENCES `kunde` (`kunde_ID`),
  ADD CONSTRAINT `constraint_raum` FOREIGN KEY (`raum_ID`) REFERENCES `raum` (`raum_ID`);

--
-- Constraints der Tabelle `buchungextras`
--
ALTER TABLE `buchungextras`
  ADD CONSTRAINT `constraint_buchung` FOREIGN KEY (`buchung_ID`) REFERENCES `buchung` (`buchung_ID`),
  ADD CONSTRAINT `constraint_extras` FOREIGN KEY (`extras_ID`) REFERENCES `extras` (`extras_ID`);

--
-- Constraints der Tabelle `kunde`
--
ALTER TABLE `kunde`
  ADD CONSTRAINT `constraint_login` FOREIGN KEY (`login_ID`) REFERENCES `login` (`login_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
