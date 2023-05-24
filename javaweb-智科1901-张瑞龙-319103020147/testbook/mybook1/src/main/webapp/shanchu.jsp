<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/19
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>删除</title>
</head>
<body>
<%
    String id  =request.getParameter("id");
%>
<%
    String name  =request.getParameter("name");
%>
<%
    String zhouzhe  =request.getParameter("zhouzhe");
%>
<%
    String money  =request.getParameter("money");
%>


<h1>是否删除？</h1>
<form action="doshanchu" method="get">
    id:  <input type="text" name="id" value="<%=id%>" readonly><br>
    书名：<input type="text" name="name" value="<%=name%>" readonly><br>
    作者：<input type="text" name="zhouzhe" value="<%=zhouzhe%>" readonly><br>
    单价：<input type="text" name="money" value="<%=money%>" readonly><br>

    <input type="submit" value="确认">
</form>

<form action="doShow" method="get">

    <input type="submit" value="返回">
</form>

</body>
</html>
