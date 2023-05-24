<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>详情</title>
</head>
<body>

<table border="1">
    <tr>
        <th>书名</th>
        <th>详情</th>
    </tr>
    <c:forEach items="${booklist}" var="book" >
        <tr>
            <td>${book.name}</td>
            <td>${book.xiangqing}</td>
            <td><

        </tr>
    </c:forEach>>
</table>
<a href="doShow1">返回</a>

</body>
</html>
