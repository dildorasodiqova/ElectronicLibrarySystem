package uz.uzinfocom.electroniclibrarysystem.service.ratingservice;

import org.springframework.http.ResponseEntity;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.RatingRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.RatingResponse;

import java.util.List;

public interface RatingService {
    ResponseEntity<RatingResponse> rateBook(RatingRequest request);


    ResponseEntity<List<RatingResponse>> getAllRatingByBook(Long bookId);
}
