CREATE TABLE categoria (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL
);

INSERT INTO categoria (nome) VALUES ('Dízimo');
INSERT INTO categoria (nome) VALUES ('Oferta avulsa');
INSERT INTO categoria (nome) VALUES ('Oferta missionária');