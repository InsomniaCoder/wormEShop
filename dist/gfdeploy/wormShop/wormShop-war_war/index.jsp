<%--
  Created by IntelliJ IDEA.
  User: GiftzyEiei
  Date: 2/4/2559
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%  
    if (null != session.getAttribute("customer")) {
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        }
    %>
    <title>Worm Shop</title>

    <%--JQuery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

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
<div align="center" style="padding: 50px 0 0 0;">

<h3 align="center" style="margin: 30px 0 30px 0;">Welcome to Worm Shop</h3>

<form action="authentication" method="post">
        <div style="margin: 10px 0 10px 0; display: block;">
            <input type="text" id="username" style="width: 250px; height: 30px;" name="username" placeholder="Username" required>
        </div>
        <div style="margin: 10px 0 10px 0; display: block;">
            <input type="password" id="password" style="width: 250px; height: 30px;" name="password" placeholder="Password" required>
        </div>
        <div style="margin: 10px 0 10px 0;">
            <input type="submit" class="btn btn-primary btn-md" style="width: 250px; margin: 3px;" value="Login">
        </div>
        Not a member yet? <a href="sign_up.jsp">Sign up</a> now!!
        <input type="hidden" name="process" value="authenticate">
</form>

</div>
</body>
</html>
