/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.dao.imp;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Qi
 */
public class GradeDaoImp implements IGameDao{
    private static String url = "jdbc:mysql://localhost:3306/huat";
    private static String user = "root";
    private static String password = "root";
    public boolean insertGrade(String grade){
        Statement stmt = null;
        PreparedStatement pstmt = null;   //灵活的对象
        Connection conn = null;
        try {
            //1.驱动注册程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接对象
            conn = (Connection) DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO gamegrade(grade) VALUES(?)";
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, grade);
            int count = pstmt.executeUpdate();
            System.out.println("影响了" + count + "行！");
            if(count==1){
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //7.关闭连接(顺序:后打开的先关闭)
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
