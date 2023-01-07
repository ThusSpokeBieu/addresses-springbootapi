package com.myapp.addresses.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.myapp.addresses.dto.AddressDto;
import com.myapp.addresses.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/person")
public class PersonController {

  private final PersonService personService;
  private final AddressService addressService;

  private final PersonMapper personMapper;
  private final AddressMapper addressMapper;

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
  public ResponseEntity<PersonDto> findById(@PathVariable Long id){
    try { 
      Optional<Person> person = personService.findById(id);
      PersonDto personDTO = personMapper.toDto(person.get());
      return ResponseEntity.ok().body(personDTO);
    } catch(Error error){
      throw new Error("Não foi possível encontrar a pessoa", error);
    }
  }

  @GetMapping(value = "/{id}/addresses")
  public ResponseEntity<List<AddressDto>> getAddresses(@PathVariable Long id){
      Optional<Person> optPerson = personService.findById(id);
      if(optPerson.isPresent()){
        Person person = optPerson.get();
        List<Address> addresses = new ArrayList<>();
        addresses.add(person.getMainAddress());
        addresses.addAll(person.getAddresses());
        return ResponseEntity.ok().body(addressMapper.toDtoList(addresses));
      } else {
        return ResponseEntity.badRequest().build();
      }
  }

  @GetMapping(value = "/{id}/mainaddress")
  public ResponseEntity<AddressDto> getMainAddress(@PathVariable Long id){
      Optional<Person> optPerson = personService.findById(id);
      if(optPerson.isPresent()){
        Person person = optPerson.get();
        Address address = person.getMainAddress();
        return ResponseEntity.ok().body(addressMapper.toDto(address));
      } else {
        return ResponseEntity.badRequest().build();
      }
  }
  
  @PostMapping
  @ResponseBody
  public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto) {
      try {
        Person person = personService.toEntity(personDto);
        personService.save(person);
        
        URI uri = ServletUriComponentsBuilder
                  .fromCurrentRequest()
                  .path("/{id}")
                  .buildAndExpand(person.getId())
                  .toUri();
                  
        return ResponseEntity.created(uri).body(personDto);

        } catch(Error error){
          throw new Error("Falha ao tentar criar nova pessoa.", error);
        }
  }

  @PutMapping(value="/{id}")
  public ResponseEntity<Person> update( @RequestBody PersonDto objDto, 
                                        @PathVariable Long id){
      Person person = personMapper.toEntity(objDto);
      personService.update(person, id);
      return ResponseEntity.ok().body(person);
  }

  @DeleteMapping(value="/delete/{id}")
  public ResponseEntity<Person> delete(@PathVariable Long id){
      personService.delete(id);
      return ResponseEntity.ok().build();
  }
  
}
