package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    String bookTitle;
    String userFullName;
    LocalDateTime startDate;
    LocalDateTime endDate;
    LocalDateTime returnedDate;
    String status;
    Integer fineAmount;
}
