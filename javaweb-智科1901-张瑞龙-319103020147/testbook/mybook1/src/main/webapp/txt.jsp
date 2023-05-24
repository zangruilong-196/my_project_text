<%--
  Created by IntelliJ IDEA.
  User: 32703
  Date: 2022/3/20
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>


<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    //String path = request.getContextPath();
    //System.out.println("path=="+path);
    //String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>使用jsp读取TXT格式文件</title>
</head>
<body>
<%
    FileInputStream fr = null;
    File file = new File("D://xiaoshuo//花千骨.txt");
    BufferedReader brd = null;
    fr = new FileInputStream(file);
    brd = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
    StringBuffer strB = new StringBuffer();   //strB用来存储jsp.txt文件里的内容
    String str = brd.readLine();
    while(str!=null){
        strB.append(str).append("<br>");   //将读取的内容放入strB
        str = brd.readLine();
    }
    brd.close();    //关闭输入流
%>
<center>
    <%=strB %>
</center>
</body>
</html>