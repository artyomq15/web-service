package by.bsu.mmf.webservice.touragency.service.util;

import by.bsu.mmf.webservice.touragency.domain.Tour;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourValidator extends Validator {

    public boolean isValidDate(String date) {
        return date != null;
    }

    public boolean isValidCost(BigDecimal cost) {
        return cost != null && cost.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isValidTour(Tour tour) {
        return tour != null
                && isValidId(tour.getId())
                && isValidDate(tour.getDate())
                && isValidCost(tour.getCost())
                && isValidId(tour.getCountryId());
    }

}
