/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import Validate.NameTextField;
import Validate.NumberTextField;
import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class DocGiaController implements Initializable {

    @FXML
    private TableView<docGia> TB_DG;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_xoa;
    @FXML
    private Button btn_them;
    @FXML
    private TextField tf_maDG;
    @FXML
    private NameTextField tf_tenDG;

    @FXML
    private NumberTextField tf_sdt;

    @FXML
    private TextField tf_dc;
    @FXML
    private TextField tf_cmnd;
    @FXML
    private TextField tf_email;

    @FXML
    private Button btn_save;
    @FXML
    private TextField tf_ngayDK;
    @FXML
    private TextField tf_ngayHet;
    ObservableList<docGia> data = FXCollections.observableArrayList();
    //ObservableList<docGia> data_luu= FXCollections.observableArrayList();
    @FXML
    private DatePicker cb_ngaySinh;
    @FXML
    private ComboBox<String> cb_GT;
    @FXML
    private TextField tf_NN;
    @FXML
    private Button btn_huy;
    @FXML
    private Button bt_load;
    @FXML
    private Button bt_up;

    @FXML
    private void focus_CTDG(MouseEvent event) {
        int i = TB_DG.getFocusModel().getFocusedIndex();
        docGia dg = data.get(i);
//        Date today = new Date();
//        if (dg.getNgayHet().before(today))
//        {
//            tf_ngayHet.setStyle("-fx-border-color:red;-fx-border-width: 2px ;");
//        }else{
//            tf_ngayHet.setStyle("-fx-border-width: 0px ;");
//        }
        tf_maDG.setText(dg.getMaDG());
        tf_tenDG.setText(dg.getTenDG());
        String dateNS = util.date.convertStringToDate(dg.getNgaySinh());
        cb_ngaySinh.getEditor().setText(dateNS);
        tf_cmnd.setText(dg.getCMND());
        for (int j = 0; j < data_gioitinh.size(); j++) {
            if (dg.getGioiTinh().equals(data_gioitinh.get(j))) {
                cb_GT.getSelectionModel().select(j);
                break;
            }
            //cb_GT.getEditor().setText(dg.getGioiTinh());
        }
        tf_dc.setText(dg.getDiaChi());
        tf_sdt.setText(dg.getSdt());
        tf_email.setText(dg.getEmail());
        String dateDK = util.date.convertStringToDate(dg.getNgayDK());
        tf_ngayDK.setText(dateDK);
        String dateHH = util.date.convertStringToDate(dg.getNgayHet());
        tf_ngayHet.setText(dateHH);
        tf_NN.setText(dg.getTrangThai());
        // tf_maDG.setDisable(false);
        tf_ngayHet.setDisable(false);
        tf_ngayDK.setDisable(false);
        // btn_save.setDisable(true);
    }
    Connection cn = null;

    @FXML
    private int saveNew(ActionEvent event) {
        String Ma = tf_maDG.getText();
        String ten = tf_tenDG.getText();
        String sdt = tf_sdt.getText();
        String email = tf_email.getText();
        String dc = tf_dc.getText();
        boolean match = true;
        boolean test = true;
        TextField[] mangTF = {tf_tenDG, tf_sdt, tf_NN, tf_dc, tf_cmnd, tf_email};
        for (int i = 0; i < 6; i++) {
            if (mangTF[i].getText().equals("")) {
                mangTF[i].setStyle("-fx-border-color:red;-fx-border-width: 2px ;");
                test = false;
            } else {
                mangTF[i].setStyle("-fx-border-width:0px;");
            }
        }
        if (cb_GT.getSelectionModel().getSelectedIndex() == 3) {
            cb_GT.setStyle("-fx-border-color:red;-fx-border-width: 2px ;");
            test = false;
        } else {
            cb_GT.setStyle("-fx-border-width:0px;");
        }
        if (cb_ngaySinh.getEditor().getText().equals("")) {
            cb_ngaySinh.setStyle("-fx-border-color:red;-fx-border-width: 2px ;");
            test = false;
        } else {
            cb_ngaySinh.setStyle("-fx-border-width:0px;");
        }
        match = (tf_email.getText()).matches("[a-zA-Z0-9_]+@[a-zA-Z]+\\.[a-zA-Z]+(\\.[a-zA-Z]+)*");
        if (match == false) {
            System.out.println("Email k dung");
            test = false;

        }
        if (tf_tenDG.getText().matches("(([a-zA-Z]+)(\\s)*)+")) {
            System.out.println("dung");
        }

        if (test == true) {
//            if ((tf_tenDG.getText()).equals("") || (cb_ngaySinh.getEditor().getText()).equals("") || (tf_ngayHet.getText()).equals("")) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Thông Báo");
//                alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
//                System.out.println("Loi!");
//                alert.showAndWait();
//                return 1;
//            } 
            try {
                Date datestr = util.date.convertDatetoString(cb_ngaySinh.getEditor().getText());
                Date datestr1 = util.date.convertDatetoString(tf_ngayHet.getText());
                java.sql.Date date = new java.sql.Date(datestr.getTime());
                java.sql.Date date1 = new java.sql.Date(datestr1.getTime());

                Date datedk = util.date.convertDatetoString(tf_ngayDK.getText());
                java.sql.Date date2 = new java.sql.Date(datedk.getTime());
                //System.out.println(datestr);
                cn = util.Connect_JDBC.getConnection();
                CallableStatement ps = null;
                if (tf_maDG.getText().equals("")) {
                    String str = "INSERT INTO dbo.Doc_Gia( MaDocGia , HoVaTen ,NgheNghiep ,SoDT ,DiaChi "
                            + ",NgaySinh ,GioiTinh ,Email ,NgayLamThe ,HanSD ,AnhThe,CMND)"
                            + "VALUES  ( 'MDG', ? ,? , ? , ? , ? , ? , ? , ? , ? ,NULL ,? )";

                    ps = cn.prepareCall("{call Insert_docgia (?,?,?,?,?,?,?,?,?,?,?)}");
                    ps.setNString(1, tf_tenDG.getText());
                    ps.setNString(2, tf_NN.getText());
                    ps.setString(3, tf_sdt.getText());
                    ps.setNString(4, tf_dc.getText());
                    ps.setDate(5, date);
                    System.out.println(data_gioitinh.get(cb_GT.getSelectionModel().getSelectedIndex()));
                    ps.setNString(6, data_gioitinh.get(cb_GT.getSelectionModel().getSelectedIndex()));
                    ps.setString(7, tf_email.getText());
                    ps.setDate(8, date2);
                    ps.setDate(9, date1);
                    ps.setString(10, tf_cmnd.getText());
                    ps.registerOutParameter(11, Types.INTEGER);
                    ps.executeUpdate();
                    if (ps.getInt(11) == 1) {
                        data.clear();
                        load_data();
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Thông Báo");
                        alert1.setHeaderText("Tạo thành công");
                        alert1.showAndWait();
                        TB_DG.setItems(data);
                        huy_edit(event);
                       // tf_cmnd.setStyle("-fx-border-width:0px;");
                    } else {
                        tf_cmnd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Thông Báo");
                        alert1.setHeaderText("Đọc Giả Đã Tồn Tại");
                        alert1.showAndWait();
                    }

//                    
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông Báo");
//                    alert.setHeaderText("Lưu thành công");
//                    alert.showAndWait();
                    return 2;
                } else {
                    String str = "UPDATE Doc_Gia SET HoVaTen= ? ,SoDT= ? "
                            + ",Email= ? ,DiaChi= ? ,HanSD= ? ,NgaySinh= ?,NgheNghiep= ? , CMND = ?,GioiTinh "
                            + "=? WHERE MaDocGia= ? ";

                    PreparedStatement ps1 = cn.prepareStatement(str);

                    ps1.setNString(1, tf_tenDG.getText());
                    ps1.setNString(2, tf_sdt.getText());
                    ps1.setNString(3, tf_email.getText());
                    ps1.setNString(4, tf_dc.getText());
                    ps1.setDate(5, date1);
                    ps1.setDate(6, date);
                    ps1.setNString(7, tf_NN.getText());
                    ps1.setString(8, tf_cmnd.getText());
                    ps1.setNString(9, data_gioitinh.get(cb_GT.getSelectionModel().getSelectedIndex()));
                    ps1.setString(10, tf_maDG.getText());
                    ps1.executeUpdate();
                    data.clear();
                    load_data();
                    TB_DG.setItems(data);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo");
                    alert.setHeaderText("Lưu thành công");
                    alert.showAndWait();
                }

            } catch (SQLException ex) {
                Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
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
        tf_ngayHet.setStyle("-fx-border-width: 0px ;");
        int i = TB_DG.getFocusModel().getFocusedIndex();
        docGia dg = data.get(i);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa ?");
        Boolean yes = alert.showAndWait().isPresent();
        if (yes) {
            try {

                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "UPDATE dbo.Doc_Gia SET HanSD=NgayLamThe WHERE MaDocGia=?";

                ps = cn.prepareStatement(str);

                ps.setString(1, dg.getMaDG());
                ps.executeUpdate();
                data.clear();
                load_data();
                focus_CTDG(null);
                TB_DG.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        bt_up.setDisable(false);
        tf_ngayHet.setStyle("-fx-border-width: 0px ;");
        tf_NN.setEditable(true);
        tf_maDG.setDisable(true);
        tf_tenDG.setEditable(true);
        tf_maDG.setEditable(true);
        tf_sdt.setEditable(true);
        // tf_ngayDK.setEditable(true);
        tf_email.setEditable(true);
        tf_dc.setEditable(true);
        tf_cmnd.setEditable(true);
        //  tf_ngayHet.setEditable(true);
        cb_GT.setDisable(false);
        cb_ngaySinh.setDisable(false);

        btn_huy.setDisable(false);
        btn_save.setDisable(false);
    }

    @FXML
    private void huy_edit(ActionEvent event) {
        tf_cmnd.setStyle("-fx-border-width: 0px ;");
        bt_up.setDisable(true);
        tf_NN.setEditable(false);
        tf_maDG.setDisable(false);
        tf_cmnd.setEditable(false);
        tf_dc.setEditable(false);
        tf_email.setEditable(false);
        tf_maDG.setEditable(false);
        //  tf_ngayDK.setEditable(false);
        // tf_ngayHet.setEditable(false);
        tf_sdt.setEditable(false);
        tf_tenDG.setEditable(false);
        //focus_CTDG();
        cb_ngaySinh.setDisable(true);
        cb_GT.setDisable(true);
        btn_huy.setDisable(true);
        btn_save.setDisable(true);
        //load_data();
    }

    @FXML
    private void reload_data(ActionEvent event) {
        data.clear();
        load_data();
    }

    @FXML
    private void tangHSD(ActionEvent event) throws ParseException {
        Calendar date = Calendar.getInstance();
        Date date_ngayhan = util.date.convertDatetoString(tf_ngayHet.getText());
        date.setTime(date_ngayhan);
        date.add(Calendar.MONTH, 9);
        date_ngayhan = date.getTime();
        tf_ngayHet.setText(util.date.convertStringToDate(date_ngayhan));

    }

    public class docGia {

        private String maDG;
        private String tenDG;
        private Date ngaySinh;
        private String sdt;
        private String gioiTinh;
        private String diaChi;
        private String CMND;
        private Date ngayDK;
        private Date ngayHet;
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

        public Date getNgayDK() {
            return ngayDK;
        }

        public void setNgayDK(Date ngayDK) {
            this.ngayDK = ngayDK;
        }

        public Date getNgayHet() {
            return ngayHet;
        }

        public void setNgayHet(Date ngayHet) {
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

        public docGia(String maDG, String tenDG, Date ngaySinh, String sdt, String gioiTinh, String diaChi, Date ngayDK, Date ngayHet, String email, String trangThai, String CMND) {
            this.maDG = maDG;
            this.tenDG = tenDG;
            this.ngaySinh = ngaySinh;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            this.CMND = CMND;
            this.ngayDK = ngayDK;
            this.ngayHet = ngayHet;
            this.email = email;
            this.trangThai = trangThai;
        }

    }
    ObservableList<String> data_gioitinh = FXCollections.observableArrayList(
            "Nam", "Nữ", "Khác", ""
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // tf_cmnd.
        bt_up.setDisable(true);
        tf_sdt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf_sdt.getText().length() > 15) {
                    String s = tf_sdt.getText().substring(0, 15);
                    tf_sdt.setText(s);
                }
            }

        });

        tf_NN.setEditable(false);
        tf_tenDG.setEditable(false);
        tf_maDG.setEditable(false);
        tf_sdt.setEditable(false);
        tf_ngayDK.setEditable(false);
        tf_email.setEditable(false);
        tf_dc.setEditable(false);
        tf_cmnd.setEditable(false);
        tf_ngayHet.setEditable(false);
        cb_GT.setDisable(true);
        cb_ngaySinh.setDisable(true);
        btn_huy.setDisable(true);
        btn_save.setDisable(true);
        cb_GT.setItems(data_gioitinh);
        //  cb_GT.setSelectionModel(null);
        String pattern = "dd/MM/yyyy";

        cb_ngaySinh.setPromptText(pattern.toLowerCase());

        cb_ngaySinh.setConverter(new StringConverter<LocalDate>() {
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
        btn_save.setDisable(true);
        TB_DG.setEditable(true);
        TableColumn<docGia, String> maDGcol = new TableColumn<>(" Ma DG ");
        maDGcol.setCellValueFactory(new PropertyValueFactory<>("maDG"));
        maDGcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(maDGcol);

        TableColumn<docGia, String> tenDGcol = new TableColumn("   Tên DG   ");
        tenDGcol.setCellValueFactory(new PropertyValueFactory<>("tenDG"));
        tenDGcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(tenDGcol);

        TableColumn<docGia, String> ngaySinhcol = new TableColumn<>("  Ngày Sinh  ");
        ngaySinhcol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        //   ngaySinhcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(ngaySinhcol);

        TableColumn<docGia, String> sdtcol = new TableColumn("     SĐT     ");
        sdtcol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        sdtcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(sdtcol);

        TableColumn<docGia, String> gioiTinhcol = new TableColumn<>("   Giới tính   ");
        gioiTinhcol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        gioiTinhcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(gioiTinhcol);

        TableColumn<docGia, String> dccol = new TableColumn("     Địa chỉ     ");
        dccol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        dccol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(dccol);

        TableColumn<docGia, String> cmndcol = new TableColumn("    Số CMND    ");
        cmndcol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        cmndcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(cmndcol);
        TableColumn<docGia, String> ngayDKcol = new TableColumn<>("Ngày đăng kí");
        ngayDKcol.setCellValueFactory(new PropertyValueFactory<>("ngayDK"));
        //   ngayDKcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(ngayDKcol);

        TableColumn<docGia, String> ngayHetcol = new TableColumn("Ngày hết hạng");
        ngayHetcol.setCellValueFactory(new PropertyValueFactory<>("ngayHet"));
        //    ngayHetcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(ngayHetcol);

        TableColumn<docGia, String> emailcol = new TableColumn<>("         Email         ");
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailcol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(emailcol);

        TableColumn<docGia, String> trangThaicol = new TableColumn("  Nghề Nghiệp   ");
        trangThaicol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        trangThaicol.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_DG.getColumns().add(trangThaicol);

        load_data();
        TB_DG.setItems(data);

    }

    void load_data() {
        tf_ngayHet.setStyle("-fx-border-width: 0px ;");
        try {
            Connection connection = util.Connect_JDBC.getConnection();
            String queryString = "SELECT * FROM Doc_Gia";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rsSql = statement.executeQuery(queryString);
            JdbcRowSet jdbcRowSet;
            jdbcRowSet = new JdbcRowSetImpl(rsSql);
            jdbcRowSet.setCommand(queryString);
            while (jdbcRowSet.next()) {
                data.add(new docGia(jdbcRowSet.getString("MaDocGia"), jdbcRowSet.getString("HoVaTen"), jdbcRowSet.getDate("NgaySinh"), jdbcRowSet.getString("SoDT"), jdbcRowSet.getString("GioiTinh"), jdbcRowSet.getString("DiaChi"),
                        jdbcRowSet.getDate("NgayLamThe"), jdbcRowSet.getDate("HanSD"), jdbcRowSet.getString("Email"), jdbcRowSet.getString("NgheNghiep"), jdbcRowSet.getString("CMND")));
            }

            jdbcRowSet.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void themDG(ActionEvent e) {
        bt_up.setDisable(false);
        tf_ngayHet.setStyle("-fx-border-width: 0px ;");
        tf_ngayDK.setDisable(false);
        tf_maDG.setDisable(true);

        //tf_maDG.setDisable(false);
        tf_maDG.setText("");
        tf_tenDG.setText("");
        cb_ngaySinh.getEditor().setText("");
        tf_dc.setText("");
        cb_GT.getSelectionModel().select(3);
        tf_cmnd.setText("");
        tf_sdt.setText("");
        tf_email.setText("");
        //   tf_ngayDK.setText("");
        //  tf_ngayHet.setText("");
        tf_NN.setText("");
        tf_NN.setEditable(true);
        tf_tenDG.setEditable(true);
        tf_maDG.setEditable(true);
        tf_sdt.setEditable(true);
        //  tf_ngayDK.setEditable(true);
        tf_email.setEditable(true);
        tf_dc.setEditable(true);
        tf_cmnd.setEditable(true);
        //   tf_ngayHet.setEditable(true);
        cb_GT.setDisable(false);
        cb_ngaySinh.setDisable(false);
        btn_huy.setDisable(false);
        btn_save.setDisable(false);
        // Date toDate = new Date();
        Calendar todate = Calendar.getInstance();

        tf_ngayDK.setText(util.date.convertStringToDate(todate.getTime()));
        todate.add(Calendar.MONTH, 9);
        tf_ngayHet.setText(util.date.convertStringToDate(todate.getTime()));
        btn_save.setDisable(false);

    }
}
