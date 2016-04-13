/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import com.sun.rowset.CachedRowSetImpl;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.sql.rowset.CachedRowSet;

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
    private TextField tf_sdt;
    @FXML
    private TextField tf_dc;
    @FXML
    private TextField tf_email;
    ObservableList<NXB> data = FXCollections.observableArrayList();
    Connection cn = null;

    @FXML
    private void focus_CTNXB(MouseEvent event) {
        int i = TB_NXB.getFocusModel().getFocusedIndex();
        NXB nxb = data.get(i);
        tf_maNXB.setText(nxb.getMaNXB());
        tf_tenNXB.setText(nxb.getTenNXB());

        tf_dc.setText(nxb.getDiaChi());
        tf_email.setText(nxb.getEmail());
        tf_sdt.setText(nxb.getSdt());

        btn_luu.setDisable(true);
    }

    @FXML
    private Button btn_luu;

    @FXML
    private int saveNew(ActionEvent event) {
        boolean test = true;
        if (tf_tenNXB.getText().equals("")) {
            test = false;
            tf_tenNXB.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_tenNXB.setStyle("-fx-border-width:0px;");
        }
         if (tf_dc.getText().equals("")) {
            test = false;
            tf_dc.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_dc.setStyle("-fx-border-width:0px;");
        }
          if (tf_sdt.getText().equals("")) {
            test = false;
            tf_sdt.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_sdt.setStyle("-fx-border-width:0px;");
        }
           if (tf_email.getText().equals("")
                   ||!tf_email.getText().matches("[a-zA-Z0-9_]+@[a-zA-Z]+\\.[a-zA-Z]+(\\.[a-zA-Z]+)*")) {
            test = false;
            tf_email.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_email.setStyle("-fx-border-width:0px;");
        }
        if (test == true) {
            try {
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                if (tf_maNXB.getText().equals("")) {
                    String str = "INSERT dbo.NhaXB( MaNXB, TenNXB, DiaChi )VALUES  ('NXB',?,? ) ";
                    ps = cn.prepareStatement(str);
                    ps.setNString(1, tf_tenNXB.getText());
                    ps.setNString(2, tf_dc.getText());

                    ps.executeUpdate();
                    data.clear();
                    Statement st = null;
                    st = cn.createStatement();
                    ResultSet rs = st.executeQuery("Select * from NhaXB");
                    while (rs.next()) {
                        data.add(new NXB(rs.getString("MaNXB"), rs.getString("TenNXB"), rs.getString("DiaChi"), rs.getString("Email"), rs.getString("SoDT")));
                    }
                    TB_NXB.setItems(data);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo");
                    alert.setHeaderText("Lưu thành công");
                    alert.showAndWait();
                    return 1;
                }
                String str = "UPDATE NhaXB SET TenNXB = ? , DiaChi = ? WHERE MaNXB = ? ";
                ps = cn.prepareStatement(str);
                ps.setNString(1, tf_tenNXB.getText());
                ps.setNString(2, tf_dc.getText());
                ps.setString(3, tf_maNXB.getText());
                ps.executeUpdate();
                data.clear();
                Statement st = null;
                st = cn.createStatement();
                ResultSet rs = st.executeQuery("Select * from NhaXB");
                while (rs.next()) {
                    data.add(new NXB(rs.getString("MaNXB"), rs.getString("TenNXB"), rs.getString("DiaChi"), rs.getString("Email"), rs.getString("SoDT")));
                }
                TB_NXB.setItems(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Lưu thành công");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(NXBController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông Báo");
        alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
        System.out.println("Loi!");
        alert.showAndWait();
        return 0;
    }

    @FXML
    private void xoa(ActionEvent event) {
        int i = TB_NXB.getFocusModel().getFocusedIndex();
        NXB nxb = data.get(i);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa ?");
        Boolean yes = alert.showAndWait().isPresent();
        if (yes) {

            try {
                
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "DELETE FROM nHaXB WHERE MaNXB= ?";
                ps = cn.prepareStatement(str);

                ps.setString(1, nxb.getMaNXB());
                ps.executeUpdate();
                data.remove(i);
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_maNXB.setDisable(true);
        btn_luu.setDisable(false);
    }

    public class NXB {

        private String maNXB;
        private String tenNXB;

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

        public NXB(String maNXB, String tenNXB, String diaChi, String email, String sdt) {
            this.maNXB = maNXB;
            this.tenNXB = tenNXB;

            this.diaChi = diaChi;
            this.email = email;
            this.sdt = sdt;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_luu.setDisable(true);
        TableColumn<NXB, String> maNXB = new TableColumn<>("Mã NXB");
        maNXB.setCellValueFactory(new PropertyValueFactory<>("maNXB"));
        TB_NXB.getColumns().add(maNXB);

        TableColumn<NXB, String> tenNXB = new TableColumn<>("Tên NXB");
        tenNXB.setCellValueFactory(new PropertyValueFactory<>("tenNXB"));
        TB_NXB.getColumns().add(tenNXB);
//        TableColumn<NXB, String> namTL = new TableColumn<>("Nam TL");
//        namTL.setCellValueFactory(new PropertyValueFactory<>("namTL"));
//        TB_NXB.getColumns().add(namTL);
        TableColumn<NXB, String> diachia = new TableColumn<>("Địa Chỉ");
        diachia.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TB_NXB.getColumns().add(diachia);
        TableColumn<NXB, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TB_NXB.getColumns().add(email);
        TableColumn<NXB, String> sdt = new TableColumn<>("SDT");
        sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        TB_NXB.getColumns().add(sdt);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername(util.Connect_JDBC.userName);
            crs.setPassword(util.Connect_JDBC.password);
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("select * from NhaXB");
            crs.execute();
            while (crs.next()) {
                data.add(new NXB(crs.getString("MaNXB"), crs.getString("TenNXB"), crs.getString("DiaChi"), crs.getString("Email"), crs.getString("SoDT")));
            }
            crs.acceptChanges();
            crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NXBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NXBController.class.getName()).log(Level.SEVERE, null, ex);
        }

        TB_NXB.setItems(data);
    }

    @FXML
    public void themNXB(ActionEvent e) {

        tf_maNXB.setText("");
        tf_tenNXB.setText("");

        tf_dc.setText("");
        tf_email.setText("");
        tf_sdt.setText("");
        tf_maNXB.setDisable(true);

        btn_luu.setDisable(false);

    }
}
