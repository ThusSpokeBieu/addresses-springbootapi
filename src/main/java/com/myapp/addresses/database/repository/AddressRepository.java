package com.myapp.addresses.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.addresses.database.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
