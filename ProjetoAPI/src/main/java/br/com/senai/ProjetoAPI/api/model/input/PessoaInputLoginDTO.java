package br.com.senai.ProjetoAPI.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaInputLoginDTO {
    private String email;
    private String senha;
}