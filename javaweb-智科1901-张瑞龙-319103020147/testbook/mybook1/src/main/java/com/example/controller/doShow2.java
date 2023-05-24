package com.example.controller;



import com.example.dao.DBaccess_2;
import com.example.pojo.book1;
import com.example.utils.JDBCutils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "doShow2", value = "/doShow2")


public class doShow2 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess_2 dba = new DBaccess_2();
            ArrayList<book1> books = dba.queryAll();
            request.getSession().setAttribute("booklist", books);
            request.getRequestDispatcher("yonghubook.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
