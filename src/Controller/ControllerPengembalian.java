/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import DAO.DAOPengembalian;
import Interface.IDAOPengembalian;
import Model.TabelDataPeminjaman;
import Model.DaftarPengembalian;
import View.formPengembalian;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class ControllerPengembalian {
    private IDAOPengembalian iKembalikan;
    private formPengembalian pengembalianFrame;
    private TabelDataPeminjaman TabelPeminjaman;
    List<DaftarPengembalian> listPengembalian;
    
    public ControllerPengembalian(formPengembalian pengembalianFrame) {
        this.pengembalianFrame = pengembalianFrame;
        iKembalikan = new DAOPengembalian();
    }
    
    public void pengembalian() {
        DaftarPengembalian p = new DaftarPengembalian();
        p.setKodeBuku(Integer.parseInt(pengembalianFrame.getKodeBuku().getText()));
        p.setNamaBuku(pengembalianFrame.getNamaBuku().getText());
        boolean rslt = iKembalikan.kembalikan(p);
        if(rslt) {
            iKembalikan.deleteDatapeminjaman(Integer.parseInt(pengembalianFrame.getKodeBuku().getText()));
            JOptionPane.showMessageDialog(null, "Kamu telah mengembalikan Buku");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal mengembalikan Buku");
        }
    }
        
    public void deleteDatapeminjaman() {
        int idBuku = Integer.parseInt(pengembalianFrame.getKodeBuku().getText());
        iKembalikan.deleteDatapeminjaman(idBuku);
    }
    
    public void isiTabel(){
        listPengembalian = iKembalikan.getAll();
        TabelDataPeminjaman tblPinjam = new TabelDataPeminjaman(listPengembalian);
        pengembalianFrame.gettabelBuku().setModel(tblPinjam);
    }
    
    public void isiField(int row) {
        pengembalianFrame.getKodeBuku().setText(String.valueOf(listPengembalian.get(row).getKodeBuku()));
        pengembalianFrame.getNamaBuku().setText(listPengembalian.get(row).getNamaBuku());
    }
}
