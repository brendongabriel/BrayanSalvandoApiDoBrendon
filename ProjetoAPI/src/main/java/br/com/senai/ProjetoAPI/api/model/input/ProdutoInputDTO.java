package br.com.senai.ProjetoAPI.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInputDTO {

    private String produto;
    private int quantidade;
    private float valorUnitario;
}
