INSERT INTO USERS (ID, USERNAME, PASSWORD)
VALUES (1, 'Qlson', 'polslpolsl');

INSERT INTO SURVEY (USER_ID, ID, CREATED_AT, STATUS, TITLE)
VALUES (1, 1, 'today', 'done', 'Ocena laboratorium podstaw miernictwa'),
       (1, 2, 'today', 'notdone', 'Ocena laboratorium metod numerycznych');

INSERT INTO QUESTION (ID, SURVEY_ID, TEXT, TYPE)
VALUES (1, 1, 'Czy ci sie podobało', 'Jednokrotna'),
       (2, 1, 'Jak oceniasz prowadzacego', 'Skala'),
       (3, 2, 'Czy dobrze cie uczył', 'Jednokrotnego'),
       (4, 2, 'Czy gadał głupoty', 'Jednokrotnego')
