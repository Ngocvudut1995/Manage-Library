/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import Validate.NameTextField;
import Validate.NumberTextField;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import static util.Encode.getSecurePassword;

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
    private TextField tf_sdt;

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
    private ComboBox<String> cb_GT;
    @FXML
    private TextField tf_email;
    @FXML
    private Button btn_huy;
    @FXML
    private Button bt_load;

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
        for (int j = 0; j < data_gioitinh.size(); j++) {
            if (nv.getGioiTinh().equals(data_gioitinh.get(j))) {
                cb_GT.getSelectionModel().select(j);
            }
        }
        // tf
        tf_email.setText(nv.getEmail());
        tf_sdt.setText(nv.getSdt());
        tf_chucVu.setText(nv.getChucVu());
        tf_luong.setText(nv.luong.toString());
       // tf_maNV.setDisable(false);
        // btn_save.setDisable(true);

    }
    Connection cn = null;

    @FXML
    private int saveNew(ActionEvent event) {
        boolean match;
        boolean test = true;
        TextField[] mangTF = {tf_tenNV, tf_sdt, tf_dc, tf_cmnd, tf_chucVu, tf_luong};
        for (int i = 0; i < 6; i++) {
            if (mangTF[i].getText().equals("")) {
                mangTF[i].setStyle("-fx-border-color:red;-fx-border-width: 2px ;");
                test = false;
            }
        }

        if (test == true) {
//            if ((tf_tenNV.getText()).equals("") || (db_ngaysinh.getEditor().getText()).equals("") || (tf_sdt.getText()).equals("")) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Thông Báo");
//                alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
//                System.out.println("Loi!");
//                alert.showAndWait();
//                return 1;
//            } 
            try {

                Date datestr = util.date.convertDatetoString(db_ngaysinh.getEditor().getText());
                //System.out.println(datestr);
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                if (tf_maNV.getText().equals("")) {
                    String str = "INSERT INTO dbo.NhanVien( MaNV ,HoVaTen ,SoDT ,Gioitinh ,NgaySinh ,Email ,Luong ,ChucVu ,DiaChi,CMND)"
                            + "VALUES  ( 'NV' , ? ,? ,? ,? ,? ,? ,? ,?, ? )";
                    ps = cn.prepareStatement(str);
                    java.sql.Date date = new java.sql.Date(datestr.getTime());
                    ps.setNString(1, tf_tenNV.getText());
                    ps.setString(2, tf_sdt.getText());
                    ps.setNString(3, data_gioitinh.get(cb_GT.getSelectionModel().getSelectedIndex()));
                    ps.setDate(4, date);
                    ps.setString(5, tf_cmnd.getText());
                    ps.setDouble(6, Double.parseDouble(tf_luong.getText()));
                    ps.setNString(7, tf_chucVu.getText());
                    ps.setNString(8, tf_dc.getText());
                    ps.setString(9, tf_cmnd.getText());
                    ps.executeUpdate();
                    data.clear();
                    load_data();
                    TB_NV.setItems(data);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo");
                    alert.setHeaderText("Thêm thành công");
                    alert.showAndWait();
                    return 0;
                } else {
                    String str = "UPDATE NhanVien SET HoVaTen = ? , NgaySinh = ? ,SoDT = ? ,DiaChi=? ,Gioitinh= ? ,Luong= ?, ChucVu= ?,CMND=? WHERE MaNV = ? ";
                    ps = cn.prepareStatement(str);
                    java.sql.Date date = new java.sql.Date(datestr.getTime());
                    ps.setNString(1, tf_tenNV.getText());
                    ps.setDate(2, date);
                    ps.setString(3, tf_sdt.getText());
                    ps.setNString(4, tf_dc.getText());
                    ps.setNString(5, data_gioitinh.get(cb_GT.getSelectionModel().getSelectedIndex()));
                    ps.setDouble(6, Double.parseDouble(tf_luong.getText()));
                    ps.setNString(7, tf_chucVu.getText());
                    ps.setString(8, tf_cmnd.getText());
                    ps.setString(9, tf_maNV.getText());
                    ps.executeUpdate();

                    data.clear();
                    load_data();
                    TB_NV.setItems(data);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo");
                    alert.setHeaderText("Lưu thành công");
                    alert.showAndWait();
                }

            } catch (SQLException ex) {
                Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn cần nhập đầy đủ và chính xác thông tin");
            System.out.println("Loi!");
            alert.showAndWait();
        }
        return 0;
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

                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "UPDATE dbo.NhanVien SET ChucVu=? WHERE MaNV=?";

                ps = cn.prepareStatement(str);
                ps.setNString(1, "Thôi Việc");
                ps.setString(2, nv.getMaNV());
                ps.executeUpdate();
                data.clear();
                load_data();
                TB_NV.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_maNV.setDisable(true);

        tf_maNV.setDisable(true);
        btn_save.setDisable(false);
        cb_GT.setDisable(false);
        tf_chucVu.setEditable(true);
        tf_cmnd.setEditable(true);
        tf_dc.setEditable(true);
        tf_email.setEditable(true);
        db_ngaysinh.setDisable(false);
        tf_maNV.setEditable(true);
        tf_luong.setEditable(true);
        tf_sdt.setEditable(true);
        tf_tenNV.setEditable(true);
        btn_huy.setDisable(false);

    }

    @FXML
    private void huy_edit(ActionEvent event) {
        tf_tenNV.setEditable(false);
        tf_maNV.setEditable(false);
        tf_sdt.setEditable(false);
        tf_luong.setEditable(false);
        tf_email.setEditable(false);
        tf_dc.setEditable(false);
        tf_cmnd.setEditable(false);
        tf_chucVu.setEditable(false);
        cb_GT.setDisable(true);
        db_ngaysinh.setDisable(true);
        btn_huy.setDisable(true);
        btn_save.setDisable(true);
        focus_ct(null);
    }

    @FXML
    private void reload_data(ActionEvent event) {
        data.clear();
        load_data();
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
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public nhanvien(String MaNV, String TenNV, Date ngaySinh, String sdt, String gioiTinh, String diaChi, Double luong, String ChucVu, String CMND, String email) {
            this.MaNV = MaNV;
            this.TenNV = TenNV;
            this.ngaySinh = ngaySinh;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            this.CMND = CMND;
            this.ChucVu = ChucVu;
            this.luong = luong;
            this.email = email;
        }

    }

    /**
     * Initializes the controller class.
     */
    void load_data() {
        try {
            cn = util.Connect_JDBC.getConnection();
            Statement st = cn.createStatement();
            ResultSet crs = st.executeQuery("select * from NhanVien");

            while (crs.next()) {
                data.add(new nhanvien(crs.getString("MaNV"), crs.getString("HoVaTen"),
                        crs.getDate("NgaySinh"), crs.getString("SoDT"), crs.getString("Gioitinh"),
                        crs.getString("DiaChi"), crs.getDouble("Luong"),
                        crs.getString("ChucVu"), crs.getString("CMND"), crs.getString("Email")));
            }

            crs.close();

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    ObservableList<String> data_gioitinh = FXCollections.observableArrayList(
            "Nam", "Nữ", "Khác"
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_save.setDisable(true);
        cb_GT.setDisable(true);
        tf_chucVu.setEditable(false);
        tf_cmnd.setEditable(false);
        tf_dc.setEditable(false);
        tf_email.setEditable(false);
        db_ngaysinh.setDisable(true);
        tf_maNV.setEditable(false);
        tf_luong.setEditable(false);
        tf_sdt.setEditable(false);
        tf_tenNV.setEditable(false);
        btn_huy.setDisable(true);
        cb_GT.setItems(data_gioitinh);
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
        TB_NV.setEditable(true);
        TableColumn<nhanvien, String> manvcol = new TableColumn("   Mã Nhân Viên   ");
        manvcol.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        manvcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(manvcol);
        TableColumn<nhanvien, String> tennvcol = new TableColumn("   Tên Nhân Viên   ");
        tennvcol.setCellValueFactory(new PropertyValueFactory<>("TenNV"));
        tennvcol.setCellFactory(TextFieldTableCell.forTableColumn());
//        tennvcol.setOnEditCommit(
//                new EventHandler<CellEditEvent<nhanvien, String>>() {
//                   
//
//                    @Override
//                    public void handle(CellEditEvent<nhanvien, String> event) {
//                      //  event.getTableView().getItems().get(event.getTablePosition().getRow()).setTenNV(event.getNewValue());
//                    }
//                }
//        );
        TB_NV.getColumns().add(tennvcol);
        TableColumn<nhanvien, Date> ngaycol = new TableColumn("   Ngày sinh   ");
        ngaycol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        // tennvcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(ngaycol);
        TableColumn<nhanvien, String> sdtcol = new TableColumn("   SDT   ");
        sdtcol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        sdtcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(sdtcol);
        TableColumn<nhanvien, String> gtcol = new TableColumn("   Giới Tính   ");
        gtcol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        gtcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(gtcol);
        TableColumn<nhanvien, String> dccol = new TableColumn("   Địa  Chỉ   ");
        dccol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        dccol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(dccol);
        TableColumn<nhanvien, String> email = new TableColumn("   Email   ");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(email);
        TableColumn<nhanvien, String> cmndcol = new TableColumn("   CMND   ");
        cmndcol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        cmndcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(cmndcol);
        TableColumn<nhanvien, String> CVcol = new TableColumn("   Chức Vụ  ");
        CVcol.setCellValueFactory(new PropertyValueFactory<>("ChucVu"));
        CVcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(CVcol);
        TableColumn<nhanvien, Double> luongcol = new TableColumn("   Lương   ");
        luongcol.setCellValueFactory(new PropertyValueFactory<>("luong"));
        //   luongcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_NV.getColumns().add(luongcol);

        //   ObservableList<nhanvien> data = FXCollections.observableArrayList();
        load_data();

        TB_NV.setItems(data);
    }

    @FXML
    public void themNhanVien(ActionEvent e) {
//        tf_email.setText("");
//        tf_maNV.setText("");
//        tf_tenNV.setText("");
//        
//        db_ngaysinh.getEditor().setText("");
//        tf_dc.setText("");
//        cb_GT.getSelectionModel().select(-2);
//        tf_cmnd.setText("");
//        tf_sdt.setText("");
//        tf_chucVu.setText("");
//        tf_luong.setText("");
//        tf_maNV.setDisable(true);
//        btn_save.setDisable(false);
//        cb_GT.setDisable(false);
//        tf_chucVu.setEditable(true);
//        tf_cmnd.setEditable(true);
//        tf_dc.setEditable(true);
//        tf_email.setEditable(true);
//        db_ngaysinh.setDisable(false);
//        tf_maNV.setEditable(true);
//        tf_luong.setEditable(true);
//        tf_sdt.setEditable(true);
//        tf_tenNV.setEditable(true);
//        btn_huy.setDisable(false);
        int i = TB_NV.getFocusModel().getFocusedIndex();
        nhanvien nv = data.get(i);
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Thông Báo");
        alert1.setHeaderText("Bạn Chắc Chắn Khôi Phục Mật Khẩu ");
        //alert.setContentText("Thêm Thành Công!");
        alert1.showAndWait();
        ButtonType result = alert1.getResult();
        if (result.getText().equals("OK")) {
            cn = util.Connect_JDBC.getConnection();
            try {
                PreparedStatement ps = cn.prepareStatement("Update Member set Password = ? "
                        + "where MaNV = ?");
                String salt = "VuDang";
                String securePass = getSecurePassword("admin", salt);
                ps.setString(1, securePass);
                ps.setString(2, nv.getMaNV());
                ps.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Mật Khẩu Đã Trở Về Mặc Định");
                //alert.setContentText("Thêm Thành Công!");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
