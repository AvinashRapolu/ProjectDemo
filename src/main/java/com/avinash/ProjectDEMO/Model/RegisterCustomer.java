package com.avinash.ProjectDEMO.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCustomer {

    private String firstName;
    private String lastName;
    private String email;
    private long mobileNo;
    private String password;
    private CustomerAddress customerAddress;
}
