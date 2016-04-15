/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.servlet;

import com.wormshop.entities.Customer;
import com.wormshop.entities.OrderDetail;
import com.wormshop.entities.OrderStatus;
import com.wormshop.entities.Product;
import com.wormshop.entities.PurchaseOrder;
import com.wormshop.services.CustomerService;
import com.wormshop.services.ShoppingService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tanat
 */
@WebServlet(name = "OrderProcessingServlet", urlPatterns = {"/placeOrder"})
public class OrderProcessingServlet extends HttpServlet {

    ShoppingService shoppingService = ShoppingService.getShoppingService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        CustomerService customerService = (CustomerService) session.getAttribute("customer");
        Customer customer = customerService.getCustomer();
        if (customerService == null) {
            System.out.println("unauthorized placeorder call");
            return;
        }

         //get current total price, calculation is already encapsulated in the getTotalPrice method.
        double totalPrice = customerService.getTotalPrice();
        
        //create order
        PurchaseOrder order = new PurchaseOrder();
        order.setOrderStatus(OrderStatus.NEW.toString());
        order.setCustomerId(customer);
        order.setTotal(totalPrice);
        shoppingService.saveOrder(order);
        System.out.println("order is saved");
        //crate all order detail 
        createAndPutAllOrderDetail(order, customerService);
        System.out.println("added all order details");
        //checkItemAvailability
        if (!allItemIsAvailable(customerService.getCustomerCart())) {
            //set status and update
            order.setOrderStatus(OrderStatus.WAITING.toString());
            shoppingService.updatePurchaseStatus(order);
            //route to page
            System.out.println("some items is not available");
            customerService.getCustomerCart().clear();
            request.setAttribute("result", "Some item you requested is not available");
            request.getRequestDispatcher("result.jsp").forward(request, response);
            return;
        }

       
        if (balanceIsEnough(totalPrice, shoppingService.getCustomerCredit(customer.getCustomerId()))) {
             //debit credit
             shoppingService.debitCustomerCredit(customer.getCustomerId(), totalPrice);
             System.out.println("debitted money");
             //debit item
             shoppingService.reduceItemAmount(customerService.getCustomerCart());
             System.out.println("debitted stock");
             //set status
             order.setOrderStatus(OrderStatus.FINISHED.toString());
             shoppingService.updatePurchaseStatus(order);
            //route to page
             customerService.getCustomerCart().clear();
             request.setAttribute("result", "Your transaction is finished! items will be delivered to you soon.");
             request.getRequestDispatcher("result.jsp").forward(request, response);  
              return;
        } else {
            //set status
            System.out.println("money is not enough");
            order.setOrderStatus(OrderStatus.WAITING.toString());
            shoppingService.updatePurchaseStatus(order);
            //route to page
            customerService.getCustomerCart().clear();
            request.setAttribute("result", "Your credit is not enough!");
            request.getRequestDispatcher("result.jsp").forward(request, response);
             return;
        }
    }

    private void createAndPutAllOrderDetail(PurchaseOrder order, CustomerService customerService) {

        List<OrderDetail> savingDetails = new ArrayList<OrderDetail>();

        Map<Product, Integer> cart = customerService.getCustomerCart();
        for (Map.Entry<Product, Integer> eachItem : cart.entrySet()) {

            Product item = eachItem.getKey();
            Integer amount = eachItem.getValue();
            double recordTotalPrice = item.getProductPrice() * amount;

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setPurchaseId(order);
            orderDetail.setProductId(item);
            orderDetail.setAmount(amount + 0.0);
            orderDetail.setTotal(recordTotalPrice);

            savingDetails.add(orderDetail);
        }
        shoppingService.saveOrderDetails(savingDetails);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * check availability of each item fresh by retrieving from db.
     * @param cart
     * @return 
     */
    private boolean allItemIsAvailable(Map<Product, Integer> cart) {
        for (Map.Entry<Product, Integer> eachItem : cart.entrySet()) {
            if (!shoppingService.productStockIsAvailable(eachItem.getKey().getProductId(), eachItem.getValue())) {
                System.out.println("item "+eachItem.getKey().getProductName()+"is not enough it has only "+eachItem.getKey().getAmountStock()+" but"
                        + " you request "+eachItem.getValue());
                return false;
            }
        }
        return true;
    }

    private boolean balanceIsEnough(double totalPrice, double credit) {
        if (credit < totalPrice) {
            return false;
        } else {
            return true;
        }
    }
}
