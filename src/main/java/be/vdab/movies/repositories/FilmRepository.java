package be.vdab.movies.repositories;

import be.vdab.movies.domain.Films;
import be.vdab.movies.exceptions.OnvoldoendeVoorraadException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;

@Repository
public class FilmRepository {

    private final JdbcTemplate template;

    public FilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Films> mapper = (rs, rowNum) ->
            new Films(rs.getLong("id"),
                    rs.getLong("genreId"),
                    rs.getString("titel"),
                    rs.getInt("voorraad"),
                    rs.getInt("gereserveerd"),
                    rs.getBigDecimal("prijs"));

    public Optional<Films> getById(long id) {
        try{
            var sql = """
                SELECT id, genreId, titel, voorraad, gereserveerd, prijs
                FROM films
                WHERE id = ?
                """;

            return Optional.of(template.queryForObject(sql, mapper, id));
        }
        catch (IncorrectResultSizeDataAccessException e){
            return Optional.empty();
        }

    }

    public List<Films> getMandjeItems(String mandje) {
        System.out.println(mandje);
        if(!mandje.isBlank()) {
            var arrMandje = Arrays.stream(mandje.split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

            var list = Arrays.stream(arrMandje).boxed().toList();

            var sql = """
                SELECT id, genreId, titel, voorraad, gereserveerd, prijs
                FROM films
                WHERE id IN (%s)
                """;

            String idList = String.join("," , Collections.nCopies(list.size(),"?"));

            return template.query(String.format(sql, idList), mapper, list.toArray());
        }else {
            return List.of();
        }
    }

    public Optional<Films> findAndLockById(long id){
        try{
            var sql = """
                    SELECT id, genreId, titel, voorraad, gereserveerd, prijs
                    FROM films
                    WHERE id = ?
                    FOR UPDATE
                    """;

            return Optional.of(template.queryForObject(sql,mapper, id));
        }catch (IncorrectResultSizeDataAccessException e){
            return Optional.empty();
        }
    }

    public void updateVoorraad(int id, int voorraad, int gereserveerd) {
        var sql= """
                UPDATE films
                SET voorraad = ?, gereserveerd = ?
                WHERE id = ?
                """;
        if(template.update(sql, voorraad, gereserveerd, id)==0){
            throw new OnvoldoendeVoorraadException();
        }
    }
}