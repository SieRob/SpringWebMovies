package be.vdab.movies.domain;

public class Genre {
    private final long id;
    private final String naam;

    public Genre(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Genre(String naam) {
        this(0, naam);
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
