package by.bsu.mmf.webservice.touragency.soap.endpoint.impl;

import by.bsu.mmf.webservice.touragency.domain.Country;
import by.bsu.mmf.webservice.touragency.service.CountryService;
import by.bsu.mmf.webservice.touragency.soap.domain.country.GetCountryRequest;
import by.bsu.mmf.webservice.touragency.soap.domain.country.GetCountryResponse;
import by.bsu.mmf.webservice.touragency.soap.endpoint.CountryEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpointImpl implements CountryEndpoint {

    private static final String NAMESPACE_URI = "http://touragency.com";

    private CountryService countryService;

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest getCountryRequest) {
        Country country = this.countryService.findOne(getCountryRequest.getId()).orElse(null);
        GetCountryResponse countryResponse = new GetCountryResponse();
        countryResponse.setCountry(country);
        return countryResponse;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
