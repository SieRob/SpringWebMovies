package be.vdab.movies.services;

import be.vdab.movies.domain.Films;
import be.vdab.movies.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FilmService {

    private final FilmRepository filmRepo;

    public FilmService(FilmRepository filmRepo) {
        this.filmRepo = filmRepo;
    }

    public Optional<Films> getById(long id) {
        return filmRepo.getById(id);
    }

    public List<Films> getMandjeItems(String mandje) {
        return filmRepo.getMandjeItems(mandje);
    }
}
