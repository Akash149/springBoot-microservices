package com.judo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judo.entitites.Shop;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Long> {

    Shop findByShopId(Long shopId); 

}
