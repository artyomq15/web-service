package by.bsu.mmf.webservice.touragency.repository.impl.jdbc;

import by.bsu.mmf.webservice.touragency.domain.Tour;
import by.bsu.mmf.webservice.touragency.repository.TourRepository;
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

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TourRepositoryJDBCImpl implements TourRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TourRepositoryJDBCImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tour> findAll() {
        try {
            return jdbcTemplate.query(Query.SELECT_ALL_TOURS, new TourMapper());
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [findAll] - Failed.", e);
        }

    }

    @Override
    public Optional<Tour> findOne(Long id) {

        try {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource(Query.TOUR_ID, id);
            Tour tour = jdbcTemplate.queryForObject(Query.SELECT_TOUR_BY_ID, sqlParameterSource, new TourMapper());

            return Optional.of(tour);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [findOne] - Failed.", e);
        }

    }

    @Override
    public Optional<Tour> save(Tour tour) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(Query.TOUR_DATE, Date.valueOf(tour.getDate()))
                .addValue(Query.TOUR_DESCRIPTION, tour.getDescription())
                .addValue(Query.TOUR_COST, tour.getCost())
                .addValue(Query.COUNTRY_ID, tour.getCountryId());

        try {

            jdbcTemplate.update(Query.INSERT_TOUR, sqlParameterSource, keyHolder, new String[]{Query.TOUR_ID});
            tour.setId(keyHolder.getKey().longValue());

            return Optional.of(tour);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [save] - Failed.", e);
        }

    }

    @Override
    public Optional<Tour> update(Tour tour) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(Query.TOUR_DATE, Date.valueOf(tour.getDate()))
                .addValue(Query.TOUR_DESCRIPTION, tour.getDescription())
                .addValue(Query.TOUR_COST, tour.getCost())
                .addValue(Query.TOUR_ID, tour.getId())
                .addValue(Query.COUNTRY_ID, tour.getCountryId());

        try {

            jdbcTemplate.update(Query.UPDATE_TOUR, sqlParameterSource);

            return Optional.of(tour);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [update] - Failed.", e);
        }

    }

    @Override
    public Optional<Tour> delete(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(Query.TOUR_ID, id);

        try {

            Optional<Tour> country = findOne(id);
            country.ifPresent(c ->
                    jdbcTemplate.update(Query.DELETE_TOUR, sqlParameterSource)
            );
            return country;

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [delete] - Failed.", e);
        }

    }

    @Override
    public List<Tour> findByCountry(Long id) {
        try {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource(Query.COUNTRY_ID, id);

            return jdbcTemplate.query(Query.SELECT_TOUR_BY_COUNTRY_ID, sqlParameterSource, new TourMapper());

        } catch (DataAccessException e) {
            throw new RepositoryException(this.getClass() + " : [findByCountry] - Failed.", e);
        }
    }

    private static class TourMapper implements RowMapper<Tour> {

        @Override
        public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Tour(
                    rs.getLong(Query.TOUR_ID),
                    rs.getDate(Query.TOUR_DATE).toLocalDate(),
                    rs.getString(Query.TOUR_DESCRIPTION),
                    rs.getBigDecimal(Query.TOUR_COST),
                    rs.getLong(Query.COUNTRY_ID)
            );
        }

    }

    private static class Query {

        private static final String TOUR_ID = "tour_id";

        private static final String TOUR_DATE = "tour_date";

        private static final String TOUR_DESCRIPTION = "tour_description";

        private static final String TOUR_COST = "tour_cost";

        private static final String COUNTRY_ID = "country_id";

        private static final String SELECT_ALL_TOURS = "SELECT " +
                "   tour.tour_id, " +
                "   tour.tour_date, " +
                "   tour.tour_description, " +
                "   tour.tour_cost, " +
                "   tour.country_id, " +
                "FROM tour;";

        private static final String INSERT_TOUR = "INSERT INTO tour (tour_date, tour_description, tour_cost, country_id) VALUES ( :tour_date, :tour_description, :tour_cost, :country_id);";

        private static final String SELECT_TOUR_BY_ID = "SELECT " +
                "   tour.tour_id, " +
                "   tour.tour_date, " +
                "   tour.tour_description, " +
                "   tour.tour_cost, " +
                "   tour.country_id, " +
                "FROM tour " +
                "WHERE tour.tour_id = :tour_id; ";

        private static final String SELECT_TOUR_BY_COUNTRY_ID = "SELECT " +
                "   tour.tour_id, " +
                "   tour.tour_date, " +
                "   tour.tour_description, " +
                "   tour.tour_cost, " +
                "   tour.country_id, " +
                "FROM tour " +
                "WHERE tour.country_id = :country_id; ";

        private static final String DELETE_TOUR = "DELETE FROM tour WHERE tour_id = :tour_id;";

        private static final String UPDATE_TOUR = "UPDATE tour SET tour_date = :tour_date , tour_description = :tour_description , tour_cost = :tour_cost, country_id = :country_id WHERE tour_id = :tour_id ;";

    }

}
