package br.com.senai.ProjetoAPI.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
    private Long codigo;
    private String nome;
    private String email;
}
