<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/8
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
<a href="sousuo.jsp">搜索</a>
<a href="doShow">主界面</a>
<hr>

<table border="1">
    <tr>
        <th>ID</th>
        <th>书名</th>
        <th>作者</th>
        <th>单价</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${booklist}" var="book" >
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.zhouzhe}</td>
            <td>${book.money}</td>
            <td><
                <a href="shanchu.jsp?id=${book.id}&name=${book.name}&zhouzhe=${book.zhouzhe}&money=${book.money}">删除</a>&nbsp;<a href="xiugai.jsp?id=${book.id}&name=${book.name}&zhouzhe=${book.zhouzhe}&money=${book.money}">修改</a></td>>
        </tr>
    </c:forEach>>
</table>
<hr>
<a href="register.jsp">添加</a>


</body>
</html>
