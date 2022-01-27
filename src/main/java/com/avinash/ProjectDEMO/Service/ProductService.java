package com.avinash.ProjectDEMO.Service;

import com.avinash.ProjectDEMO.Parts.Inventory.Entity.InventoryEntity;
import com.avinash.ProjectDEMO.Parts.Inventory.Model.Inventory;
import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntityPriceDetails;
import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntityProduct;
import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntitySkus;
import com.avinash.ProjectDEMO.Parts.Product2.Model_Product.PriceDetails;
import com.avinash.ProjectDEMO.Parts.Product2.Model_Product.Product;
import com.avinash.ProjectDEMO.Parts.Product2.Model_Product.Skus;
import com.avinash.ProjectDEMO.Parts.Product2.Repository.RepositoryProduct;
import com.avinash.ProjectDEMO.Parts.Product2.Repository.RepositorySkus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    RepositoryProduct repositoryProduct;
    @Autowired
    RepositorySkus repositorySkus;


    public String addProduct(Product product)
    {
        Optional<EntityProduct> productEntity = Optional.ofNullable(repositoryProduct.findByProductCode(product.getProductCode()));
        if(productEntity.isPresent()){
            return "product already exist with this product code.";
        }
        else{
        EntityProduct entityProduct = new EntityProduct();

        List<EntitySkus> entitySkusList = new ArrayList();

        entityProduct.setProductCode(product.getProductCode());
        entityProduct.setProductName(product.getProductName());
        entityProduct.setDescription(product.getDescription());

        entityProduct.setEntitySkus(entitySkusList);
        repositoryProduct.save(entityProduct);
        return "product is added.";
        }
    }

    public String addSkus(Skus skus)
    {
        Optional<EntityProduct> productEntity = Optional.ofNullable(repositoryProduct.findByProductCode(skus.getProductCode()));
        if(productEntity.isPresent()){
            EntitySkus entitySkus = new EntitySkus();
            entitySkus.setSkuCode(skus.getSkuCode());
            entitySkus.setSize(skus.getSize());
            entitySkus.setProductCode(skus.getProductCode());
            entitySkus.setProducts(productEntity.get());
            productEntity.get().getEntitySkus().add(entitySkus);
            repositoryProduct.save(productEntity.get());
            return "sku is added.";
        }
        else
        {
            return "no product found.";
        }
    }
    public String addPrice(PriceDetails priceDetails)
    {
        Optional<EntitySkus> entitySkus = Optional.ofNullable(repositorySkus.findBySkuCode(priceDetails.getSkuCode()));
        if(entitySkus.isPresent()) {
            EntityPriceDetails priceDetails1 = new EntityPriceDetails();
            priceDetails1.setPrice(priceDetails.getPrice());
            priceDetails1.setCurrency(priceDetails.getCurrency());
            priceDetails1.setSkuCode(priceDetails.getSkuCode());
            entitySkus.get().setEntityPriceDetails(priceDetails1);
            repositorySkus.save(entitySkus.get());
            return "Price added";
        }
        else {return "Check the sku code.";}
    }
    public List allProducts()
    {
        return repositoryProduct.findAll();
    }

    public ResponseEntity<String> inv(Inventory inventory)
    {
        Optional<EntitySkus> entitySkus = Optional.ofNullable(repositorySkus.findBySkuCode(inventory.getSkuCode()));
        if (entitySkus.isPresent())
        {
            InventoryEntity ie =new InventoryEntity();
            ie.setSkuCode(inventory.getSkuCode());
            ie.setQuantityAvailable(inventory.getQuantityAvailable());
            entitySkus.get().setInventoryEntity(ie);
            repositorySkus.save(entitySkus.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("inventory is updated.");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no Product found");
    }
}
