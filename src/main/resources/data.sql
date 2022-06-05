-- Script runs after hibernate initializes the entities

INSERT INTO pessoa(nome) VALUES('Victor');
INSERT INTO pessoa(nome) VALUES('Emanuel');
INSERT INTO pessoa(nome) VALUES('Ribeiro');
INSERT INTO pessoa(nome) VALUES('Silva');
INSERT INTO pessoa(nome) VALUES('');

SELECT * FROM pessoa ORDER BY id;