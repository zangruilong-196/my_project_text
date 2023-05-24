<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>详情</title>
</head>
<body>

<%
    String name  =request.getParameter("name");
%>

<form action="doxiangqingShow" method="get">

    书名：<input type="text" name="bookname" value="<%=name%>"><br>


    <input type="submit" value="确定">
</form>


</body>
</html>
