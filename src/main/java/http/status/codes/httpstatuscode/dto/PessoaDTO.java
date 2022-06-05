package http.status.codes.httpstatuscode.dto;

import http.status.codes.httpstatuscode.domain.Pessoa;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PessoaDTO {
    final Long id;
    final String nome; // TODO: adicionar validação

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
    }
}
