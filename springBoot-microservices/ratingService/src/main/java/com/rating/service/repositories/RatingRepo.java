package com.rating.service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.service.entities.Rating;

public interface RatingRepo extends MongoRepository<Rating, String> {

    List<Rating> findByHotelId(String hotelId);
    List<Rating> findByUserId(String userId);

}
