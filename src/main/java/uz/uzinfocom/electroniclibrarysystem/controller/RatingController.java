package uz.uzinfocom.electroniclibrarysystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.RatingRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.RatingResponse;
import uz.uzinfocom.electroniclibrarysystem.service.ratingservice.RatingService;

import java.util.List;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping
    public ResponseEntity<RatingResponse> rateBook(@Valid @RequestBody RatingRequest request) {
        return ratingService.rateBook(request);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping
    ResponseEntity<RatingResponse> updateRating(Long ratingId, @Valid  @RequestBody RatingRequest request){
       return ratingService.updateRating(ratingId, request);
    }

    @GetMapping("/bookId")
    public ResponseEntity<List<RatingResponse>> getAllRatingByBook(@RequestParam Long bookId){
        return ratingService.getAllRatingByBook(bookId);
    }
}
