package uz.uzinfocom.electroniclibrarysystem.service.ratingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.RatingRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.RatingResponse;
import uz.uzinfocom.electroniclibrarysystem.repository.RatingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;

    @Override
    public ResponseEntity<RatingResponse> rateBook(RatingRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<List<RatingResponse>> getAllRatingByBook(Long bookId) {
        return null;
    }
}
