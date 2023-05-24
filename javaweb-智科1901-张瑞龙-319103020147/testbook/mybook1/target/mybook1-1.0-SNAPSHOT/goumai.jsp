<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>欢迎购买！</title>
</head>
<body>


<%
    String name  =request.getParameter("name");
%>
<%
    String zhouzhe  =request.getParameter("zhouzhe");
%>
<%
    String money  =request.getParameter("money");
%>



<h1>订单详情：</h1>
<form action="dogoumai" method="get">

    书名：<input type="text" name="bookname1" value="<%=name%>"><br>
    作者：<input type="text" name="zhouzhe1" value="<%=zhouzhe%>"><br>
    单价：<input type="text" name="money1" value="<%=money%>"><br>
    <h1>是否购买？</h1>

    <input type="submit" value="确定">
</form>

<form action="doShow1" method="get">
    <input type="submit" value="返回">
</form>



</body>
</html>
