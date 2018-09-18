package by.bsu.mmf.webservice.touragency.service.impl;

import by.bsu.mmf.webservice.touragency.domain.Tour;
import by.bsu.mmf.webservice.touragency.repository.TourRepository;
import by.bsu.mmf.webservice.touragency.service.TourService;
import by.bsu.mmf.webservice.touragency.service.exception.ServiceException;
import by.bsu.mmf.webservice.touragency.service.util.TourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    private TourRepository tourRepository;

    private TourValidator tourValidator = new TourValidator();

    @Override
    public List<Tour> findAll() {
        return this.tourRepository.findAll();
    }

    @Override
    public Optional<Tour> findOne(Long id) {
        if (!tourValidator.isValidId(id)) {
            throw new ServiceException(this.getClass() + " : [findOne] - Invalid ID: " + id);
        }
        return tourRepository.findOne(id);

    }

    @Override
    public Optional<Tour> save(Tour tour) {
        if (!tourValidator.isValidTour(tour)) {
            throw new ServiceException(this.getClass() + " : [save] - Invalid tour. id =  " + tour.getId());
        }
        return tourRepository.save(tour);

    }

    @Override
    public Optional<Tour> update(Tour tour) {
        if (!tourValidator.isValidTour(tour)) {
            throw new ServiceException(this.getClass() + " : [update] - Invalid tour. id =  " + tour.getId());
        }
        return tourRepository.update(tour);

    }

    @Override
    public Optional<Tour> delete(Long id) {
        if (!tourValidator.isValidId(id)) {
            throw new ServiceException(this.getClass() + " : [delete] - Invalid ID: " + id);
        }
        return tourRepository.delete(id);

    }

    @Override
    public List<Tour> findByCountry(Long id) {
        if (!tourValidator.isValidId(id)) {
            throw new ServiceException(this.getClass() + " : [findByCountry] - Invalid ID: " + id);
        }
        return tourRepository.findByCountry(id);
    }

    @Autowired
    public void setTourRepository(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }
}
