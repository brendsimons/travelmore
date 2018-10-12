SET GLOBAL time_zone = '+01:00';

DROP TABLE location;

CREATE TABLE location (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name              VARCHAR(255) NOT NULL
);

INSERT INTO `location` (`id`, `name`) VALUES
(1, 'Brussel'),
(2, 'Parijs'),
(3, 'Londen'),
(4, 'Rome'),
(5, 'Berlijn');

DROP TABLE transportType;

CREATE TABLE transportType (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name              VARCHAR(255) NOT NULL
);

INSERT INTO `transportType` (`id`, `name`) VALUES
(1, 'Vliegtuig'),
(2, 'Bus'),
(3, 'Trein');

DROP TABLE trip;

CREATE TABLE trip (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  departureLocation INT NOT NULL,
  arrivalLocation   INT NOT NULL,
  goDate            DATETIME NOT NULL,
  backDate          DATETIME NOT NULL,
  places            INT NOT NULL,
  price             DOUBLE NOT NULL,
  transportType     INT NOT NULL
);

INSERT INTO `trip` (`id`, `departureLocation`, `arrivalLocation`, `goDate`, `backDate`, `places`, `price`, `transportType`) VALUES
(1, 1, 2, '2018-10-12 15:00:00', '2018-10-19 15:00:00', 10, 55.45, 1),
(2, 5, 3, '2018-10-20 11:30:00', '2018-10-25 19:00:00', 20, 45.33, 2),
(3, 4, 2, '2018-10-24 10:00:00', '2018-10-30 15:00:00', 15, 29.64, 3),
(4, 1, 4, '2018-10-24 13:00:00', '2018-11-02 15:00:00', 20, 71.45, 1),
(5, 3, 1, '2018-10-28 17:00:00', '2018-11-05 23:00:00', 10, 23.45, 2),
(6, 1, 5, '2018-10-29 12:00:00', '2018-11-10 06:00:00', 25, 49.25, 3);

DROP TABLE user;

CREATE TABLE user (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstName         VARCHAR(255) NOT NULL,
  lastName          VARCHAR(255) NOT NULL,
  email             VARCHAR(255) NOT NULL,
  password          VARCHAR(255) NOT NULL
);

INSERT INTO `user` (`id`, `firstName`, `lastName`, `email`, `password`) VALUES
(1, 'Firstname1', 'Lastname1', 'user2@test.com', 'test'),
(2, 'Firstname2', 'Lastname2', 'user2@test.com', 'test'),
(3, 'Firstname3', 'Lastname3', 'user2@test.com', 'test'),
(4, 'Firstname4', 'Lastname4', 'user2@test.com', 'test'),
(5, 'Firstname5', 'Lastname5', 'user2@test.com', 'test');

DROP TABLE booking;

CREATE TABLE booking (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  trip              INT NOT NULL,
  user              INT NOT NULL,
  amountOfPeople    INT NOT NULL,
  paid              TINYINT(1) DEFAULT 0
);

INSERT INTO `booking` (`id`, `trip`, `user`, `amountOfPeople`) VALUES
(1, 1, 1, 1),
(2, 3, 2, 5),
(3, 5, 3, 4),
(4, 4, 4, 3),
(5, 2, 5, 2),
(6, 3, 1, 3);