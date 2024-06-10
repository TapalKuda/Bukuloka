package Controller;

import DAO.DAOUser;
import Model.userakun;
import View.formAdmin;
import View.formLogin;
import View.formRegister;
import View.formUser;
import java.sql.SQLException;

public class userController {
    private formLogin view;
    private formRegister registerView;
    private DAOUser dao;

    // Constructor untuk formLogin
    public userController(formLogin view) {
        this.view = view;
        this.dao = new DAOUser();
    }

    // Constructor untuk formRegister
    public userController(formRegister view) {
        this.registerView = view;
        this.dao = new DAOUser();
    }

    // Method login
    public void login() throws SQLException {
        String username = view.getUsername();
        String password = view.getPassword();
        boolean isAdmin = view.isAdminSelected();

        userakun user;
        if (isAdmin) {
            user = dao.loginAdmin(username, password);
            if (user != null) {
                view.showMessage("Selamat Datang, " + user.getNama());
                view.dispose();
                showAdminForm(); // Menampilkan formAdmin jika login sebagai admin berhasil
            } else {
                view.showMessage("Username atau Password salah!");
            }
        } else {
            user = dao.login(username, password);
            if (user != null) {
                view.showMessage("Selamat Datang, " + user.getNama());
                view.dispose();
                showUserForm(); // Menampilkan formUser jika login sebagai user berhasil
            } else {
                view.showMessage("Username atau Password salah!");
            }
        }
    }
    
    // Method registrasi
    public void register() {
        String username = registerView.gettxtUsername().getText();
        String password = registerView.gettxtPassword().getText();
        String nama = registerView.gettxtNama().getText();

        userakun user = new userakun(username, password, nama);
        boolean success = dao.register(user);

        if (success) {
            registerView.showMessage("Registrasi Berhasil");
            // Mengalihkan JFrame
            registerView.dispose();
            showLoginForm();
        } else {
            registerView.showMessage("Registrasi Gagal");
        }
    }

    // Method untuk menunjukkan formUser
    private void showUserForm() {
        formUser userForm = new formUser();
        userForm.setVisible(true);
    }

    // Method untuk menunjukkan formAdmin
    private void showAdminForm() {
        formAdmin adminForm = new formAdmin();
        adminForm.setVisible(true);
    }
    
    // Method menampilkan menu register
    public void showFormregister() {
        formRegister registerForm = new formRegister();
        view.dispose();
        registerForm.setVisible(true);
    }

    private void showLoginForm() {
        formLogin loginForm = new formLogin();
        loginForm.setVisible(true);
    }
}
