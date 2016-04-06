/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanLy;

import Template.Bao_Cao.Report_DocGiaController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.sql.rowset.CachedRowSet;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class NhanVienController implements Initializable {
    @FXML
    private TableView<nhanvien> TB_NV;
    @FXML
    private Button btn_xoa;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_them;
    @FXML
    private TextField tf_maNV;
    @FXML
    private TextField tf_tenNV;
    @FXML
    private TextField tf_NgaySinh;
    @FXML
    private TextField tf_sdt;
    @FXML
    private TextField tf_gt;
    @FXML
    private TextField tf_dc;
    @FXML
    private TextField tf_cmnd;
    @FXML
    private TextField tf_chucVu;
    @FXML
    private TextField tf_luong;
    @FXML
    private Button btn_save;
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
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername("sa");
            crs.setPassword("123456");
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("select * from NhanVien");
            crs.execute();
            while(crs.next()){
                data.add(new nhanvien(crs.getString(1), crs.getString(2), crs.getString(3),crs.getString(4) , crs.getString(5),crs.getString(6),crs.getString(7),crs.getString(8),crs.getDouble(9)));
            }
            crs.acceptChanges();
            crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         TB_NV.setItems(data);
    } 
    @FXML
    public void themNhanVien(ActionEvent e) {
        ObservableList<nhanvien> data = FXCollections.observableArrayList();
        tf_maNV.setText("");
        tf_tenNV.setText("");
        tf_NgaySinh.setText("");
        tf_dc.setText("");
        tf_gt.setText("");
        tf_cmnd.setText("");
        tf_sdt.setText("");
        tf_chucVu.setText("");
        tf_luong.setText("");
        
              
    }
    @FXML
    public void thongTinNV(ActionEvent e){
        ObservableList<nhanvien> data = FXCollections.observableArrayList();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername("sa");
            crs.setPassword("123456");
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("select * from NhanVien");
            crs.execute();
            while(crs.next()){
                data.add(new nhanvien(crs.getString(1), crs.getString(2), crs.getString(3),crs.getString(4) , crs.getString(5),crs.getString(6),crs.getString(7),crs.getString(8),crs.getDouble(9)));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
