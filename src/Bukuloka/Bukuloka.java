/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Bukuloka;

import helper.KoneksiDB;
import View.formLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class Bukuloka {
    static Connection con;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KoneksiDB.connection();
        formLogin loginForm = new formLogin();
            loginForm.setVisible(true);
    }

    public static Connection getConnection() {
    if (con == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/bukuloka","root","");
                System.out.print(con + "\nBisa \n");
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("error"+ex);
            }
        }
        return con;    
    }
}
