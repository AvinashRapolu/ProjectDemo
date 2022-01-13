package com.avinash.ProjectDEMO.Product2.Model_Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDetails {
    private String skuCode_pd;
    private String price;
    private  String currency;
}
