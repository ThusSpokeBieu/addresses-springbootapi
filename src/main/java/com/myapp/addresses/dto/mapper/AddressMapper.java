package com.myapp.addresses.dto.mapper;

import org.mapstruct.Mapper;

import com.myapp.addresses.database.model.Address;
import com.myapp.addresses.dto.AddressDTO;

@Mapper
public interface AddressMapper {
  AddressDTO toDto(Address entity);
  Address toEntity(AddressDTO dto);
}
