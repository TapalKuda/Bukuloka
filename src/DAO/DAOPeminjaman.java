package DAO;

import Interface.IDAOPeminjaman;
import Model.TabelDataPeminjaman;
import Model.DataBuku;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import helper.KoneksiDB;


/**
 *
 * @author fahri
 */
public class DAOPeminjaman implements IDAOPeminjaman{
    Connection con;
    String baca = "SELECT * FROM databuku ORDER BY KodeBuku asc";
    String borrow = "INSERT INTO datapeminjaman (KodeBuku,NamaBuku) SELECT KodeBuku,NamaBuku FROM databuku WHERE KodeBuku=? AND NamaBuku=?";
    String hapus1 = "DELETE FROM databuku WHERE KodeBuku=?";
    String returning = "INSERT INTO databuku (KodeBuku,NamaBuku) SELECT KodeBUku,NamaBuku FROM datapeminjaman WHERE KodeBuku=? AND NamaBuku=?";
    String hapus2 = "DELETE FROM datapeminjaman WHERE KodeBuku=?";
    
    public DAOPeminjaman(){
        con = KoneksiDB.connection();
    }
    @Override
    public List<TabelDataPeminjaman> getAll() {
        return null;
    }

    @Override
    public boolean pinjam(DataBuku b) {
        boolean result = true;
            PreparedStatement statement = null;
            try {
                statement = con.prepareStatement(borrow);
                statement.setInt(1, b.getKodeBuku());
                statement.setString (2, b.getNamaBuku());
                statement.execute();
                
            } catch(SQLException e)
            {
                System.out.println("Pinjam Gagal!");
                result = false;
            }
            
        return result;
    }

    @Override
    public void deleteDatabuku(int KodeBuku) {
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(hapus1);
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
            System.out.println("gagal delete");
            }
        }
    }
}