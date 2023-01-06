package com.myapp.addresses.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.addresses.database.model.Person;

public interface PersonRepository extends JpaRepository<Person, UUID>{
}
