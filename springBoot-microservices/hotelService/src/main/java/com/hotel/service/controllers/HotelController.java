package com.hotel.service.controllers;

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

import com.hotel.service.entities.Hotel;
import com.hotel.service.services.impl.HotelServiceImpl;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(id));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
    }

    @PutMapping
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotelService.updateHotel(hotel));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
