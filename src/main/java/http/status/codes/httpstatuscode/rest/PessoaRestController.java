package http.status.codes.httpstatuscode.rest;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import http.status.codes.httpstatuscode.dto.PessoaDTO;
import http.status.codes.httpstatuscode.exceptions.DataNotValidException;
import http.status.codes.httpstatuscode.exceptions.IllegalArgumentException;
import http.status.codes.httpstatuscode.exceptions.ResourceAlreadyExistsException;
import http.status.codes.httpstatuscode.exceptions.ResourceNotFoundException;
import http.status.codes.httpstatuscode.services.PessoaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
public class PessoaRestController {

    private final PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PessoaDTO createPessoa(@RequestBody PessoaDTO pessoaDTO) throws ResourceAlreadyExistsException {
        return pessoaService.createPessoa(pessoaDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PessoaDTO getPessoa(@PathVariable("id") Long id)
            throws ResourceNotFoundException, DataNotValidException, IllegalArgumentException {
        PessoaDTO pessoaDTO = pessoaService.getPessoa(id);
        return pessoaDTO;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PessoaDTO getPessoa(@RequestParam("nome") String nome)
            throws ResourceNotFoundException, DataNotValidException, IllegalArgumentException {
        PessoaDTO pessoaDTO = pessoaService.getPessoa(nome);
        return pessoaDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePessoa(@PathVariable("id") Long id) throws IllegalArgumentException {
        pessoaService.deletePessoa(id);
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(code = HttpStatus.MOVED_PERMANENTLY)
    public String getPessoaByNomeOld(@PathVariable String nome) {
        // Não redireciona
        return "Endpoint correto é 'http://localhost:8080/api/pessoa?nome=" + nome + "'";
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(code = HttpStatus.MOVED_PERMANENTLY)
    public String getPessoaByIdOld(HttpServletResponse response, @PathVariable("id") Long id) {
        // Automaticamente redireciona usuário para link especificado
        response.addHeader("Location", "http://localhost:8080/api/pessoa/" + id);
        return "Endpoint correto é '/pessoa/" + id + "'";
    }

    @GetMapping("/{id}/existente")
    public ResponseEntity<PessoaDTO> getPessoaIfModified(@PathVariable("id") Long id) {
        Optional<PessoaDTO> pessoaDTO = pessoaService.getPessoaSeExiste(id);
        if (pessoaDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/cpf")
    public String getPessoaCPF(@PathVariable("id") Long id) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para acessar isso");
    }

    @GetMapping("/{id}/idade")
    public String getIdadePessoa(@PathVariable("id") Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Rescurso não existe atualmente");
    }

}
