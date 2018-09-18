
package by.bsu.mmf.webservice.touragency.soap.domain.country;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id" })
@XmlRootElement(name = "getCountryRequest")
public class GetCountryRequest {
    private Long id;
}
