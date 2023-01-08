package com.myapp.addresses.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myapp.addresses.database.model.Address;
import com.myapp.addresses.database.repository.AddressRepository;
import com.myapp.addresses.dto.AddressDto;
import com.myapp.addresses.dto.mapper.AddressMapper;

@Service
public class AddressService {
  
  private final AddressRepository repository;
  private final AddressMapper addressMapper;

  public AddressService(AddressRepository repository, AddressMapper addressMapper,
                        AddressRepository addressRepository) {
    this.repository = repository;
    this.addressMapper = addressMapper;
  }

  public List<Address> findAll() {
    return repository.findAll();
  }

  public Optional<Address> findById(Long id){
    return repository.findById(id);
  }

  public void save(Address address) {
    Objects.requireNonNull(address);
    Address newAddress = address;
    repository.save(newAddress);
  }

  
  public void saveAll(List<Address> addresses){
    Objects.requireNonNull(addresses);
    List<Address> addressList = addresses;
    repository.saveAll(addressList);
  }

  public void deleteAll(){
    repository.deleteAll();
  }

  public AddressDto toDto(Address address){
    return addressMapper.toDto(address);
  }

  public Address toEntity(AddressDto dto){
    return addressMapper.toEntity(dto);
  }

  public List<Address> toEntity(List<AddressDto> addressesDto){
    return addressMapper.toEntityList(addressesDto);
  }

  public List<AddressDto> toDto(List<Address> address){
    return addressMapper.toDtoList(address);
  }
}
