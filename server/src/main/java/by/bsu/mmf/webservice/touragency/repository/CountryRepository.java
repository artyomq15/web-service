package by.bsu.mmf.webservice.touragency.repository;

import by.bsu.mmf.webservice.touragency.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    List<Country> findAll();
    Optional<Country> findOne(Long id);
    Optional<Country> findByName(String name);
    Optional<Country> save(Country country);
    Optional<Country> update(Country country);
    Optional<Country> delete(Long id);
}
