package by.bsu.mmf.webservice.touragency.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @ApiModelProperty(notes = "Database generated tour id")
    private Long id;
    @ApiModelProperty(notes = "Tour date")
    private String date;
    @ApiModelProperty(notes = "Tour description")
    private String description;
    @ApiModelProperty(notes = "Tour cost")
    private BigDecimal cost;
    @ApiModelProperty(notes = "Country id where tour take place")
    private Long countryId;
}
