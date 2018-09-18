package by.bsu.mmf.webservice.touragency.soap.domain.country;

import by.bsu.mmf.webservice.touragency.domain.Country;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "name" })
@XmlRootElement(name = "updateCountryRequest")
public class UpdateCountryRequest {
    private Long id;
    private String name;
}

