INSERT INTO USERS (ID, USERNAME, PASSWORD)
VALUES (11, 'Qlson', 'polslpolsl');

INSERT INTO SURVEY (USER_ID, ID, CREATED_AT, STATUS, TITLE)
VALUES (11, 11, '2023-08-23', 'done', 'Ocena laboratorium podstaw miernictwa'),
       (11, 12, '2023-08-23', 'notdone', 'Ocena laboratorium metod numerycznych');

INSERT INTO QUESTION (ID, SURVEY_ID, TEXT, SEQUENCE, TYPE)
VALUES (11, 11, 'Czy ci sie podobało', 1, 'Jednokrotna'),
       (12, 11, 'Jak oceniasz prowadzacego', 2, 'Skala'),
       (13, 11, 'Czy dobrze cie uczył', 3, 'Jednokrotnego'),
       (14, 11, 'Czy gadał głupoty', 4, 'Jednokrotnego'),
       (15, 11, 'Czy lubisz go', 5, 'Jednokrotnego');


INSERT INTO ANSWER (ID, QUESTION_ID, TEXT, SEQUENCE, DATA)
VALUES (11, 11, 'Tak', 1, 1),
       (12, 11, 'Tak troche', 2, 1),
       (13, 11, 'Moze być', 3, 1),
       (14, 11, 'Niezbyt', 4, 1),
       (15, 11, 'Nie', 5, 1);
