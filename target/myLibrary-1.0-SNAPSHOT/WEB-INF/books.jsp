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
<% List<Book> books = (List<Book>) request.getAttribute("book");
    User user = (User) session.getAttribute("user");
    String keyword = request.getParameter("keyword") == null ||
            request.getParameter("keyword").equals("null") ? "" : request.getParameter("keyword");
%>
<body>
<a href="/home"> Back </a>
<h2>Book</h2> <a href="/createBook">Create Book</a>
<form action="/book" method="get">
    Name <input type="text" name="keyword" value="<%=keyword%>">
    <input type="submit" value="search">
    <table border="2">
        <tr>
            <th>image</th>
            <th>id</th>
            <th>title</th>
            <th>description</th>
            <th>price</th>
            <th>author</th>
            <th>user</th>
            <th>action</th>
        </tr>
        <%if (books != null || !books.isEmpty()) {%>
        <%
            for (Book book : books) {
                if (user.getUserType() == UserType.ADMIN) {
        %>
        <tr>
            <td>
                <% if (book.getPicName() == null || book.getPicName().equalsIgnoreCase("null")) { %>
                <img src="/img/default_pic.png" width="100">
                <%} else {%>
                <a href="/getImage?picName=<%=book.getPicName()%>"><img
                        src="/getImage?picName=<%=book.getPicName()%>" width="100"> </a></td>
            <%}%>
            <td><%=book.getId()%>
            </td>
            <td><%=book.getTitle()%>
            </td>
            <td><%=book.getDescription()%>
            </td>
            <td><%=book.getPrice()%>
            </td>
            <td><%=book.getAuthor().getName()%>
            </td>
            <td><%=book.getUser().getName()%>
            </td>
        <td><a href="/removeBook?id=<%=book.getId()%>">Delete</a>
            / <a href="/updateBook?id=<%=book.getId()%>">Update</a></td>
        </tr>

        <%} else if (book.getUser().getId() == user.getId()) {%>
        <tr>
            <td>
                <% if (book.getPicName() == null || book.getPicName().equalsIgnoreCase("null")) { %>
                <img src="/img/default_pic.png" width="100">
                <%} else {%>
                <a href="/getImage?picName=<%=book.getPicName()%>"><img
                        src="/getImage?picName=<%=book.getPicName()%>" width="100"> </a></td>
            <%}%>
            <td><%=book.getId()%>
            </td>
            <td><%=book.getTitle()%>
            </td>
            <td><%=book.getDescription()%>
            </td>
            <td><%=book.getPrice()%>
            </td>
            <td><%=book.getAuthor().getName()%>
            </td>
            <td><%=book.getUser().getName()%>
            </td>
            <td><a href="/removeBook?id=<%=book.getId()%>">Delete</a>
                / <a href="/updateBook?id=<%=book.getId()%>">Update</a></td>
        </tr>
        <%}%>
        <%}%>
        <%}%>
    </table>
</form>
</body>
</html>