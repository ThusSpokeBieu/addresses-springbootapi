package com.myapp.addresses.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
 
  private String name;
  private LocalDate birthday;
  private AddressDto mainAddress;
  private List<AddressDto> addresses;
}
