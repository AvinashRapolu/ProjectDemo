package com.avinash.ProjectDEMO.Entity;

import com.avinash.ProjectDEMO.CustomValidations.Password;
import com.avinash.ProjectDEMO.CustomValidations.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegisterCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @NotNull @NotBlank @NotEmpty @Email
    private String email;
    @PhoneNumber
    private long mobileNo;
    @Password
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private CustomerAddressEntity customerAddressEntity;
}
