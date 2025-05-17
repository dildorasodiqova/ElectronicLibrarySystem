package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingRequest {

    @NotNull(message = "User ID is required")
    Long userId;

    @NotNull(message = "Book ID is required")
    Long bookId;
    @Min(1)
    @Max(5)
    int stars;
}
