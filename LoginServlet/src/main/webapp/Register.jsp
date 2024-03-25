<%--
  Created by IntelliJ IDEA.
  User: olvad
  Date: 26/02/2024
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form name="registerForm" method="post" action="/LoginServlet_war/registerServlet"
          onsubmit="return validateRegister()">
        <div>
            <label>Name:</label>
            <input name="name" type="text" />
        </div>
        <div>
            <label>Username:</label>
            <input name="username" type="text" />
        </div>
        <div>
            <label>Password:</label>
            <input name="password" type="password" />
        </div>
        <div>
            <label>Retype password:</label>
            <input name="repassword" type="password" />
        </div>
        <button type="submit">register</button>
        <button type="reset">clear</button>
    </form>
</body>
</html>
