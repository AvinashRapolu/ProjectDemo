package com.avinash.ProjectDEMO.Parts.Product2.Entity_Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EntityPriceDetails {
    @Id
    private String skuCode;
    private String price;
    private  String currency;
}