package com.avinash.ProjectDEMO.Product2.Model_Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productCode;
    private String productName;
    private String description;
}
