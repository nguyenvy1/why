-- Initialize tables

DROP TABLE IF EXISTS DOGS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS SITTERS;
DROP TABLE IF EXISTS REVIEWS;

CREATE TABLE DOGS
(
  dog_id   INT AUTO_INCREMENT PRIMARY KEY,
  dog_name VARCHAR(250) NOT NULL,
  owner_id VARCHAR(250) NOT NULL,
);
CREATE INDEX ind_dog_name on DOGS (dog_name);

CREATE TABLE OWNERS
(
  owner_id           INT AUTO_INCREMENT PRIMARY KEY,
  owner_name         VARCHAR(250) NOT NULL,
  owner_image        VARCHAR(250) NOT NULL,
  owner_phone_number VARCHAR(250) NOT NULL,
  owner_email        VARCHAR(250) NOT NULL
);
CREATE INDEX ind_user_name on OWNERS (owner_name);

CREATE TABLE SITTERS
(
  sitter_id           INT AUTO_INCREMENT PRIMARY KEY,
  sitter_name         VARCHAR(250) NOT NULL,
  sitter_image        VARCHAR(250) NOT NULL,
  sitter_email        VARCHAR(250) NOT NULL,
  sitter_phone_number VARCHAR(250) NOT NULL,
  avg_rating          DOUBLE,
  num_sits            int
);
CREATE INDEX ind_sitter_name on SITTERS (sitter_name);

-- Load .csv data into reviews table
CREATE TABLE REVIEWS
(
  rating              int          NOT NULL,
  sitter_image        VARCHAR(250) NOT NULL,
  end_date            DATE         NOT NULL,
  text                VARCHAR (2000) NOT NULL,
  owner_image         VARCHAR(250) NOT NULL,
  dogs                VARCHAR(250) NOT NULL,
  sitter              VARCHAR(250) NOT NULL,
  owner               VARCHAR(250) NOT NULL,
  start_date          DATE         NOT NULL,
  sitter_phone_number VARCHAR(250) NOT NULL,
  sitter_email        VARCHAR(250) NOT NULL,
  owner_phone_number  VARCHAR(250) NOT NULL,
  owner_email         VARCHAR(250) NOT NULL,
)
AS
SELECT *
FROM CSVREAD ('./server/src/main/resources/reviews.csv', null,'fieldSeparator=,');

-- Load owners from CSV data into OWNERS table
INSERT INTO OWNERS (owner_name, owner_image, owner_phone_number, owner_email)
  SELECT DISTINCT (owner), owner_image, owner_phone_number, owner_email from REVIEWS;

-- Load sitters from CSV data into SITTERS table. Also calculates average rating and number of dog sits.
INSERT INTO SITTERS(sitter_name, sitter_image, sitter_email, sitter_phone_number, avg_rating, num_sits)
  SELECT DISTINCT (sitter), sitter_image, sitter_email, sitter_phone_number, AVG(cast(rating as FLOAT)), COUNT(1)
  FROM REVIEWS GROUP BY sitter;

-- Loading dogs happens after application startup; H2 doesn't support splitting of strings