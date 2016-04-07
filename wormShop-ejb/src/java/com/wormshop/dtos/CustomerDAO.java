/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.dtos;

import com.wormshop.entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tanat
 */
@Stateless
@LocalBean
public class CustomerDAO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "wormShop-ejbPU")
    private EntityManager em;

    
    
    public Integer findByIdAndPassword(String username, String password){

        Customer findingCustomer = new Customer();
        findingCustomer.setName(username);
        findingCustomer.setPassword(password);
        
        TypedQuery<Customer> findQuery = em.createQuery("SELECT customer FROM Customer customer WHERE customer.name = :name AND customer.password = :password", Customer.class);
        findQuery.setParameter("name", username);
        findQuery.setParameter("password", password);
        List<Customer> resultList = findQuery.getResultList();
        
        if(1 == resultList.size()){
            Customer foundCustomer = resultList.get(0);
            return foundCustomer.getCustomerId();
        }else{
            return -1;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
}
