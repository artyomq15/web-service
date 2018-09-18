package by.bsu.mmf.webservice.touragency.service.impl;

import by.bsu.mmf.webservice.touragency.domain.Country;
import by.bsu.mmf.webservice.touragency.repository.CountryRepository;
import by.bsu.mmf.webservice.touragency.service.CountryService;
import by.bsu.mmf.webservice.touragency.service.exception.ServiceException;
import by.bsu.mmf.webservice.touragency.service.util.CountryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    private CountryValidator countryValidator = new CountryValidator();

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findOne(Long id) {
        if (!countryValidator.isValidId(id)) {
            throw new ServiceException(this.getClass() + " : [findOne] - Invalid ID: " + id);
        }
        return countryRepository.findOne(id);

    }

    @Override
    public Optional<Country> save(Country country) {
        if (!countryValidator.isValidCountry(country)) {
            throw new ServiceException(this.getClass() + " : [save] - Invalid country. id =  " + country.getId());
        }
        if (countryRepository.findByName(country.getName()).isPresent()) {
            throw new ServiceException(this.getClass() + " : [save] - There is country with such name. name =  " + country.getName());
        }
        return countryRepository.save(country);

    }

    @Override
    public Optional<Country> update(Country country) {
        if (!countryValidator.isValidCountry(country)) {
            throw new ServiceException(this.getClass() + " : [update] - Invalid country. id =  " + country.getId());
        }
        if (countryRepository.findByName(country.getName()).isPresent()) {
            throw new ServiceException(this.getClass() + " : [update] - There is country with such name. name =  " + country.getName());
        }
        return countryRepository.update(country);

    }

    @Override
    public Optional<Country> delete(Long id) {
        if (!countryValidator.isValidId(id)) {
            throw new ServiceException(this.getClass() + " : [delete] - Invalid ID: " + id);
        }
        return countryRepository.delete(id);
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

}
