package by.bsu.mmf.webservice.touragency.soap.domain.country;

import by.bsu.mmf.webservice.touragency.domain.Country;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "country" })
@XmlRootElement(name = "deleteCountryResponse")
public class DeleteCountryResponse {
    private Country country;
}
