CREATE TABLE produto (
    codigo bigint not null auto_increment,
    produto varchar(100) not null,
    quantidade int  not null,
    valor_unitario float not null,
    valor_total_em_estoque float not null,
    primary key(codigo)
);