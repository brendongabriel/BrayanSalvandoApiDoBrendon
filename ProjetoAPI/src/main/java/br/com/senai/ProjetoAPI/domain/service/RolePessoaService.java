package br.com.senai.ProjetoAPI.domain.service;

import br.com.senai.ProjetoAPI.domain.model.RolePessoa;
import br.com.senai.ProjetoAPI.domain.repository.RolePessoasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RolePessoaService {
    private RolePessoasRepository rolePessoasRepository;

    public RolePessoa cadastrar(RolePessoa rolePessoa) {
        return rolePessoasRepository.save(rolePessoa);
    }
}
