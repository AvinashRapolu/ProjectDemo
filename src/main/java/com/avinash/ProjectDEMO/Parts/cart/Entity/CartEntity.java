package com.avinash.ProjectDEMO.Parts.cart.Entity;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.RegisterEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private String customerEmail;
    private String orderCode;
    private String skuCode;
    private int quantity;

    @ManyToOne
    @JsonIgnoreProperties("cartEntityList")
    private RegisterEntity registerEntity;
}
