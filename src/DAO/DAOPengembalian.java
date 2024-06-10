package DAO;


import Interface.IDAOPengembalian;
import Model.DataBuku;
import Model.DaftarPengembalian;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import helper.KoneksiDB;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author fahri
 */
public class DAOPengembalian implements IDAOPengembalian{
    Connection con;
    String baca = "SELECT * FROM datapeminjaman ORDER BY KodeBuku asc";
    String returning = "INSERT INTO databuku (KodeBuku,NamaBuku) SELECT KodeBuku,NamaBuku FROM datapeminjaman WHERE KodeBuku=? AND NamaBuku=?";
    String hapus2 = "DELETE FROM datapeminjaman WHERE KodeBuku=?";
    
    public DAOPengembalian(){
        con = KoneksiDB.connection();
    }

    @Override
    public List<DaftarPengembalian> getAll() {
        List<DaftarPengembalian> listPengembalian = null;
        try{
            listPengembalian = new ArrayList<DaftarPengembalian>();
            Statement St = con.createStatement(); 
            ResultSet rs = St.executeQuery(baca);
            while (rs.next()){
                DaftarPengembalian p = new DaftarPengembalian();
                p.setKodeBuku(rs.getInt("KodeBuku"));
                p.setNamaBuku(rs.getString("NamaBuku"));
                listPengembalian.add(p);
            }
        } catch (SQLException ex){
            System.out.println("ERROR!" + ex);
        }
        return listPengembalian;
    }

    @Override
    public boolean kembalikan(DaftarPengembalian p) {
        boolean result = true;
            PreparedStatement statement = null;
            try {
                statement = con.prepareStatement(returning);
                statement.setInt(1, p.getKodeBuku());
                statement.setString (2, p.getNamaBuku());
                statement.execute();
            } catch(SQLException e) {
                System.out.println("Kembalikan Gagal!");
                result = false;
            } finally {
                try {
                    statement.close();
                } catch(SQLException ex) {
                    System.out.println("Kembalikan Gagal");
                    result = false;
                }
            }
        return result;
    }

    @Override
    public void deleteDatapeminjaman(int KodeBuku) {
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(hapus2);
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
            System.out.println("gagal delete"+ex);
            }
        }
    }
}