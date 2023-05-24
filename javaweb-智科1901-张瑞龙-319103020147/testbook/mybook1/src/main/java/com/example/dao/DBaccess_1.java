package com.example.dao;


import com.example.pojo.User;
import com.example.utils.JDBCutils;
import com.example.pojo.User;
import com.example.utils.JDBCutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*实现对数据库的增删改查 */
/*实现对数据库的增删改查 */
// 1 增


public class DBaccess_1 {

    public boolean insert(User user) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        // 1.1先保证添加按当前主键最大值加一的顺序递增
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("alter table `yuewenbook`.`user1`  AUTO_INCREMENT=1;");
        // 2.1 再执行添加指令
        String sqlInsert = "INSERT INTO `yuewenbook`.`user1` (`name`, `password`) VALUES ( ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
        pstmt.setString(1, user.getName());
        pstmt.setString(2,user.getPassword());

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
    public boolean delete(User user) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlDelete = "DELETE FROM `yuewenbook`.`user1` WHERE `id` = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1,user.getId());
        int i = pstmt.executeUpdate();
        JDBCutils.releaseResource(conn, pstmt);
        if(i>0){
            return true;
        }else{
            return  false;
        }
    }
    // 3 改
    public boolean modify(User user) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlModify = "UPDATE `yuewenbook`.`user1` SET `name` = ?, `password` = ?, WHERE `id` = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlModify);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getPassword());
        pstmt.setInt(3,user.getId());
        int i = pstmt.executeUpdate();
        JDBCutils.releaseResource(conn, pstmt);
        if(i>0){
            return true;
        }else{
            return  false;
        }
    }
    // 4 查所有
    public ArrayList<User> queryAll() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCutils.getConnection();
        String sqlQuery = "SELECT * FROM `yuewenbook`.`user1` ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        List<User> users = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String pass = rs.getString("password");
            User newuser = new User(id,name,pass);
            users.add(newuser);
        }
        //JDBCutils.releaseResource(conn, (PreparedStatement) stmt);
        conn.close();
        stmt.close();
        return (ArrayList<User>) users;

    }
    // 5. 条件查询:用户名和密码匹配者，登录验证用；返回符合条件的用户列表
    public ArrayList<User> query(String username, String password) throws SQLException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = JDBCutils.getConnection();
        String sqlquery = "select * from `yuewenbook`.`user1` WHERE `name`=? and `password`=?";
        PreparedStatement pstmt = conn.prepareStatement(sqlquery);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3)));
        }
        return users;
    }

}
