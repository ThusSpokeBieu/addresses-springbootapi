package com.myapp.addresses.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myapp.addresses.dto.AddressDto;
import com.myapp.addresses.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myapp.addresses.database.model.Address;
import com.myapp.addresses.database.model.Person;
import com.myapp.addresses.dto.mapper.AddressMapper;
import com.myapp.addresses.dto.mapper.PersonMapper;
import com.myapp.addresses.service.AddressService;
import com.myapp.addresses.service.PersonService;

import static java.util.stream.Collectors.toList;

import java.net.URI;
@RestController
@RequestMapping("/")
public class PersonController {
  private PersonService personService;
  private AddressService addressService;

  private PersonMapper personMapper;
  private AddressMapper addressMapper;

  public PersonController(PersonService personService, 
                          AddressService addressService, 
                          PersonMapper personMapper,
                          AddressMapper addressMapper) {
    this.personService = personService;
    this.addressService = addressService;
    this.personMapper = personMapper;
    this.addressMapper = addressMapper;
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<PersonDto>> findAll(){
    try {
          List<PersonDto> dtoList = personService.findAll()
                              .stream()
                              .map(personMapper::toDto)
                              .collect(toList());
          return ResponseEntity.ok().body(dtoList);
    } catch(Error error){
      throw new Error("Não foi possível buscar as pessoas.", error);
    } 
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<PersonDto> findById(@PathVariable UUID id){
    try { 
      Optional<Person> person = personService.findById(id);
      PersonDto personDTO = personMapper.toDto(person.get());
      return ResponseEntity.ok().body(personDTO);
    } catch(Error error){
      throw new Error("Não foi possível encontrar a pessoa", error);
    }
  }
  
  @PostMapping
  @ResponseBody
  public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDTO) {
      try {
        AddressDto addressDTO = personDTO.getMainAddress();
        Address address = addressMapper.toEntity(addressDTO);
        List<AddressDto> listDto = personDTO.getAddresses();
        addressService.save(address);

        Person person = personMapper.toEntity(personDTO);
        person.setMainAddress(address);

        if(listDto != null ){
          List<Address> listAddress = addressMapper.toEntityList(listDto);
          addressService.saveAll(listAddress);
          person.setAddresses(listAddress);
        }

        personService.save(person);
        
        URI uri = ServletUriComponentsBuilder
                  .fromCurrentRequest()
                  .path("/{id}")
                  .buildAndExpand(person.getId())
                  .toUri();
        return ResponseEntity.created(uri).body(personDTO);

        } catch(Error error){
          throw new Error("Falha ao tentar criar nova pessoa.", error);
        }
  }
  
}
