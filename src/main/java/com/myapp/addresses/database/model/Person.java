package com.myapp.addresses.database.model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="pessoas") @DynamicUpdate
@Getter @Setter @Builder @AllArgsConstructor
@SQLDelete(sql = "UPDATE pessoas SET deletado = true WHERE id=?")
@FilterDef(name = "deletedPersonFilter", parameters = @ParamDef(name = "deleted", type = org.hibernate.type.descriptor.java.BooleanJavaType.class))
@Filter(name = "deletedPersonFilter", condition = "deletado = :deleted")
public class Person {

  @Serial
  private static final long serialVersionUID = 1L;
  
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

  @Column(name="nome", nullable = false)
  private String name;

  @Column(name="data_de_nascimento", nullable = false)
  @Temporal(TemporalType.DATE)
  private LocalDate birthdate;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Address mainAddress;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Address> addresses;

  @Builder.Default
  @Column(name="criado_em", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar createdAt = Calendar.getInstance();

  @Builder.Default
  @Column(name="atualizado_em", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar updatedAt = Calendar.getInstance();

  @Builder.Default
  @Column(name="deletado")
  private boolean deleted = false;

  public Person(){
  }

	public void addAddresses(Address address) {
    this.addresses.add(address);
	}

  public void addAddresses(List<Address> addresses) {
    this.addresses.addAll(addresses);
	}

  public void delete(){
    this.deleted = true;
  }

  public void restore(){
    this.deleted = false;
  }
}
