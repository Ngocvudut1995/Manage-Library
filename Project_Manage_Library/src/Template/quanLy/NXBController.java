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
public class NXBController implements Initializable {

    @FXML
    private TableView<NXB> TB_NXB;
    @FXML
    private Button btn_them;
    @FXML
    private Button btn_xoa;
    @FXML
    private Button btn_edit;
    @FXML
    private TextField tf_maNXB;
    @FXML
    private TextField tf_tenNXB;
    @FXML
    private TextField tf_namThanhLap;
    @FXML
    private TextField tf_sdt;
    @FXML
    private TextField tf_dc;
    @FXML
    private TextField tf_email;

    @FXML
    private void focus_CTNXB(MouseEvent event) {
         int i = TB_NXB.getFocusModel().getFocusedIndex();
        NXB nxb = data.get(i);
        tf_maNXB.setText(nxb.getMaNXB());
        tf_tenNXB.setText(nxb.getTenNXB());
        tf_namThanhLap.setText(nxb.getNamTL());
        tf_dc.setText(nxb.getDiaChi());
        tf_email.setText(nxb.getEmail());
        tf_sdt.setText(nxb.getSdt());
    }

    @FXML
    private Button btn_luu;

    public class NXB {

        private String maNXB;
        private String tenNXB;
        private String namTL;
        private String sdt;
        private String diaChi;
        private String email;

        public String getMaNXB() {
            return maNXB;
        }

        public void setMaNXB(String maNXB) {
            this.maNXB = maNXB;
        }

        public String getTenNXB() {
            return tenNXB;
        }

        public void setTenNXB(String tenNXB) {
            this.tenNXB = tenNXB;
        }

        public String getNamTL() {
            return namTL;
        }

        public void setNamTL(String namTL) {
            this.namTL = namTL;
        }

        public String getSdt() {
            return sdt;
        }

        public void setSdt(String sdt) {
            this.sdt = sdt;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public void setDiaChi(String diaChi) {
            this.diaChi = diaChi;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public NXB(String maNXB, String tenNXB, String namTL, String diaChi, String email, String sdt) {
            this.maNXB = maNXB;
            this.tenNXB = tenNXB;
            this.namTL = namTL;
            this.diaChi = diaChi;
            this.email = email;
            this.sdt = sdt;
        }

    }

    ObservableList<NXB> data = FXCollections.observableArrayList();

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<NXB, String> maNXB = new TableColumn<>("Mã NXB");
        maNXB.setCellValueFactory(new PropertyValueFactory<>("maNXB"));
        TB_NXB.getColumns().add(maNXB);
        // TODO
        TableColumn<NXB, String> tenNXB = new TableColumn<>("Tên NXB");
        tenNXB.setCellValueFactory(new PropertyValueFactory<>("tenNXB"));
        TB_NXB.getColumns().add(tenNXB);
//        TableColumn<NXB, String> namTL = new TableColumn<>("Nam TL");
//        namTL.setCellValueFactory(new PropertyValueFactory<>("namTL"));
//        TB_NXB.getColumns().add(namTL);
        TableColumn<NXB, String> diachia = new TableColumn<>("Địa Chỉ");
        diachia.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TB_NXB.getColumns().add(diachia);
//        TableColumn<NXB, String> email = new TableColumn<>("Email");
//        email.setCellValueFactory(new PropertyValueFactory<>("email"));
//        TB_NXB.getColumns().add(email);
//        TableColumn<NXB, String> sdt = new TableColumn<>("SDT");
//        email.setCellValueFactory(new PropertyValueFactory<>("email"));
//        TB_NXB.getColumns().add(email);

//        try {
//            Connection connection = util.Connect_JDBC.getConnection();
//            String queryString = "SELECT * FROM NhaXB";
//            Statement statement;
//            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rsSql = statement.executeQuery(queryString);
//           
//            while (rsSql.next()) {
//                data.add(new NXB(rsSql.getString("MaNXB"), rsSql.getString(2),rsSql.getString(3), rsSql.getString(2), rsSql.getString(3),rsSql.getString(2)));
//            }
//           // jdbcRowSet.close();
//            connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(NXBController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
             crs.setUsername(util.Connect_JDBC.userName);
            crs.setPassword(util.Connect_JDBC.password);
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("select * from NhaXB");
            crs.execute();
            while(crs.next()){
                data.add(new NXB(crs.getString("MaNXB"), crs.getString("TenNXB"), crs.getString("DiaChi"), crs.getString(3), crs.getString(3),crs.getString(3)));
            }
            crs.acceptChanges();
            crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NXBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NXBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.add(new NXB("NXB001", "Kim Đồng", "", "", "","01227401239"));
      TB_NXB.setItems(data);
    }

    @FXML
    public void themNXB(ActionEvent e) {

        tf_maNXB.setText("");
        tf_tenNXB.setText("");
        tf_namThanhLap.setText("");
        tf_dc.setText("");
        tf_email.setText("");
        tf_sdt.setText("");

    }
}
