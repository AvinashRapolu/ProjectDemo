package com.avinash.ProjectDEMO.Parts.Customer.Repository;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Integer> {
}
