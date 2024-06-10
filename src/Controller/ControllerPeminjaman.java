package Controller;

import DAO.DAOPeminjaman;
import Interface.IDAOPeminjaman;
import Model.TabelDataPeminjaman;
import Model.DataBuku;
import Model.TabelDataBuku;
import View.formUser;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerPeminjaman {
    private IDAOPeminjaman iPinjam;
    private formUser userFrame;
    private TabelDataBuku TabelBuku;
    List<TabelDataPeminjaman> listPeminjaman;
    
    public ControllerPeminjaman(formUser userFrame) {
        this.userFrame = userFrame;
        iPinjam = new DAOPeminjaman();
    }
    
    public void pinjam() {
        DataBuku b = new DataBuku();
        b.setKodeBuku(Integer.parseInt(userFrame.getKodeBuku().getText()));
        b.setNamaBuku(userFrame.getNamaBuku().getText());
        boolean rslt = iPinjam.pinjam(b);
        if(rslt) {
            iPinjam.deleteDatabuku(Integer.parseInt(userFrame.getKodeBuku().getText()));
            JOptionPane.showMessageDialog(null, "Kamu telah meminjam Buku");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal meminjam Buku");
        }
    }
    
    public void deleteDatabuku() {
        int idBuku = Integer.parseInt(userFrame.getKodeBuku().getText());
        iPinjam.deleteDatabuku(idBuku);
    }
    
    public void deleteDatapeminjaman() {
        int id_Buku = Integer.parseInt(userFrame.getKodeBuku().getText());
        iPinjam.deleteDatapeminjaman(id_Buku);
    }
}
