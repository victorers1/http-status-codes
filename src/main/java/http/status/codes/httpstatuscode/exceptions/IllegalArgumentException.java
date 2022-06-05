package http.status.codes.httpstatuscode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Argumento passado é inválido")
public class IllegalArgumentException extends Exception {

}
