package com.myapp.addresses.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.addresses.database.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long>{
    @Query("select p from Person p where p.deletedAt is null")
    List<Person> findAllActive();

    @Query("select p from Person p where p.id = ?1 and p.deletedAt is null")
    Optional<Person> findActiveById(@NonNull Long id);

    @Transactional
    @Modifying
    @Query("update Person p set p.deletedAt = CAST( GETDATE() AS Date )  where p.id = ?1")
    void softDeleteById(@NonNull Long id);




}
