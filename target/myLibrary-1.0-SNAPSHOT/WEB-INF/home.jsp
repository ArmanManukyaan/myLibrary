<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Smart
  Date: 27.04.2023
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<%User user = (User) session.getAttribute("user");%>
Welcome <%=user.getName()%> <%=user.getSurname()%> <a href="/logout">logout</a> <br>
<a href="/book">Book</a> |
<a href="/author">Author</a>
</body>
</html>
