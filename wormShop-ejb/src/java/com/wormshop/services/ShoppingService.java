/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.services;

import com.wormshop.dtos.CustomerDAO;
import com.wormshop.dtos.ProductDAO;
import com.wormshop.entities.Customer;
import com.wormshop.entities.Product;
import java.util.List;
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
public class ShoppingService {
   
    

    protected ShoppingService(){
        
    }
    
     private static ShoppingService shoppingService;
     ProductDAO productDAO = lookupProductDAOBean();
     
     /**
      * 
      * @return singleton instance of ShoppingService itself 
      */ 
     public static  ShoppingService getShoppingService(){
         if(null == shoppingService){
             shoppingService = new ShoppingService();
         }
         return shoppingService;
     }
     
    /**
     * check from db that product amount is enough
     * @param productId
     * @param checkingAmount
     * @return
     */
    public boolean productStockIsAvailable(Integer productId,Integer checkingAmount){
        
        Product findingProduct = productDAO.findProductById(productId);
        
        if( findingProduct.getAmount() > checkingAmount){
            return true;
        }else{
            return false;
        }
    } 
     
     
    /**
     * query all of the product
     * @return list of product object
     */
    public List<Product> getAllProduct(){
        return productDAO.getAllProduct();
    }
    
    
    
    public Product findProductById(Integer prodId){
        return productDAO.findProductById(prodId);
    }
        private ProductDAO lookupProductDAOBean() {
        try {
            Context c = new InitialContext();
            return (ProductDAO) c.lookup("java:global/wormShop/wormShop-ejb/ProductDAO!com.wormshop.dtos.ProductDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
   
   
     
}
