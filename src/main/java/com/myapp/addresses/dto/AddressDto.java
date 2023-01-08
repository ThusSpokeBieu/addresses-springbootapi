package com.myapp.addresses.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressDto implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message="Por favor, insira um CEP brasileiro válido.")
    private String zipCode;

    @NotNull
    private String streetAddress;

    @NotNull
    private Integer number;

    @NotNull
    private String city;
}
