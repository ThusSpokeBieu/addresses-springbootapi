package com.myapp.addresses.configuration;

import com.myapp.addresses.database.model.Address;
import com.myapp.addresses.database.model.Person;
import com.myapp.addresses.service.AddressService;
import com.myapp.addresses.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    private PersonService personService;
    private AddressService addressService;

    public Instantiation(PersonService personService, AddressService addressService) {
        this.personService = personService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        personService.deleteAll();
        addressService.deleteAll();

        Address address1 = Address
                .builder()
                .number(1936)
                .streetAddress("Melville Street")
                .city("Jackson, TN")
                .zipCode("38301")
                .createdAt(Calendar.getInstance())
                .updatedAt(Calendar.getInstance())
                .build();

        Address address2 = Address
                .builder()
                .number(1936)
                .streetAddress("Melville Street")
                .city("Jackson, TN")
                .zipCode("38301")
                .createdAt(Calendar.getInstance())
                .updatedAt(Calendar.getInstance())
                .build();

        Address address3 = Address
                .builder()
                .number(1936)
                .streetAddress("Melville Street")
                .city("Jackson, TN")
                .zipCode("38301")
                .createdAt(Calendar.getInstance())
                .updatedAt(Calendar.getInstance())
                .build();

        Person person1 = Person
                .builder()
                .name("Barbara E. Jenkin")
                .birthday(LocalDate.of(2015, 3,5))
                .mainAddress(address1)
                .addresses(Arrays.asList(address2, address3))
                .createdAt(Calendar.getInstance())
                .updatedAt(Calendar.getInstance())
                .build();

        addressService.saveAll(Arrays.asList(address1, address2, address3));
        personService.save(person1);
    }
}
