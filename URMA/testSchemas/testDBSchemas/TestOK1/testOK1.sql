DELETE FROM KOLONE_U_STRANOM_KLJUCU
DELETE FROM STRANI_KLJUC
DELETE FROM ATRIBUTI
DELETE FROM TABELE
delete from struktura_podsistema
DELETE from podsistem
insert into podsistem values ('PSW2017', 'PSW2017', 'S')
insert into podsistem values ('MAIN', 'Main', 'P')
insert into struktura_podsistema values ('PSW2017', 'MAIN')
insert into TABELE values ('MAIN', 'PAR', 'Parent')
insert into ATRIBUTI values ('MAIN', 'PAR', 'NAM', 'ParentName', 'char', 1, 14, 0, 1)
insert into ATRIBUTI values ('MAIN', 'PAR', 'ADD', 'Address', 'varchar', 1, 128, 0, 0)
insert into ATRIBUTI values ('MAIN', 'PAR', 'AGE', 'Age', 'int', 0, 0, 0, 0)
insert into ATRIBUTI values ('MAIN', 'PAR', 'DOB', 'Date of birth', 'datetime', 0, 0, 0, 0)
insert into TABELE values ('MAIN', 'CHI', 'Child')
insert into ATRIBUTI values ('MAIN', 'CHI', 'NAM', 'ParentName', 'char', 1, 14, 0, 1)
insert into ATRIBUTI values ('MAIN', 'CHI', 'SUR', 'Surname', 'varchar', 1, 128, 0, 1)
insert into ATRIBUTI values ('MAIN', 'CHI', 'NOP', 'Number of presents', 'int', 0, 0, 0, 0)
INSERT INTO STRANI_KLJUC VALUES ('MAIN','PAR','MAIN','CHI','PAR_CHI','Parent-Child',1,1,0,0)
INSERT INTO KOLONE_U_STRANOM_KLJUCU VALUES ('MAIN','PAR','MAIN','CHI','PAR_CHI','NAM','NAM')
