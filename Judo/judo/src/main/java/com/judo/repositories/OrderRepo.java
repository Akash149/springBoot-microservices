package com.judo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judo.entitites.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findByOrderId(Long orderId);
    
}
