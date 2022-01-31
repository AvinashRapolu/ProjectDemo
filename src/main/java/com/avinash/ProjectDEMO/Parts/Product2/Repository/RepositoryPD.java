package com.avinash.ProjectDEMO.Parts.Product2.Repository;

import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntityPriceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPD extends JpaRepository<EntityPriceDetails,String> {
    EntityPriceDetails findBySkuCode(String skuCode);
}
