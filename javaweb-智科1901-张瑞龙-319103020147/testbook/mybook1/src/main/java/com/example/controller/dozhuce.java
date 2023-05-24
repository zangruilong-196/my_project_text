package com.example.controller;


import com.example.dao.DBaccess1;
import com.example.pojo.User;
import com.example.utils.JDBCutils;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "dozhuche", value = "/dozhuce")
public class dozhuce extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取传递来的注册信息，初始化一个新user,
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User newuser = new User(name,password);
        response.getWriter().println(name);
        response.getWriter().println(password);
        response.getWriter().println("<br>");
        // 将新用户插入数据库
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess1 dba = new DBaccess1();
            dba.insert(newuser);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
