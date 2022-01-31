package com.avinash.ProjectDEMO.Parts.cart.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String customerEmail;
    private String orderCode;
    private String skuCode;
    private int quantity;
}
