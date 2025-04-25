package vn.scrip.middle.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EpisodeRequest {
    @NotEmpty
    private String name;

    @NotNull
    private Integer displayOrder;

    @NotNull
    private Boolean status;

    @NotNull
    private Integer movieId;
}
