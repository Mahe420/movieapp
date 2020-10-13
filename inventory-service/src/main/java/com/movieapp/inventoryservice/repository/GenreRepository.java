
package com.movieapp.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movieapp.inventoryservice.entity.Genre;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
	
	@Query(value= "select id from genre where name=?;" , nativeQuery=true)
	Optional<Integer>  getGenrIdByName(String name);

}
