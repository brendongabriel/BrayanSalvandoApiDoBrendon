CREATE TABLE pessoa (
    codigo bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar (120) not null,
    perfil varchar(45) not null,
    primary key (codigo)
);