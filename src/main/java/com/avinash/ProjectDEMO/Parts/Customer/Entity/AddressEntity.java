package com.avinash.ProjectDEMO.Parts.Customer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id2;
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

    @ManyToOne
    @JsonIgnoreProperties("addressEntity")
    private RegisterEntity registerEntity;

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id2=" + id2 +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", pinCode=" + pinCode +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", registerEntity=" + registerEntity +
                '}';
    }
}
