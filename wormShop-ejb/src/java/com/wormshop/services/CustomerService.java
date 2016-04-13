/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.services;

import com.wormshop.entities.Customer;
import com.wormshop.entities.Product;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author Tanat
 */
@Stateful
@LocalBean
public class CustomerService {

    private double totalPrice;
    private Customer customer = null;
    private Map<Product,Integer> customerCart = new HashMap<Product, Integer>();
    

    public void calculateTotalPrice(){
                
            totalPrice = 0;
            
            for (Map.Entry<Product,Integer> eachItem : customerCart.entrySet()) {
                double price = eachItem.getKey().getProductPrice();
                int amount = eachItem.getValue();
                totalPrice+=(price*amount);
            }
    }
    
    /**
     * return fresh total every time total price get queried
     */
    public double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Map<Product, Integer> getCustomerCart() {
        return customerCart;
    }
}
