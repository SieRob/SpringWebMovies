package be.vdab.movies.domain;

import java.time.LocalDateTime;

public class Reservaties {
    private final long klantId;
    private final long filmId;
    private final LocalDateTime reservatie;

    public Reservaties(long klantId, long filmId, LocalDateTime reservatie) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatie = reservatie;
    }

    public Reservaties(long klantId, long filmId) {
        this(klantId, filmId, LocalDateTime.now());
    }

    public long getKlantId() {
        return klantId;
    }

    public long getFilmId() {
        return filmId;
    }

    public LocalDateTime getReservatie() {
        return reservatie;
    }
}
