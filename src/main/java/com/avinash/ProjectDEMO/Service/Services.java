package com.avinash.ProjectDEMO.Service;

import com.avinash.ProjectDEMO.Entity.CustomerAddressEntity;
import com.avinash.ProjectDEMO.Entity.RegisterCustomerEntity;
import com.avinash.ProjectDEMO.Model.RegisterCustomer;
import com.avinash.ProjectDEMO.Repository.RegisterCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {
    @Autowired
    private RegisterCustomerRepository registerCustomerRepository;

    public String add(RegisterCustomer registerCustomer)
    {
        RegisterCustomerEntity rge=new RegisterCustomerEntity();
        rge.setFirstName(registerCustomer.getFirstName());
        rge.setLastName(registerCustomer.getLastName());
        rge.setEmail(registerCustomer.getEmail());
        rge.setMobileNo(registerCustomer.getMobileNo());
        rge.setPassword(registerCustomer.getPassword());

        CustomerAddressEntity cae=new CustomerAddressEntity();
        cae.setLine1(registerCustomer.getCustomerAddress().getLine1());
        cae.setLine2(registerCustomer.getCustomerAddress().getLine2());
        cae.setPinCode(registerCustomer.getCustomerAddress().getPinCode());
        cae.setState(registerCustomer.getCustomerAddress().getState());
        cae.setCountry(registerCustomer.getCustomerAddress().getCountry());
        cae.setShippingAddress(registerCustomer.getCustomerAddress().isShippingAddress());
        cae.setBillingAddress(registerCustomer.getCustomerAddress().isBillingAddress());

        rge.setCustomerAddressEntity(cae);

        registerCustomerRepository.save(rge);
        return "customer is added";
    }
    public List customers()
    {
        return registerCustomerRepository.findAll();
    }
}
