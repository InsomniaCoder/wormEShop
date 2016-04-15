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
import javax.persistence.Query;
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
      public int getAmount(int productId){
   
        String queryStr = " select p.amountStock from Product p where p.productId = ?1   ";
        Query query = em.createQuery(queryStr).setParameter(1,productId);
        Object amountQuery = query.getSingleResult();
        int amount = (int)amountQuery;
        return amount;
    }
   
       public void updateProductAmount(Product product ,double amount){
         Product foundProduct = em.find(Product.class, product.getProductId());
         int reductAmount = (int)amount;
         foundProduct.setAmountStock(product.getAmountStock() - reductAmount);
         em.persist(foundProduct);
         em.flush();
   }
}
