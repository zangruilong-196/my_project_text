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
import java.util.ArrayList;

@WebServlet(name = "doShow", value = "/doShow")

public class doShow extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess dba = new DBaccess();
            ArrayList<book> books = dba.queryAll();
            request.getSession().setAttribute("booklist", books);
            request.getRequestDispatcher("main.jsp").forward(request,response);
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
