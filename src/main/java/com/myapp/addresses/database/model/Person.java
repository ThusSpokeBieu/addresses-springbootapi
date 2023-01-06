package com.myapp.addresses.database.model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="pessoas")
@Getter @Setter
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

  public Person(){
    Calendar now = Calendar.getInstance();
    this.setCreatedAt(now);
    this.setUpdatedAt(now);
  }

  public void delete(){
    this.deletedAt = Calendar.getInstance();
  }

  public void restore(){
    this.deletedAt = null;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
    result = prime * result + ((mainAddress == null) ? 0 : mainAddress.hashCode());
    result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    result = prime * result + ((deletedAt == null) ? 0 : deletedAt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (birthday == null) {
      if (other.birthday != null)
        return false;
    } else if (!birthday.equals(other.birthday))
      return false;
    if (mainAddress == null) {
      if (other.mainAddress != null)
        return false;
    } else if (!mainAddress.equals(other.mainAddress))
      return false;
    if (addresses == null) {
      if (other.addresses != null)
        return false;
    } else if (!addresses.equals(other.addresses))
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (updatedAt == null) {
      if (other.updatedAt != null)
        return false;
    } else if (!updatedAt.equals(other.updatedAt))
      return false;
    if (deletedAt == null) {
      if (other.deletedAt != null)
        return false;
    } else if (!deletedAt.equals(other.deletedAt))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    return "Person [name=" + name + ", birthday=" + birthday + ", mainAddress=" + mainAddress + ", addresses="
        + addresses + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
  }
  
}
