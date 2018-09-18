package by.bsu.mmf.webservice.touragency.soap.domain.country;

import by.bsu.mmf.webservice.touragency.domain.Country;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "countries" })
@XmlRootElement(name = "getCountriesResponse")
public class GetCountriesResponse {
    private List<Country> countries;
}
