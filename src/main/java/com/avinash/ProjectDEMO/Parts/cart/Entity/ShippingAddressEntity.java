package com.avinash.ProjectDEMO.Parts.cart.Entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShippingAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id2;
    @NotNull
    @NotEmpty
    @NotBlank
    private String line1;
    private String line2;
    @NotNull /*@Pattern(regexp="^[0-9](6)",message="Enter numeric data for quantity")*/
    private int pinCode;
    @NotNull @NotEmpty @NotBlank
    private String state;
    @NotNull @NotEmpty @NotBlank
    private String country;
}
