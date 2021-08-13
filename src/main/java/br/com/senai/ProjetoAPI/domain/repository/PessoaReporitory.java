package br.com.senai.ProjetoAPI.domain.repository;

import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaReporitory extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNome(String nome);



}
