package com.avinash.ProjectDEMO.Parts.Inventory.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InventoryEntity {
    @Id
    private String skuCode;
    private int quantityAvailable;
}
