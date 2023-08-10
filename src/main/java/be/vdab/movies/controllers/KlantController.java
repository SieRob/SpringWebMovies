package be.vdab.movies.controllers;

import be.vdab.movies.domain.Klanten;
import be.vdab.movies.exceptions.KlantNietgevondenException;
import be.vdab.movies.services.KlantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("klanten")
public class KlantController {
    private final KlantService klantService;

    private record KlantNaam (String familienaam, String voornaam){
        KlantNaam (Klanten klanten){this(klanten.getFamilienaam(), klanten.getVoornaam());

        }
    }
    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping(params = "naamBevat")
    List<Klanten> getKlanten(String naamBevat){
        return klantService.getKlanten(naamBevat);
    }

    @GetMapping("{id}")
    KlantNaam getKlantById(@PathVariable long id){
        return klantService.getKlantById(id)
                .map(klant -> new KlantNaam(klant))
                .orElseThrow(() -> new KlantNietgevondenException(id));
    }
}
