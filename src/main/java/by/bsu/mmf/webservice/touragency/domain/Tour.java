package by.bsu.mmf.webservice.touragency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    private Long id;
    private LocalDate date;
    private String description;
    private BigDecimal cost;
    private Long countryId;
}
