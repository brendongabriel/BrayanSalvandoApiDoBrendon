package br.com.senai.ProjetoAPI.domain.service;

import br.com.senai.ProjetoAPI.api.assembler.ProdutoAssembler;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import br.com.senai.ProjetoAPI.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProdutoService {
    ProdutoRepository produtoRepository;
    ProdutoAssembler produtoAssembler;

    public static void editar(Long produtoId, Produto produto) {
    }

    @Transactional
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }
}
