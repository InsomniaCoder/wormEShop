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

    private Customer customer = null;
    private Map<Product,Integer> customerCart = new HashMap<Product, Integer>();
    private int getCalled = 0;

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

    public int getGetCalled() {
        return getCalled;
    }

    public void setGetCalled(int getCalled) {
        this.getCalled = getCalled;
    }
}
