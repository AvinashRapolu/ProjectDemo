package com.avinash.ProjectDEMO.Parts.Customer.Entity;

import com.avinash.ProjectDEMO.CustomValidations.PhoneNumber;
import com.avinash.ProjectDEMO.Parts.cart.Entity.CartEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties("id2")
    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull @NotBlank @NotEmpty @Email
    private String email;
    @PhoneNumber
    private long mobileNo;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("registerEntity")
    private List<AddressEntity> addressEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("registerEntity")
    private List<CartEntity> cartEntityList;

    @Override
    public String toString() {
        return "RegisterEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                ", password='" + password + '\'' +
                ", addressEntity=" + addressEntity +
                '}';
    }
}