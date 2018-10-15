package by.bsu.mmf.webservice.touragency.controller;

import by.bsu.mmf.webservice.touragency.domain.Country;
import by.bsu.mmf.webservice.touragency.service.CountryService;
import by.bsu.mmf.webservice.touragency.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private CountryService countryService;

    @GetMapping()
    public ResponseEntity<List<Country>> getAll() {
        try {
            return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Country> get(@PathVariable Long id) {
        try {
            Optional<Country> country = countryService.findOne(id);
            return country.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody Country country) {
        try {
            Optional<Country> savedCountry = countryService.save(country);
            return savedCountry.map(c -> new ResponseEntity<>(HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Country country) {
        try {
            Optional<Country> updatedCountry = countryService.update(country);
            return updatedCountry.map(c -> new ResponseEntity<>(HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        try {
            Optional<Country> country = countryService.delete(id);
            return country.map(c -> new ResponseEntity<>(HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
