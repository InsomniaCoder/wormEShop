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
    @PersistenceContext(unitName = "wormShop-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 

    
    
    public Customer findByIdAndPassword(String username, String password){

        TypedQuery<Customer> findQuery = em.createQuery("SELECT customer FROM Customer customer WHERE customer.username = :name AND customer.password = :password", Customer.class);
        findQuery.setParameter("name", username);
        findQuery.setParameter("password", password);
        List<Customer> resultList = findQuery.getResultList();
        
        if(1 == resultList.size()){
            Customer foundCustomer = resultList.get(0);
            return foundCustomer;
        }else{
            return null;
        }
    }
    
     public Customer create(String name,String pw){
    Customer c = new Customer();
    String queryStr = " select MAX(c.customerId) from Customer c  ";
        Query query = em.createQuery(queryStr);
        Object result = query.getSingleResult();
        
        if(result==null||result==""){
           c.setCustomerId(1);
           c.setUsername(name);
           c.setPassword(pw);
           em.persist(c);
        }
        else{
           int newPk =(int)result;
           newPk++;
           c.setCustomerId(newPk);
           c.setUsername(name);
           c.setPassword(pw);
           em.persist(c);
        }      
      return c;
    }
     
      public double getCredit(int id){
       String queryStr = " select c from Customer c where c.customerId = ?1   ";
        Query query = em.createQuery(queryStr).setParameter(1,id);
        List<Customer> cus = query.getResultList();
        if(!(cus.isEmpty())){
              Object a = cus.get(0).getCredit();
              Double credit = (Double)a;
              return credit;
        }
        return 0;
   }
   
    
    public boolean debit(int id ,double amount){
       String queryStr = " SELECT c FROM Customer c where c.customerId = ?1   ";
       Query query = em.createQuery(queryStr).setParameter(1,id);
       List<Customer> cus = query.getResultList();
      
       if(!(cus.isEmpty())){
           Object a = cus.get(0).getCredit();
           double credit = (double)a;
             if(amount>credit){
                 return false;
             }
             else{
                 double balanceCredit = credit-amount;
                 String queryStat = " update Customer c set c.credit = ?1 where c.customerId = ?2   ";
                 Query queryUpdate = em.createQuery(queryStat).setParameter(1,balanceCredit).setParameter(2,id);
                 int updateCount = queryUpdate.executeUpdate();
                 return true;
             }
        }
      
       return false;// no id 
        
   }
    

    public void persist(Object object) {
        em.persist(object);
    }

  
}
