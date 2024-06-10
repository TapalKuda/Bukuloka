/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.UserAkun;
import Model.DataBuku;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IDAOUser {
    public UserAkun login(String username, String password);
    
    public boolean register(UserAkun user);
    
    // Read Buku
    public List<DataBuku> getAll();
}


