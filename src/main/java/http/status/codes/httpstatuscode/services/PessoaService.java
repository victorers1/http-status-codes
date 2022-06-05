package http.status.codes.httpstatuscode.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import http.status.codes.httpstatuscode.domain.Pessoa;
import http.status.codes.httpstatuscode.dto.PessoaDTO;
import http.status.codes.httpstatuscode.exceptions.DataNotValidException;
import http.status.codes.httpstatuscode.exceptions.ResourceNotFoundException;
import http.status.codes.httpstatuscode.exceptions.IllegalArgumentException;
import http.status.codes.httpstatuscode.exceptions.ResourceAlreadyExistsException;
import http.status.codes.httpstatuscode.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaDTO createPessoa(PessoaDTO pessoaDTO) throws ResourceAlreadyExistsException {
        if (pessoaRepository.countByNome(pessoaDTO.getNome()) > 0) {
            throw new ResourceAlreadyExistsException();
        }

        Pessoa pessoa = new Pessoa(pessoaDTO.getId(), pessoaDTO.getNome());
        Pessoa novaPessoa = pessoaRepository.save(pessoa);
        PessoaDTO novaPessoaDTO = new PessoaDTO(novaPessoa);
        return novaPessoaDTO;
    }

    /**
     *
     * @param argumento parâmetro do tipo String ou Long
     * @return DTO de uma pessoa
     * @throws ResourceNotFoundException
     * @throws DataNotValidException
     * @throws IllegalArgumentException
     */
    public PessoaDTO getPessoa(Object argumento)
            throws ResourceNotFoundException, DataNotValidException, IllegalArgumentException {

        Optional<Pessoa> pessoa = Optional.empty();

        if (argumento.getClass() == String.class) {
            pessoa = pessoaRepository.findByNome(argumento.toString());
        } else if (argumento.getClass() == Long.class) {
            pessoa = pessoaRepository.findById(Long.valueOf(argumento.toString()));
        } else {
            throw new IllegalArgumentException();
        }

        if (pessoa.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        if (!validarPessoaEntity(pessoa.get())) {
            throw new DataNotValidException();
        }

        final PessoaDTO pessoaDTO = new PessoaDTO(pessoa.get());

        return pessoaDTO;
    }

    public Optional<PessoaDTO> getPessoaSeExiste(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (pessoa.isPresent()) {
            Pessoa p = pessoa.get();
            PessoaDTO pDTO = new PessoaDTO(p);
            return Optional.of(pDTO);
        }

        return Optional.empty();
    }

    public void deletePessoa(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        pessoaRepository.deleteById(id);
    }

    boolean validarPessoaEntity(Pessoa pessoa) {
        return pessoa.getId() != null &&
                pessoa.getNome() != null &&
                !pessoa.getNome().isEmpty();

    }
}
