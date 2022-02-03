package com.avinash.ProjectDEMO.Service;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.RegisterEntity;
import com.avinash.ProjectDEMO.Parts.Customer.Model.AddressModel;
import com.avinash.ProjectDEMO.Parts.Customer.Repository.AddressRepository;
import com.avinash.ProjectDEMO.Parts.Customer.Repository.RegisterRepository;
import com.avinash.ProjectDEMO.Parts.Inventory.Entity.InventoryEntity;
import com.avinash.ProjectDEMO.Parts.Inventory.Repository.InventoryRepo;
import com.avinash.ProjectDEMO.Parts.Product2.Entity_Product.EntitySkus;
import com.avinash.ProjectDEMO.Parts.Product2.Repository.RepositoryPD;
import com.avinash.ProjectDEMO.Parts.Product2.Repository.RepositoryProduct;
import com.avinash.ProjectDEMO.Parts.Product2.Repository.RepositorySkus;
import com.avinash.ProjectDEMO.Parts.cart.Entity.BillingAddressEntity;
import com.avinash.ProjectDEMO.Parts.cart.Entity.CartEntity;
import com.avinash.ProjectDEMO.Parts.cart.Entity.OrderEntity;
import com.avinash.ProjectDEMO.Parts.cart.Entity.ShippingAddressEntity;
import com.avinash.ProjectDEMO.Parts.cart.Model.Cart;
import com.avinash.ProjectDEMO.Parts.cart.Repo.CartRepo;
import com.avinash.ProjectDEMO.Parts.cart.Repo.OrderRepo;
import com.avinash.ProjectDEMO.Security_Login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartService
{
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
    @Autowired
    RepositoryPD priceDetails;
    @Autowired
    RepositoryProduct repositoryProduct;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    Services services;
    @Autowired
    AddressRepository addressRepository;

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
                if(cartRepo.existsByCustomerEmail(tokenService.getTokenDetails(token))&& cartRepo.existsBySkuCode(cart.getSkuCode()))
                {
                    CartEntity cartEntity =cartRepo.findByCustomerEmailAndSkuCode(tokenService.getTokenDetails(token),cart.getSkuCode());
                    if(quantityA - ( cartEntity.getQuantity() + cart.getQuantity())>=0)
                    {
                    cartRepo.updateByEmailAndSkuCode(tokenService.getTokenDetails(token),cart.getSkuCode(),cart.getQuantity());
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cart is updated.");
                    }
                    else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("updated cart check quantity, \nquantity available="+(quantityA - cartEntity.getQuantity()) );

                }
                else
                {
                    String uniqueID = UUID.randomUUID().toString();
                    CartEntity cartEntity = new CartEntity();
                    cartEntity.setCustomerEmail(tokenService.getTokenDetails(token));
                    cartEntity.setOrderCode(uniqueID);
                    cartEntity.setQuantity(cart.getQuantity());
                    cartEntity.setSkuCode(cart.getSkuCode());
                    registerEntity.getCartEntityList().add(cartEntity);
                    registerRepository.save(registerEntity);
                    //**************************** update inventory ********************************************
//                    EntitySkus entitySkus = repositorySkus.findBySkuCode(cart.getSkuCode());
//                    InventoryEntity ie =new InventoryEntity();
//                    ie.setSkuCode(cart.getSkuCode());
//                    ie.setQuantityAvailable(quantityA- cart.getQuantity());
//                    entitySkus.setInventoryEntity(ie);
//                    repositorySkus.save(entitySkus);
//                    //************************************************************************
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body("product is added to cart");
                }

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
            List<CartEntity> cart =registerEntity.getCartEntityList().stream().collect(Collectors.toList());
            List finalCart=new ArrayList();
            AtomicReference<Double> grandTotal= new AtomicReference<>(0.0);
            cart.forEach(x-> {
                String code = x.getSkuCode();
                int quantity= x.getQuantity();
                Double price= 1.0;

                price= price*(Double.parseDouble(priceDetails.findBySkuCode(x.getSkuCode()).getPrice()));
                 Double total = (price* quantity);
                LinkedHashMap<String, String> hm = new LinkedHashMap<>();
                hm.put("Product",(repositoryProduct.findByProductCode(repositorySkus.findBySkuCode(code)
                                                                                    .getProductCode())).getProductName());
                hm.put("Size",repositorySkus.findBySkuCode(code).getSize());
                hm.put("price",priceDetails.findBySkuCode(x.getSkuCode()).getPrice());
                hm.put("Quantity",String.valueOf(quantity));
                hm.put("Total",total+" RS");
                finalCart.add(hm);
                grandTotal.updateAndGet(v-> v+total);
            });
            finalCart.add("GrandTotal: "+grandTotal+" RS");
            return finalCart;


        }
        else return Arrays.asList("token is invalid.");
    }

