package com.avinash.ProjectDEMO.Parts.cart.Repo;

import com.avinash.ProjectDEMO.Parts.cart.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,String> {
    CartEntity findByCustomerEmailAndSkuCode(String registerEntity,String skuCode);
    CartEntity findByCustomerEmail(String registerEntity);


    boolean existsByCustomerEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE cart_entity u set u.quantity= quantity + :qty WHERE u.customer_email=:email and u.sku_code=:sku", nativeQuery = true)
            void updateByEmailAndSkuCode(@Param("email") String email,@Param("sku") String sku,@Param("qty") Integer qty);
    boolean existsBySkuCode(String skuCode);

}
