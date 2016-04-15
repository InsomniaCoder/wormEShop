/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.services;

import com.wormshop.dtos.CustomerDAO;
import com.wormshop.dtos.OrderDetailDAO;
import com.wormshop.dtos.ProductDAO;
import com.wormshop.dtos.PurchaseOrderDAO;
import com.wormshop.entities.Customer;
import com.wormshop.entities.OrderDetail;
import com.wormshop.entities.Product;
import com.wormshop.entities.PurchaseOrder;
import java.util.List;
import java.util.Map;
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
     OrderDetailDAO orderDetailDAO = lookupOrderDetailDAOBean();
     CustomerDAO customerDAO = lookupCustomerDAOBean();
     PurchaseOrderDAO purchaseOrderDAO = lookupPurchaseOrderDAOBean();
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
     
    /*****************
     * Shop page
     */
     
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
   
      /**********
       * purchasing 
       */  
    public void saveOrder(PurchaseOrder order){
       purchaseOrderDAO.create(order);
    }
    public void saveOrderDetails(List<OrderDetail> detailList){
       for(OrderDetail orderDetail : detailList){
           orderDetailDAO.create(orderDetail);
       }
    }
        
        /**
     * check from db that product amount is enough
     * @param productId id of the finding product
     * @param checkingAmount required amount to check
     * @return
     */
    public boolean productStockIsAvailable(Integer productId,Integer checkingAmount){
        
        Product findingProduct = productDAO.findProductById(productId);
        
        if( findingProduct.getAmountStock() >= checkingAmount){
            return true;
        }else{
            return false;
        }
    } 

    public void updatePurchaseStatus(PurchaseOrder purchaseOrder){
        purchaseOrderDAO.setOrderStatus(purchaseOrder.getPurchaseId(),purchaseOrder.getOrderStatus());
    }
    
    public double getCustomerCredit(Integer custId){
        return customerDAO.getCredit(custId);
    }
    
    public void debitCustomerCredit(Integer custId,Double totalPrice){
          customerDAO.debit(custId, totalPrice);
    }
    
    public void reduceItemAmount(Map<Product, Integer> customerCart) {
        for (Map.Entry<Product, Integer> eachItem : customerCart.entrySet()) {
            productDAO.updateProductAmount(eachItem.getKey(),eachItem.getValue());
        }
    }
    
     public void reduceItemAmountFromList(  List<OrderDetail> details) {
        for (OrderDetail eachItem : details) {
            productDAO.updateProductAmount(eachItem.getProductId(),eachItem.getAmount());
        }
    }

    private CustomerDAO lookupCustomerDAOBean() {
        try {
            Context c = new InitialContext();
            return (CustomerDAO) c.lookup("java:global/wormShop/wormShop-ejb/CustomerDAO!com.wormshop.dtos.CustomerDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private OrderDetailDAO lookupOrderDetailDAOBean() {
        try {
            Context c = new InitialContext();
            return (OrderDetailDAO) c.lookup("java:global/wormShop/wormShop-ejb/OrderDetailDAO!com.wormshop.dtos.OrderDetailDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PurchaseOrderDAO lookupPurchaseOrderDAOBean() {
        try {
            Context c = new InitialContext();
            return (PurchaseOrderDAO) c.lookup("java:global/wormShop/wormShop-ejb/PurchaseOrderDAO!com.wormshop.dtos.PurchaseOrderDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
