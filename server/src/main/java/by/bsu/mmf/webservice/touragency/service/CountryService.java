package by.bsu.mmf.webservice.touragency.service;

import by.bsu.mmf.webservice.touragency.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findOne(Long id);
    Optional<Country> save(Country country);
    Optional<Country> update(Country country);
    Optional<Country> delete(Long id);
}
