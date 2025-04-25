package vn.scrip.middle.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import vn.scrip.middle.model.enums.MovieType;

import java.util.List;

@Data
public class MovieRequestDTO {
    @NotEmpty
    private String name;

    @NotEmpty
    private String trailerUrl;

    @NotEmpty
    private String description;

    @NotNull
    private Integer releaseYear;

    @NotNull
    private MovieType type;

    @NotNull
    private Boolean status;

    @NotNull
    private Long countryId;

    private List<Long> genreIds;
    private List<Long> actorIds;
    private List<Long> directorIds;
}