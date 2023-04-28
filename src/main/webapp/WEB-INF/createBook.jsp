<%@ page import="java.util.List" %>
<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: Smart
  Date: 28.04.2023
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book</title>
</head>
<%List<Author> authors = (List<Author>) request.getAttribute("author"); %>
<body>
Create Book
<form action="/createBook" method="post">
    Title <input name="title" type="text"><br>
    Description <input name="description" type="text"><br>
    Price <input name="price" type="text"><br>
    Author:
    <select name="authorID">
        <%for (Author author : authors) {%>
        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%></option>
        <%}%>
    </select>
    <input type="submit" value="create">
</form>
</body>
</html>