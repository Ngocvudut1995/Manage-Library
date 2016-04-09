/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TextField tf_tenDG;
    @FXML
    private TextField tf_ngaySinh;
    @FXML
    private TextField tf_sdt;
    @FXML
    private TextField tf_gt;
    @FXML
    private TextField tf_dc;
    @FXML
    private TextField tf_cmnd;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_TT;
    @FXML
    private Button btn_save;
    @FXML
    private TextField tf_ngayDK;
    @FXML
    private TextField tf_ngayHet;
    ObservableList<docGia> data = FXCollections.observableArrayList();
    //ObservableList<docGia> data_luu= FXCollections.observableArrayList();

    @FXML
    private void focus_CTDG(MouseEvent event) {
        int i = TB_DG.getFocusModel().getFocusedIndex();
        docGia dg = data.get(i);
        tf_maDG.setText(dg.getMaDG());
        tf_tenDG.setText(dg.getTenDG());
        String dateNS = util.date.convertStringToDate(dg.getNgaySinh());
        tf_ngaySinh.setText(dateNS);
        tf_cmnd.setText(dg.getCMND());
        tf_gt.setText(dg.getGioiTinh());
        tf_dc.setText(dg.getDiaChi());
        tf_sdt.setText(dg.getSdt());
        tf_email.setText(dg.getEmail());
        String dateDK = util.date.convertStringToDate(dg.getNgayDK());
        tf_ngayDK.setText(dateDK);
        String dateHH = util.date.convertStringToDate(dg.getNgayHet());
        tf_ngayHet.setText(dateHH);
        tf_TT.setText(dg.getTrangThai());
        tf_ngayDK.setDisable(true);
        tf_maDG.setDisable(true);
        tf_TT.setDisable(true);
        tf_cmnd.setDisable(true);
        tf_dc.setDisable(true);
        tf_email.setDisable(true);
        tf_gt.setDisable(true);
        tf_ngayHet.setDisable(true);
        tf_ngaySinh.setDisable(true);
        tf_sdt.setDisable(true);
        tf_tenDG.setDisable(true);
        btn_save.setDisable(true);
    }
    Connection cn = null;

    @FXML
    private int saveNew(ActionEvent event) {
        String Ma = tf_maDG.getText();
        String ten = tf_tenDG.getText();
        String sdt = tf_sdt.getText();
        String email = tf_email.getText();
        String dc = tf_dc.getText();
        //docGia dg=data.get(i);
        if ((tf_tenDG.getText()).equals("") || (tf_ngaySinh.getText()).equals("") || (tf_ngayHet.getText()).equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
            System.out.println("Loi!");
            alert.showAndWait();
            return 1;
        } else {
            try {
                Date datestr = util.date.convertDatetoString(tf_ngaySinh.getText());
                Date datestr1 = util.date.convertDatetoString(tf_ngayHet.getText());
                java.sql.Date date = new java.sql.Date(datestr.getTime());
                java.sql.Date date1 = new java.sql.Date(datestr1.getTime());
                //System.out.println(datestr);
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "UPDATE Doc_Gia SET HoVaTen= ? ,SoDT= ? ,Email= ? ,DiaChi= ? ,HanSD= ? ,NgaySinh= ? WHERE MaDocGia= ? ";

                ps = cn.prepareStatement(str);

                ps.setNString(1, tf_tenDG.getText());
                ps.setNString(2, tf_sdt.getText());
                ps.setNString(3, tf_email.getText());
                ps.setNString(4, tf_dc.getText());
                ps.setDate(5, date1);
                ps.setDate(6, date);

                ps.setNString(7, tf_maDG.getText());
                ps.executeUpdate();
                data.clear();
                Statement st = null;
                st = cn.createStatement();
                ResultSet rs = st.executeQuery("Select * from Doc_Gia");
                while (rs.next()) {
                    data.add(new docGia(rs.getString("MaDocGia"), rs.getString("HoVaTen"), rs.getDate("NgaySinh"), rs.getString("SoDT"), rs.getString("GioiTinh"), rs.getString("DiaChi"),
                            rs.getDate("NgayLamThe"), rs.getDate("HanSD"), rs.getString("Email"), rs.getString(10)));
                }
                TB_DG.setItems(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Lưu thành công");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
    }

    @FXML
    private void xoa(ActionEvent event) {
        int i = TB_DG.getFocusModel().getFocusedIndex();
        docGia dg = data.get(i);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa ?");
        Boolean yes = alert.showAndWait().isPresent();
        if (yes) {
            try {
                data.remove(i);

                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "DELETE FROM Doc_Gia WHERE MaDocGia= ?";

                ps = cn.prepareStatement(str);

                ps.setString(1, dg.getMaDG());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_TT.setDisable(false);
        tf_cmnd.setDisable(false);
        tf_dc.setDisable(false);
        tf_email.setDisable(false);
        tf_gt.setDisable(false);
        tf_ngayHet.setDisable(false);
        tf_ngaySinh.setDisable(false);
        tf_sdt.setDisable(false);
        tf_tenDG.setDisable(false);
        btn_save.setDisable(false);
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

        public docGia(String maDG, String tenDG, Date ngaySinh, String sdt, String gioiTinh, String diaChi, Date ngayDK, Date ngayHet, String email, String trangThai) {
            this.maDG = maDG;
            this.tenDG = tenDG;
            this.ngaySinh = ngaySinh;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            //this.CMND=CMND;
            this.ngayDK = ngayDK;
            this.ngayHet = ngayHet;
            this.email = email;
            this.trangThai = trangThai;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_save.setDisable(true);
        TableColumn<docGia, String> maDGcol = new TableColumn<>(" Ma DG ");
        maDGcol.setCellValueFactory(new PropertyValueFactory<>("maDG"));
        TB_DG.getColumns().add(maDGcol);

        TableColumn<docGia, String> tenDGcol = new TableColumn("   Tên DG   ");
        tenDGcol.setCellValueFactory(new PropertyValueFactory<>("tenDG"));
        TB_DG.getColumns().add(tenDGcol);

        TableColumn<docGia, String> ngaySinhcol = new TableColumn<>("  Ngày Sinh  ");
        ngaySinhcol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        TB_DG.getColumns().add(ngaySinhcol);

        TableColumn<docGia, String> sdtcol = new TableColumn("     SĐT     ");
        sdtcol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        TB_DG.getColumns().add(sdtcol);

        TableColumn<docGia, String> gioiTinhcol = new TableColumn<>("   Giới tính   ");
        gioiTinhcol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        TB_DG.getColumns().add(gioiTinhcol);

        TableColumn<docGia, String> dccol = new TableColumn("     Địa chỉ     ");
        dccol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TB_DG.getColumns().add(dccol);

//            TableColumn<docGia,String> cmndcol=new TableColumn("    Số CMND    ");
//            cmndcol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
//            TB_DG.getColumns().add(cmndcol);
        TableColumn<docGia, String> ngayDKcol = new TableColumn<>("Ngày đăng kí");
        ngayDKcol.setCellValueFactory(new PropertyValueFactory<>("ngayDK"));
        TB_DG.getColumns().add(ngayDKcol);

        TableColumn<docGia, String> ngayHetcol = new TableColumn("Ngày hết hạng");
        ngayHetcol.setCellValueFactory(new PropertyValueFactory<>("ngayHet"));
        TB_DG.getColumns().add(ngayHetcol);

        TableColumn<docGia, String> emailcol = new TableColumn<>("         Email         ");
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TB_DG.getColumns().add(emailcol);

        TableColumn<docGia, String> trangThaicol = new TableColumn("  Trạng Thái  ");
        trangThaicol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        TB_DG.getColumns().add(trangThaicol);

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
                        jdbcRowSet.getDate("NgayLamThe"), jdbcRowSet.getDate("HanSD"), jdbcRowSet.getString("Email"), jdbcRowSet.getString(10)));
            }

            jdbcRowSet.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TB_DG.setItems(data);

    }

    @FXML
    public void themDG(ActionEvent e) {
        tf_ngayDK.setDisable(false);
        //tf_maDG.setDisable(false);
        tf_maDG.setText("");
        tf_tenDG.setText("");
        tf_ngaySinh.setText("");
        tf_dc.setText("");
        tf_gt.setText("");
        tf_cmnd.setText("");
        tf_sdt.setText("");
        tf_email.setText("");
        tf_ngayDK.setText("");
        tf_ngayHet.setText("");
        tf_TT.setText("");

        tf_TT.setDisable(false);
        tf_cmnd.setDisable(false);
        tf_dc.setDisable(false);
        tf_email.setDisable(false);
        tf_gt.setDisable(false);
        tf_ngayHet.setDisable(false);
        tf_ngaySinh.setDisable(false);
        tf_sdt.setDisable(false);
        tf_tenDG.setDisable(false);
        btn_save.setDisable(false);

    }
}
