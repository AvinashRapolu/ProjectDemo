package com.avinash.ProjectDEMO.Security_Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Email(message = "enter a valid email")
    private String userEmail;
    private String password;
}
