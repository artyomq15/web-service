package by.bsu.mmf.webservice.touragency.soap.endpoint.impl;

import by.bsu.mmf.webservice.touragency.domain.Tour;
import by.bsu.mmf.webservice.touragency.service.TourService;
import by.bsu.mmf.webservice.touragency.soap.domain.tour.*;
import by.bsu.mmf.webservice.touragency.soap.endpoint.TourEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import java.time.LocalDate;
import java.util.List;

@Endpoint
public class TourEndpointImpl implements TourEndpoint {

    private static final String NAMESPACE_URI = "http://touragency.com/Tour/";

    private TourService tourService;

    @Override
    @SoapAction(value = NAMESPACE_URI + "getTour")
    @ResponsePayload
    public GetTourResponse getTour(@RequestPayload GetTourRequest getTourRequest) {
        Tour tour = tourService.findOne(getTourRequest.getId()).orElse(null);
        GetTourResponse tourResponse = new GetTourResponse();
        tourResponse.setTour(tour);
        return tourResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "getTours")
    @ResponsePayload
    public GetToursResponse getTours(@RequestPayload GetToursRequest getToursRequest
    ) {
        List<Tour> tours = tourService.findAll();
        GetToursResponse toursResponse = new GetToursResponse();
        toursResponse.setTours(tours);
        return toursResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "getToursByCountry")
    @ResponsePayload
    public GetToursByCountryResponse getToursByCountry(@RequestPayload GetToursByCountryRequest getToursByCountryRequest) {
        List<Tour> tours = tourService.findByCountry(getToursByCountryRequest.getId());
        GetToursByCountryResponse toursResponse = new GetToursByCountryResponse();
        toursResponse.setTours(tours);
        return toursResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "saveTour")
    @ResponsePayload
    public SaveTourResponse saveTour(@RequestPayload SaveTourRequest saveTourRequest) {
        Tour tour = new Tour(
                1L,
                saveTourRequest.getDate(),
                saveTourRequest.getDescription(),
                saveTourRequest.getCost(),
                saveTourRequest.getCountryId()
        );
        tour = tourService.save(tour).orElse(null);
        SaveTourResponse tourResponse = new SaveTourResponse();
        tourResponse.setTour(tour);
        return tourResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "updateTour")
    @ResponsePayload
    public UpdateTourResponse updateTour(@RequestPayload UpdateTourRequest updateTourRequest) {
        Tour tour = new Tour(
                updateTourRequest.getId(),
                updateTourRequest.getDate(),
                updateTourRequest.getDescription(),
                updateTourRequest.getCost(),
                updateTourRequest.getCountryId()
        );
        tour = tourService.update(tour).orElse(null);
        UpdateTourResponse tourResponse = new UpdateTourResponse();
        tourResponse.setTour(tour);
        return tourResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "deleteTour")
    @ResponsePayload
    public DeleteTourResponse deleteTour(@RequestPayload DeleteTourRequest deleteTourRequest) {
        Tour tour = tourService.delete(deleteTourRequest.getId()).orElse(null);
        DeleteTourResponse tourResponse =new DeleteTourResponse();
        tourResponse.setTour(tour);
        return tourResponse;
    }

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }
}
