package com.myapp.addresses.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myapp.addresses.database.model.Address;
import com.myapp.addresses.database.repository.AddressRepository;

@Service
public class AddressService {
  
  private AddressRepository repository;

  public AddressService(AddressRepository repository) {
    this.repository = repository;
  }

  public List<Address> findAll() {
    return repository.findAll();
  }

  public Optional<Address> findById(Long id){
    return repository.findById(id);
  }

  public void save(Address address) {
    Objects.requireNonNull(address);
    repository.save(address);
  }

  
  public void saveAll(List<Address> addresses){
    Objects.requireNonNull(addresses);
    repository.saveAll(addresses);
  }

  public void deleteAll(){
    repository.deleteAll();
  }
}
