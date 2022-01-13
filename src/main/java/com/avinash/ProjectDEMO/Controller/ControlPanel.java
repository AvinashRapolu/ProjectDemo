package com.avinash.ProjectDEMO.Controller;

import com.avinash.ProjectDEMO.Model.RegisterCustomer;
import com.avinash.ProjectDEMO.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ControlPanel {
    @Autowired
    private Services services;

    @PostMapping("/add-customer")
    public String add(@Valid @RequestBody RegisterCustomer registerCustomer)
    {
        return services.add(registerCustomer);
    }

    @GetMapping("/all-customers")
    public List customers()
    {
        return services.customers();
    }
}