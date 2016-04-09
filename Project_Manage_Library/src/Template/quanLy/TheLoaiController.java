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
public class TheLoaiController implements Initializable {

    @FXML
    private TableView<theloai> TB_Theloai;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_xoa;
    @FXML
    private Button btn_them;
    @FXML
    private TextField tf_maTL;
    @FXML
    private TextField tf_tenTl;
    @FXML
    private Button btn_add;

    @FXML
    private void focus_CTTL(MouseEvent event) {
        int i = TB_Theloai.getFocusModel().getFocusedIndex();
        theloai tl = data.get(i);
        tf_maTL.setText(tl.getMaTL());
        tf_tenTl.setText(tl.getTenTL());
        tf_maTL.setDisable(true);
        tf_tenTl.setDisable(true);
        btn_save.setDisable(true);
        btn_add.setOpacity(0);
        btn_add.setDisable(true);
    }
    Connection cn = null;
    @FXML
    private Button btn_save;

    @FXML
    private int newSave(ActionEvent event) {
        if ((tf_tenTl.getText()).equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
            System.out.println("Loi!");
            alert.showAndWait();
            return 1;
        } else {
            try {
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "UPDATE TheLoai SET TenTheLoai = ? WHERE MaTheLoai=? ";
                ps = cn.prepareStatement(str);
                ps.setNString(1, tf_tenTl.getText());
                ps.setString(2, tf_maTL.getText());
                ps.executeUpdate();
                data.clear();
                Statement st = null;
                st = cn.createStatement();
                ResultSet rs = st.executeQuery("Select * from TheLoai");
                while (rs.next()) {
                    data.add(new theloai(rs.getString(1), rs.getString(2)));
                }
                TB_Theloai.setItems(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Lưu thành công");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
    }

    @FXML
    private void xoa(ActionEvent event) {
        int i = TB_Theloai.getFocusModel().getFocusedIndex();
        theloai tl = data.get(i);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa ?");
        Boolean yes = alert.showAndWait().isPresent();
        if (yes) {

            try {
                data.remove(i);
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "DELETE FROM TheLoai WHERE MaTheLoai= ?";

                ps = cn.prepareStatement(str);

                ps.setString(1, tl.getMaTL());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_tenTl.setDisable(false);
        btn_save.setDisable(false);
        btn_add.setOpacity(0);
        btn_add.setDisable(true);
    }

    @FXML
    private int ThemThongTin(ActionEvent event) {
        if ((tf_tenTl.getText()).equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
            System.out.println("Loi!");
            alert.showAndWait();
            return 1;
        } else {
            try {
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "INSERT INTO dbo.TheLoai( MaTheLoai, TenTheLoai, MaPhong ) VALUES  ('TL',?,null )";
                ps = cn.prepareStatement(str);
                ps.setNString(1, tf_tenTl.getText());
                
                ps.executeUpdate();
                data.clear();
                Statement st = null;
                st = cn.createStatement();
                ResultSet rs = st.executeQuery("Select * from TheLoai");
                while (rs.next()) {
                    data.add(new theloai(rs.getString(1), rs.getString(2)));
                }
                TB_Theloai.setItems(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Lưu thành công");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
    }

    /**
     * Initializes the controller class.
     *
     */
    public class theloai {

        String maTL;
        String TenTL;

        public theloai(String maTL, String TenTL) {
            this.maTL = maTL;
            this.TenTL = TenTL;
        }

        public String getMaTL() {
            return maTL;
        }

        public void setMaTL(String maTL) {
            this.maTL = maTL;
        }

        public String getTenTL() {
            return TenTL;
        }

        public void setTenTL(String TenTL) {
            this.TenTL = TenTL;
        }

    }
    ObservableList<theloai> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_save.setDisable(true);
        btn_add.setOpacity(0);
        btn_add.setDisable(true);
        TableColumn<theloai, String> maTL = new TableColumn<>("Mã Thể Loại");
        maTL.setCellValueFactory(new PropertyValueFactory<>("maTL"));
        TB_Theloai.getColumns().add(maTL);
        TableColumn<theloai, String> tenTL = new TableColumn<>("Tên Thể Loại");
        tenTL.setCellValueFactory(new PropertyValueFactory<>("TenTL"));
        TB_Theloai.getColumns().add(tenTL);
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername(util.Connect_JDBC.userName);
            crs.setPassword(util.Connect_JDBC.password);
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("SELECT * FROM TheLoai");
            crs.execute();
            while (crs.next()) {
                data.add(new theloai(crs.getString(1), crs.getString(2)));
            }
            crs.acceptChanges();
            crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TB_Theloai.setItems(data);
    }

    @FXML
    public void themTL(ActionEvent e) {

        tf_maTL.setText("");
        tf_tenTl.setText("");
        tf_tenTl.setDisable(false);
        
        btn_save.setDisable(true);
        btn_add.setOpacity(1);
        btn_add.setDisable(false);

    }
}
