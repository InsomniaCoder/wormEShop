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
public class AdminService {

    protected AdminService(){
        
    }

     private static AdminService adminService;
     
     ProductDAO productDAO = lookupProductDAOBean();
     OrderDetailDAO orderDetailDAO = lookupOrderDetailDAOBean();
     CustomerDAO customerDAO = lookupCustomerDAOBean();
     PurchaseOrderDAO purchaseOrderDAO = lookupPurchaseOrderDAOBean();
     /**
      * 
      * @return singleton instance of ShoppingService itself 
      */ 
     public static  AdminService getAdminService(){
         if(null == adminService){
             adminService = new AdminService();
         }
         return adminService;
     }
     
    public List<PurchaseOrder> getAllWaitingOrder(){
        return purchaseOrderDAO.findAllWaitingOrder();
    }
    
    public PurchaseOrder find(int poId){
        return purchaseOrderDAO.findPurchaseOrderById(poId);
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
