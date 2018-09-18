package by.bsu.mmf.webservice.touragency.soap.domain.tour;

import by.bsu.mmf.webservice.touragency.domain.Tour;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "tour" })
@XmlRootElement(name = "updateTourResponse")
public class UpdateTourResponse {
    private Tour tour;
}
