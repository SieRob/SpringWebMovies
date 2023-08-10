package be.vdab.movies.services;

import be.vdab.movies.domain.Klanten;
import be.vdab.movies.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class KlantService {
    private final KlantRepository klantRepo;

    public KlantService(KlantRepository klantRepo) {
        this.klantRepo = klantRepo;
    }

    public List<Klanten> getKlanten(String naamBevat) {
        return klantRepo.getKlanten(naamBevat);
    }

    public Optional<Klanten> getKlantById(long id) {
        return klantRepo.getKlantenById(id);
    }
}
