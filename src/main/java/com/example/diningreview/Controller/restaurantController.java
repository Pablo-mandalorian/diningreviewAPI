package com.example.diningreview.Controller;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.diningreview.Model.Restaurant;
import com.example.diningreview.Repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurants")
public class restaurantController {
    
    private final RestaurantRepository restaurantRepository;
    private final Pattern zipCodePattern = Pattern.compile("\\d{5}");

    public restaurantController(final RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    @PostMapping("/addRestaurant")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createrRestaurant(@RequestBody Restaurant restaurant){
        validateNewRestaurant(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(!optionalRestaurant.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Restaurant restaurant = optionalRestaurant.get();
        return restaurant;


    }

    @GetMapping("/search")
    public Iterable<Restaurant> searchRestaurantsByAllergy(@RequestParam String zipCode, @RequestParam String allergy){
        validateZipCode(zipCode);

        Iterable<Restaurant> restaurants = Collections.emptyList();
        if(allergy.equalsIgnoreCase("peanut")){
            restaurants = restaurantRepository.findRestaurantByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(zipCode);
        }else if(allergy.equalsIgnoreCase("egg")){
            restaurants = restaurantRepository.findRestaurantByZipCodeAndEggScoreNotNullOrderByEggScore(zipCode);
        }else if(allergy.equalsIgnoreCase("dairy")){
            restaurants = restaurantRepository.findRestaurantByZipCodeAndDairyScoreNotNullOrderByDairyScore(zipCode);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return restaurants;
    }


    private void validateNewRestaurant(@RequestBody Restaurant restaurant){
        if(ObjectUtils.isEmpty(restaurant.getRestuarantName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        validateZipCode(restaurant.getZipCode());

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantByrestuarantNameAndZipCode(restaurant.getRestuarantName(), restaurant.getZipCode());
        if(optionalRestaurant.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }


    private void validateZipCode(String zipCode){
        if(!zipCodePattern.matcher(zipCode).matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
