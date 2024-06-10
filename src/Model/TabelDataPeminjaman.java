/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author fahri
 */
public class TabelDataPeminjaman extends AbstractTableModel{


    
    public TabelDataPeminjaman(List<DaftarPengembalian> listPengembalian) {
        this.listPengembalian = listPengembalian;
    }
    
    List<DaftarPengembalian> uPengembalian;


    /**
     * @return the kodeBuku
     */
    public int getKodeBuku() {
        return kodeBuku;
    }

    /**
     * @param kodeBuku the kodeBuku to set
     */
    public void setKodeBuku(int kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    /**
     * @return the namaBuku
     */
    public String getNamaBuku() {
        return namaBuku;
    }

    /**
     * @param namaBuku the namaBuku to set
     */
    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }
    private int kodeBuku;
    private String namaBuku;

    @Override
    public int getRowCount() {
        return this.listPengembalian.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listPengembalian.get(rowIndex).getKodeBuku();
            case 1:
                return listPengembalian.get(rowIndex).getNamaBuku();
            default:
                return null;
        }
    }
    
    List<DaftarPengembalian> listPengembalian;
}
