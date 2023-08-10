package be.vdab.movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OnvoldoendeVoorraadException extends RuntimeException{
    public OnvoldoendeVoorraadException(String titel) {
        super("onvoldoende voorraad beschikbaar voor " + titel);
    }

    public OnvoldoendeVoorraadException() {
        super("onvoldoende voorraad beschikbaar");
    }
}
