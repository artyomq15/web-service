package by.bsu.mmf.webservice.touragency.service;

import by.bsu.mmf.webservice.touragency.domain.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {
    List<Tour> findAll();
    Optional<Tour> findOne(Long id);
    Optional<Tour> save(Tour tour);
    Optional<Tour> update(Tour tour);
    Optional<Tour> delete(Long id);
    List<Tour> findByCountry(Long id);
}
