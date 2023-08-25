INSERT INTO USERS (ID, USERNAME, PASSWORD)
VALUES (11, 'Qlson', 'polslpolsl');

INSERT INTO SURVEY (USER_ID, ID, CREATED_AT, STATUS, TITLE)
VALUES (11, 11, '2023-08-23', 'done', 'Ocena laboratorium podstaw miernictwa'),
       (11, 12, '2023-08-23', 'notdone', 'Ocena laboratorium metod numerycznych');

INSERT INTO QUESTION (ID, SURVEY_ID, TEXT, SEQUENCE, TYPE)
VALUES (11, 11, 'Czy ci sie podobało', 1, 'Jednokrotna'),
       (12, 11, 'Jak oceniasz prowadzacego', 2, 'Skala'),
       (13, 12, 'Czy dobrze cie uczył', 3, 'Jednokrotnego'),
       (14, 12, 'Czy gadał głupoty', 4, 'Jednokrotnego')
