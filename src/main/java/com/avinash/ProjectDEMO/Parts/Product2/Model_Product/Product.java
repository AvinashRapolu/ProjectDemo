package com.avinash.ProjectDEMO.Parts.Product2.Model_Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productCode;
    private String productName;
    private String description;
    private List<Skus> skus;
    private List<PriceDetails> pd;
}
