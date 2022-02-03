package com.avinash.ProjectDEMO.Parts.cart.Model;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.RegisterEntity;
import com.avinash.ProjectDEMO.Parts.cart.Entity.BillingAddressEntity;
import com.avinash.ProjectDEMO.Parts.cart.Entity.ShippingAddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    private ShippingAddressEntity shippingAddressEntity;
    private BillingAddressEntity billingAddressEntity;
    private RegisterEntity registerEntity;
    private String orderStatus;
}
