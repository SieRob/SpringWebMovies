package be.vdab.movies.repositories;

import be.vdab.movies.domain.Klanten;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KlantRepository {
    private final JdbcTemplate template;

    public KlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Klanten> mapper = (rs, rowNum) ->
            new Klanten(rs.getLong("id"),
                    rs.getString("familienaam"),
                    rs.getString("voornaam"),
                    rs.getString("straatNummer"),
                    rs.getString("postcode"),
                    rs.getString("gemeente"));

    public List<Klanten> getKlanten(String naamBevat) {
        var sql = """
                SELECT id, familienaam, voornaam, straatNummer, postcode, gemeente
                FROM klanten
                WHERE familienaam LIKE ?
                ORDER BY familienaam, voornaam
                """;

        System.out.println(sql);
        return template.query(sql, mapper, "%" + naamBevat + "%");

    }

    public Optional<Klanten> getKlantenById(long id) {
        try {
            var sql = """
                SELECT id, familienaam, voornaam, straatNummer, postcode, gemeente
                FROM klanten
                WHERE id = ?
                """;

            return Optional.of(template.queryForObject(sql, mapper, id));
        }catch (IncorrectResultSizeDataAccessException e){
            return Optional.empty();
        }
    }
}
