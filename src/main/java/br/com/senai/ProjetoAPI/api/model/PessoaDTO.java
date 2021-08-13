package br.com.senai.ProjetoAPI.api.model;

import br.com.senai.ProjetoAPI.domain.model.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
    private Long codigo;
    private String nome;
    private String email;
    private Perfil perfil;

}
