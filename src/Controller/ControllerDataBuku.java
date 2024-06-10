/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import DAO.DAODataBuku;
import View.formAdmin;
import java.util.List;
import Model.DataBuku;
import Model.TabelDataBuku;
import javax.swing.JOptionPane;
import Interface.IDAODataBuku;

/**
 *
 * @author Asus
 */
public class ControllerDataBuku {
    private IDAODataBuku IDAOdataBuku;
    private formAdmin frame;
    private TabelDataBuku TabelDataBuku;
    List<DataBuku> listBuku;
    
    
    public ControllerDataBuku(formAdmin frame){
        this.frame = frame;
        IDAOdataBuku = new DAODataBuku();
    }
    
    public void isiTabel(){
        listBuku = IDAOdataBuku.getAll();
        TabelDataBuku tbldataBuku = new TabelDataBuku(listBuku);
        frame.getTabelData().setModel(tbldataBuku);
    }
    
    public void isiField(int row){
        frame.getKodeBuku().setText(String.valueOf(listBuku.get(row).getKodeBuku()));
        frame.getNamaBuku().setText(listBuku.get(row).getNamaBuku());
    }

   
    public void Insert(){
        DataBuku b = new DataBuku();
        b.setKodeBuku(Integer.parseInt(frame.getKodeBuku().getText()));
        b.setNamaBuku(frame.getNamaBuku().getText());
        boolean rslt = IDAOdataBuku.insert(b);
        if (rslt)
            JOptionPane.showMessageDialog(null, " INPUT SUKSES! ");
        else
            JOptionPane.showMessageDialog(null, " INPUT GAGAL / DATA DUPLIKAT ");
    }
    
    public void reset(){
        if(!frame.getKodeBuku().isEnabled())
            frame.getKodeBuku().setEnabled(true);
        frame.getKodeBuku().setText("");
        frame.getNamaBuku().setText("");
    }
    
    public void update(){
        DataBuku b = new DataBuku();
        b.setNamaBuku(frame.getNamaBuku().getText());
        b.setKodeBuku(Integer.parseInt(frame.getKodeBuku().getText()));
        boolean result = IDAOdataBuku.update(b);
        if (result)
            JOptionPane.showMessageDialog(null, "Update Berhasil");
        else
            JOptionPane.showMessageDialog(null, "Update Gagal");
        
    }
    
    public void delete(){
        int id_buku = Integer.parseInt(frame.getKodeBuku().getText());
        IDAOdataBuku.delete(id_buku);
        JOptionPane.showMessageDialog(null, "Delete berhasil");
    }       
}
