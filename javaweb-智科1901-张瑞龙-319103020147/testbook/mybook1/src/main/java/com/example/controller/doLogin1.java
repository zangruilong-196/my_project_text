package com.example.controller;


import com.example.dao.DBaccess_1;
import com.example.pojo.User;
import com.example.utils.JDBCutils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "doLogin1", value = "/doLogin1")
public class doLogin1 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取登录页面的用户名和密码
        String user_name = request.getParameter("name");
        String user_pass = request.getParameter("password");
        // 解决中文乱码
        response.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
        response.setHeader("Content-Type", "text/html;charset=utf-8");    //通知浏览器使用utf-8解码
        // 连接数据库，验证用户名和密码
        try {
            Connection conn = JDBCutils.getConnection();
            DBaccess_1 dba = new DBaccess_1();
            ArrayList<User> query_users = dba.query(user_name,user_pass);
            if(query_users == null || query_users.isEmpty()){
                response.sendRedirect("loginFail.jsp");
            }else{
                request.getRequestDispatcher("doShow1").forward(request,response);
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        this.doGet(request,response);
    }



}
