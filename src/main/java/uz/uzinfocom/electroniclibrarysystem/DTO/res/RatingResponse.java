package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.Rating;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingResponse {
    Long id;
    Long bookId;
    String bookTitle;
    String userFullName;
    int stars;


    public RatingResponse convert(Rating rating) {
        RatingResponse response = new RatingResponse();
        response.setBookId(rating.getId());
        response.setBookId(rating.getBook().getId());
        response.setBookTitle(rating.getBook().getTitle());
        response.setUserFullName(rating.getUserEntity().getFullName());
        response.setStars(response.getStars());
        return response;
    }
}
