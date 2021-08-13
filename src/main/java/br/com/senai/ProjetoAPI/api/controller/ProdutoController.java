package br.com.senai.ProjetoAPI.api.controller;

import br.com.senai.ProjetoAPI.api.assembler.ProdutoAssembler;
import br.com.senai.ProjetoAPI.api.model.ProdutoDTO;
import br.com.senai.ProjetoAPI.api.model.input.ProdutoInputDTO;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import br.com.senai.ProjetoAPI.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoAssembler produtoAssembler;
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ProdutoDTO cadastrar(@RequestBody ProdutoInputDTO produtoInputDTO){
        Produto novoProduto = produtoAssembler.toEntity(produtoInputDTO);
        novoProduto.setValorTotalEmEstoque(produtoInputDTO.getValorUnitario()*produtoInputDTO.getQuantidade());
        Produto produto = produtoService.cadastrar(novoProduto);
        return produtoAssembler.toModel(produto);
    }

    @PutMapping("/editar/{pessoaId}")
    public ResponseEntity<ProdutoDTO> editar(@Valid @PathVariable Long produtoId,
                                            @RequestBody ProdutoInputDTO produtoInputDTO){
        Produto produto = produtoAssembler.toEntity(produtoInputDTO);
        ProdutoService.editar(produtoId,produto);
        return ResponseEntity.ok(produtoAssembler.toModel(produto));
    }
}
