package com.avinash.ProjectDEMO.Repository;

import com.avinash.ProjectDEMO.Entity.RegisterCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterCustomerRepository extends JpaRepository<RegisterCustomerEntity,Integer> {
}
