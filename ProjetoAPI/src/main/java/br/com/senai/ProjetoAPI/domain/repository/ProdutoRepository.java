package br.com.senai.ProjetoAPI.domain.repository;

import br.com.senai.ProjetoAPI.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    @Query("SELECT p FROM Produto p WHERE p.produto = ?1")
    List<Produto> findByProduto(String produto);
}
