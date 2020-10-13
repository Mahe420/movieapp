
package com.movieapp.inventoryservice.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieapp.inventoryservice.entity.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{


}
