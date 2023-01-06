package com.myapp.addresses.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressDTO implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
    private String zipCode;
    private String streetAddress;
    private Integer number;
    private String city;
}
