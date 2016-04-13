/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.services;

import com.wormshop.dtos.CustomerDAO;
import com.wormshop.entities.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Tanat
 */
public class AuthenticationService {
    

    protected AuthenticationService(){
            
    }
    
     private static AuthenticationService authenticationService;
     private CustomerDAO customerDAO = lookupCustomerDTOBean();
     
     
     public static  AuthenticationService getAuthenticationService(){
         if(null == authenticationService){
             authenticationService = new AuthenticationService();
         }
         return authenticationService;
     }
    
     public Customer authenticate(String username, String password){
        return  customerDAO.findByIdAndPassword(username, password);
     }

    private CustomerDAO lookupCustomerDTOBean() {
        try {
            Context c = new InitialContext();
            return (CustomerDAO) c.lookup("java:global/wormShop/wormShop-ejb/CustomerDAO!com.wormshop.dtos.CustomerDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
     
}
