<%--
  Created by IntelliJ IDEA.
  User: GiftzyEiei
  Date: 5/4/2559
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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

<div style="margin: 5% 35% 5% 35%; width: 360px" >
    <h3 style="margin: 30px 0 30px 0;">Create Worm Account</h3>

    <form>
        <div style="margin: 10px 0 10px 0; display: block;">
            <fieldset>
                <div style="display: block">
                    <strong>Name</strong>
                </div>
                <input type="text" name="firstName" placeholder="First Name" required>
                <input type="text" name="lastName" placeholder="Last Name" required>
            </fieldset>
        </div>
        <div style="margin: 10px 0 10px 0; display: block;">
            <div style="display: block">
                <strong>Username</strong>
            </div>
            <input type="text" style="width: 350px; height: 30px;" name="username" placeholder="Username" required>
        </div>
        <div style="margin: 10px 0 10px 0; display: block;">
            <div style="display: block">
                <strong>Password</strong>
            </div>
            <input type="text" style="width: 350px; height: 30px;" name="password" placeholder="Password" required>
        </div>
        <div style="margin: 10px 0 10px 0; display: block;">
            <div style="display: block">
                <strong>Re-Enter Password</strong>
            </div>
            <input type="text" style="width: 350px; height: 30px;" name="re-password" placeholder="Re-Enter Password" required>
        </div>
        <div style="margin: 10px 0 10px 0;" align="center">
            <input type="submit" class="btn btn-primary btn-md" style="width: 100px; margin: 3px;" value="Sign Up">
        </div>
    </form>
</div>
</body>
</html>
