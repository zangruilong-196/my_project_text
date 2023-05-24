<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>主界面</title>
</head>
<body>
<a href="sousuo1.jsp">搜索</a>
<a href="doShow1">主界面</a>
<a href="yonghu.jsp">用户中心</a>

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
                <a href="goumai.jsp?name=${book.name}&zhouzhe=${book.zhouzhe}&money=${book.money}">购买</a>&nbsp;<a href="xiangqing.jsp?name=${book.name}">详情</a></td>>
        </tr>
    </c:forEach>>
</table>
<hr>


</body>
</html>
