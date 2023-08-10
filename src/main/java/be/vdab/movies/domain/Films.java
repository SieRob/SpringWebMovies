package be.vdab.movies.domain;

import be.vdab.movies.exceptions.OnvoldoendeVoorraadException;

import java.math.BigDecimal;

public class Films {
    private final long id;
    private final long genreId;
    private final String titel;
    private int voorraad;
    private int gereserveerd;
    private final BigDecimal prijs;

    public Films(long id, long genreId, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public Films(long genreId, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this(0, genreId, titel, voorraad, gereserveerd, prijs);
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitel() {
        return titel;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void reserveer(){
        if(getVoorraad() > 0) {
            voorraad = voorraad - 1;
            gereserveerd = gereserveerd + 1;
        }
        else{
            throw new OnvoldoendeVoorraadException(getTitel());
        }
    }
}
