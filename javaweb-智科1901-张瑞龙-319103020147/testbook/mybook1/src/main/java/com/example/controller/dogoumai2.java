package com.example.controller;


import com.example.dao.DBaccess1;
import com.example.dao.DBaccess2;
import com.example.dao.DBaccess_2;
import com.example.pojo.User;
import com.example.pojo.book1;
import com.example.utils.JDBCutils;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "dogoumai2", value = "/dogoumai2")

public class dogoumai2 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取传递来的注册信息，初始化一个新user,
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String zhouzhe = request.getParameter("zhouzhe");
        String xiangqing = request.getParameter("xiangqing");
        String path = request.getParameter("path");

        book1 newbook = new book1(id,name,zhouzhe,xiangqing,path);
        response.getWriter().println(name);
        response.getWriter().println(zhouzhe);
        response.getWriter().println("<br>");
        // 将新用户插入数据库
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess_2 dba = new DBaccess_2();
            dba.insert(newbook);
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
