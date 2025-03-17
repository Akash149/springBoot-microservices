package com.rating.service.services;

import java.util.List;

import com.rating.service.entities.Rating;

public interface RatingService {

    public Rating createRating(Rating rating);

    public Rating getRating(String id);

    public List<Rating> getAllRatings();

    public List<Rating> getRatingsByHotelId(String hotelId);

    public List<Rating> getRatingsByUserId(String userId);

    public Rating updateRating(Rating rating);

    public void deleteRating(String id);

}
