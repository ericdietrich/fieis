CREATE TABLE fiel (
    id CHAR(36) DEFAULT (UUID()) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11),
    email VARCHAR (255)
 );