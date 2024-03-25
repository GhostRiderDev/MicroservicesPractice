<%--
  Created by IntelliJ IDEA.
  User: cos4h
  Date: 26/02/2024
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="loginForm" action="/LoginServlet_war/loginServlet" method="post"
      onsubmit="return validate()">
    <div>
        <label>Username:</label>
        <input type="text" name="username"/>
    </div>
    <div>
        <label>Password:</label>
        <input type="password" name="password"/>
    </div>
    <input type="submit" value="Login">
</form>
New user <a href="Register.jsp">Register</a>

<script src="js/Login.js" type="text/javascript">

</script>
</body>
</html>
