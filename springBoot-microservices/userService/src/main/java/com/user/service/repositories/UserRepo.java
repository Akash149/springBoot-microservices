package com.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.entities.User;

public interface UserRepo extends JpaRepository<User, String> {


}
