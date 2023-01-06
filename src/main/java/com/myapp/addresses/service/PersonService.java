package com.myapp.addresses.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.myapp.addresses.database.model.Person;
import com.myapp.addresses.database.repository.PersonRepository;

@Service
public class PersonService {

  private PersonRepository repository;

  public PersonService(PersonRepository repository) {
    this.repository = repository;
  }

  public List<Person> findAll() {
    return repository.findAll();
  }

  public Optional<Person> findById(UUID id){
    return repository.findById(id);
  }

  public void save(Person person) {
    Objects.requireNonNull(person);
    repository.save(person);
  }

  public void deleteAll(){
    repository.deleteAll();
  }
  
}
