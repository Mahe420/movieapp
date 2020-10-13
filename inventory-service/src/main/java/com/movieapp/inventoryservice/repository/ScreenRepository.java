
package com.movieapp.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieapp.inventoryservice.entity.Screen;


@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer>{

}
