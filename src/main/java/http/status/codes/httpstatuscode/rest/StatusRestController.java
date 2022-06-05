package http.status.codes.httpstatuscode.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusRestController {

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public String getServerStatus() {
        return "I'm OK";
    }
}
