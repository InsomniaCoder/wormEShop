/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.servlet;

import com.wormshop.entities.Customer;
import com.wormshop.services.AuthenticationService;
import com.wormshop.services.CustomerService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/authentication"})
public class AuthenticationServlet extends HttpServlet {
    

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

       
        
        System.out.println("read name : " + request.getParameter("username"));
        System.out.println("read password : " + request.getParameter("password"));
        System.out.println("read process : " + request.getParameter("process"));

    
            String action = request.getParameter("process");
            if("authenticate".equals(action)) {
                System.out.println("authentication call");
                doAuthenticate(request, response);
            } else if ("signup".equals(action)) {
                System.out.println("signup call");
                doRegister(request, response);
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

    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer foundCustomer = AuthenticationService.getAuthenticationService().authenticate(username, password);
        if (foundCustomer != null) {
             
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(60*60);
            
            //30 minute inactive session
                System.out.println("user : "+foundCustomer.getUsername()+" is logging in ...");
                 session.setMaxInactiveInterval(30 * 60);
                 CustomerService customerService = lookupCustomerServiceBean();
                 customerService.setCustomer(foundCustomer);
                 session.setAttribute("customer", customerService);  
                 //request.getRequestDispatcher("shop.jsp").forward(request, response);
                 response.sendRedirect("shop.jsp");
                  return;
            
        } else {
                 response.sendRedirect("error.jsp");
                  return;
        }

    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) {

    }

    private CustomerService lookupCustomerServiceBean() {
        try {
            Context c = new InitialContext();
            return (CustomerService) c.lookup("java:global/wormShop/wormShop-ejb/CustomerService!com.wormshop.services.CustomerService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
