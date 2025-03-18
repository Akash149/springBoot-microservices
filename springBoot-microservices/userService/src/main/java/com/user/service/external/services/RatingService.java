package com.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

    @GetMapping("/api/v1/ratings/{hotelId}")
    Rating getRating(@PathVariable String hotelId);

    @GetMapping("/api/v1/ratings/user/{userId}")
    Rating[] getRatingsByUser(@PathVariable String userId);

    @GetMapping("/api/v1/ratings/hotel/{hotelId}")
    Rating[] getRatingsByHotel(@PathVariable String hotelId);

    @PostMapping("/api/v1/ratings")
    Rating createRating(Rating rating);

    @PutMapping("/api/v1/ratings")
    Rating updateRating(Rating rating);

    @DeleteMapping("/api/v1/ratings/{id}")
    void deleteRating(@PathVariable String id);

    
}
