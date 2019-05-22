DROP SCHEMA IF EXISTS test;
CREATE SCHEMA test;

USE test;

DROP TABLE if exists parts;
CREATE TABLE parts
(
    id          int(10) PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(100)          NOT NULL,
    isNecessary BIT     DEFAULT false NOT NULL,
    count       int(10) DEFAULT 0     NOT NULL
)
    COLLATE = utf8_general_ci;
CREATE UNIQUE INDEX parts_title_uindex ON parts (title);

INSERT INTO parts (title, isNecessary, count)
VALUES
       ('материнская плата', 1, 3),
       ('звуковая карта', 0, 2),
       ('процессор', 1, 2),
       ('подсветка корпуса', 0, 0),
       ('HDD диск', 1, 1),
       ('корпус', 1, 10),
       ('память', 1, 10),
       ('SSD диск', 1, 15),
       ('видеокарта', 0, 7),
       ('материнская плата2', 1, 5),
       ('звуковая карта2', 0, 4),
       ('процессор2', 1, 1),
       ('подсветка корпуса2', 0, 5),
       ('HDD диск2', 1, 0),
       ('корпус2', 1, 7),
       ('память2', 1, 9),
       ('SSD диск2', 1, 3),
       ('видеокарта2', 0, 6),
       ('материнская плата3', 1, 0),
       ('звуковая карта3', 0, 1),
       ('процессор3', 1, 4),
       ('подсветка корпуса3', 0, 7),
       ('HDD диск3', 1, 8),
       ('корпус3', 1, 0),
       ('память3', 1, 0),
       ('SSD диск3', 1, 6),
       ('видеокарта3', 0, 2);