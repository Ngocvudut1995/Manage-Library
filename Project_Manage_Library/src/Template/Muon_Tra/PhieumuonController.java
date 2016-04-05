/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import com.sun.rowset.JdbcRowSetImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static util.Connect_JDBC.password;
import static util.Connect_JDBC.url;
import static util.Connect_JDBC.userName;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class PhieumuonController implements Initializable {

    public class phieumuonsach {

        private String MaMuon;
        private String MaSach;
        private String NgayMuon;
        private String HanTra;
        private String TinhTrang;

        public phieumuonsach(String MaMuon, String MaSach, String NgayMuon, String HanTra, String TinhTrang) {
            this.MaMuon = MaMuon;
            this.MaSach = MaSach;
            this.NgayMuon = NgayMuon;
            this.HanTra = HanTra;
            this.TinhTrang = TinhTrang;
        }

        public String getMaMuon() {
            return MaMuon;
        }

        public void setMaMuon(String MaMuon) {
            this.MaMuon = MaMuon;
        }

        public String getNgayMuon() {
            return NgayMuon;
        }

        public void setNgayMuon(String NgayMuon) {
            this.NgayMuon = NgayMuon;
        }

        public String getHanTra() {
            return HanTra;
        }

        public void setHanTra(String HanTra) {
            this.HanTra = HanTra;
        }

        public String getTinhTrang() {
            return TinhTrang;
        }

        public void setTinhTrang(String TinhTrang) {
            this.TinhTrang = TinhTrang;
        }

        public String getMaSach() {
            return MaSach;
        }

        public void setMaSach(String MaSach) {
            this.MaSach = MaSach;
        }

    }
    @FXML
    private TextField Ma_DG;
    @FXML
    protected TextField TenNV;
    @FXML
    private Button bt_them;
    @FXML
    private Button bt_huy;
    @FXML
    private Button bt_luu;
    @FXML
    private Button bt_xuat;
    @FXML
    private TableView<phieumuonsach> TB_Muon;

    ObservableList<phieumuonsach> data = FXCollections.observableArrayList();
    @FXML
    private TextField MaSach;
    Connection cn = null;

    @FXML
    private void them_sach(ActionEvent event) throws IOException {
        // Date today = new Date();
        Locale currentLocale;
        currentLocale = new Locale("vi", "VN");

        DateFormat currentDateFormat = DateFormat.getDateInstance(3, currentLocale);
        Calendar calendar = Calendar.getInstance();
        String today = currentDateFormat.format(calendar.getTime());
         
      
        if (!MaSach.getText().equals("")) {
            try {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                cn = util.Connect_JDBC.getConnection();
                // cn = DriverManager.getConnection("jdbc:sqlserver://VUDANG:1433;databaseName = QLThuVien","admin","123456");
                String queryString = "SELECT a.*,b.TGMuon FROM dbo.Book a, dbo.LoaiSach b WHERE a.MaLoaiSach = b.MaLoaiSach";
                Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = st.executeQuery(queryString);
               // rs.next();
                //System.out.println(rs.getString(1));
         
//                String s = MaSach.getText();
//                System.out.println(s+"|");
                 String s = MaSach.getText();
                while(rs.next()) {
                    
                   
                    System.out.println(s+"|"+rs.getString(1)+"|");
                    if (s.equalsIgnoreCase(rs.getString(1).trim())) {
                        System.out.println(rs.getString(1));
                         calendar.add(calendar.MONTH, rs.getInt("TGMuon"));
                         String dealline = currentDateFormat.format(calendar.getTime());
                        data.add(new phieumuonsach(rs.getString(1), rs.getString(1),today, dealline,"Bình Thường"));
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
            }

           TB_Muon.setItems(data);
            MaSach.setText("");
        }

    }

    @FXML
    public void luu_vaoDB(ActionEvent event) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = util.Connect_JDBC.getConnection();
            Statement stmt = cn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM COFFEES");
            JdbcRowSetImpl rowSet = new JdbcRowSetImpl(rs);
            for(int i = 0; i<data.size();i++){
                phieumuonsach pm = data.get(i);
                rowSet.last();
                rowSet.moveToInsertRow();
                rowSet.updateString(1,pm.getMaMuon());
                rowSet.updateString(2,pm.getMaSach());
                Date date = new Date(pm.getNgayMuon());
               // rowSet.updateDate(3,date);
                rowSet.updateString(1,pm.getMaMuon());
                
            }
             
            //  TB_Muon.setItems(data1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void xuat_excel(ActionEvent event) {
    }

    @FXML
    private void huy_toan_bo(ActionEvent event) {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<phieumuonsach, String> col = new TableColumn<>("Mã Mượn");
        col.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        TB_Muon.getColumns().add(col);
         TableColumn<phieumuonsach, String> MScol = new TableColumn<>("Mã Sách");
        MScol.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        TB_Muon.getColumns().add(MScol);
         TableColumn<phieumuonsach, String> NMcol = new TableColumn<>("Ngày Mượn");
        NMcol.setCellValueFactory(new PropertyValueFactory<>("NgayMuon"));
        TB_Muon.getColumns().add(NMcol);
         TableColumn<phieumuonsach, String> Han = new TableColumn<>("Hạn Trả");
        Han.setCellValueFactory(new PropertyValueFactory<>("HanTra"));
        TB_Muon.getColumns().add(Han);
         TableColumn<phieumuonsach, String> Tinhtrang = new TableColumn<>("Tình Trạng");
        Tinhtrang.setCellValueFactory(new PropertyValueFactory<>("TinhTrang"));
        TB_Muon.getColumns().add(Tinhtrang);
      
    }

}
