package com.myapp.addresses.dto.mapper;

import org.mapstruct.Mapper;

import com.myapp.addresses.database.model.Person;
import com.myapp.addresses.dto.PersonDTO;

@Mapper
public interface PersonMapper {
  PersonDTO toDto(Person entity);
  Person toEntity(PersonDTO dto);
}
