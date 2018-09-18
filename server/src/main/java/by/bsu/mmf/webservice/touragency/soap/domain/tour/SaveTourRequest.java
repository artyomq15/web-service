package by.bsu.mmf.webservice.touragency.soap.domain.tour;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "date", "description", "cost", "countryId" })
@XmlRootElement(name = "saveTourRequest")
public class SaveTourRequest {
    private String date;
    private String description;
    private BigDecimal cost;
    private Long countryId;
}
