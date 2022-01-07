package com.avinash.ProjectDEMO.Entity;

import javax.persistence.*;

@Entity
public class RegisterCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private long mobileNo;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_address_entity_id")
    private CustomerAddressEntity customerAddressEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public CustomerAddressEntity getCustomerAddressEntity() {
        return customerAddressEntity;
    }

    public void setCustomerAddressEntity(CustomerAddressEntity customerAddressEntity) {
        this.customerAddressEntity = customerAddressEntity;
    }
}
