package com.judo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judo.entitites.WiFiSession;

@Repository
public interface WiFiSessionRepo extends JpaRepository<WiFiSession, Long> {

}
