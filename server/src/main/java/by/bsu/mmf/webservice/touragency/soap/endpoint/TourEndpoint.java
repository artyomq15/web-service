package by.bsu.mmf.webservice.touragency.soap.endpoint;

import by.bsu.mmf.webservice.touragency.soap.domain.tour.*;

public interface TourEndpoint {
    GetTourResponse getTour(GetTourRequest getTourRequest);
    GetToursResponse getTours(GetToursRequest getToursRequest);
    GetToursByCountryResponse getToursByCountry(GetToursByCountryRequest getToursByCountryRequest);
    SaveTourResponse saveTour(SaveTourRequest saveTourRequest);
    UpdateTourResponse updateTour(UpdateTourRequest updateTourRequest);
    DeleteTourResponse deleteTour(DeleteTourRequest deleteTourRequest);
}
