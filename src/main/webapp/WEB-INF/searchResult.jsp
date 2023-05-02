<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Smart
  Date: 29.04.2023
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<%List<Book> books = (List<Book>) request.getAttribute("books");%>
<body>
<table border="1">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author</th>
    </tr>
        <% if (books != null||!books.isEmpty()) { %>
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
        </td>
    </tr>
        <%}%>
        <%}%>
</body>
</html>
