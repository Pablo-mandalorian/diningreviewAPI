package com.example.diningreview.Controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.diningreview.Model.*;
import com.example.diningreview.Repository.RestaurantRepository;
import com.example.diningreview.Repository.ReviewRepository;

@RestController
@RequestMapping("/admin")
public class adminController {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public adminController(final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository){
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //Get reviews that are Pending
    @GetMapping("/reviews")
    public List<Review> getReviewsPending(@RequestParam String reviewStatus){
        Status pendingStatus = Status.Reviewing;
        pendingStatus = Status.valueOf(reviewStatus.toUpperCase());
        return reviewRepository.findReviewByStatus(pendingStatus);
    }

    //Accept or reject Reviews
    @PutMapping("/review/{reviewId}")
    public void reviewAction(@PathVariable Long reviewId, @RequestBody Admin admin){
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(!optionalReview.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Review review = optionalReview.get();

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(reviewId);
        if(restaurantOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(admin.getAccepted()){
            review.setStatus(Status.Accepted);
        }else{
            review.setStatus(Status.Rejected);
        }

        reviewRepository.save(review);
        updateReview(restaurantOptional.get());

    }

    private void updateReview(@RequestBody Restaurant restaurant){
        List<Review> reviews = reviewRepository.findReviewByRestaurantIdAndStatus(restaurant.getId(), Status.Accepted);
        if(reviews.size()==0){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        int peanutSum = 0;
        int peanutCount = 0;
        int dairySum = 0;
        int dairyCount = 0;
        int eggSum = 0;
        int eggCount = 0;
        for (Review r : reviews) {
            if (!ObjectUtils.isEmpty(r.getPeanutScoreOp())) {
                peanutSum += r.getPeanutScoreOp();
                peanutCount++;
            }
            if (!ObjectUtils.isEmpty(r.getDairyScoreOp())) {
                dairySum += r.getDairyScoreOp();
                dairyCount++;
            }
            if (!ObjectUtils.isEmpty(r.getEggScoreOp())) {
                eggSum += r.getEggScoreOp();
                eggCount++;
            }
        }

        int totalCount = peanutCount + dairyCount + eggCount ;
        int totalSum = peanutSum + dairySum + eggSum;

        float overallScore = (float) totalSum / totalCount;
        restaurant.setOverallScore(decimalFormat.format(overallScore));

        if (peanutCount > 0) {
            float peanutScore = (float) peanutSum / peanutCount;
            restaurant.setPeanutScore(decimalFormat.format(peanutScore));
        }

        if (dairyCount > 0) {
            float dairyScore = (float) dairySum / dairyCount;
            restaurant.setDairyScore(decimalFormat.format(dairyScore));
        }

        if (eggCount > 0) {
            float eggScore = (float) eggSum / eggCount;
            restaurant.setEggScore(decimalFormat.format(eggScore));
        }

        restaurantRepository.save(restaurant);

    }



}
