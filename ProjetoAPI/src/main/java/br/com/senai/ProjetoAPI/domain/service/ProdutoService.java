package br.com.senai.ProjetoAPI.domain.service;

import br.com.senai.ProjetoAPI.api.assembler.ProdutoAssembler;
import br.com.senai.ProjetoAPI.api.model.ProdutoDTO;
import br.com.senai.ProjetoAPI.domain.exception.ProgramaException;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import br.com.senai.ProjetoAPI.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private ProdutoAssembler produtoAssembler;

    public ResponseEntity<ProdutoDTO> editar(Long produtoId, Produto produto) {
        if (!produtoRepository.existsById(produtoId)){
            throw new ProgramaException("Produto não encontrado");
        }
        Produto produto1 = this.buscar(produtoId);
        produto.setCodigo(produtoId);
        produto.setValorTotalEmEstoque(produto.getValorUnitario()*produto.getQuantidade());
        produto1 = produtoRepository.save(produto);
        return ResponseEntity.ok(produtoAssembler.toModel(produto1));
    }

    @Transactional
    public Produto cadastrar(Produto produto) {
        produto.setValorTotalEmEstoque(produto.getValorUnitario()*produto.getQuantidade());
        return produtoRepository.save(produto);
    }

    public ResponseEntity<ProdutoDTO> buscarId(Long produtoId) {
        return  produtoRepository.findById(produtoId)
                .map(produto ->
                        ResponseEntity.ok(produtoAssembler.toModel(produto))
                )
                .orElseThrow(()->new ProgramaException("Produto não encontrado."));
    }

    public Produto buscar(Long produtoId){
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProgramaException("Produto não encontrado."));
    }


    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @Transactional
    public void deletar(Long produtoId){
        if (produtoRepository.findById(produtoId) != null){
            throw new ProgramaException("Produto não encontrado");
        }
        produtoRepository.deleteById(produtoId );
    }

    public List<ProdutoDTO> procurarNome(String produtoNome) {
        if (produtoAssembler.toCollectionModel(produtoRepository.findByProduto(produtoNome)).size() == 0){
            throw new ProgramaException("Não foi encontrado nenhum produto com esse nome");
        }
        return produtoAssembler.toCollectionModel(produtoRepository.findByProduto(produtoNome));

    }

    public List<ProdutoDTO> procurarContaining(String nomeContaining) {
        if (produtoAssembler.toCollectionModel(produtoRepository.findByNomeContaining(nomeContaining)).size() == 0){
            throw new ProgramaException("Não foi encontrado nenhum produto com esse nome");
        }
        return produtoAssembler.toCollectionModel(produtoRepository.findByNomeContaining(nomeContaining));
    }
}
