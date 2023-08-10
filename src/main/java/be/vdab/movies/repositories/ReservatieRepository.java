package be.vdab.movies.repositories;

import be.vdab.movies.domain.Reservaties;
import be.vdab.movies.exceptions.FilmNietGevondenException;
import be.vdab.movies.exceptions.OnvoldoendeVoorraadException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class ReservatieRepository {
    private final JdbcTemplate template;

    public ReservatieRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void nieuweReservatie(Reservaties reservering) {
        var sql  = """
                INSERT INTO reservaties(klantId, filmId, reservatie)
                VALUES (?,?,?)
                """;



        if(template.update(sql, reservering.getKlantId(), reservering.getFilmId(), reservering.getReservatie())==0){
            throw new FilmNietGevondenException();
        }
        /*var keyholder = new GeneratedKeyHolder();
        template.update(con -> {
            var stmt = con.prepareStatement(sql);//, PreparedStatement.NO_GENERATED_KEYS);
            stmt.setLong(1, reservering.getKlantId());
            stmt.setLong(2, reservering.getFilmId());
            stmt.setObject(3, reservering.getReservatie());
            return stmt;
        });

        return "OK";
        */
    }
}
