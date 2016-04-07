/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.servlet;

import com.wormshop.services.AuthenticationService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
        int customerId = AuthenticationService.getAuthenticationService().authenticate(username, password);
        if (customerId != -1) {
            Map<Integer, Integer> cart = new HashMap();
            HttpSession session = request.getSession();
            //15 minute inactive session
            session.setMaxInactiveInterval(15 * 60);
            session.setAttribute("customerId", customerId);
            session.setAttribute("username", username);
            session.setAttribute("cart", cart);

            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }

    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) {

    }

}
