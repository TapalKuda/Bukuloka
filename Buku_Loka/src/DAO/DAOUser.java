package DAO;

import Helper.KoneksiDB;
import static Helper.KoneksiDB.connection;
import Interface.IDAOUser;
import Model.dataBuku;
import Model.userakun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOUser implements IDAOUser {
    private Connection con;
    
    public DAOUser(){
        con = KoneksiDB.connection();
    }
    
    @Override
    public boolean register(userakun user) {
        con = KoneksiDB.connection();
        String sql = "INSERT INTO user (username, password, nama) VALUES (?,?,?)";
        
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNama());
            int rowsInserted = ps.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
            return rowsInserted > 0;
        } catch(SQLException e) {
            System.out.println("Error Registrasi");
        }
        return false;
    }
    
    @Override
    public userakun login(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            String nama = rs.getString("nama");
            System.out.println("User found: " + nama); // Output terminal
            userakun user = new userakun(
                    rs.getString("username"),
                    rs.getString("password"),
                    nama
            );
                return user;
            } else {
            System.out.println("User not found"); // Output terminal
            return null; // User not found
            }
        } catch (SQLException e) {
            System.out.println("Error");
            return null; // Error occurred
        }
    }
    
    public userakun loginAdmin(String adminUsername, String adminPassword) throws SQLException {
        String query = "SELECT * FROM admin WHERE adminUsername = ? AND adminPassword = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, adminUsername);
            stmt.setString(2, adminPassword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nama = rs.getString("adminUsername");
                System.out.println("Admin found: " + nama); // Debugging statement
                userakun admin = new userakun(
                        rs.getString("adminUsername"),
                        rs.getString("adminPassword"),
                        "Admin"
                );
                return admin;
        } else {
                System.out.println("Admin not found"); // Debugging statement
                return null; // Admin not found
            }
    } catch (SQLException e) {
        System.out.println("Error Admin");
        return null; // Terjadi Error
        }
    }

    @Override
    public List<dataBuku> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    