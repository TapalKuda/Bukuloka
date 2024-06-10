/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.TabelDataPeminjaman;
import Model.DataBuku;
import java.util.List;

/**
 *
 * @author fahri
 */
public interface IDAOPeminjaman {
    public List<TabelDataPeminjaman> getAll();
    
    public boolean pinjam(DataBuku b);
    
    public void deleteDatabuku(int KodeBuku);
    
    public void deleteDatapeminjaman(int KodeBuku);
}
