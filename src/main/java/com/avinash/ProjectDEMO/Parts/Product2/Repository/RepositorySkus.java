package com.avinash.ProjectDEMO.Parts.Product2.Repository;

import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntitySkus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorySkus extends JpaRepository<EntitySkus,Integer> {
    EntitySkus findBySkuCode(String skuCode);
}
