package by.bsu.mmf.webservice.touragency.soap.endpoint;

import by.bsu.mmf.webservice.touragency.soap.domain.country.*;

public interface CountryEndpoint {
    GetCountryResponse getCountry(GetCountryRequest getCountryRequest);
    GetCountriesResponse getCountries(GetCountriesRequest getCountriesRequest);
    SaveCountryResponse saveCountry(SaveCountryRequest saveCountryRequest);
    UpdateCountryResponse updateCountry(UpdateCountryRequest updateCountryRequest);
    DeleteCountryResponse deleteCountry(DeleteCountryRequest deleteCountryRequest);
}
