package com.example.controller;



import com.example.dao.DBaccess;
import com.example.dao.DBaccess1;
import com.example.pojo.User;
import com.example.pojo.book;
import com.example.utils.JDBCutils;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "doshanchu", value = "/doshanchu")

public class doshanchu extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取传递来的注册信息，初始化一个新user,

        int id = Integer.parseInt(request.getParameter("id"));
        String book_name = request.getParameter("bookname");
        String book_zuozhe = request.getParameter("zhouzhe");
        int book_money = Integer.parseInt(request.getParameter("money"));


        book newbook = new book(id,book_name,book_zuozhe,book_money);
        response.getWriter().println(id);
        response.getWriter().println(book_name);
        response.getWriter().println(book_zuozhe);
        response.getWriter().println(book_money);
        response.getWriter().println("<br>");
        // 将新用户插入数据库
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess dba = new DBaccess();
            dba.delete(newbook);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        this.doGet(request,response);
    }




}
