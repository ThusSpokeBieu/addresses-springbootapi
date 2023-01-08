# MyAddresses 
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Projeto para teste prático e portfólio, que tem como objetivo principal criar uma API que possa:

- [Criar uma pessoa](#criar-uma-pessoa)
- [Editar uma pessoa](#editar-uma-pessoa)
- [Consultar uma pessoa](#consultar-uma-pessoa)
- [Listar pessoas](#listar-pessoas)
- [Criar endereço para pessoa](#criar-endereço-para-pessoa)
- [Listar endereços da pessoa](#listar-endereços-da-pessoa)
- [Poder informar qual endereço é o principal da pessoa](#poder-informar-qual-endereço-é-o-principal-da-pessoa)


*Read this in other languages: [English](README.en.md), [Português](README.md).*

## Índice
  - [Tecnologias](#tecnologias)
  - [Requerimentos](#requerimentos)
  - [Instalação e Uso](#instalação-e-uso)
  - [Progresso](#progresso)
  - [Arquitetura](#arquitetura)
  - [Licença](#licença)

## Tecnologias
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Springboot 3.0.1](https://spring.io/blog/2022/12/22/spring-boot-3-0-1-available-now)
- [H2 Database Engine](https://www.h2database.com/html/main.html)
- [Apache Maven 3.8.6](https://maven.apache.org/download.cgi)
- [Lombok 1.18.24](https://projectlombok.org/)
- [Mapstruct 1.5.3](https://mapstruct.org/)
- [Postman v10.7.0](https://www.postman.com/)

## Requerimentos
Será necessário que tenha instalado o Java 17 e o Maven para conseguir instalar as dependências. Precisará também de alguma ferramenta para fazer requisições HTTP como o Postman. 

O H2 está  configurado para que você possa acessá-lo em:

http://localhost:8080/h2-console

```    
JDBC URL: jdbc:h2:mem:addresses
username: admin
password: 
``` 

Você pode configurar essas opções no arquivo: application.yml (src/main/resources)

## Instalação e Uso
Clone o repositório, e após isso, use o seguinte comandos no terminal de sua preferência:

```bash
    mvn spring-boot:run
```

Isso vai compilar o projeto e fazê-lo rodar na porta 8080 do localhost (verifique se é a mesma porta no console do springboot).

Se ele conseguir rodar, acesse em algum navegador:

http://localhost:8080/

Você acessará um painel administrativo que tem algumas informações (como o id dos usuários).

A aplicação está configurada para instanciar dez pessoas quando inicializada.

Esse painel servirá como apoio para testar recursos da API.

Use o postman ou outro aplicativo de sua referência para testar os recursos:

### Criar uma pessoa

- Faça uma Requisição Post no link http://localhost:8080/person
- A body dessa requisição deve ser do formato JSON, como no seguinte exemplo:

```
{
        "name": "Fulano",
        "birthdate": "1990-03-04",
        "mainAddress": {
            "zipCode": "00001-001",
            "streetAddress": "Rua Principal",
            "number": 1,
            "city": "Carapicuíba-SP"
        },
        "addresses": [
            {
                "zipCode": "00001-002",
                "streetAddress": "Rua Secundária",
                "number": 2,
                "city": "Goiânia-GO"
            }
        ]
}
```
O id da pessoa sempre será em ordem crescente, como já foram instanciadas 10 pessoas, a próxima terá o id 11, e assim por diante.

Você pode conferir isso atualizando o painel administrativo.


### Editar uma pessoa.

- Faça uma Requisição Put no link http://localhost:8080/person/{id}
- O {id} deve ser substituído pelo id da pessoa que quer editar. Exemplo para editar pessoa de id 1: http://localhost:8080/person/1
- A body dessa requisição deve ser do formato JSON, como no exemplo:

```
{
        "name": "Atualizado",
        "birthdate": "1999-12-31",
        "mainAddress": {
            "zipCode": "99999-999",
            "streetAddress": "Rua Atualizada 1",
            "number": 1000,
            "city": "Atualizada-SP"
        },
        "addresses": [
            {
                "zipCode": "99999-998",
                "streetAddress": "Rua Atualizada 1",
                "number": 999,
                "city": "Atualizada-GO"
            }
        ]
}
```

### Consultar uma pessoa.

- Faça uma Requisição get no link http://localhost:8080/person/{id}
- O {id} deve ser substituído pelo id da pessoa que quer consultar. Exemplo para consultar pessoa de id 1: http://localhost:8080/person/1
- A resposta será no formato JSON, você pode acessá-lo pelo navegador também.

### Listar pessoas.

- Faça uma Requisição get no link http://localhost:8080/person/
- As pessoas já estão listadas no painel administrativo, mas, nesse endereço você receberá um JSON com a lista de todas pessoas e seus detalhes. Você pode acessá-lo no navegador.

### Criar endereço para pessoa

- Faça uma Requisição PATCH no link http://localhost:8080/person/addresses/{id}
- O {id} deve ser substituído pelo id da pessoa que quer editar. Exemplo para editar pessoa de id 5: http://localhost:8080/person/addresses/5
- Dessa forma, você adicionará endereços novos na lista de endereços da pessoa. 
- A body deve está em formato JSON seguindo o exemplo abaixo:

```
{
  "zipCode": "00001-111",
  "streetAddress": "Novo endereço",
  "number": 3,
  "city": "Novo Endereço-BA"
}
``` 

### Listar endereços da pessoa

- Faça uma Requisição GET no link http://localhost:8080/person/addresses/{id}
- O {id} deve ser substituído pelo id da pessoa que quer consultar. Exemplo para consultar pessoa de id 5: http://localhost:8080/person/addresses/5 
- Dessa forma, você receberá a resposta em formato de JSON de todos os endereços da pessoa. 

### Poder informar qual endereço é o principal da pessoa
- Faça uma Requisição GET no link http://localhost:8080/person/address/{id}
- O {id} deve ser substituído pelo id da pessoa que quer consultar. Exemplo para consultar pessoa de id 5: http://localhost:8080/person/addresses/5 
- Dessa forma, você receberá a resposta em formato de JSON de apenas o endereço principal da pessoa do id. 

### Outros recursos: 

Em breve serão citados aqui.


## Progresso

O projeto ainda está em desenvolvimento, provavelmente em 80%.


## Arquitetura de Diretórios

A arquitetura foi feita usando como base MVC e packages por camadas.
Abaixo segue a arquitetura da pasta principal da aplicação.

```
addresses

  ├── AddressesApplication.java

  ├── configuration
          └── Instantiation.java

  ├── controller
          ├── HomeController.java
          └── PersonController.java

  ├── database
        ├── model
              ├── Address.java
              └── Person.java
        └──repository
              ├── AddressRepository.java
              └── PersonRepository.java

  ├── dto   
        ├── mapper
              ├── AddressMapper.java
              └── PersonDto.java
        ├── AddressDto.java
        └── PersonDto.java

  └── service
        ├── AddressService.java
        └── PersonService.java
```

## Licença
Este projeto é licenciado sob os termos da licença MIT.