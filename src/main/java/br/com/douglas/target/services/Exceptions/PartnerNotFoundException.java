package br.com.douglas.target.services.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PartnerNotFoundException extends RuntimeException{

    public PartnerNotFoundException(String s) {
        super(s);
    }

    public PartnerNotFoundException() {
    }
}
