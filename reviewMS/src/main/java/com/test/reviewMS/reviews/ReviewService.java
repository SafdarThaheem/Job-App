package com.test.reviewMS.reviews;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<List<Review>> getReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long reviewId);
    Review updateReview(Long reviewId, Review review);
    boolean deleteReview(Long reviewId);
}
