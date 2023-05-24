<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a><br><hr>
<h2>请选择操作：</h2><br>
<form action="zhuce.jsp" name="">
    <input type="submit" value="新用户注册(商家)"></form>
</form>
<form action="zhuce1.jsp" name="">
    <input type="submit" value="新用户注册(买家)"></form>
</form>

<br>
<form action="login.jsp" name="">
    <input type="submit" value="用户登录（商家）"></form>
</form>
<form action="login1.jsp" name="">
    <input type="submit" value="用户登录（买家）"></form>
</form>

</body>
</html>