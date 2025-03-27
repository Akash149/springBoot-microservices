package com.rating.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.service.entities.Rating;
import com.rating.service.services.impl.RatingServiceImpl;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getRating(id));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    @PutMapping
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.updateRating(rating));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable String id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingsByHotelId(hotelId));
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

}
