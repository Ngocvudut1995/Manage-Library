/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import com.sun.rowset.JdbcRowSetImpl;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            
        try {
            Connection connection=util.Connect_JDBC.getConnection();
            String queryString= "SELECT * FROM Doc_Gia";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rsSql = statement.executeQuery(queryString); 
            JdbcRowSet jdbcRowSet;
            jdbcRowSet = new JdbcRowSetImpl(rsSql);
            jdbcRowSet.setCommand(queryString);
            while(jdbcRowSet.next()){
                data.add(new docGia(jdbcRowSet.getString(1), jdbcRowSet.getString(2), jdbcRowSet.getString(3), jdbcRowSet.getString(4), jdbcRowSet.getString(5), jdbcRowSet.getString(6),
                                                                                    jdbcRowSet.getString(7), jdbcRowSet.getString(8), jdbcRowSet.getString(9), jdbcRowSet.getString(10), jdbcRowSet.getString(11)));
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
        ObservableList<docGia> data = FXCollections.observableArrayList();
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
                     
    }
}
