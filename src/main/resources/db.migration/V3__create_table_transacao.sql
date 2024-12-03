CREATE TABLE transacao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    valor DECIMAL(10,2) NOT NULL,
    fiel_id BIGINT NOT NULL,
    data DATETIME NOT NULL,
    categoria_id BIGINT NOT NULL,
    FOREIGN KEY (fiel_id) REFERENCES fiel(id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);