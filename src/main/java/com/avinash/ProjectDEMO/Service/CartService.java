package com.avinash.ProjectDEMO.Service;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.RegisterEntity;
import com.avinash.ProjectDEMO.Parts.Customer.Repository.RegisterRepository;
import com.avinash.ProjectDEMO.Parts.Inventory.Entity.InventoryEntity;
import com.avinash.ProjectDEMO.Parts.Inventory.Repository.InventoryRepo;
import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntitySkus;
import com.avinash.ProjectDEMO.Parts.Product2.Repository.RepositorySkus;
import com.avinash.ProjectDEMO.Parts.cart.Entity.CartEntity;
import com.avinash.ProjectDEMO.Parts.cart.Model.Cart;
import com.avinash.ProjectDEMO.Parts.cart.Repo.CartRepo;
import com.avinash.ProjectDEMO.Security_Login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CartService {
    @Autowired
    InventoryRepo inventoryRepo;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    RepositorySkus repositorySkus;
    @Autowired
    TokenService tokenService;

    public ResponseEntity<String> addCart(Cart cart, String token)
    {
        if(token.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login to get token.");
        }
        else if(tokenService.validToken(token)) {
            RegisterEntity registerEntity =registerRepository.findByEmail(tokenService.getTokenDetails(token));
            int quantityA = inventoryRepo.findBySkuCode(cart.getSkuCode()).getQuantityAvailable();
            if(quantityA- cart.getQuantity()>=0)
            {
                CartEntity cartEntity=new CartEntity();
                cartEntity.setCustomerEmail(cart.getCustomerEmail());
                cartEntity.setOrderCode(cart.getOrderCode());
                cartEntity.setQuantity(cart.getQuantity());
                cartEntity.setSkuCode(cart.getSkuCode());
                registerEntity.getCartEntityList().add(cartEntity);
                registerRepository.save(registerEntity);
                //**************************** update inventory ********************************************
               /* EntitySkus entitySkus = repositorySkus.findBySkuCode(cart.getSkuCode());
                    InventoryEntity ie =new InventoryEntity();
                    ie.setSkuCode(cart.getSkuCode());
                    ie.setQuantityAvailable(quantityA- cart.getQuantity());
                    entitySkus.setInventoryEntity(ie);
                    repositorySkus.save(entitySkus); */
                //************************************************************************
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("product is added to cart");
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("limited stock; available = "+quantityA);

        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("token is invalid.");
    }
    public List allCart(String token)
    {
        if(token.isEmpty())
        {
            return Arrays.asList("Login to get token.");
        }
        else if(tokenService.validToken(token)) {
            RegisterEntity registerEntity =registerRepository.findByEmail(tokenService.getTokenDetails(token));
                return registerEntity.getCartEntityList();

        }
        else return Arrays.asList("token is invalid.");
    }
    public ResponseEntity<String> removeFromCart()
    {

    }
}
