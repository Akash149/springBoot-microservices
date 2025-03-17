package com.hotel.service.services;

import java.util.List;

import com.hotel.service.entities.Hotel;

public interface HotelService {

    List<Hotel> getAllHotels();
    Hotel getHotelById(String id);
    Hotel addHotel(Hotel hotel);
    Hotel updateHotel(Hotel hotel);
    void deleteHotel(String id);

}
