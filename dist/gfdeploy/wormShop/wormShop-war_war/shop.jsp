<%--
  Created by IntelliJ IDEA.
  User: GiftzyEiei
  Date: 2/4/2559
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.wormshop.services.CustomerService"%>
<%@page import="com.wormshop.entities.Customer"%>
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
        CustomerService sessionCustomer = (CustomerService)session.getAttribute("customer");
    if (null == sessionCustomer) {
            request.getRequestDispatcher("index.jsp");
        }
        Customer customer = sessionCustomer.getCustomer();
    %>
    <title>Worm Shop</title>
</head>
<body>
    <div id="header" style="margin-bottom: 10px; float: right; padding-right: 10%;padding-top: 2%;height: 5%;">
        Welcome    <%= customer.getName()%>
    </div>
       
    <div id="productDiv" style="float: left; clear: both; padding-left: 10px;width: 75%;border:  blue;">
        <table style="width:50%;margin:4% 25% 25% 25%;" border="0" align="center" cellpadding="0" cellspacing="2">
            <tr>
              <%    
        
              ShoppingService shoppingService = ShoppingService.getShoppingService(); 
              List<Product> productList = shoppingService.getAllProduct();
              for(int i = 0; i < productList.size(); i++){
                  
               String eachItem = "<td> <div id=\"item\""+i+">"+
                       productList.get(i).getProductName()+ "</br>"+
                       "price = "+productList.get(i).getProductPrice()+"</br>"+
                       "<input type=\"button\" onClick=\" \" value=\"add to cart\">"+
                       "</div></td>";
               out.println(eachItem);   
              }
               %> 
        </tr>
        </table>
    </div>
    
    <div  id="cartDiv" style="float: right;padding-right: 10px;width: 20%;">
            Cart
    </div>
</body>
</html>
