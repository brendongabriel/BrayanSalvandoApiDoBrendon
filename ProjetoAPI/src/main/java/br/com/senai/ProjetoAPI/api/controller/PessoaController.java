package br.com.senai.ProjetoAPI.api.controller;

import br.com.senai.ProjetoAPI.api.assembler.PessoaAssembler;
import br.com.senai.ProjetoAPI.api.model.PessoaDTO;
import br.com.senai.ProjetoAPI.api.model.ProdutoDTO;
import br.com.senai.ProjetoAPI.api.model.input.PessoaInputDTO;
import br.com.senai.ProjetoAPI.api.model.input.ProdutoInputDTO;
import br.com.senai.ProjetoAPI.domain.model.Perfil;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import br.com.senai.ProjetoAPI.domain.repository.PessoaReporitory;
import br.com.senai.ProjetoAPI.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        novaPessoa.setSenha(new BCryptPasswordEncoder().encode(pessoaInputDTO.getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);
        return pessoaAssembler.toModel(pessoa);
    }

    @GetMapping("/listar")
    public List<PessoaDTO> listar(){
        return pessoaAssembler.toCollectionModel(pessoaService.listar());
    }

    @GetMapping("/buscar/{pessoaId}")
    public ResponseEntity<PessoaDTO> buscarId(@PathVariable Long pessoaId){
        return pessoaService.buscarId(pessoaId);
    }


    @PutMapping("/editar/{pessoaId}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long pessoaId,
                                             @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        pessoaService.editar(pessoaId,pessoa);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
    }

    @PutMapping("/editaradmin/{pessoaId}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long pessoaId){
        Pessoa pessoa = pessoaService.editarPerfilAdmin(pessoaId);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
    }

    @DeleteMapping("/deletar/{pessoaId}")
    public void deletar(@PathVariable Long pessoaId){
        pessoaService.deletar(pessoaId);
    }

}
