package vn.scrip.middle.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EpisodeRequestDTO {
    @NotEmpty
    private String name;

    @NotNull
    private Integer displayOrder;

    @NotNull
    private Boolean status;

    @NotNull
    private Long movieId;
}