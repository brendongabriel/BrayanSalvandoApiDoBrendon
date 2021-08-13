CREATE TABLE role_pessoa(
    id bigint not null auto_increment primary key,
    pessoa_codigo bigint not null,
    role_nome_role varchar(45) not null
);

ALTER TABLE role_pessoa add CONSTRAINT fk_usuarios
FOREIGN KEY(pessoa_codigo) REFERENCES pessoa(codigo);

ALTER TABLE role_pessoa add CONSTRAINT fk_role
FOREIGN KEY(role_nome_role) REFERENCES role(nome_role);