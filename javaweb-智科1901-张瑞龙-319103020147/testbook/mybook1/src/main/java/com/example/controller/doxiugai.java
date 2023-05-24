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

@WebServlet(name = "doxiugai", value = "/doxiugai")


public class doxiugai extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取传递来的注册信息，初始化一个新user,

        int id = Integer.parseInt(request.getParameter("id"));
        String book_name = request.getParameter("bookname");
        String book_zuozhe = request.getParameter("zhouzhe");

        //String money = request.getParameter("money");
        int book_money = Integer.parseInt(request.getParameter("money"));





        String book_name1 = request.getParameter("bookname1");

        if (book_name == null)
        {
            book_name = book_name1;
        }






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
            dba.modify(newbook);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
































        /*

        int id = Integer.parseInt(request.getParameter("id"));
        // 解决中文乱码
        response.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
        response.setHeader("Content-Type", "text/html;charset=utf-8");    //通知浏览器使用utf-8解码

        // 连接数据库，验证用户名和密码
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess dba1 = new DBaccess();
            ArrayList<book> query_books1 = dba1.queryId(id);
            if(query_books1 == null || query_books1.isEmpty()){
                response.sendRedirect("loginFail.jsp");
            }else{
                request.getSession().setAttribute("booklist", query_books1);
                request.getRequestDispatcher("show.jsp").forward(request,response);
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
*/



    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        this.doGet(request,response);
    }
}
