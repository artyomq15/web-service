package by.bsu.mmf.webservice.touragency.soap.domain.country;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "countries" })
@XmlRootElement(name = "getCountriesResponse")
public class GetCountriesResponse {
    private List<Country> countries;
}
