<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
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
<% List<Book> books = (List<Book>) request.getAttribute("book");%>
<%User user = (User) session.getAttribute("user");%>
<body>
<a href="/home"> Back </a>
<h2>Book</h2> <a href="/createBook">Create Book</a>
<form action="/searchBook"method="post">
    Name <input type="text" name="name">
    <input type="submit" value="search">
<table border="2">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author</th>
        <% if (user.getUserType() == UserType.ADMIN) { %>
        <th>action</th>
        <%}%>
    </tr>
    <%if (books != null || !books.isEmpty()) {%>
    <%for (Book book : books) {%>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td><%=book.getAuthor().getName()%>
                <% if (user.getUserType() == UserType.ADMIN) { %>
        <td><a href="/removeBook?id=<%=book.getId()%>">Delete</a>
            / <a href="/updateBook?id=<%=book.getId()%>">Update</a></td>
        <%}%>
    </tr>
    <%}%>
    <%}%>
</table>
</form>
</body>
</html>