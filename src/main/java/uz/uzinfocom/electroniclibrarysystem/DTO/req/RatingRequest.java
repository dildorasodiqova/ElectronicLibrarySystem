package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingRequest {
    //     Long userId;
    Long bookId;
    int stars; // 0 to 5
}
