package br.com.senai.ProjetoAPI.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long codigo;
    private String produto;
    private int quantidade;
    private float valorUnitario;
    private float valorTotalEmEstoque;

}
