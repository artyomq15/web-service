package by.bsu.mmf.webservice.touragency.soap.domain.tour;

import by.bsu.mmf.webservice.touragency.domain.Tour;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "tours" })
@XmlRootElement(name = "getToursResponse")
public class GetToursResponse {
    private List<Tour> tours;
}
