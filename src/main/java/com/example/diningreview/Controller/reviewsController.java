package com.example.diningreview.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.diningreview.Model.Restaurant;
import com.example.diningreview.Model.Review;
import com.example.diningreview.Model.Status;
import com.example.diningreview.Model.User;
import com.example.diningreview.Repository.RestaurantRepository;
import com.example.diningreview.Repository.ReviewRepository;
import com.example.diningreview.Repository.UserRepository;

@RestController
@RequestMapping("/diningreviews")
public class reviewsController {
    //Dependencys injection
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    reviewsController(final UserRepository userRepository, final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository){
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }
    
      //Dining Review entity-related scenarios

      //As a registered user, I want to submit a dining review.
      
      @PostMapping
      @ResponseStatus(HttpStatus.CREATED)
      public void addReview(@RequestBody Review review){
        validateReview(review);

        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(review.getId());
        if(optionalRestaurant.isEmpty()){
          throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        review.setStatus(Status.Reviewing);
        reviewRepository.save(review);
      }
  

      private void validateReview(Review review){
        if(ObjectUtils.isEmpty(review.getSubmittedBy())){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(ObjectUtils.isEmpty(review.getRestaurantId())){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(ObjectUtils.isEmpty(review.getDairyScoreOp()) && 
        ObjectUtils.isEmpty(review.getEggScoreOp()) && 
        ObjectUtils.isEmpty(review.getPeanutScoreOp())){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = this.userRepository.findByDisplayName(review.getSubmittedBy());
        if(!optionalUser.isPresent()){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
      }
}
