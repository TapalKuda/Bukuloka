/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.util.List;
import Model.DataBuku;
import Bukuloka.Bukuloka;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Interface.IDAODataBuku;

/**
 *
 * @author Asus
 */
public class DAODataBuku implements IDAODataBuku {
    Connection con;
    String read = "select * from databuku ORDER BY KodeBuku asc;";
    String strInsert = "insert into databuku (KodeBuku,NamaBuku) values (?,?);";
    String strUpdate = "update databuku set NamaBuku=? where KodeBuku=?";
    String strDelete = "delete from databuku where KodeBuku=?";
    String strSearch = "select * from databuku where NamaBuku like ?;";
    
    @Override
    public List<DataBuku> getAll() {
        List<DataBuku> listAdmin = null;
        try{
            listAdmin = new ArrayList<DataBuku>();
            Statement St = con.createStatement(); 
            ResultSet rs = St.executeQuery(read);
            while (rs.next()){
                DataBuku b = new DataBuku();
                b.setKodeBuku(rs.getInt("KodeBuku"));
                b.setNamaBuku(rs.getString("NamaBuku"));
                listAdmin.add(b);
            }
        } catch (SQLException ex){
            System.out.println("ERROR!" + ex);
        }
        return listAdmin;
    }

    @Override
    public boolean insert(DataBuku b) {
     boolean result = true;
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(strInsert);
            statement.setInt(1, b.getKodeBuku());
            statement.setString (2, b.getNamaBuku());
            statement.execute();
            
        }catch(SQLException e)
        {
            System.out.println("gagal input");
            result = false;
        }
        finally
        {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println("gagal input");
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean update(DataBuku b) {
      boolean result = true;
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(strUpdate);
            statement.setString (1, b.getNamaBuku());
            statement.setInt (2, b.getKodeBuku());
            statement.execute();
            
        }catch(SQLException e)
        {
            System.out.println("Update Gagal!");
            result = false;
        }
        return result;
    }

    @Override
    public List<DataBuku> getCariNama(String Nama) {
      List<DataBuku> listAdmin = null;
        try{
            listAdmin = new ArrayList<DataBuku>();
            PreparedStatement st = con.prepareStatement (strSearch); 
            st.setString(1, "%"+Nama+"%");
            ResultSet rs = st.executeQuery(); 
            while(rs.next()){
                DataBuku mhs = new DataBuku (); 
                mhs.setKodeBuku(rs.getInt("Kode")); 
                mhs.setNamaBuku(rs.getString("Nim")); 
                listAdmin.add(mhs);
            }
            } catch (SQLException ex){
            System.out.println("ERROR!"+ex);
            }
        return listAdmin;
    }
   
    public DAODataBuku(){
        con = Bukuloka.getConnection();
    }

    @Override
    public void delete(int KodeBuku) {
       PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(strDelete);
            statement.setInt(1, KodeBuku );
            statement.execute();
            
        }catch(SQLException e)
        {
            System.out.println("gagal delete"+e);
        }
        finally
        {
            try {
                statement.close();
            } catch (SQLException ex) {
            System.out.println("gagal delete");
            }
        }
    }
}