/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.DataBuku;
import java.util.List;


/**
 *
 * @author azkan
 */
public interface IDAODataBuku 
{
    public List<DataBuku> getAll();
    
    //insert data
    public boolean insert(DataBuku b);
    
    //update data
    public boolean update(DataBuku b);
    
    //delete dataa
    public void delete(int KodeBuku);
    
    //search data
    public List<DataBuku> getCariNama(String Nama);
}
