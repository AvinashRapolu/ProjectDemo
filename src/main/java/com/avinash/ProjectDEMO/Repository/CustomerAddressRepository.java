package com.avinash.ProjectDEMO.Repository;

import com.avinash.ProjectDEMO.Entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity,Integer> {
}
