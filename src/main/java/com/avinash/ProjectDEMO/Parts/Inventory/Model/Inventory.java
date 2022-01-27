package com.avinash.ProjectDEMO.Parts.Inventory.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private String skuCode;
    private int quantityAvailable;
}
