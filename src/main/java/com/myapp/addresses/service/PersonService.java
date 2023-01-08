package com.myapp.addresses.service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myapp.addresses.database.model.Address;
import com.myapp.addresses.database.model.Person;
import com.myapp.addresses.database.repository.PersonRepository;
import com.myapp.addresses.dto.PersonDto;
import com.myapp.addresses.dto.mapper.AddressMapper;
import com.myapp.addresses.dto.mapper.PersonMapper;

import jakarta.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;

@Service
public class PersonService {

  private final PersonRepository repository;
  private final AddressService addressService;
  private final PersonMapper personMapper;
  private final AddressMapper addressMapper;
  private final EntityManager entityManager;

  public PersonService( PersonRepository repository, 
                        AddressService addressService, 
                        PersonMapper personMapper,
                        AddressMapper addressMapper,
                        EntityManager entityManager) {
    this.repository = repository;
    this.addressService = addressService;
    this.personMapper = personMapper;
    this.addressMapper = addressMapper;
    this.entityManager = entityManager;
  }

  public List<Person> findAll(boolean deleted) {
    Session session = entityManager.unwrap(Session.class);
    Filter filter = session.enableFilter("deletedPersonFilter");
    filter.setParameter("deleted", deleted);
    List<Person> people =  repository.findAll();
    session.disableFilter("deletedPersonFilter");
    return people;
  }

  public Optional<Person> findById(Long id){
    return repository.findById(id);
  }

  public void save(Person person) {
    Objects.requireNonNull(person);
    Person newPerson = person;
    newPerson.setCreatedAt(Calendar.getInstance());
    newPerson.setUpdatedAt(Calendar.getInstance());
    repository.save(newPerson);
  }

  public void saveAll(List<Person> people){
    repository.saveAll(people);
  }

  public void deleteAll(){
    repository.deleteAll();
  }

  public PersonDto toDto(Person person){
    return personMapper.toDto(person);
  }

  public Person toEntity(PersonDto personDto){
    Address address = addressMapper.toEntity(personDto.getMainAddress());
    Person entity = personMapper.toEntity(personDto);
    entity.setMainAddress(address);

    if (personDto.getAddresses() != null){
        List<Address> addressList = addressService
                     .toEntity(personDto.getAddresses());
        entity.setAddresses(addressList);
    } 

    return entity;
  }

  
  public Person update(Person newPerson, Long id){  
    return repository.findById(id)
      .map(person -> {
        person.setName(newPerson.getName());
        person.setBirthdate(newPerson.getBirthdate());
        person.setMainAddress(newPerson.getMainAddress());
        person.setAddresses(newPerson.getAddresses());
        person.setUpdatedAt(Calendar.getInstance());
        return repository.save(person);
      })
      .orElseGet(() -> {
        newPerson.setId(id);
        return repository.save(newPerson);
      });
  }

  public void delete(Long id){  
    repository.deleteById(id);
  }
  
}
