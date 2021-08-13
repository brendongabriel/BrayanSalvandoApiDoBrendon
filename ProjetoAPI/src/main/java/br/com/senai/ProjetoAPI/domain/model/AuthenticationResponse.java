package br.com.senai.ProjetoAPI.domain.model;

import br.com.senai.ProjetoAPI.api.model.PessoaLoginDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private PessoaLoginDTO pessoaLoginDTO;
}
