package be.vdab.movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KlantNietgevondenException extends RuntimeException {
    public KlantNietgevondenException(long id) {
        super("Geen data gevonden voor klantid: " + id);
    }
}
