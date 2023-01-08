package com.myapp.addresses.configuration;

import com.myapp.addresses.database.model.Address;
import com.myapp.addresses.database.model.Person;
import com.myapp.addresses.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final PersonService personService;

    public Instantiation(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {

      Calendar now = Calendar.getInstance();

			// Seeding addresses
      Address address1 = Address.builder().number(1823)
      		.streetAddress("Rua José Ítalo de Camargo")
      		.city("Carapicuíba-SP").zipCode("06363-140").build();

      Address address2 = Address.builder().number(247)
      		.streetAddress("Rua Amor")
      		.city("Olinda-PE").zipCode("53190-580").build();

      Address address3 = Address.builder().number(346)
					.streetAddress("Rua José Crispiano Coelho Brandão")
      		.city("Petrolina-PE").zipCode("56328-785").build();

			Address address4 = Address.builder().number(1821)
          .streetAddress("Rua Nivaldo Socher")
          .city("Colombo-PR").zipCode("83403-190").build();

			Address address5 = Address.builder().number(238)
          .streetAddress("Rua Dona Lourdes Brêda")
          .city("Maceió-AL").zipCode("57071-490").build();

			Address address6 = Address.builder().number(1456)
          .streetAddress("Travessa Vista Alegre")
          .city("Rio de Janeiro-RJ").zipCode("23590-350").build();
			
			Address address7 = Address.builder().number(1768)
          .streetAddress("Avenida Artur Moraes")
          .city("Jequié-BA").zipCode("45206-010").build();

			Address address8 = Address.builder().number(593)
          .streetAddress("Rua Doutor Elizardo Elias de Souza")
          .city("Goiânia-GO").zipCode("74780-140").build();

			Address address9 = Address.builder().number(927)
          .streetAddress("Rua Mário de Bona")
          .city("Tubarão-SC").zipCode("88708-105").build();

			Address address10 = Address.builder().number(1493)
          .streetAddress("Travessa Olmiro Silveira")
          .city("Cruz Alta-RS").zipCode("98015-070").build();

			// Seeding people
      Person person1 = Person.builder()
      	  .name("Marina Carvalho Barros").birthdate(LocalDate.of(1965, 3,20))
          .mainAddress(address1).addresses(Arrays.asList(address8))
          .createdAt(now).updatedAt(now).build();

      Person person2 = Person.builder()
          .name("Rebeca Cardoso Rocha").birthdate(LocalDate.of(1998, 7,3))
          .mainAddress(address2).addresses(Arrays.asList(address3, address4))
          .createdAt(now).updatedAt(now).build();

			Person person3 = Person.builder()
          .name("Nicolas Ferreira Costa").birthdate(LocalDate.of(1959, 3,23))
          .mainAddress(address5).addresses(Arrays.asList(address7, address10))
					.createdAt(now).updatedAt(now).build();
			
			Person person4 = Person.builder()
          .name("João Azevedo Pereira").birthdate(LocalDate.of(1975, 4,27))
          .mainAddress(address3).addresses(Arrays.asList(address4, address2))
          .createdAt(now).updatedAt(now).build();
			
			Person person5 = Person.builder()
          .name("Livia Pinto Oliveira").birthdate(LocalDate.of(1952, 9, 3))
          .mainAddress(address4).createdAt(now).updatedAt(now).build();

			Person person6 = Person.builder()
          .name("Raissa Azevedo Oliveira").birthdate(LocalDate.of(1937, 6, 20))
          .mainAddress(Address.builder().city("Porto Alegre-RS").zipCode("91180-530").streetAddress("Avenida Doutor Rubem Knijnik").number(1459).build())
					.createdAt(now).updatedAt(now).build();

			Person person7 = Person.builder()
          .name("Ágatha Silva Rodrigues").birthdate(LocalDate.of(1983, 9, 24))
          .mainAddress(Address.builder().city("Contagem-MG").zipCode("32143-240").streetAddress("Rua Vinte e Três").number(581).build())
					.addresses(Arrays.asList(address8)).createdAt(now).updatedAt(now).build();

			Person person8 = Person.builder()
          .name("Alice Santos Araujo").birthdate(LocalDate.of(1939, 5, 4))
          .mainAddress(address6).addresses(Arrays.asList(address1))
					.createdAt(now).updatedAt(now).build();
			
			Person person9 = Person.builder()
          .name("Rodrigo Sousa Ribeiro").birthdate(LocalDate.of(1975, 12, 25))
          .mainAddress(address7).addresses(Arrays.asList(address3, address4, address6))
					.createdAt(now).updatedAt(now).build();

			Person person10 = Person.builder()
          .name("Kauê Lima Pinto").birthdate(LocalDate.of(1947, 4, 1))
          .mainAddress(address8).addresses(Arrays.asList(address9, address10))
					.createdAt(now).updatedAt(now).build();

      personService.saveAll(Arrays.asList(person1, person2, 
																					person3, person4, 
																					person5, person6, 
																					person7, person8, 
																					person9, person10));
    }
}
