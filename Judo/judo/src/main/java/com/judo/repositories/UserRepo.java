package com.judo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judo.entitites.UserDetails;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Long> {
    
    UserDetails findByEmail(String email);
    
    UserDetails findByEmailAndPassword(String email, String password);
    
    UserDetails findByPhoneAndPassword(String phone, String password);

}
