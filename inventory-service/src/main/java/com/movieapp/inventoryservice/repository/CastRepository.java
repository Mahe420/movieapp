package com.movieapp.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movieapp.inventoryservice.entity.Cast;


@Repository
public interface CastRepository extends JpaRepository<Cast, Integer> {

	@Query(value= "select id from cast where name=?;" , nativeQuery=true)
	Optional<Integer>  getcastIdByName(String name);
}
