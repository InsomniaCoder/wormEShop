/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.dtos;

import com.wormshop.entities.Customer;
import com.wormshop.entities.PurchaseOrder;
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
public class PurchaseOrderDAO {
    @PersistenceContext(unitName = "wormShop-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

      public void create(PurchaseOrder prOrder){
            
        String queryStr = " select MAX(p.purchaseId) from PurchaseOrder p  ";
        Query query =   em.createQuery(queryStr);
        Object result = query.getSingleResult();
        
        if(result==null||result==""){
           prOrder.setPurchaseId(1);
           em.persist(prOrder);
        }
        else{
           int currentMaxPk =(int)result;
           currentMaxPk++;
           prOrder.setPurchaseId(currentMaxPk);
          
           em.persist(prOrder);
        }      
    }
      
     public void setOrderStatus(int id,String status){
    String queryStat = " update PurchaseOrder c set c.orderStatus = ?1 where c.purchaseId = ?2   ";
    Query queryUpdate = em.createQuery(queryStat).setParameter(1,status).setParameter(2,id);
    queryUpdate.executeUpdate();
    }

    public List<PurchaseOrder> findAllWaitingOrder() {
        String findWaitingQuery = "SELECT po FROM PurchaseOrder po WHERE po.orderStatus = 'WAITING'";
        TypedQuery<PurchaseOrder> waitingQuery = em.createQuery(findWaitingQuery, PurchaseOrder.class);
        return waitingQuery.getResultList();
    }

    public PurchaseOrder findPurchaseOrderById(int poId) {
        return em.find(PurchaseOrder.class, poId);
    }
   
}
