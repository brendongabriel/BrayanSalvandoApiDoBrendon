package br.com.senai.ProjetoAPI.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Setter
@Entity
@Getter
public class Role implements GrantedAuthority {

    @Id
    private String nomeRole;

    @ManyToMany
    private List<Pessoa> pessoas;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
