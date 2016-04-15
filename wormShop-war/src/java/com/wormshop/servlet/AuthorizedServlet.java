/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.servlet;

import com.wormshop.entities.OrderDetail;
import com.wormshop.entities.OrderStatus;
import com.wormshop.entities.Product;
import com.wormshop.entities.PurchaseOrder;
import com.wormshop.services.AdminService;
import com.wormshop.services.CustomerService;
import com.wormshop.services.ShoppingService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tanat
 */
@WebServlet(name = "AuthorizedServlet", urlPatterns = {"/authorize"})
public class AuthorizedServlet extends HttpServlet {

    AdminService adminService = AdminService.getAdminService();
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
       
            CustomerService customerService;
            customerService = (CustomerService)request.getSession().getAttribute("customer");
            if(customerService == null || !(customerService.getCustomer().getUsername().equalsIgnoreCase("admin"))){
                System.out.println("unauthorized  call");
                return;
            }
           
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            PurchaseOrder confirmingPo = adminService.find(orderId);
            List<OrderDetail> details = confirmingPo.getOrderDetailList();
            
             if (!allItemIsAvailable(details)) {
             //route to page
             System.out.println("some items is not available");
             customerService.getCustomerCart().clear();
             request.setAttribute("result", "Some item you requested is not available");
             request.getRequestDispatcher("result.jsp").forward(request, response);
             return;
        }

       
        if (balanceIsEnough(confirmingPo.getTotal(), shoppingService.getCustomerCredit(confirmingPo.getCustomerId().getCustomerId()))) {
             //debit credit
             shoppingService.debitCustomerCredit(confirmingPo.getCustomerId().getCustomerId(), confirmingPo.getTotal());
             System.out.println("debitted money");
             //debit item
             shoppingService.reduceItemAmountFromList(details);
             System.out.println("debitted stock");
             //set status
             confirmingPo.setOrderStatus(OrderStatus.FINISHED.toString());
             shoppingService.updatePurchaseStatus(confirmingPo);
            //route to page
             request.setAttribute("result", "This transaction is changed to finished! all clear.");
             request.getRequestDispatcher("result.jsp").forward(request, response);  
              return;
        } else {
            //set status
            System.out.println("money is not enough");
            //route to page
            customerService.getCustomerCart().clear();
            request.setAttribute("result", "Your credit is not enough!");
            request.getRequestDispatcher("result.jsp").forward(request, response);
             return;
        } 
    }

    /**
     * check availability of each item fresh by retrieving from db.
     * @param cart
     * @return 
     */
    private boolean allItemIsAvailable(List<OrderDetail> details) {
        for (OrderDetail eachItem :details) {
            if (!shoppingService.productStockIsAvailable(eachItem.getProductId().getProductId(), eachItem.getAmount().intValue())) {
                System.out.println("item "+eachItem.getProductId().getProductName()+"is not enough it has only "+eachItem.getProductId().getAmountStock()+" but"
                        + " you request "+eachItem.getAmount().intValue());
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

}
