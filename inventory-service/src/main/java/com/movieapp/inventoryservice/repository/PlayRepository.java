
package com.movieapp.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieapp.inventoryservice.entity.Play;


@Repository
public interface PlayRepository extends JpaRepository<Play, Integer> {

}