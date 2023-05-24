<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/18
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>show</title>
</head>
<body>

<hr>

<h1>当前书籍信息：</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>书名</th>
        <th>作者</th>
        <th>单价</th>
    </tr>
    <c:forEach items="${booklist}" var="book" >
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.zhouzhe}</td>
            <td>${book.money}</td>
        </tr>
    </c:forEach>>
</table>
<hr>



</body>
</html>
