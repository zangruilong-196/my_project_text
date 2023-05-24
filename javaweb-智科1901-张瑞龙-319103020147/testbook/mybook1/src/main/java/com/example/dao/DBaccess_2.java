package com.example.dao;


import com.example.pojo.book1;
import com.example.utils.JDBCutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBaccess_2 {

    public boolean insert(book1 book1) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        // 1.1先保证添加按当前主键最大值加一的顺序递增
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("alter table `yuewenbook`.`book2`  AUTO_INCREMENT=1;");
        // 2.1 再执行添加指令
        String sqlInsert = "INSERT INTO `yuewenbook`.`book2` (`name`, `zhouzhe`, `xiangqing`,`path`) VALUES ( ?, ?, ?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
        pstmt.setString(1, book1.getName());
        pstmt.setString(2,book1.getZhouzhe());
        pstmt.setString(3,book1.getXiangqing());
        pstmt.setString(4,book1.getPath());

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
    public boolean delete(book1 book) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlDelete = "DELETE FROM `yuewenbook`.`book2` WHERE `id` = ?";
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
    public boolean modify(book1 book1) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlModify = "UPDATE `yuewenbook`.`book2` SET `name` = ?, `zhouzhe` = ?, `xiangqing` = ?, `path` = ?WHERE `id` = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlModify);
        pstmt.setString(1, book1.getName());
        pstmt.setString(2,book1.getZhouzhe());
        pstmt.setString(3,book1.getXiangqing());
        pstmt.setString(4,book1.getPath());
        pstmt.setInt(5,book1.getId());
        int i = pstmt.executeUpdate();
        JDBCutils.releaseResource(conn, pstmt);
        if(i>0){
            return true;
        }else{
            return  false;
        }
    }
    // 4 查所有
    public ArrayList<book1> queryAll() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlQuery = "SELECT * FROM `yuewenbook`.`book2` ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        List<book1> books = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String zhouzhe = rs.getString("zhouzhe");
            String xiangqing =  rs.getString("xiangqing");
            String path = rs.getString("path");
            book1 newbook = new book1(id,name,zhouzhe,xiangqing,path);
            books.add(newbook);
        }
        //JDBCutils.releaseResource(conn, (PreparedStatement) stmt);
        conn.close();
        stmt.close();
        return (ArrayList<book1>) books;

    }






    // 6. 条件查询:按书名查询，返回符合条件的书籍列表
    public ArrayList<book1> queryName(String bookname) throws SQLException, ClassNotFoundException {
        ArrayList<book1> books = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`book2` WHERE `name`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setString(1,bookname);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            books.add(new book1(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return books;

    }




    // 7. 条件查询:按作者查询，返回符合条件的书籍列表
    public ArrayList<book1> queryZuozhe(String bookname) throws SQLException, ClassNotFoundException {
        ArrayList<book1> books = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`book2` WHERE `zhouzhe`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setString(1,bookname);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            books.add(new book1(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return books;

    }


    // 7. 条件查询:按id查询，返回符合条件的书籍列表
    public ArrayList<book1> queryId(int bookid) throws SQLException, ClassNotFoundException {
        ArrayList<book1> books = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`book2` WHERE `id`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setInt(1,bookid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            books.add(new book1(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return books;

    }


}
