/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.servlet;

import com.wormshop.entities.OrderDetail;
import com.wormshop.entities.Product;
import com.wormshop.entities.PurchaseOrder;
import com.wormshop.services.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
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
         //get current total price
         double totalPrice = customerService.getTotalPrice();
         //create order
         PurchaseOrder order = new PurchaseOrder();
         //crate all order detail 
         createAndPutAllOrderDetail(order, customerService);
         
         
         
         
         
         
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

    private void createAndPutAllOrderDetail(PurchaseOrder order) {
           
    }

    private void createAndPutAllOrderDetail(PurchaseOrder order, CustomerService customerService) {
      
        Map<Product,Integer> cart = customerService.getCustomerCart();
       
//           for (Map.Entry<Product,Integer> eachItem : cart)) {
//               
//               OrderDetail orderDetail = new OrderDetail();
//               
//               
//               
//            }
        
       
        
    }

}
