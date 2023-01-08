package com.myapp.addresses.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @NotNull
  private String name;

  @NotNull
  @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message="Por favor, insira uma data válida no seguinte formato: AAAA-MM-DD.")
  private LocalDate birthdate;

  @NotNull
  private AddressDto mainAddress;
  private List<AddressDto> addresses;
}
