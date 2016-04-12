/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import Template.Bao_Cao.Report_DocGiaController;
import com.sun.rowset.CachedRowSetImpl;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProvider;

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
    private TextField tf_NgaySinh;
    @FXML
    private TextField tf_sdt;
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
    ObservableList<nhanvien> data = FXCollections.observableArrayList();
    @FXML
    private DatePicker db_ngaysinh;
    @FXML
    private ComboBox<?> cb_GT;

    @FXML
    private void focus_ct(MouseEvent event) {
        int i = TB_NV.getFocusModel().getFocusedIndex();
        nhanvien nv = data.get(i);
        tf_maNV.setText(nv.getMaNV());
        tf_tenNV.setText(nv.getTenNV());
        tf_cmnd.setText(nv.getCMND());
        String dateNS = util.date.convertStringToDate(nv.getNgaySinh());
        db_ngaysinh.getEditor().setText(dateNS);
        tf_dc.setText(nv.getDiaChi());
        cb_GT.getEditor().setText(nv.gioiTinh);
        tf_sdt.setText(nv.getSdt());
        tf_chucVu.setText(nv.getChucVu());
        tf_luong.setText(nv.luong.toString());

        btn_save.setDisable(true);

    }
    Connection cn = null;

    @FXML
    private int saveNew(ActionEvent event) {
//        boolean match;
//        if(tf_tenNV.getText().matches("*\s\D")){
//            System.out.println("ten k dc chua so");
//        }

        if ((tf_tenNV.getText()).equals("") || (db_ngaysinh.getEditor().getText()).equals("") || (tf_sdt.getText()).equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
            System.out.println("Loi!");
            alert.showAndWait();
            return 1;
        } else {
            try {

                Date datestr = util.date.convertDatetoString(db_ngaysinh.getEditor().getText());
                //System.out.println(datestr);
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                if (tf_maNV.getText().equals("")) {
                    String str = "INSERT INTO dbo.NhanVien( MaNV ,HoVaTen ,SoDT ,Gioitinh ,NgaySinh ,Email ,Luong ,ChucVu ,DiaChi)"
                            + "VALUES  ( 'NV' , ? ,? ,? ,? ,? ,? ,? ,? )";
                    ps = cn.prepareStatement(str);
                    java.sql.Date date = new java.sql.Date(datestr.getTime());
                    ps.setNString(1, tf_tenNV.getText());
                    ps.setString(2, tf_sdt.getText());
                    ps.setNString(3, "nam");
                    ps.setDate(4, date);
                    ps.setString(5, tf_cmnd.getText());
                    ps.setDouble(6, Double.parseDouble(tf_luong.getText()));
                    ps.setNString(7, tf_chucVu.getText());
                    ps.setNString(8, tf_dc.getText());
                    ps.executeUpdate();
                    data.clear();
                    Statement st = null;
                    st = cn.createStatement();
                    ResultSet rs = st.executeQuery("Select * from NhanVien");
                    while (rs.next()) {
                        data.add(new nhanvien(rs.getString("MaNV"), rs.getString("HoVaTen"), rs.getDate("NgaySinh"), rs.getString("SoDT"),
                                rs.getString("Gioitinh"), rs.getString("DiaChi"), rs.getDouble("Luong"), rs.getString("ChucVu")));
                    }
                    TB_NV.setItems(data);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo");
                    alert.setHeaderText("Thêm thành công");
                    alert.showAndWait();
                    return 0;
                }

                
                String str = "UPDATE NhanVien SET HoVaTen = ? , NgaySinh = ? ,SoDT = ? ,DiaChi=? ,Gioitinh= ? ,Luong= ?, ChucVu= ? WHERE MaNV = ? ";
                ps = cn.prepareStatement(str);
                java.sql.Date date = new java.sql.Date(datestr.getTime());
                ps.setNString(1, tf_tenNV.getText());
                ps.setDate(2, date);
                ps.setString(3, tf_sdt.getText());
                ps.setNString(4, tf_dc.getText());
                ps.setNString(5, "nam");
                ps.setDouble(6, Double.parseDouble(tf_luong.getText()));
                ps.setNString(7, tf_chucVu.getText());
                ps.setString(8, tf_maNV.getText());
                ps.executeUpdate();

                data.clear();
                Statement st = null;
                st = cn.createStatement();
                ResultSet rs = st.executeQuery("Select * from NhanVien");
                while (rs.next()) {
                    data.add(new nhanvien(rs.getString("MaNV"), rs.getString("HoVaTen"), rs.getDate("NgaySinh"), rs.getString("SoDT"),
                            rs.getString("Gioitinh"), rs.getString("DiaChi"), rs.getDouble("Luong"), rs.getString("ChucVu")));
                }
                TB_NV.setItems(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Lưu thành công");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
    }

    @FXML
    private void xoa(ActionEvent event) {
        int i = TB_NV.getFocusModel().getFocusedIndex();
        nhanvien nv = data.get(i);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa ?");
        Boolean yes = alert.showAndWait().isPresent();
        if (yes) {

            try {
                data.remove(i);
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "DELETE FROM NhanVien WHERE MaNV= ?";

                ps = cn.prepareStatement(str);

                ps.setString(1, nv.getMaNV());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_maNV.setDisable(true);

        btn_save.setDisable(false);

    }

    public class nhanvien {

        private String MaNV;
        private String TenNV;
        private Date ngaySinh;
        private String sdt;
        private String gioiTinh;
        private String diaChi;
        private String CMND;
        private String ChucVu;
        private Double luong;

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

        public nhanvien(String MaNV, String TenNV, Date ngaySinh, String sdt, String gioiTinh, String diaChi, Double luong, String ChucVu) {
            this.MaNV = MaNV;
            this.TenNV = TenNV;
            this.ngaySinh = ngaySinh;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            // this.CMND = CMND;
            this.ChucVu = ChucVu;
            this.luong = luong;
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_save.setDisable(true);
        ObservableList cursors = FXCollections.observableArrayList(
                "Nam", "Nữ", "Khác"
        );
        cb_GT.setItems(cursors);
        
        String pattern = "dd/MM/yyyy";

        db_ngaysinh.setPromptText(pattern.toLowerCase());

        db_ngaysinh.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
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

        //   ObservableList<nhanvien> data = FXCollections.observableArrayList();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername(util.Connect_JDBC.userName);
            crs.setPassword(util.Connect_JDBC.password);
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("select * from NhanVien");
            crs.execute();
            while (crs.next()) {
                data.add(new nhanvien(crs.getString("MaNV"), crs.getString("HoVaTen"), crs.getDate("NgaySinh"), crs.getString("SoDT"), crs.getString("Gioitinh"), crs.getString("Email"), crs.getDouble("Luong"), crs.getString("ChucVu")));
            }
            crs.acceptChanges();
            crs.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        TB_NV.setItems(data);
    }

    @FXML
    public void themNhanVien(ActionEvent e) {

        tf_maNV.setText("");
        tf_tenNV.setText("");
        db_ngaysinh.getEditor().setText("");
        tf_dc.setText("");
        cb_GT.getEditor().setText("");
        tf_cmnd.setText("");
        tf_sdt.setText("");
        tf_chucVu.setText("");
        tf_luong.setText("");
        tf_maNV.setDisable(true);
        btn_save.setDisable(false);

    }

}
