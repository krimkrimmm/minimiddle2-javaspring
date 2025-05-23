package vn.scrip.middle.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEpisodeRequest {
    @NotEmpty(message = "Tên tập phim không được để trống")
    String name;

    @NotNull(message = "Thứ tự tập phim không được để trống")
    Integer displayOrder;

    @NotNull(message = "Trạng thái tập phim không được để trống")
    Boolean status;

    @NotNull(message = "Phim không được để trống")
    Integer movieId;
}