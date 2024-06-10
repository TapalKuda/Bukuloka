package Controller;

import DAO.DAOUser;
import Interface.IDAOUser;
import Model.TabelDataBuku;
import Model.DataBuku;
import Model.UserAkun;
import View.formAdmin;
import View.formLogin;
import View.formPengembalian;
import View.formRegister;
import View.formUser;
import java.sql.SQLException;
import java.util.List;

public class ControllerUser {
    private formLogin view;
    private formRegister registerView;
    private DAOUser dao;
    private formAdmin frame;
    private IDAOUser IDAOUser;
    private formUser userFrame;
    private formPengembalian returnFrame;
    List<DataBuku> listBuku;

    // Constructor untuk formLogin
    public ControllerUser(formLogin view) {
        this.view = view;
        this.dao = new DAOUser();
    }

    // Constructor untuk formRegister
    public ControllerUser(formRegister view) {
        this.registerView = view;
        this.dao = new DAOUser();
    }
    
    // Constructor untuk Admin
    public ControllerUser(formAdmin frame) {
        this.frame = frame;
        IDAOUser = new DAOUser();
    }

    public ControllerUser(formUser userFrame) {
        this.userFrame = userFrame;
        IDAOUser = new DAOUser();
    }
    
    public ControllerUser(formPengembalian returnFrame) {
        this.returnFrame = returnFrame;
        IDAOUser = new DAOUser();
    }

    // Method login
    public void login() throws SQLException {
        String username = view.getUsername();
        String password = view.getPassword();
        boolean isAdmin = view.isAdminSelected();

        UserAkun user;
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

        UserAkun user = new UserAkun(username, password, nama);
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
    
    public void isiTabel() {
        listBuku = IDAOUser.getAll();
        TabelDataBuku tbldataBuku = new TabelDataBuku(listBuku);
        userFrame.getTabelData().setModel(tbldataBuku);
    }
    
    public void isiField(int row){
        userFrame.getKodeBuku().setText(String.valueOf(listBuku.get(row).getKodeBuku()));
        userFrame.getNamaBuku().setText(listBuku.get(row).getNamaBuku());
    }

    // Method untuk menunjukkan formUser
    public void showUserForm() {
        formUser userForm = new formUser();
        userForm.setVisible(true);
    }

    // Method untuk menunjukkan formAdmin
    private void showAdminForm() {
        formAdmin adminForm = new formAdmin();
        adminForm.setVisible(true);
    }
    
    // Method menampilkan menu register
    public void showFormRegister() {
        formRegister registerForm = new formRegister();
        view.dispose();
        registerForm.setVisible(true);
    }

    private void showLoginForm() {
        formLogin loginForm = new formLogin();
        loginForm.setVisible(true);
    }
    
    public void showPengembalianForm() {
        formPengembalian pengembalianForm = new formPengembalian();
        userFrame.dispose();
        pengembalianForm.setVisible(true);
    }
}
