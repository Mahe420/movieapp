package com.movieapp.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieapp.inventoryservice.entity.Address;



@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
