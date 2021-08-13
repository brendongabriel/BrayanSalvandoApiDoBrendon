package br.com.senai.ProjetoAPI.domain.service;

import br.com.senai.ProjetoAPI.api.assembler.PessoaAssembler;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.repository.PessoaReporitory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PessoaService {
    private PessoaReporitory pessoaReporitory;
    private PessoaAssembler pessoaAssembler;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {
        return pessoaReporitory.save(pessoa);
    }
}
