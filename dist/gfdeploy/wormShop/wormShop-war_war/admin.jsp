<%--
  Created by IntelliJ IDEA.
  User: GiftzyEiei
  Date: 2/4/2559
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.wormshop.entities.OrderDetail"%>
<%@page import="com.wormshop.entities.PurchaseOrder"%>
<%@page import="com.wormshop.services.AdminService"%>
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
        AdminService adminService = AdminService.getAdminService();
        if (null == sessionCustomer || !(sessionCustomer.getCustomer().getUsername().equalsIgnoreCase("admin"))) {
            request.getRequestDispatcher("index.jsp");
        }
        Customer customer = sessionCustomer.getCustomer();
    %>
    <title>Admin authorize page</title>
    <script   src="https://code.jquery.com/jquery-2.2.3.min.js"   integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <%--bootstrap--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
</head>
<body>
    <div id="header" style="margin-bottom: 10px; float: right; padding-right: 10%;padding-top: 2%;height: 5%;">
        <h3> Welcome    <%= customer.getUsername()%> </h3>
     </div>
       
    <div id="waitingDiv" style="float: left; clear: both; padding-left: 10px;width: 80%;border:  blue;">
        <table class="table table-condensed" style="width:70%;margin:4% 25% 25% 25%;" border="0" align="center" cellpadding="0" cellspacing="25">
            <tbody style="width: 80%;">
              <% 
                
                out.println("<tr> <th> Order id </th>  <th> client </th>  <th> total </th>  <th> status </th>  <th> authorize</th></tr>");
                ShoppingService shoppingService = ShoppingService.getShoppingService();
                List<PurchaseOrder> orderList = adminService.getAllWaitingOrder();
                for(PurchaseOrder po : orderList){
                    out.println("<tr id=\"orderRow"+po.getPurchaseId()+"\" onClick=\"hideDetail("+po.getPurchaseId()+")\" >");
                        out.println("<td>"+po.getPurchaseId()+"</td> <td>"+ po.getCustomerId().getUsername()+ "</td>");
                        out.println("<td>"+po.getTotal()+"</td> <td>"+ po.getOrderStatus()+ "</td> <td> <input onClick=\"authorizeOrder("+po.getPurchaseId()+")\" type=\"button\" value=\"authorize\"> </td>");
                    out.println("</tr>");
                    List<OrderDetail> details = po.getOrderDetailList();
                    for(OrderDetail detail : details){
                        out.println("<tr class=\"detailRow"+po.getPurchaseId()+" info\" style=\" display:none;\" ><td>"+detail.getOrderId()+"</td> <td>"+ detail.getProductId().getProductName()+ "</td>");
                        out.println("<td>"+detail.getAmount()+"</td> <td>"+ detail.getTotal()+ "</td><td></td></tr>");
                    }
                }
                    
              %> 
              </tbody>
        </table>
    </div>
              
    
    </div>
    <script>
        
        hideDetail = function(trId){
            var rowId = ".detailRow"+trId;
            
            if($( rowId ).is(":visible")){
                $( rowId ).css("display","none");
            }else{
               $( rowId ).css("display","table-row");
            }
        };
        
        authorizeOrder = function(orderId){
              window.location.href = "authorize?orderId="+orderId; 
        };
        
        
    </script>
</body>
</html>
