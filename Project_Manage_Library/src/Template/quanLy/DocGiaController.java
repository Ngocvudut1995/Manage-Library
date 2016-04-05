/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class DocGiaController implements Initializable {
    @FXML
    private TableView<docGia> TB_DG;

    public  class docGia{
        private String maDG;
        private String tenDG;
        private String ngaySinh;
        private String sdt;
        private String gioiTinh;
        private String diaChi;
        private String CMND;
        private String ngayDK;
        private String ngayHet;
        private String email;
        private String trangThai;

        public String getMaDG() {
            return maDG;
        }

        public void setMaDG(String maDG) {
            this.maDG = maDG;
        }

        public String getTenDG() {
            return tenDG;
        }

        public void setTenDG(String tenDG) {
            this.tenDG = tenDG;
        }

        public String getNgaySinh() {
            return ngaySinh;
        }

        public void setNgaySinh(String ngaySinh) {
            this.ngaySinh = ngaySinh;
        }

        public String getSdt() {
            return sdt;
        }

        public void setSdt(String sdt) {
            this.sdt = sdt;
        }

        public String getGioiTinh() {
            return gioiTinh;
        }

        public void setGioiTinh(String gioiTinh) {
            this.gioiTinh = gioiTinh;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public void setDiaChi(String diaChi) {
            this.diaChi = diaChi;
        }
        public String getCMND() {
            return CMND;
        }

        public void setCMND(String CMND) {
            this.CMND = CMND;
        }
        public String getNgayDK() {
            return ngayDK;
        }

        public void setNgayDK(String ngayDK) {
            this.ngayDK = ngayDK;
        }

        public String getNgayHet() {
            return ngayHet;
        }

        public void setNgayHet(String ngayHet) {
            this.ngayHet = ngayHet;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(String trangThai) {
            this.trangThai = trangThai;
        }
        
        public docGia(String maDG, String tenDG, String ngaySinh, String sdt, String gioiTinh, String diaChi,String CMND, String ngayDK, String ngayHet, String email, String trangThai) {
            this.maDG = maDG;
            this.tenDG = tenDG;
            this.ngaySinh = ngaySinh;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            this.CMND=CMND;
            this.ngayDK = ngayDK;
            this.ngayHet = ngayHet;
            this.email = email;
            this.trangThai = trangThai;
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            TableColumn<docGia,String> maDGcol=new TableColumn<>(" Ma DG ");
            maDGcol.setCellValueFactory(new PropertyValueFactory<>("maDG"));
            TB_DG.getColumns().add(maDGcol);
            
            TableColumn<docGia,String> tenDGcol=new TableColumn("   Tên DG   ");
            tenDGcol.setCellValueFactory(new PropertyValueFactory<>("tenDG"));
            TB_DG.getColumns().add(tenDGcol);
            
            TableColumn<docGia,String> ngaySinhcol=new TableColumn<>("  Ngày Sinh  ");
           ngaySinhcol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            TB_DG.getColumns().add(ngaySinhcol);
            
            TableColumn<docGia,String> sdtcol=new TableColumn("     SĐT     ");
            sdtcol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
            TB_DG.getColumns().add(sdtcol);
            
            TableColumn<docGia,String> gioiTinhcol=new TableColumn<>("   Giới tính   ");
            gioiTinhcol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
            TB_DG.getColumns().add(gioiTinhcol);
            
            TableColumn<docGia,String> dccol=new TableColumn("     Địa chỉ     ");
            dccol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
            TB_DG.getColumns().add(dccol);
            
            TableColumn<docGia,String> cmndcol=new TableColumn("    Số CMND    ");
            cmndcol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
            TB_DG.getColumns().add(cmndcol);
            
            TableColumn<docGia,String> ngayDKcol=new TableColumn<>("Ngày đăng kí");
            ngayDKcol.setCellValueFactory(new PropertyValueFactory<>("ngayDK"));
            TB_DG.getColumns().add(ngayDKcol);
            
            TableColumn<docGia,String> ngayHetcol=new TableColumn("Ngày hết hạng");
            ngayHetcol.setCellValueFactory(new PropertyValueFactory<>("ngayHet"));
            TB_DG.getColumns().add(ngayHetcol);
            
            TableColumn<docGia,String> emailcol=new TableColumn<>("         Email         ");
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            TB_DG.getColumns().add(emailcol);
            
            TableColumn<docGia,String> trangThaicol=new TableColumn("  Trạng Thái  ");
            trangThaicol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
            TB_DG.getColumns().add(trangThaicol);
            
            ObservableList<docGia> data= FXCollections.observableArrayList();
            
    }  
    
}
