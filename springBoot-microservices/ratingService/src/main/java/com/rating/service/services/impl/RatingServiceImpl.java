package com.rating.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.entities.Rating;
import com.rating.service.repositories.RatingRepo;
import com.rating.service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public Rating getRating(String id) {
        return ratingRepo.findById(id).orElse(null);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public Rating updateRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public void deleteRating(String id) {
        ratingRepo.deleteById(id);
    }

}
