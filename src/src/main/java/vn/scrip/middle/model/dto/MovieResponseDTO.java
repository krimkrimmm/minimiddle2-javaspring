package vn.scrip.middle.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.scrip.middle.model.enums.MovieType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
    private Long id;
    private String name;
    private String trailerUrl;
    private String description;
    private Integer releaseYear;
    private MovieType type;
    private Boolean status;
    private String countryName;
}
