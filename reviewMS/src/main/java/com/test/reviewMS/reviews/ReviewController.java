package com.test.reviewMS.reviews;

import com.test.reviewMS.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getReviews(@RequestParam Long companyId) {
        Optional<List<Review>> reviews = reviewService.getReviews(companyId);
        if (reviews.get().isEmpty()) {
            ApiResponse failedResponse = new ApiResponse(
                    HttpStatus.NOT_FOUND.name(),
                    "No Content Available!",
                    reviews.get()
            );
            return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
        }
        ApiResponse successResponse = new ApiResponse(
                HttpStatus.OK.name(),
                "Content Available!",
                reviews.get()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addReview(
            @RequestParam Long companyId,
            @RequestBody Review review
    ) {
        boolean company = reviewService.addReview(companyId, review);
        if (company) {
            ApiResponse successResponse = new ApiResponse(
                    HttpStatus.CREATED.name(),
                    "review added!",
                    company
            );
            return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
        }
        ApiResponse failedResponse = new ApiResponse(
                HttpStatus.NOT_FOUND.name(),
                "company not found!",
                null
        );
        return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> getReview(@PathVariable Long reviewId) {
        Review review = reviewService.getReview(reviewId);
        if (review == null) {
            ApiResponse failedResponse = new ApiResponse(
                    HttpStatus.NOT_FOUND.name(),
                    "Review Not Found",
                    null
            );
            return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
        }
        ApiResponse successResponse = new ApiResponse(
                HttpStatus.OK.name(),
                "Review Found!",
                review
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> updateReview(@PathVariable Long reviewId,
                                                    @RequestBody Review review) {
        Review Updated = reviewService.updateReview(reviewId, review);
        if (Updated == null) {
            ApiResponse failedResponse = new ApiResponse(
                    HttpStatus.NOT_MODIFIED.name(),
                    "Review Not Update",
                    null
            );
            return new ResponseEntity<>(failedResponse, HttpStatus.NOT_MODIFIED);
        }
        ApiResponse successResponse = new ApiResponse(
                HttpStatus.OK.name(),
                "Review Updated!",
                Updated
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        if(!deleted) {
            ApiResponse failedResponse = new ApiResponse(
                    HttpStatus.NOT_FOUND.name(),
                    "Review Not Deleted",
                    null
            );
            return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
        }
        ApiResponse successResponse = new ApiResponse(
                HttpStatus.OK.name(),
                "Review Deleted!",
                deleted
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

}

//1-> pass data to service layer and get its response if any else throw response via exception handler
//2->validate the response
//3-> if response is valid make a success api response else failed api response
//4-> return response with body and response status