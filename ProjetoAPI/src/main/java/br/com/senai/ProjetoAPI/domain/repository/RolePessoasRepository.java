package br.com.senai.ProjetoAPI.domain.repository;

import br.com.senai.ProjetoAPI.domain.model.RolePessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePessoasRepository extends JpaRepository<RolePessoa, String> {

}
