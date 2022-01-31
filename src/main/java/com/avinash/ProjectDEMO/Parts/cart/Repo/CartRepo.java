package com.avinash.ProjectDEMO.Parts.cart.Repo;

import com.avinash.ProjectDEMO.Parts.cart.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<CartEntity,String> {
}
