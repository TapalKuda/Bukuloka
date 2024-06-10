/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.DaftarPengembalian;
import java.util.List;

/**
 *
 * @author fahri
 */
public interface IDAOPengembalian {
    public List<DaftarPengembalian> getAll();
    
    public boolean kembalikan(DaftarPengembalian p);
    
    public void deleteDatapeminjaman(int KodeBuku);
}
