package vn.scrip.middle.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EpisodeDTO {

    @NotEmpty
    private String name;

    @NotNull
    private Integer displayOrder;

    @NotNull
    private Boolean status;

    @NotNull
    private Long movieId;
}
