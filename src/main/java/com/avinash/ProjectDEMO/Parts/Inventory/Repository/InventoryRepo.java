package com.avinash.ProjectDEMO.Parts.Inventory.Repository;

import com.avinash.ProjectDEMO.Parts.Inventory.Entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<InventoryEntity,String> {
    InventoryEntity findBySkuCode(String skuCode);
}
