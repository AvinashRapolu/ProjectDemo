package com.avinash.ProjectDEMO.Parts.Product2.Entity_Product;

import com.avinash.ProjectDEMO.Parts.Inventory.Entity.InventoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EntitySkus {
    @Id
    private String skuCode;
    private String productCode;
    private String size;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("entitySkus")
    private EntityProduct products;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EntityPriceDetails entityPriceDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private InventoryEntity inventoryEntity;
}
