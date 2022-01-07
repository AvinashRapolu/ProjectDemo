package com.avinash.ProjectDEMO.Controller;

import com.avinash.ProjectDEMO.Model.RegisterCustomer;
import com.avinash.ProjectDEMO.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlPanel {
    @Autowired
    private Services services;
    @PostMapping("/add")
    public String add(@RequestBody RegisterCustomer registerCustomer)
    {
        return services.add(registerCustomer);
    }
}
