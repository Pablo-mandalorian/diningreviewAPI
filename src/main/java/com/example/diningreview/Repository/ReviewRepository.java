package com.example.diningreview.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.diningreview.Model.Review;
import com.example.diningreview.Model.Status;

public interface ReviewRepository extends CrudRepository<Review,Long>{
    List<Review> findReviewByRestaurantIdAndStatus(Long restaurantId, Status status);
    List<Review> findReviewByStatus(Status status);
}
