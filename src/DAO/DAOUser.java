package DAO;

import helper.KoneksiDB;
import Interface.IDAOUser;
import Model.DataBuku;
import Model.UserAkun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOUser implements IDAOUser {
    Connection con;
    String read = "SELECT * FROM databuku";
    
    public DAOUser(){
        con = KoneksiDB.connection();
    }
    
    @Override
    public boolean register(UserAkun user) {
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
    public UserAkun login(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            String nama = rs.getString("nama");
            System.out.println("User found: " + nama); // Output terminal
            UserAkun user = new UserAkun(
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
    
    public UserAkun loginAdmin(String adminUsername, String adminPassword) throws SQLException {
        String query = "SELECT * FROM admin WHERE adminUsername = ? AND adminPassword = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, adminUsername);
            stmt.setString(2, adminPassword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nama = rs.getString("adminUsername");
                System.out.println("Admin found: " + nama); // Debugging statement
                UserAkun admin = new UserAkun(
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
    public List<DataBuku> getAll() {
        List<DataBuku> listdb = null;
        try{
            listdb = new ArrayList<DataBuku>();
            Statement St = con.createStatement(); 
            ResultSet rs = St.executeQuery(read);
            while (rs.next()){
                DataBuku b = new DataBuku();
                b.setKodeBuku(rs.getInt("KodeBuku"));
                b.setNamaBuku(rs.getString("NamaBuku"));
                listdb.add(b);
            }
        } catch (SQLException ex){
            System.out.println("ERROR!"+ex);
        }
        return listdb;
    }

}
    