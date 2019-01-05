package by.bsu.mmf.webservice.touragency.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @ApiModelProperty(notes = "Database generated country id")
    private Long id;

    @ApiModelProperty(notes = "Country name")
    private String name;
}
