package com.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
@Service
public interface HotelService {

    @GetMapping("/api/v1/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

}
