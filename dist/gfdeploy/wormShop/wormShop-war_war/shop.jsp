<%--
  Created by IntelliJ IDEA.
  User: GiftzyEiei
  Date: 2/4/2559
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="java.util.*"%>
<%@page import="com.wormshop.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.wormshop.services.ShoppingService"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%  
    if (null == session.getAttribute("customerId")) {
            request.getRequestDispatcher("index.jsp");
        }
    %>
    <title>Worm Shop</title>
</head>
<body>
    <div style="float: right; padding-right: 15px;">
        Welcome    <%= session.getAttribute("username") %>
    </div>
       
    <div style="float: left; clear: both; padding-left:  10px;">
        <%    ShoppingService shoppingService = ShoppingService.getShoppingService(); 
              List<Product> productList = shoppingService.getAllProduct();
              for(int i = 0; i < productList.size(); i++){
               out.println(productList.get(i).getProductName());   
              }
              
        
        %> 
    </div>
    
    <div style="float: right; clear: both;padding-right: 10px;">
            cart
    </div>
</body>
</html>
