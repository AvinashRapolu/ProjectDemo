package com.avinash.ProjectDEMO.Parts.Customer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddressModel {
    private String line1;
    private String line2;
    private int pinCode;
    private String state;
    private String country;
    private boolean shippingAddress;
    private boolean billingAddress;

}
