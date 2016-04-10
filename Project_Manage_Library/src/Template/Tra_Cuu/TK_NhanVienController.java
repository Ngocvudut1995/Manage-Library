/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Tra_Cuu;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class TK_NhanVienController implements Initializable {

    ObservableList<nhanvien> data = FXCollections.observableArrayList();
    @FXML
    private TextField tf_TKmaNV;
    @FXML
    private TextField tf_TKtenNV;
    @FXML
    private Button btn_TKmaNV;
    @FXML
    private Button btn_TKtenNV;
    @FXML
    private TextField tf_maNV;
    @FXML
    private TextField tf_tenNV;
    @FXML
    private TextField tf_chucVu;
    @FXML
    private TextField tf_ngaySInh;
    @FXML
    private TextField tf_gt;
    @FXML
    private TextField tf_dc;
    @FXML
    private TextField tf_sdt;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_luong;
    @FXML
    private TextField tf_phong;
    @FXML
    private TableView<nhanvien> TB_NV;
    Connection cn = null;

    @FXML
    private void tracuuTheoMaNV(ActionEvent event) {
        data.clear();
        cn = util.Connect_JDBC.getConnection();
        String str="SELECT * FROM dbo.NhanVien WHERE (MaNV LIKE '"+tf_TKmaNV.getText()+"%') OR (MaNV LIKE '%"+tf_TKmaNV.getText()+"')OR (MaNV LIKE '%"+tf_TKmaNV.getText()+"%')";
        PreparedStatement ps=null;
        try {
            ps=cn.prepareStatement(str);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                data.add(new nhanvien(data.size()+1, rs.getString("MaNV"), rs.getNString("HoVaTen"), rs.getDate("NgaySinh"), rs.getString("SoDT")
                        , rs.getNString("Gioitinh"), rs.getString("CMND"), rs.getNString("ChucVu"), rs.getDouble("Luong"), rs.getString("Email"), rs.getString("MaPhong")));
            }
            TB_NV.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(TK_NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tracuuTheoTenNV(ActionEvent event) {
    }

    public class nhanvien {

        private int stt;
        private String MaNV;
        private String TenNV;
        private Date ngaySinh;
        private String sdt;
        private String gioiTinh;
        private String diaChi;
        //private String CMND;
        private String ChucVu;
        private Double luong;
        private String email;
        private String phong;

        public nhanvien(int stt,String MaNV, String TenNV, Date ngaySinh, String sdt, String gioiTinh, String diaChi, String ChucVu, Double luong, String email, String phong) {
            this.stt=stt;
            this.MaNV = MaNV;
            this.TenNV = TenNV;
            this.ngaySinh = ngaySinh;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            this.ChucVu = ChucVu;
            this.luong = luong;
            this.email = email;
            this.phong = phong;
        }

        public int getStt() {
            return stt;
        }

        public void setStt(int stt) {
            this.stt = stt;
        }

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

        public Date getNgaySinh() {
            return ngaySinh;
        }


        public void setNgaySinh(Date ngaySinh) {
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

        public String getPhong() {
            return phong;
        }

        public void setPhong(String phong) {
            this.phong = phong;
        }

        public void setDiaChi(String diaChi) {
            this.diaChi = diaChi;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
        TableColumn<nhanvien, String> cmndcol = new TableColumn("   Email   ");
        cmndcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TB_NV.getColumns().add(cmndcol);
        TableColumn<nhanvien, String> CVcol = new TableColumn("   Chức Vụ  ");
        CVcol.setCellValueFactory(new PropertyValueFactory<>("ChucVu"));
        TB_NV.getColumns().add(CVcol);
        TableColumn<nhanvien, Double> luongcol = new TableColumn("   Lương   ");
        luongcol.setCellValueFactory(new PropertyValueFactory<>("luong"));
        TB_NV.getColumns().add(luongcol);
        TableColumn<nhanvien, String> phongcol = new TableColumn("   Phòng   ");
        phongcol.setCellValueFactory(new PropertyValueFactory<>("phong"));
        TB_NV.getColumns().add(phongcol);
    }

    @FXML
    private void focus_CT(MouseEvent event) {
        int i = TB_NV.getFocusModel().getFocusedIndex();
        nhanvien nv = data.get(i);
        tf_maNV.setText(nv.getMaNV());
        tf_tenNV.setText(nv.getTenNV());
        tf_email.setText(nv.getEmail());
        String dateNS = util.date.convertStringToDate(nv.getNgaySinh());
        tf_ngaySInh.setText(dateNS);
        tf_dc.setText(nv.getDiaChi());
        tf_gt.setText(nv.getGioiTinh());
        tf_sdt.setText(nv.getSdt());
        tf_chucVu.setText(nv.getChucVu());
        tf_luong.setText(nv.luong.toString());
        tf_phong.setText(nv.getPhong());
                
    }

}
