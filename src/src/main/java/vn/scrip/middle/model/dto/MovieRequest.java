package vn.scrip.middle.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import vn.scrip.middle.model.enums.MovieType;

import java.util.List;

@Data
public class MovieRequest {
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
    private Integer countryId;

    private List<Integer> genreIds;
    private List<Integer> actorIds;
    private List<Integer> directorIds;
}
