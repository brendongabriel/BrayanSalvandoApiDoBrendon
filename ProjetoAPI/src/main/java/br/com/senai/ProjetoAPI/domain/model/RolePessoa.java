package br.com.senai.ProjetoAPI.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "role_pessoa")
public class RolePessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Long pessoa_codigo;

    private String role_nome_role;
}
