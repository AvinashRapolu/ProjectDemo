package com.avinash.ProjectDEMO.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull @NotEmpty @NotBlank
    private String line1;
    private String line2;
    @NotNull
    private int pinCode;
    @NotNull @NotEmpty @NotBlank
    private String state;
    @NotNull @NotEmpty @NotBlank
    private String country;

    private boolean shippingAddress;
    private boolean billingAddress;
}
