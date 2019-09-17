package com.app.customermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@Data
public class Customer {

  @Id
  private String id;

  private String firstName;
  private String lastName;
  private String phone;
  private String address;
  private String city;
  private String postalCode;
  private String email;
}
