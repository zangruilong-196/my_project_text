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

@WebServlet(name = "dosousuo", value = "/dosousuo")

public class dosousuo extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取搜索页面的书名或作者
        String book_name = request.getParameter("bookname");
        // 解决中文乱码
        response.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
        response.setHeader("Content-Type", "text/html;charset=utf-8");    //通知浏览器使用utf-8解码
        // 连接数据库,开始查询
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess dba = new DBaccess();
            ArrayList<book> query_books = dba.queryName(book_name);


            if(query_books == null || query_books.isEmpty()){

                try {
                    Connection conn1 = JDBCutils.getConnection();
                    DBaccess dba1 = new DBaccess();
                    ArrayList<book> query_books1 = dba.queryZuozhe(book_name);

                    if(query_books1 == null || query_books1.isEmpty()){
                        response.sendRedirect("sousuoFail.jsp");
                    }
                    else
                    {

                        request.getSession().setAttribute("booklist", query_books1);
                        request.getRequestDispatcher("main.jsp").forward(request,response);

                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("sousuoFail.jsp");
            }else{
                request.getSession().setAttribute("booklist", query_books);
                request.getRequestDispatcher("main.jsp").forward(request,response);
            }


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
