package br.com.senai.ProjetoAPI.api.assembler;

import br.com.senai.ProjetoAPI.api.model.PessoaDTO;
import br.com.senai.ProjetoAPI.api.model.input.PessoaInputDTO;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.repository.PessoaReporitory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PessoaAssembler {

    private ModelMapper modelMapper;
    private PessoaReporitory pessoaReporitory;

    public Pessoa toEntity(PessoaInputDTO pessoaInputDTO){
        return modelMapper.map(pessoaInputDTO, Pessoa.class);
    }

    public List<PessoaDTO> toCollectionModel(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public PessoaDTO toModel(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

}
