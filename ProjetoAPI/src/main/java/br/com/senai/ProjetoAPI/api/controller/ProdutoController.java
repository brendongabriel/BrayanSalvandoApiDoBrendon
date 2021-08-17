package br.com.senai.ProjetoAPI.api.controller;

import br.com.senai.ProjetoAPI.api.assembler.ProdutoAssembler;
import br.com.senai.ProjetoAPI.api.model.ProdutoDTO;
import br.com.senai.ProjetoAPI.api.model.input.ProdutoInputDTO;
import br.com.senai.ProjetoAPI.domain.exception.ProgramaException;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import br.com.senai.ProjetoAPI.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoAssembler produtoAssembler;
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ProdutoDTO cadastrar(@RequestBody ProdutoInputDTO produtoInputDTO){
        Produto novoProduto = produtoAssembler.toEntity(produtoInputDTO);
        Produto produto = produtoService.cadastrar(novoProduto);
        return produtoAssembler.toModel(produto);
    }

    @PutMapping("/editar/{produtoId}")
    public ResponseEntity<ProdutoDTO> editar(@Valid @PathVariable Long produtoId,
                                            @RequestBody ProdutoInputDTO produtoInputDTO){
        Produto produto = produtoAssembler.toEntity(produtoInputDTO);
        produtoService.editar(produtoId,produto);
        return ResponseEntity.ok(produtoAssembler.toModel(produto));
    }

    @GetMapping("/buscar/{produtoId}")
    public ResponseEntity<ProdutoDTO> buscarId(@PathVariable Long produtoId){
        return produtoService.buscarId(produtoId);
    }

    @GetMapping("procurar/{produtoNome}")
    public List<ProdutoDTO> procurarNome(@PathVariable String produtoNome){
        return produtoService.procurarNome(produtoNome);
    }

    @GetMapping("/procurar/containing/{nomeContaining}")
    public List<ProdutoDTO> listarNomeContaining(@PathVariable String nomeContaining){
        return produtoService.procurarContaining(nomeContaining);
    }

    @GetMapping("/listar")
    public List<ProdutoDTO> listar(){
        return produtoAssembler.toCollectionModel(produtoService.listar());
    }

    @DeleteMapping("/deletar/{produtoId}")
    public void deletar(@PathVariable Long produtoId){
        produtoService.deletar(produtoId);
    }

}
