package com.example.controller;

import com.example.dao.DBaccess;
import com.example.pojo.book;
import com.example.utils.JDBCutils;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "doRegister", value = "/doRegister")
public class doRegister extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取传递来的添加信息，初始化一个新book,
        String name = request.getParameter("name");
        String zhouzhe = request.getParameter("zhouzhe");
        int money = Integer.parseInt(request.getParameter("money"));
        book newbook = new book(name,zhouzhe,money);
        response.getWriter().println(name);
        response.getWriter().println(zhouzhe);
        response.getWriter().println(money);
        response.getWriter().println("<br>");
        // 将新书插入数据库
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess dba = new DBaccess();
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
