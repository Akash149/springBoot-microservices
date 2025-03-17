package com.judo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judo.entitites.MenuItem;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {


}
