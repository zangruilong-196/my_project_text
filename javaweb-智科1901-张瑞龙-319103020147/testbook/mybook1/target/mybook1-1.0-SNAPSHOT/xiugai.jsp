<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/18
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改</title>
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



<h1>请输入修改内容：</h1>
<form action="doxiugai" method="get">

    书名：<input type="text" name="bookname1" value="<%=name%>"><br>
    作者：<input type="text" name="zhouzhe1" value="<%=zhouzhe%>"><br>
    单价：<input type="text" name="money1" value="<%=money%>"><br>

    请输入修改项：<br>
    id: <input type="text" name="id" value="<%=id%>" readonly><br>
    书名：<input type="text" name="bookname"><br>
    作者：<input type="text" name="zhouzhe"><br>
    单价：<input type="text" name="money"><br>

    <input type="submit" value="提交">
</form>

</body>
</html>
