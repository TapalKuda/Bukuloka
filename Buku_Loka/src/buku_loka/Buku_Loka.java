/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buku_loka;

import Helper.KoneksiDB;
import View.formLogin;

/**
 *
 * @author Asus
 */
public class Buku_Loka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KoneksiDB.connection();
        formLogin loginForm = new formLogin();
            loginForm.setVisible(true);
    }
}
