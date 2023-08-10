package be.vdab.movies.repositories;

import be.vdab.movies.domain.Genre;
import be.vdab.movies.dto.GenreEnFilms;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository {

    private final JdbcTemplate template;

    private final RowMapper<Genre> mapper = (rs, rowNum) ->
            new Genre(rs.getLong("id"),
                    rs.getString("naam"));
    public GenreRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Genre> findAll(){
        var sql= """
                SELECT id, naam
                FROM genres
                ORDER BY naam
                """;

        return template.query(sql, mapper);
    }

    public Optional<List<GenreEnFilms>> findById(long id) {
        try {
            var sql= """
                    SELECT g.id as genreId, g.naam, f.id as filmId, f.titel
                    FROM genres g
                    LEFT JOIN films f on g.id = f.genreId
                    WHERE g.id = ?
                    ORDER BY f.titel
                    """;

            RowMapper<GenreEnFilms> filmMapper = (rs, rowNum) ->
                    new GenreEnFilms(rs.getLong("genreId"),
                            rs.getString("naam"),
                            rs.getLong("filmId"),
                            rs.getString("titel"));

            return Optional.of(template.query(sql, filmMapper, id));

        }catch (IncorrectResultSizeDataAccessException e){
            return Optional.empty();
        }
    }
}
