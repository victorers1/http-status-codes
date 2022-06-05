package http.status.codes.httpstatuscode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Dados estão corrompidos")
public class DataNotValidException extends Exception {

}
