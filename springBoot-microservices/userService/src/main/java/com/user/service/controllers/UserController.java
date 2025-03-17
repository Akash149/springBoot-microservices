package com.user.service.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.external.services.HotelService;
import com.user.service.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        var _user = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(_user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        var user = userService.getUserById(id);
        // Get ratings from rating service
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/api/v1/ratings/user/" + id, Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        // for (Rating rating : ratings) {
        //     ResponseEntity<Hotel> resp = restTemplate.getForEntity("http://localhost:8282/api/v1/hotels/" + rating.getHotelId(), Hotel.class);
        //     Hotel hotel = resp.getBody();
        //     rating.setHotel(hotel);
        // }
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // ResponseEntity<Hotel> resp = restTemplate.getForEntity("http://HOTEL-SERVICE/api/v1/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        var _user = userService.updateUser(user);
        return ResponseEntity.ok(_user);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        var users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

}
