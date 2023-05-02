<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.UserType" %><%--
  Created by IntelliJ IDEA.
  User: Smart
  Date: 27.04.2023
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<% List<Author> authors = (List<Author>) request.getAttribute("author");%>
<body>
<a href="/home"> Back </a>
<h2>Authors</h2> <a href="/createAuthor">Create Author</a>
<%User user = (User) session.getAttribute("user");%>
<table border="2">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <% if (user.getUserType() == UserType.ADMIN) { %>
        <th>action</th>
        <%}%>
    </tr>
    <%if (authors != null || !authors.isEmpty()) {%>
    <%for (Author author : authors) {%>
    <tr>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
                <% if (user.getUserType() == UserType.ADMIN) { %>
        <td><a href="/removeAuthor?id=<%=author.getId()%>">Delete</a>
            / <a href="/updateAuthor?id=<%=author.getId()%>">Update</a></td>
        <%}%>
    </tr>
    <%}%>
    <%}%>
</table>
</body>
</html>