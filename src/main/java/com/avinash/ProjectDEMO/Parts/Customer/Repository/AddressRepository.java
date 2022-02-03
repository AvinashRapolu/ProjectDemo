package com.avinash.ProjectDEMO.Parts.Customer.Repository;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.AddressEntity;
import com.avinash.ProjectDEMO.Parts.Customer.Entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity,Integer> {
    List<AddressEntity> findByRegisterEntity(RegisterEntity registerEntity);
}
