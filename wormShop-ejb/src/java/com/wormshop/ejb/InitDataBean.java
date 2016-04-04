/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.ejb;

import com.wormshop.entities.Customer;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tanat
 */
@Singleton
@LocalBean
@Startup
public class InitDataBean {
    @PersistenceContext(unitName = "wormShop-ejbPU")
    private EntityManager em;

//    @PostConstruct
    public void initData(){
        System.out.println("create worm 1");
        
        Customer defaultCustomer = new Customer();
        Customer adminCustomer = new Customer();
        defaultCustomer.setCustomerId(1);
        defaultCustomer.setName("Worm1");
        em.persist(defaultCustomer);
        em.flush();   
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
}
