package uz.uzinfocom.electroniclibrarysystem.service.ratingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.RatingRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.RatingResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;
import uz.uzinfocom.electroniclibrarysystem.entity.Rating;
import uz.uzinfocom.electroniclibrarysystem.entity.User;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.RatingRepository;
import uz.uzinfocom.electroniclibrarysystem.service.bookservice.BookService;
import uz.uzinfocom.electroniclibrarysystem.service.orderservice.OrderService;
import uz.uzinfocom.electroniclibrarysystem.service.userservice.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;
    private final OrderService orderService;
    private final UserService userService;
    private final BookService bookService;

    @Override
    public ResponseEntity<RatingResponse> rateBook(RatingRequest request) {
        boolean exists = orderService.existsByUserIdAndBookIdAndStatusIn(
                request.getUserId(),
                request.getBookId(),
                List.of(OrderStatus.ACCEPTED, OrderStatus.RETURNED)
        );

        if (!exists) {
            throw new ExceptionWithStatusCode(400, "You haven't received this book or haven't returned it yet, so it can't be rated");
        }
            User user = userService.findById(request.getUserId());
            Book book = bookService.findById(request.getBookId());
            Rating rating = new Rating(user, book, request.getStars());
            ratingRepository.save(rating);
            return ResponseEntity.ok(new RatingResponse().convert(rating));

    }

    @Override
    public ResponseEntity<RatingResponse> updateRating(Long ratingId, RatingRequest request) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ExceptionWithStatusCode(404, "Rating not found"));

        rating.setStars(request.getStars());

        ratingRepository.save(rating);

        return ResponseEntity.ok(new RatingResponse().convert(rating));
    }


    @Override
    public ResponseEntity<List<RatingResponse>> getAllRatingByBook(Long bookId) {
        List<Rating> allByBookId = ratingRepository.findAllByBookId(bookId);
        return ResponseEntity.ok(allByBookId.stream()
                .map(rating -> new RatingResponse().convert(rating))
                .collect(Collectors.toList()));
    }
}
