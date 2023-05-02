<%--
  Created by IntelliJ IDEA.
  User: Smart
  Date: 28.04.2023
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Author</title>
</head>
<body>
Create Author
<a href="/author">Back</a>
<form action="/createAuthor" method="post">
    Name <input name="name" type="text"><br>
    Surname <input name="surname" type="text"><br>
    Email <input name="email" type="text"><br>
    Age <input name="age" type="text"><br>
    <input type="submit" value="c reate">
</form>

</body>
</html>
