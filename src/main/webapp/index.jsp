<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
Login:
<form action="/login" method="post">
    Email: <input name="email" type="text"><br>
    Password:<input name="password" type="password"><br>
    <input type="submit" value="login">
</form>
<a href="/register.jsp">Register</a>
</body>
</html>