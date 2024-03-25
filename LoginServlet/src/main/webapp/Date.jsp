<%--
  Created by IntelliJ IDEA.
  User: olvad
  Date: 27/02/2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Header.jsp" %>
<h3>Current Date and Time: <%= new Date()%>
</h3>
<div>
    <%!
        int num = 0;
    %>
    <%! public void displayNums() {
            for (int i = 0; i < 30; i++) {
                System.out.println("Hello");
            }
        }
    %>
    <%displayNums();
    %>
</div>
<%@include file="Footer.jsp" %>
</body>
</html>
