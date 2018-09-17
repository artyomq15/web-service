package by.bsu.mmf.webservice.touragency.soap.endpoint;

import by.bsu.mmf.webservice.touragency.soap.domain.country.GetCountryRequest;
import by.bsu.mmf.webservice.touragency.soap.domain.country.GetCountryResponse;

public interface CountryEndpoint {
    GetCountryResponse getCountry(GetCountryRequest getCountryRequest);
    GetCountriesResponse getCountries(GetCountriesRequest getCountriesRequest);
}
