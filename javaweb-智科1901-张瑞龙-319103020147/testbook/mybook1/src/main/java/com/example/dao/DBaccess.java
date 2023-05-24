package com.example.dao;


import com.example.pojo.book;
import com.example.utils.JDBCutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBaccess {


    public boolean insert(book book) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        // 1.1先保证添加按当前主键最大值加一的顺序递增
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("alter table `yuewenbook`.`book`  AUTO_INCREMENT=1;");
        // 2.1 再执行添加指令
        String sqlInsert = "INSERT INTO `yuewenbook`.`book` (`name`, `zhouzhe`, `money`) VALUES ( ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
        pstmt.setString(1, book.getName());
        pstmt.setString(2,book.getZhouzhe());
        pstmt.setInt(3,book.getMoney());

        int i = pstmt.executeUpdate();
        JDBCutils.releaseResource(conn, pstmt);
        stmt.close();
        if(i>0){
            return true;
        }else{
            return  false;
        }
    }

    // 2 删
    public boolean delete(book book) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlDelete = "DELETE FROM `yuewenbook`.`book` WHERE `id` = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1,book.getId());
        int i = pstmt.executeUpdate();
        JDBCutils.releaseResource(conn, pstmt);
        if(i>0){
            return true;
        }else{
            return  false;
        }
    }
    // 3 改
    public boolean modify(book book) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlModify = "UPDATE `yuewenbook`.`book` SET `name` = ?, `zhouzhe` = ?, `money` = ? WHERE `id` = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlModify);
        pstmt.setString(1, book.getName());
        pstmt.setString(2,book.getZhouzhe());
        pstmt.setInt(3,book.getMoney());
        pstmt.setInt(4,book.getId());
        int i = pstmt.executeUpdate();
        JDBCutils.releaseResource(conn, pstmt);
        if(i>0){
            return true;
        }else{
            return  false;
        }
    }
    // 4 查所有
    public ArrayList<book> queryAll() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlQuery = "SELECT * FROM `yuewenbook`.`book` ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        List<book> books = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String zhouzhe = rs.getString("zhouzhe");
            int money =  rs.getInt("money");
            book newbook = new book(id,name,zhouzhe,money);
            books.add(newbook);
        }
        //JDBCutils.releaseResource(conn, (PreparedStatement) stmt);
        conn.close();
        stmt.close();
        return (ArrayList<book>) books;

    }


    // 5. 条件查询:用户名和密码匹配者，登录验证用；返回符合条件的用户列表
    public ArrayList<book> query(String username, String password) throws SQLException, ClassNotFoundException {
        ArrayList<book> books = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`book` WHERE `name`=? and `zhouzhe`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            books.add(new book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
        return books;
    }




    // 6. 条件查询:按书名查询，返回符合条件的书籍列表
    public ArrayList<book> queryName(String bookname) throws SQLException, ClassNotFoundException {
        ArrayList<book> books = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`book` WHERE `name`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setString(1,bookname);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            books.add(new book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
        return books;

    }




    // 7. 条件查询:按作者查询，返回符合条件的书籍列表
    public ArrayList<book> queryZuozhe(String bookname) throws SQLException, ClassNotFoundException {
        ArrayList<book> books = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`book` WHERE `zhouzhe`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setString(1,bookname);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            books.add(new book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
        return books;

    }


    // 7. 条件查询:按id查询，返回符合条件的书籍列表
    public ArrayList<book> queryId(int bookid) throws SQLException, ClassNotFoundException {
        ArrayList<book> books = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`book` WHERE `id`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setInt(1,bookid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            books.add(new book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
        return books;

    }

}


