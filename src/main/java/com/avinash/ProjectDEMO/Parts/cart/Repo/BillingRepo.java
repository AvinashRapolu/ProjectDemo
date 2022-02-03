package com.avinash.ProjectDEMO.Parts.cart.Repo;

import com.avinash.ProjectDEMO.Parts.cart.Entity.BillingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepo extends JpaRepository<BillingAddressEntity,String> {
}
