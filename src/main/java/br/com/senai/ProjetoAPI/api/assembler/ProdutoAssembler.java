package br.com.senai.ProjetoAPI.api.assembler;

import br.com.senai.ProjetoAPI.api.model.PessoaDTO;
import br.com.senai.ProjetoAPI.api.model.ProdutoDTO;
import br.com.senai.ProjetoAPI.api.model.input.PessoaInputDTO;
import br.com.senai.ProjetoAPI.api.model.input.ProdutoInputDTO;
import br.com.senai.ProjetoAPI.domain.model.Pessoa;
import br.com.senai.ProjetoAPI.domain.model.Produto;
import br.com.senai.ProjetoAPI.domain.repository.PessoaReporitory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProdutoAssembler {
    private ModelMapper modelMapper;
    private PessoaReporitory pessoaReporitory;

    public Produto toEntity(ProdutoInputDTO produtoInputDTO){
        return modelMapper.map(produtoInputDTO, Produto.class);
    }

    public List<ProdutoDTO> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public ProdutoDTO toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }


}
