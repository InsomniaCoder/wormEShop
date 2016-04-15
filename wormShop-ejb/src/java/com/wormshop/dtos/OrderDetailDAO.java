/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.dtos;

import com.wormshop.entities.OrderDetail;
import com.wormshop.entities.PurchaseOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tanat
 */
@Stateless
@LocalBean
public class OrderDetailDAO {
    @PersistenceContext(unitName = "wormShop-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List findAll(){
       List<OrderDetail> product = (List<OrderDetail>) em.createNamedQuery("OrderDetail.findAll").getResultList();
        return product;
       }
    
     public void create(OrderDetail OrderDetail){
            
        String queryStr = " select MAX(od.orderId) from OrderDetail od  ";
        Query query =   em.createQuery(queryStr);
        Object result = query.getSingleResult();
        
        if(result==null||result==""){
           OrderDetail.setOrderId(1);
           em.persist(OrderDetail);
        }
        else{
           int currentMaxPk =(int)result;
           currentMaxPk++;
           OrderDetail.setOrderId(currentMaxPk);
           em.persist(OrderDetail);
        }      
    }

}
