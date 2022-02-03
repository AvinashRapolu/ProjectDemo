package com.avinash.ProjectDEMO.Parts.cart.Repo;

import com.avinash.ProjectDEMO.Parts.cart.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OrderRepo extends JpaRepository<OrderEntity,String> {
    OrderEntity findByOrderCode(String orderCode);
    @Transactional
    @Modifying
    @Query(value = "UPDATE order_entity u set u.order_status=:status WHERE u.order_code=:ordercode", nativeQuery = true)
    void updateByOrderCode(@Param("ordercode") String orderCode, @Param("status") String status);

}
