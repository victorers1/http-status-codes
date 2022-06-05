package http.status.codes.httpstatuscode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Não encontrado")
public class ResourceNotFoundException extends Exception {

}
