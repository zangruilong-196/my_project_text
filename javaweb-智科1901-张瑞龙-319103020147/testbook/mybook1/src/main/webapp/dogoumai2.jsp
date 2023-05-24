<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>继续</title>
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
    String xiangqing  =request.getParameter("xiangqing");
%>
<%
    String path  =request.getParameter("path");
%>




<form action="dogoumai2" method="get">

    <input type="text" name="id" value="<%=id%>"><br>
    <input type="text" name="name" value="<%=name%>"><br>
    <input type="text" name="zhouzhe" value="<%=zhouzhe%>"><br>
    <input type="text" name="xiangqing" value="<%=xiangqing%>"><br>
    <input type="text" name="path" value="<%=path%>"><br>

    <input type="submit" value="继续">
</form>


</body>
</html>
