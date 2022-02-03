package com.avinash.ProjectDEMO.Service;

import com.avinash.ProjectDEMO.Parts.Customer.Entity.AddressEntity;
import com.avinash.ProjectDEMO.Parts.Customer.Entity.RegisterEntity;
import com.avinash.ProjectDEMO.Parts.Customer.Model.AddressModel;
import com.avinash.ProjectDEMO.Parts.Customer.Model.RegisterModel;
import com.avinash.ProjectDEMO.Parts.Customer.Repository.RegisterRepository;
import com.avinash.ProjectDEMO.Security_Login.JasyptService;
import com.avinash.ProjectDEMO.Security_Login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Services {
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    CartService cartService;


    public ResponseEntity<String> addCustomer(RegisterModel registerModel)
    {
        if(registerRepository.findByEmail(registerModel.getEmail()) == null){
        RegisterEntity rce;
        rce = convertCustomerModelToEntity(registerModel);
        List<AddressEntity> addressEntityList =new ArrayList<>();
        rce.setAddressEntity(addressEntityList);
        registerRepository.save(rce);
        return ResponseEntity.status(HttpStatus.CREATED).body("REGISTRATION SUCCESSFUL\n");}
        else if (registerModel == null){
            return ResponseEntity.status(HttpStatus.CREATED).body("customer details should be filled properly. \n");
        }
        else{
            return ResponseEntity.status(HttpStatus.CREATED).body("Email already exist \n");
        }
    }

    public List alldetails()
    {
        return registerRepository.findAll();
    }

    public ResponseEntity<String> addAddress(AddressModel addressModel,String token){
        if(token.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Encryption Required.");
        }
        else if(tokenService.validToken(token))
        {
            RegisterEntity registerEntity = registerRepository.findByEmail(tokenService.getTokenDetails(token));
            List<AddressEntity> addressEntityList;
            addressEntityList = registerEntity.getAddressEntity();
            AddressEntity addressEntity = convertAddressModelToEntity(addressModel);
            addressEntity.setRegisterEntity(registerEntity);

            addressEntityList.add(addressEntity);

            registerRepository.save(registerEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body("Address added. \n");

        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token.");
    }


    public List findByEmail( String encryptedToken)
    {
        if(tokenService.validToken(encryptedToken)){
            if(tokenService.getTokenDetails(encryptedToken).isEmpty()){
                return Arrays.asList( "Please Login");
            }else {
                RegisterEntity customerEntity = registerRepository.findByEmail(tokenService.getTokenDetails(encryptedToken));
                return Arrays.asList(customerEntity);
            }
        }
        else {return Arrays.asList("Invalid Token ...LOGIN AGAIN ");}
    }
//===========================================================================================================================

//converting on CustomerModel  to CustomerEntity
public RegisterEntity convertCustomerModelToEntity(RegisterModel registerModel){
    RegisterEntity customerEntity = new RegisterEntity();
    customerEntity.setFirstName(registerModel.getFirstName());
    customerEntity.setLastName(registerModel.getLastName());
    customerEntity.setEmail(registerModel.getEmail());

    customerEntity.setPassword(new JasyptService().encryption(registerModel.getPassword()));
    customerEntity.setMobileNo(registerModel.getMobileNo());

    return customerEntity;
}

    //converting AddressModel to AddressEntity
    public AddressEntity convertAddressModelToEntity(AddressModel addressModel){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setLine1(addressModel.getLine1());
        addressEntity.setLine2(addressModel.getLine2());
        addressEntity.setCountry(addressModel.getCountry());
        addressEntity.setState(addressModel.getState());
        addressEntity.setPinCode(addressModel.getPinCode());
        addressEntity.setShippingAddress(addressModel.isShippingAddress());
        addressEntity.setBillingAddress(addressModel.isBillingAddress());
        return addressEntity;
    }

    //converting AddressEntity to AddressModel
    public AddressModel convertAddressEntityToModel(AddressEntity addressEntity){
        AddressModel addressModel = new AddressModel();
//        addressModel.set(addressEntity.getAddressId());
        addressModel.setLine1(addressEntity.getLine1());
        addressModel.setLine2(addressEntity.getLine2());
        addressModel.setCountry(addressEntity.getCountry());
        addressModel.setState(addressEntity.getState());
        addressModel.setPinCode(addressEntity.getPinCode());
        addressModel.setBillingAddress(addressEntity.isBillingAddress());
        addressModel.setShippingAddress(addressEntity.isShippingAddress());
        return addressModel;
    }

    //converting CustomerEntity to CustomerModel
    public RegisterModel convertCustomerEntityToModel(RegisterEntity customerEntity) {
        RegisterModel customerModel = new RegisterModel();
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setEmail(customerEntity.getEmail());
        customerModel.setPassword(customerEntity.getEmail());
        customerModel.setPassword(null);
        customerModel.setMobileNo(customerEntity.getMobileNo());

        List<AddressModel> addressModelList = new ArrayList(customerEntity.getAddressEntity());

        customerModel.setAddressModels(addressModelList);
        return customerModel;
    }
//==================================================================================================================
    public String login(String userEmail, String password){
        RegisterEntity registerCustomer = registerRepository.findByEmail(userEmail);
        if(registerCustomer!=null && new JasyptService().decryption(registerCustomer.getPassword()).equals(password)){
            return tokenService.GenerateToken(userEmail);
        }
        else{ return "INVALID_LOGIN";}

    }
    public String logout(String encryptedToken){
        if(tokenService.validToken(encryptedToken) && !tokenService.getTokenDetails(encryptedToken).isEmpty())
            return "Logout Success";
        else{
            return "Not Logged In";
        }
    }
}