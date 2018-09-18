package by.bsu.mmf.webservice.touragency.soap.domain.country;

import by.bsu.mmf.webservice.touragency.domain.Country;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "country" })
@XmlRootElement(name = "updateCountryResponse")
public class UpdateCountryResponse {
    private Country country;
}
