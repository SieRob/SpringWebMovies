package be.vdab.movies.services;

import be.vdab.movies.domain.Reservaties;
import be.vdab.movies.exceptions.FilmNietGevondenException;
import be.vdab.movies.exceptions.OnvoldoendeVoorraadException;
import be.vdab.movies.repositories.FilmRepository;
import be.vdab.movies.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class ReservatieService {
    private final ReservatieRepository reservatieRepo;
    private final FilmRepository filmRepo;
    public ReservatieService(ReservatieRepository reservatieRepo, FilmRepository filmRepo) {
        this.reservatieRepo = reservatieRepo;
        this.filmRepo = filmRepo;
    }

    public ArrayList<String> nieuweReservatie(long klantid, String mandje) {
        System.out.println(mandje);
        var arrMandje = mandje.split(",");
        var data = new ArrayList<String>();

        for(var item : arrMandje) {
            var filmId = Integer.parseInt(item);

            var reservering = new Reservaties(klantid, filmId);
            var film = filmRepo.findAndLockById(filmId).orElseThrow(() -> new FilmNietGevondenException());

            try {
                film.reserveer();
                filmRepo.updateVoorraad(filmId, film.getVoorraad(), film.getGereserveerd());
                reservatieRepo.nieuweReservatie(reservering);
                data.add(film.getTitel()+": OK");

            }catch(OnvoldoendeVoorraadException e){
                data.add(film.getTitel()+": Uitverkocht");
            }
            catch (FilmNietGevondenException e){
                data.add("Error voor film me Id: " + filmId);
            }
        }
        return data;
    }
}
