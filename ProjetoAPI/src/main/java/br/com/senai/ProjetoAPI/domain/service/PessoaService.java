package br.com.senai.ProjetoAPI.domain.service;

import br.com.senai.ProjetoAPI.api.assembler.PessoaAssembler;
import br.com.senai.ProjetoAPI.api.model.PessoaDTO;
import br.com.senai.ProjetoAPI.api.model.ProdutoDTO;
import br.com.senai.ProjetoAPI.domain.exception.ProgramaException;
import br.com.senai.ProjetoAPI.domain.model.Perfil;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import br.com.senai.ProjetoAPI.domain.model.RolePessoa;
import br.com.senai.ProjetoAPI.domain.repository.PessoaReporitory;
import br.com.senai.ProjetoAPI.domain.repository.RolePessoasRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {
    private PessoaAssembler pessoaAssembler;
    private PessoaReporitory pessoaReporitory;
    private RolePessoaService rolePessoaService;
    private RolePessoasRepository rolePessoasRepository;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {
        Pessoa pessoaExiste = pessoaReporitory.findByEmail(pessoa.getEmail());
        if (pessoaExiste != null){
            throw new ProgramaException("Esse email ja está cadastrado");
        }
        Pessoa pessoa1 = pessoaReporitory.save(pessoa);
        RolePessoa rolePessoa = new RolePessoa();
        rolePessoa.setPessoa_codigo(pessoa.getCodigo());
        rolePessoa.setRole_nome_role("ROLE_USER");
        rolePessoaService.cadastrar(rolePessoa);
        return pessoa1;
    }

    public List<Pessoa> listar() {
        return pessoaReporitory.findAll();
    }

    public ResponseEntity<PessoaDTO> buscarId(Long pessoaId) {
        return  pessoaReporitory.findById(pessoaId)
                .map(pessoa ->
                        ResponseEntity.ok(pessoaAssembler.toModel(pessoa))
                )
                .orElseThrow(()->new ProgramaException("Pessoa não encontrada."));
    }

    public Pessoa buscar(Long produtoId){
        return pessoaReporitory.findById(produtoId)
                .orElseThrow(() -> new ProgramaException("Pessoa não encontrada."));
    }

    public ResponseEntity<PessoaDTO> editar(Long pessoaId, Pessoa pessoa) {
        if (!pessoaReporitory.existsById(pessoaId)){
            throw new ProgramaException("Pessoa não encontrada");
        }
        if (pessoaReporitory.findByEmail(pessoa.getEmail()) != null){
            throw new ProgramaException("Esse email ja está cadastrado");
        }
        Pessoa pessoa1 = this.buscar(pessoaId);
        pessoa.setCodigo(pessoaId);
        pessoa1 = pessoaReporitory.save(pessoa);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa1));
    }

    public Pessoa editarPerfilAdmin(Long pessoaId) {
        if (!pessoaReporitory.existsById(pessoaId)){
            throw new ProgramaException("Pessoa não encontrada");
        }
        Pessoa pessoa1 = this.buscar(pessoaId);
        pessoa1.setCodigo(pessoaId);
        pessoa1 = pessoaReporitory.save(pessoa1);
        return pessoa1;
    }

    public void deletar(Long pessoaId) {
        if (!pessoaReporitory.existsById(pessoaId)){
            throw new ProgramaException("Pessoa não encontrada");
        }
        pessoaReporitory.deleteById(pessoaId);
    }
}
