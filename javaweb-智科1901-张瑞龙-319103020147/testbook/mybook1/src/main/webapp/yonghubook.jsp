<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书单</title>
</head>
<body>

<a href="doShow1">主界面</a>


<hr>

<table border="1">
    <tr>
        <th>ID</th>
        <th>书名</th>
        <th>作者</th>
        <th>详情</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${booklist}" var="book" >
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.zhouzhe}</td>
            <td>${book.xiangqing}</td>
            <td><
                <a href="txt.jsp?path=${book.path}">阅读</a></td>>
        </tr>
    </c:forEach>>
</table>
<hr>

</body>
</html>
