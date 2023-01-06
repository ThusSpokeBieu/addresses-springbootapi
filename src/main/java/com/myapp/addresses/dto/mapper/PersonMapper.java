package com.myapp.addresses.dto.mapper;

import org.mapstruct.Mapper;

import com.myapp.addresses.database.model.Person;
import com.myapp.addresses.dto.PersonDto;

@Mapper(componentModel = "spring")
public interface PersonMapper {
  PersonDto toDto(Person entity);
  Person toEntity(PersonDto dto);
}
