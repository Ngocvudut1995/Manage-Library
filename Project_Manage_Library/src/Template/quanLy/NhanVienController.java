/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanLy;

import Template.Bao_Cao.Report_DocGiaController;
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
public class NhanVienController implements Initializable {
    @FXML
    private TableView<nhanvien> TB_NV;
    public class nhanvien{
      private  String MaNV;
      private  String TenNV;
      private  String ngaySinh;
      private  String sdt;
      private  String gioiTinh;
      private  String diaChi;
       private  String CMND;
      private  String ChucVu;
      private  Double luong;

        public String getMaNV() {
            return MaNV;
        }

        public void setMaNV(String MaNV) {
            this.MaNV = MaNV;
        }

        public String getTenNV() {
            return TenNV;
        }

        public void setTenNV(String TenNV) {
            this.TenNV = TenNV;
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

        public String getChucVu() {
            return ChucVu;
        }

        public void setChucVu(String ChucVu) {
            this.ChucVu = ChucVu;
        }

        public Double getLuong() {
            return luong;
        }

        public void setLuong(Double luong) {
            this.luong = luong;
        }

        public nhanvien(String MaNV, String TenNV, String ngaySinh, String sdt, String gioiTinh, String diaChi, String CMND, String ChucVu, Double luong) {
            this.MaNV = MaNV;
            this.TenNV = TenNV;
            this.ngaySinh = ngaySinh;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            this.CMND = CMND;
            this.ChucVu = ChucVu;
            this.luong = luong;
        }
      
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<nhanvien, String> manvcol = new TableColumn("   Mã Nhân Viên   ");
        manvcol.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        TB_NV.getColumns().add(manvcol);
        TableColumn<nhanvien, String> tennvcol = new TableColumn("   Tên Nhân Viên   ");
        tennvcol.setCellValueFactory(new PropertyValueFactory<>("TenNV"));
        TB_NV.getColumns().add(tennvcol);
        TableColumn<nhanvien, String> ngaycol = new TableColumn("   Ngày sinh   ");
        ngaycol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        TB_NV.getColumns().add(ngaycol);
        TableColumn<nhanvien, String> sdtcol = new TableColumn("   SDT   ");
        sdtcol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        TB_NV.getColumns().add(sdtcol);
        TableColumn<nhanvien, String> gtcol = new TableColumn("   Giới Tính   ");
        gtcol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        TB_NV.getColumns().add(gtcol);
        TableColumn<nhanvien, String> dccol = new TableColumn("   Địa  Chỉ   ");
        dccol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TB_NV.getColumns().add(dccol);
        TableColumn<nhanvien, String> cmndcol = new TableColumn("   CMND   ");
        cmndcol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        TB_NV.getColumns().add(cmndcol);
        TableColumn<nhanvien, String> CVcol = new TableColumn("   Chức Vụ  ");
        CVcol.setCellValueFactory(new PropertyValueFactory<>("ChucVu"));
        TB_NV.getColumns().add(CVcol);
        TableColumn<nhanvien, Double> luongcol = new TableColumn("   Lương   ");
        luongcol.setCellValueFactory(new PropertyValueFactory<>("luong"));
        TB_NV.getColumns().add(luongcol);
         
        ObservableList<nhanvien> data = FXCollections.observableArrayList();
         data.add(new nhanvien("MHS2", "VU", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null));
         TB_NV.setItems(data);
    }    
    
}
