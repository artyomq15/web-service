package by.bsu.mmf.webservice.touragency.repository.impl.jdbc;

import by.bsu.mmf.webservice.touragency.domain.Country;
import by.bsu.mmf.webservice.touragency.repository.CountryRepository;
import by.bsu.mmf.webservice.touragency.repository.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepositoryJDBCImpl implements CountryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CountryRepositoryJDBCImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> findAll() {
        try {
            return jdbcTemplate.query(Query.SELECT_ALL_COUNTRIES, new CountryMapper());
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [findAll] - Failed.", e);
        }
    }

    @Override
    public Optional<Country> findOne(Long id) {
        try {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource(Query.COUNTRY_ID, id);
            Country c = jdbcTemplate.queryForObject(Query.SELECT_COUNTRY_BY_ID, sqlParameterSource, new CountryMapper());
            return Optional.of(c);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [findOne] - Failed.", e);
        }

    }

    @Override
    public Optional<Country> findByName(String name) {
        try {

            SqlParameterSource sqlParameterSource = new MapSqlParameterSource(Query.COUNTRY_NAME, name);
            Country c = jdbcTemplate.queryForObject(Query.SELECT_COUNTRY_BY_NAME, sqlParameterSource, new CountryMapper());
            return Optional.of(c);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [findByName] - Failed.", e);
        }

    }

    @Override
    public Optional<Country> save(Country country) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(Query.COUNTRY_NAME, country.getName());

        try {

            jdbcTemplate.update(Query.INSERT_COUNTRY, sqlParameterSource, keyHolder, new String[]{Query.COUNTRY_ID});

            country.setId(keyHolder.getKey().longValue());

            return Optional.of(country);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [save] - Failed.", e);
        }

    }

    @Override
    public Optional<Country> update(Country country) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(Query.COUNTRY_ID, country.getId())
                .addValue(Query.COUNTRY_NAME, country.getName());

        try {

            jdbcTemplate.update(Query.UPDATE_COUNTRY, sqlParameterSource);

            return Optional.of(country);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [update] - Failed.", e);
        }

    }

    @Override
    public Optional<Country> delete(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(Query.COUNTRY_ID, id);

        try {
            Optional<Country> country = findOne(id);
            country.ifPresent(c ->
                    jdbcTemplate.update(Query.DELETE_COUNTRY, sqlParameterSource)
            );
            return country;

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [delete] - Failed.", e);
        }

    }

    private static final class CountryMapper implements RowMapper<Country> {

        @Override
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Country(rs.getLong(Query.COUNTRY_ID), rs.getString(Query.COUNTRY_NAME));
        }

    }

    private static class Query {

        private static final String COUNTRY_ID = "country_id";

        private static final String COUNTRY_NAME = "country_name";

        private static final String SELECT_ALL_COUNTRIES = "SELECT country_id, country_name FROM country;";

        private static final String SELECT_COUNTRY_BY_ID = "SELECT country_id, country_name FROM country WHERE country_id = :country_id;";

        private static final String SELECT_COUNTRY_BY_NAME = "SELECT country_id, country_name FROM country WHERE country_name = :country_name;";

        private static final String INSERT_COUNTRY = "INSERT INTO country (country_name) VALUES (:country_name)";

        private static final String UPDATE_COUNTRY = "UPDATE country SET country_name = :country_name WHERE country_id = :country_id;";

        private static final String DELETE_COUNTRY = "DELETE FROM country WHERE country_id = :country_id;";

    }


}
