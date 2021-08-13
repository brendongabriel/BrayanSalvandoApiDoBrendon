package br.com.senai.ProjetoAPI.api.controller;

import br.com.senai.ProjetoAPI.api.assembler.PessoaAssembler;
import br.com.senai.ProjetoAPI.domain.model.AuthenticationResponse;
import br.com.senai.ProjetoAPI.api.model.input.PessoaInputLoginDTO;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.security.ImplementsUserDetailsService;
import br.com.senai.ProjetoAPI.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private AuthenticationManager authenticationManager;
    private ImplementsUserDetailsService implementsUserDetailsService;
    private JWTUtil jwtUtil;
    private PessoaAssembler pessoaAssembler;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody PessoaInputLoginDTO pessoaInputLoginDTO) throws  Exception{
        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInputLoginDTO);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(pessoa.getUsername(), pessoa.getPassword())
            );
        }catch (BadCredentialsException ex){
            throw new Exception("Usuario ou senha invalido",ex);
        }

        final UserDetails userDetails = implementsUserDetailsService.loadUserByUsername(
                pessoa.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, pessoaAssembler.toModelLogin(pessoa)));

    }

}