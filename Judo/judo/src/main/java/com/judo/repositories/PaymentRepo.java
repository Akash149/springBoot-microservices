package com.judo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judo.entitites.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    

}
