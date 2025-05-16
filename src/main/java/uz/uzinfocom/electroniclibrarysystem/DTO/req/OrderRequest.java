package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    Long bookId;
//    Long userId;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
