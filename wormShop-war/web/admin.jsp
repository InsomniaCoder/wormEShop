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
    <title>Admin authorize page</title>
</head>
<body>
    <div id="header" style="margin-bottom: 10px; float: right; padding-right: 10%;padding-top: 2%;height: 5%;">
       Welcome    <%= customer.getUsername()%>
    </div>
       
    <div id="waitingDiv" style="float: left; clear: both; padding-left: 10px;width: 75%;border:  blue;">
        <table style="width:70%;margin:4% 25% 25% 25%;" border="0" align="center" cellpadding="0" cellspacing="25">
            <tbody style="width: 80%;">
              <%    
                ShoppingService shoppingService = ShoppingService.getShoppingService();
                List<Product> productList = shoppingService.getAllProduct();
                 
                String[] itemDiv = new String[productList.size()];
                
                for(int i = 0; i < productList.size(); i++){   
                    Product eachProduct = productList.get(i);
                    int prodId = eachProduct.getProductId();
                    String eachItem = "<td style=\"margin-right:5px\"> <div id=\"item"+prodId+"\">"+
                            "<span id=\"itemName"+prodId+"\">"+eachProduct.getProductName()+ "</span></br></br>"+
                            "price = "+eachProduct.getProductPrice()+"</br></br>"+
                            "amount = <input id=\"prodAmount"+prodId +"\" type=\"text\" size=\"3\">   </br></br>"+
                            "<input type=\"button\" onClick=\"addToCart("+ prodId+","+eachProduct.getProductPrice()+")\" value=\"add to cart\">"+
                            "</div></td>";
                    itemDiv[i] = (eachItem);
                }
                
                int itemCount = 0;
                out.println("<tr style=\"margin-bottom: 7px;\">");
                for(String item : itemDiv){
                     out.println(item);
                     itemCount++;
                     if(itemCount%3 == 0){
                        out.println("</tr>");
                        out.println("<tr style=\"margin-bottom: 7px;\">");
                     }
                }
                if(productList.size()% 3 !=0){
                    out.println("</tr>");
                }
                  
              %> 
              </tbody>
        </table>
    </div>
    
    </div>
        <script   src="https://code.jquery.com/jquery-2.2.3.min.js"   integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
        
</body>
</html>
