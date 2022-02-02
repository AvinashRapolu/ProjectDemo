package com.avinash.ProjectDEMO.Controller;

import com.avinash.ProjectDEMO.Parts.Customer.Model.AddressModel;
import com.avinash.ProjectDEMO.Parts.Customer.Model.RegisterModel;
import com.avinash.ProjectDEMO.Parts.Inventory.Model.Inventory;
import com.avinash.ProjectDEMO.Parts.Product2.Model_Product.PriceDetails;
import com.avinash.ProjectDEMO.Parts.Product2.Model_Product.Product;
import com.avinash.ProjectDEMO.Parts.Product2.Model_Product.Skus;
import com.avinash.ProjectDEMO.Parts.cart.Model.Cart;
import com.avinash.ProjectDEMO.Security_Login.Login;
import com.avinash.ProjectDEMO.Service.CartService;
import com.avinash.ProjectDEMO.Service.ProductService;
import com.avinash.ProjectDEMO.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ControlPanel {
    @Autowired
    private Services services;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
//============================================== login ==========================================================================

    @RequestMapping(value ="/login")
    public String login(@Valid @RequestBody Login loginModel)  {
        String userEmail = loginModel.getUserEmail();
        String password = loginModel.getPassword();
        return services.login(userEmail, password);
    }

    @RequestMapping(value="/logout")
    public String logout(@RequestHeader String encryptedToken){
        return services.logout(encryptedToken);
    }
//========================================CUSTOMER===============================================================================

    @PostMapping("/add-customer")
    public ResponseEntity<String> add(@Valid @RequestBody RegisterModel registerModel)
    {
        return services.addCustomer(registerModel);
    }
    @PutMapping("/add-address/")
    public ResponseEntity<String> addAddress(@Valid @RequestBody AddressModel addressModel,@RequestHeader String token){
        return services.addAddress(addressModel,token);
    }

    @GetMapping("/customer")
    public List findcustomer( @RequestHeader String encripted)
    {
        return services.findByEmail(encripted);
    }

    @GetMapping("/all-customers")
    public List<RegisterModel> customers()
    {
        return services.alldetails();
    }
//===============================================PRODUCT===========================================================================
    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product product)
    {
        return productService.addProduct(product);
    }
    @PostMapping("/add-sku")
    public String addSku(@RequestBody Skus skus)
    {
        return productService.addSkus(skus);
    }
    @PostMapping("/add-price")
    public String addPrice(@RequestBody PriceDetails priceDetails)
    {
        return productService.addPrice(priceDetails);
    }

    @GetMapping("all-products")
    public List<Product> allproducts()
    {
        return productService.allProducts();
    }

//============================================INVENTORY===========================================================================

    @PostMapping("/inventory")
    public ResponseEntity<String> inv(@RequestBody Inventory inventory)
    {
        return productService.inv(inventory);
    }

    @GetMapping("/all-inventory")
    public List inventory()
    {
        return productService.inventory();
    }
//===================================================CART======================================================================
    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addCart(@RequestBody Cart cart,@RequestHeader String token)
    {
        return cartService.addCart(cart,token);
    }
    @GetMapping("/cart")
    public List allCart(@RequestHeader String token)
    {
        return cartService.allCart(token);
    }

//    @PostMapping("/update-cart")
//    public ResponseEntity<String> updateCart(@RequestHeader String token,@RequestBody Cart cart)
//    {
//        return cartService.updateCart(token, cart);
//    }
}