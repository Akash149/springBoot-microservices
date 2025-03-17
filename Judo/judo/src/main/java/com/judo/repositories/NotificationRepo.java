package com.judo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judo.entitites.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long>{

}
