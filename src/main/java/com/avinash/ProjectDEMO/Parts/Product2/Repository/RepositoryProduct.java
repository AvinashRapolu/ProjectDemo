package com.avinash.ProjectDEMO.Parts.Product2.Repository;

import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntityProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduct extends JpaRepository<EntityProduct,String> {
    EntityProduct findByProductCode(String productCode);
}
