package by.bsu.mmf.webservice.touragency.controller;

import by.bsu.mmf.webservice.touragency.domain.Tour;
import by.bsu.mmf.webservice.touragency.service.TourService;
import by.bsu.mmf.webservice.touragency.service.exception.ServiceException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tours")
public class TourController {

    private TourService tourService;

    @ApiOperation(value = "Get list of all tours")
    @GetMapping()
    public ResponseEntity<List<Tour>> getAll() {
        try {
            return new ResponseEntity<>(tourService.findAll(), HttpStatus.OK);
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @ApiOperation(value = "Get tour by id")
    @GetMapping("{id}")
    public ResponseEntity<Tour> get(@PathVariable Long id) {
        try {
            Optional<Tour> tour = tourService.findOne(id);
            return tour.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @ApiOperation(value = "Add new tour")
    @PostMapping()
    public ResponseEntity add(@RequestBody Tour tour) {
        try {
            Optional<Tour> savedTour = tourService.save(tour);
            return savedTour.map(t -> new ResponseEntity<>(HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @ApiOperation(value = "Update tour")
    @PutMapping()
    public ResponseEntity update(@RequestBody Tour tour) {
        try {
            Optional<Tour> updatedTour = tourService.update(tour);
            return updatedTour.map(t -> new ResponseEntity<>(HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @ApiOperation(value = "Delete tour by id")
    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        try {
            Optional<Tour> tour = tourService.delete(id);
            return tour.map(t -> new ResponseEntity<>(HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }
}
