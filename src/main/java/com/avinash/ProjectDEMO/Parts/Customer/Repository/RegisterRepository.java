package com.avinash.ProjectDEMO.Parts.Customer.Repository;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<RegisterEntity,Integer > {
    RegisterEntity findByEmail(String email);
}
