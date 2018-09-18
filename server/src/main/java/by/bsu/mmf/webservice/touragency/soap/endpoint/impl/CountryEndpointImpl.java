package by.bsu.mmf.webservice.touragency.soap.endpoint.impl;

import by.bsu.mmf.webservice.touragency.domain.Country;
import by.bsu.mmf.webservice.touragency.service.CountryService;
import by.bsu.mmf.webservice.touragency.soap.domain.country.*;
import by.bsu.mmf.webservice.touragency.soap.endpoint.CountryEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import java.util.List;

@Endpoint
public class CountryEndpointImpl implements CountryEndpoint {

    private static final String NAMESPACE_URI = "http://touragency.com/Country/";

    private CountryService countryService;

    @Override
    @SoapAction(value = NAMESPACE_URI + "getCountry")
    // @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest getCountryRequest) {
        Country country = this.countryService.findOne(getCountryRequest.getId()).orElse(null);
        GetCountryResponse countryResponse = new GetCountryResponse();
        countryResponse.setCountry(country);
        return countryResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "getCountries")
    // @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountriesRequest")
    @ResponsePayload
    public GetCountriesResponse getCountries(@RequestPayload GetCountriesRequest getCountriesRequest) {
        List<Country> countries = this.countryService.findAll();
        GetCountriesResponse countriesResponse = new GetCountriesResponse();
        countriesResponse.setCountries(countries);
        return countriesResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "saveCountry")
    // @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveCountryRequest")
    @ResponsePayload
    public SaveCountryResponse saveCountry(@RequestPayload SaveCountryRequest saveCountryRequest) {
        Country country = this.countryService.save(new Country(1L, saveCountryRequest.getName())).orElse(null);
        SaveCountryResponse countryResponse = new SaveCountryResponse();
        countryResponse.setCountry(country);
        return countryResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "updateCountry")
    // @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCountryRequest")
    @ResponsePayload
    public UpdateCountryResponse updateCountry(@RequestPayload UpdateCountryRequest updateCountryRequest) {
        System.out.println(updateCountryRequest.getId());
        Country country = this.countryService.update(new Country(updateCountryRequest.getId(), updateCountryRequest.getName())).orElse(null);
        UpdateCountryResponse countryResponse = new UpdateCountryResponse();
        countryResponse.setCountry(country);
        return countryResponse;
    }

    @Override
    @SoapAction(value = NAMESPACE_URI + "deleteCountry")
    // @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCountryRequest")
    @ResponsePayload
    public DeleteCountryResponse deleteCountry(@RequestPayload DeleteCountryRequest deleteCountryRequest) {
        Country country = this.countryService.delete(deleteCountryRequest.getId()).orElse(null);
        DeleteCountryResponse countryResponse = new DeleteCountryResponse();
        countryResponse.setCountry(country);
        return countryResponse;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
