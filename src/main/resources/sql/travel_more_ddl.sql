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
(2, 5, 3, '2018-10-20 11:30:00', '2018-10-25 19:00:00', 20, 45, 2),
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
  password          VARCHAR(255) NOT NULL,
  village           VARCHAR(255) NOT NULL,
  street            VARCHAR(255) NOT NULL
);

INSERT INTO `user` (`id`, `firstName`, `lastName`, `email`, `password`, `village`, `street`) VALUES
(1, 'Firstname1', 'Lastname1', 'user2@test.com', 'test', 'GemeenteA', 'Straat 2'),
(2, 'Firstname2', 'Lastname2', 'user1@test.com', 'test', 'GemeenteB', 'Straat 4'),
(3, 'Firstname3', 'Lastname3', 'user3@test.com', 'test', 'GemeenteC', 'Straat 6'),
(4, 'Firstname4', 'Lastname4', 'user4@test.com', 'test', 'GemeenteD', 'Straat 8'),
(5, 'Firstname5', 'Lastname5', 'user5@test.com', 'test', 'GemeenteE', 'Straat 10');

DROP TABLE booking;

CREATE TABLE booking (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  trip              INT NOT NULL,
  user              INT NOT NULL,
  amountOfPeople    INT NOT NULL,
  paid              TINYINT(1) DEFAULT 0,
  paymentMethod     VARCHAR(255) NOT NULL
);

INSERT INTO `booking` (`id`, `trip`, `user`, `amountOfPeople`, `paymentMethod`) VALUES
(1, 1, 1, 1, 'Bancontact'),
(2, 3, 2, 5, 'Creditcard'),
(3, 5, 3, 4, 'Bancontact'),
(4, 4, 4, 3, 'Creditcard'),
(5, 2, 5, 2, 'PayPal'),
(6, 3, 1, 3, 'PayPal');