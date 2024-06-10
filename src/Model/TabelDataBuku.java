/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TabelDataBuku extends AbstractTableModel{
    
    public TabelDataBuku(List<DataBuku> listBuku) {
        this.listBuku = listBuku;
    }
    
    List<UserAkun> uAkun;

    @Override
    public int getRowCount() {
        return this.listBuku.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
       
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Buku";
            case 1:
                return "Nama Buku";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listBuku.get(rowIndex).getKodeBuku();
            case 1:
                return listBuku.get(rowIndex).getNamaBuku();
            default:
                return null;
        }
    }
    
    List<DataBuku> listBuku;
}
