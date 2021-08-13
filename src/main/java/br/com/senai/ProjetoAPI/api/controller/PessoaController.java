package br.com.senai.ProjetoAPI.api.controller;

import br.com.senai.ProjetoAPI.api.assembler.PessoaAssembler;
import br.com.senai.ProjetoAPI.api.model.PessoaDTO;
import br.com.senai.ProjetoAPI.api.model.input.PessoaInputDTO;
import br.com.senai.ProjetoAPI.domain.model.Perfil;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.repository.PessoaReporitory;
import br.com.senai.ProjetoAPI.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private PessoaReporitory pessoaReporitory;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;

    @PostMapping("/cadastrar")
    public PessoaDTO cadastrar(@RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        novaPessoa.setPerfil(Perfil.USER);
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);
        return pessoaAssembler.toModel(pessoa);
    }
}
