<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>继续</title>
</head>
<body>
<table border="1">
    <c:forEach items="${booklist}" var="book" >

        <td>${book.id}</td>
        <td>${book.name}</td>
        <td>${book.zhouzhe}</td>
        <td>${book.path}</td>
        <a href="dogoumai2.jsp?id=${book.id}&name=${book.name}&zhouzhe=${book.zhouzhe}&xiangqing=${book.xiangqing}&path=${book.path}">继续</a>
    </c:forEach>>
</table>



</body>
</html>
