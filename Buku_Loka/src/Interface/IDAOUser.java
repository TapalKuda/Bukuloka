/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.userakun;
import Model.dataBuku;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IDAOUser {
    public userakun login(String username, String password);
    
    public boolean register(userakun user);
    
    public List<dataBuku> getAll();
}
