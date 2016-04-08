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
    ObservableList<docGia> data= FXCollections.observableArrayList();
    //ObservableList<docGia> data_luu= FXCollections.observableArrayList();
    
    @FXML
    private void focus_CTDG(MouseEvent event) {
        int i=TB_DG.getFocusModel().getFocusedIndex();
        docGia dg=data.get(i);
        tf_maDG.setText(dg.getMaDG());
        tf_tenDG.setText(dg.getTenDG());
        tf_ngaySinh.setText(dg.getNgaySinh().toString());
        tf_cmnd.setText(dg.getCMND());
        tf_gt.setText(dg.getGioiTinh());
        tf_dc.setText(dg.getDiaChi());
        tf_sdt.setText(dg.getSdt());
        tf_email.setText(dg.getEmail());
        tf_ngayDK.setText(dg.getNgayDK().toString());
        tf_ngayHet.setText(dg.getNgayHet().toString());
        tf_TT.setText(dg.getTrangThai());
    }

    @FXML
    private void saveNew(ActionEvent event) {
        String Ma=tf_maDG.getText();
        String ten=tf_tenDG.getText();
        String sdt=tf_sdt.getText();
        String email=tf_email.getText();
        String dc=tf_dc.getText();
        //docGia dg=data.get(i);
        
       
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
            //DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date(df.parse(tf_ngayHet.getText()).getTime()); 
            // Date date1 = new Date(df.parse(tf_ngaySinh.getText()).getTime()); 
            System.out.println(date);
            String str = df.format(date);
            Date date1 = new Date(df.parse(tf_ngaySinh.getText()).getTime());
            String str1 = df.format(date1);
           //java.sql.Date sqldate = new java.sql.Date(dg.getNgaySinh().getTime());
           // String str1 = df.format(date1); 
            System.out.println(str); // System.out.println(date.toString());
            
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername(util.Connect_JDBC.userName);
            crs.setPassword(util.Connect_JDBC.password);
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("UPDATE Doc_Gia SET HoVaTen='"+ten+"',SoDT='"+sdt+"',Email='"+email+"',DiaChi='"+dc+"',HanSD='"+str+"',NgaySinh='"+str1+"' "                 
                    + "WHERE MaDocGia='"+Ma+"'");
            crs.execute();
            crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ParseException ex) { 
            Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        } 
           
    }

    public  class docGia{
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
            
//            TableColumn<docGia,String> cmndcol=new TableColumn("    Số CMND    ");
//            cmndcol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
//            TB_DG.getColumns().add(cmndcol);
            
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
