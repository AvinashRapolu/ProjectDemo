package com.avinash.ProjectDEMO.Parts.cart.Repo;

import com.avinash.ProjectDEMO.Parts.cart.Entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepo extends JpaRepository<ShippingAddressEntity,Integer> {
}
