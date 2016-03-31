/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Bao_Cao;

import com.sun.rowset.CachedRowSetImpl;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class Report_NhanVienController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public class Employee {
        private String MaNV;
        private String CMND;
        private String Hovaten;
        private String SoDT;
        private String DiaChi;
        private String NgaySinh;
        private String GioiTinh;
        private String Email;
        private Double luong;
        private String chucvu;
        private String maphong;

        public String getMaNV() {
            return MaNV;
        }

        public void setMaNV(String MaNV) {
            this.MaNV = MaNV;
        }

        public String getCMND() {
            return CMND;
        }

        public void setCMND(String CMND) {
            this.CMND = CMND;
        }

        public String getHovaten() {
            return Hovaten;
        }

        public void setHovaten(String Hovaten) {
            this.Hovaten = Hovaten;
        }

        public String getSoDT() {
            return SoDT;
        }

        public void setSoDT(String SoDT) {
            this.SoDT = SoDT;
        }

        public String getDiaChi() {
            return DiaChi;
        }

        public void setDiaChi(String DiaChi) {
            this.DiaChi = DiaChi;
        }

        public String getNgaySinh() {
            return NgaySinh;
        }

        public void setNgaySinh(String NgaySinh) {
            this.NgaySinh = NgaySinh;
        }

        public String getGioiTinh() {
            return GioiTinh;
        }

        public void setGioiTinh(String GioiTinh) {
            this.GioiTinh = GioiTinh;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public Double getLuong() {
            return luong;
        }

        public void setLuong(Double luong) {
            this.luong = luong;
        }

        public String getChucvu() {
            return chucvu;
        }

        public void setChucvu(String chucvu) {
            this.chucvu = chucvu;
        }

        public String getMaphong() {
            return maphong;
        }

        public void setMaphong(String maphong) {
            this.maphong = maphong;
        }

        public Employee(String MaNV, String CMND, String Hovaten, String SoDT, String DiaChi, String NgaySinh, String GioiTinh, String Email, Double luong, String chucvu, String maphong) {
            this.MaNV = MaNV;
            this.CMND = CMND;
            this.Hovaten = Hovaten;
            this.SoDT = SoDT;
            this.DiaChi = DiaChi;
            this.NgaySinh = NgaySinh;
            this.GioiTinh = GioiTinh;
            this.Email = Email;
            this.luong = luong;
            this.chucvu = chucvu;
            this.maphong = maphong;
        }
    }
     @FXML
    private TableView<Employee> TBNhanVien;
    @FXML
    private Button bt_nhanvien_tuan;

    @FXML
    public void getNhanVien_Tuan(ActionEvent e) {
        ObservableList<Employee> data = FXCollections.observableArrayList();
        
        data.add(new Employee("NV001", null, null, null, null, null, null, null, 15000000.0, null, null));
        data.add(new Employee("NV001", null, null, null, null, null, null, null, Double.NaN, null, null));
        data.add(new Employee("NV001", null, null, null, null, null, null, null, Double.NaN, null, null));
        data.add(new Employee("NV001", null, null, null, null, null, null, null, Double.NaN, null, null));
        data.add(new Employee("NV001", null, null, null, null, null, null, null, Double.NaN, null, null));
        data.add(new Employee("NV001", null, null, null, null, null, null, null, Double.NaN, null, null));
        data.add(new Employee("NV001", null, null, null, null, null, null, null, Double.NaN, null, null));
        data.add(new Employee("NV001", null, null, null, null, null, null, null, Double.NaN, null, null));
        TBNhanVien.setItems(data);
       
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
