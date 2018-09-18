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
@XmlType(name = "", propOrder = { "id", "date", "description", "cost", "countryId" })
@XmlRootElement(name = "updateTourRequest")
public class UpdateTourRequest {
    private Long id;
    private LocalDate date;
    private String description;
    private BigDecimal cost;
    private Long countryId;
}
