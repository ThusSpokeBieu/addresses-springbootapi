package com.myapp.addresses.database.model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Person {

  @Serial
  private static final long serialVersionUID = 1L;
  
  private @Id @GeneratedValue(strategy = GenerationType.UUID) UUID id;

  @Column(name="nome", nullable = false)
  private String name;

  @Column(name="data_de_nascimento", nullable = false)
  private LocalDate birthday;

  @ManyToOne
  @JoinColumn
  private Address mainAddress;

  @ManyToMany
  @JoinColumn
  private List<Address> addresses;

  @Column(name="criado_em", nullable = false)
  private Calendar createdAt;

  @Column(name="ataualizado_em", nullable = false)
  private Calendar updatedAt;

  @Column(name="deletado_em", nullable = true)
  private Calendar deletedAt = null;
  
}
