package http.status.codes.httpstatuscode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Item já existe")
public class ResourceAlreadyExistsException extends Exception {

}
