package com.myapp.addresses.dto.mapper;

import java.util.List;

import com.myapp.addresses.dto.AddressDto;
import org.mapstruct.Mapper;

import com.myapp.addresses.database.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
  AddressDto toDto(Address entity);
  Address toEntity(AddressDto dto);

  List<AddressDto> toDtoList(List<Address> entityList);
  List<Address> toEntityList(List<AddressDto> dtoList);
}
