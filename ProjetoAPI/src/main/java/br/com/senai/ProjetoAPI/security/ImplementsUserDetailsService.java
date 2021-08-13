package br.com.senai.ProjetoAPI.security;

import br.com.senai.ProjetoAPI.domain.exception.ProgramaException;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.repository.PessoaReporitory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    private PessoaReporitory pessoaReporitory;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaReporitory.findByEmail(email);
        if (pessoa  == null){
            throw new ProgramaException("Usuario ou senha inválido");
        }
        return new User(
                pessoa.getUsername(),
                pessoa.getPassword(),
                true,
                true,
                true,
                true,
                pessoa.getAuthorities()
        );
    }
}