//    public ResponseEntity<String> adds(String token){
//        RegisterEntity registerEntity=registerRepository.findByEmail(tokenService.getTokenDetails(token));
//
//        List<AddressEntity> addressModelList =addressRepository.findByRegisterEntity(registerEntity);
//
//            ListIterator<AddressEntity> listIterator =addressModelList.listIterator();
//            listIterator.hasNext()
//        return null;
//    }
    public ResponseEntity<String> placeOrder(String token,AddressModel addressModel)
    {
        RegisterEntity registerEntity=registerRepository.findByEmail(tokenService.getTokenDetails(token));

        AtomicInteger quantityA = new AtomicInteger();
        registerEntity.getCartEntityList().stream().forEach(cart -> {
            quantityA.set(inventoryRepo.findBySkuCode(cart.getSkuCode()).getQuantityAvailable());

            EntitySkus entitySkus = repositorySkus.findBySkuCode(cart.getSkuCode());
            InventoryEntity ie =new InventoryEntity();
            ie.setSkuCode(cart.getSkuCode());
            ie.setQuantityAvailable(quantityA.get() - cart.getQuantity());
            entitySkus.setInventoryEntity(ie);
            repositorySkus.save(entitySkus);
        });
        String uniqueID = UUID.randomUUID().toString();
        OrderEntity orderEntity=new OrderEntity();
            orderEntity.setOrderStatus("received");
            orderEntity.setOrderCode(uniqueID);
//            orderEntity.setShippingAddressEntity(convertAddressModelToShipping(addressModel));
            registerEntity.getOrderEntityList().add(orderEntity);
            registerRepository.save(registerEntity);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order Placed  "+uniqueID);
    }

    public ResponseEntity<String> updateStatus(String orderCode,String OrderStatus)
    {
        orderRepo.updateByOrderCode(orderCode, OrderStatus);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Status Updated");
    }

    public ResponseEntity<String> returnProduct(String token,String skucode,String ordercode )
    {
        RegisterEntity registerEntity=registerRepository.findByEmail(tokenService.getTokenDetails(token));

        OrderEntity orderEntity= orderRepo.findByOrderCode()
////        **************************** update inventory ********************************************
//                    EntitySkus entitySkus = repositorySkus.findBySkuCode(cart.getSkuCode());
//                    InventoryEntity ie =new InventoryEntity();
//                    ie.setSkuCode(cart.getSkuCode());
//                    ie.setQuantityAvailable(quantityA- cart.getQuantity());
//                    entitySkus.setInventoryEntity(ie);
//                    repositorySkus.save(entitySkus);
////                    ************************************************************************
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product returned");
    }




//================================== Conversions =========================================================================
    public ShippingAddressEntity convertAddressModelToShipping(AddressModel addressModel)
    {
        ShippingAddressEntity shipping = new ShippingAddressEntity();
        shipping.setLine1(addressModel.getLine1());
        shipping.setLine2(addressModel.getLine2());
        shipping.setCountry(addressModel.getCountry());
        shipping.setPinCode(addressModel.getPinCode());
        shipping.setState(addressModel.getState());
        return shipping;
    }
    public BillingAddressEntity convertAddressModelToBilling(AddressModel addressModel)
    {
        BillingAddressEntity shipping = new BillingAddressEntity();
        shipping.setLine1(addressModel.getLine1());
        shipping.setLine2(addressModel.getLine2());
        shipping.setCountry(addressModel.getCountry());
        shipping.setPinCode(addressModel.getPinCode());
        shipping.setState(addressModel.getState());
        return shipping;
    }

}
