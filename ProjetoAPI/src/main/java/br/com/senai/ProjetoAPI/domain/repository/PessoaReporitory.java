package br.com.senai.ProjetoAPI.domain.repository;

import br.com.senai.ProjetoAPI.domain.model.Perfil;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaReporitory extends JpaRepository<Pessoa, Long> {

    @Query("SELECT u FROM Pessoa u WHERE u.email = ?1")
    Pessoa findByEmail(String email);

    @Query("SELECT p FROM Pessoa p WHERE p.codigo = ?1")
    List<Pessoa> findByPessoa(String codigo);

}
