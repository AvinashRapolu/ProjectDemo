package com.avinash.ProjectDEMO.Parts.Customer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterModel {

    private String firstName;
    private String lastName;
    private String email;
    private long mobileNo;
    private String password;
    private List<AddressModel> addressModels;
}
