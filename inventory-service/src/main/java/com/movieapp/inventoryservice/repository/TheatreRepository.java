
package com.movieapp.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieapp.inventoryservice.entity.Theatre;


@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

}
