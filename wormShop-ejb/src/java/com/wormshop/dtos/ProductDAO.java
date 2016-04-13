/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.dtos;

import com.wormshop.entities.Customer;
import com.wormshop.entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tanat
 */
@Stateless
@LocalBean
public class ProductDAO {
    
    @PersistenceContext(unitName = "wormShop-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Product> getAllProduct(){
        
        TypedQuery<Product> findQuery = em.createNamedQuery("Product.findAll", Product.class);
        return findQuery.getResultList();
    }
    
    /**
     * 
     * @param prodId
     * @return instance of found product -> null if not found 
     */
    public Product findProductById(Integer prodId){
        
        TypedQuery<Product> findQuery = em.createNamedQuery("Product.findByProductId", Product.class);
        findQuery.setParameter("productId", prodId);
        return findQuery.getSingleResult();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
