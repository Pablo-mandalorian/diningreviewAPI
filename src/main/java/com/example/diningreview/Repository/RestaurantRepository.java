package com.example.diningreview.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.diningreview.Model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant,Long>{
    Optional<Restaurant> findRestaurantByrestuarantNameAndZipCode(String name, String zipCode);
    List<Restaurant> findRestaurantByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(String zipCode);
    List<Restaurant> findRestaurantByZipCodeAndEggScoreNotNullOrderByEggScore(String zipCode);
    List<Restaurant> findRestaurantByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipCode);
}
