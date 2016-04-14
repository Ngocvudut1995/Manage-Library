/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import com.sun.rowset.CachedRowSetImpl;
import java.net.URL;
import java.sql.CallableStatement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private ComboBox<?> cb_phong;
    @FXML
    private TextField tf_tang;
    @FXML
    private Button btn_huy;

    @FXML
    private void focus_CTTL(MouseEvent event) {
        int i = TB_Theloai.getFocusModel().getFocusedIndex();
        theloai tl = data.get(i);
        tf_maTL.setText(tl.getMaTL());
        tf_tenTl.setText(tl.getTenTL());
        cb_phong.getSelectionModel().select(tl.getStt_phong() - 1);
        tf_tang.setText(tl.getTang().toString());
        //tf_maTL.setDisable(true);
        //  tf_tenTl.setDisable(true);
       // tf_maTL.setDisable(false);
       // btn_save.setDisable(true);
    }
    Connection cn = null;
    @FXML
    private Button btn_save;

    @FXML
    private int newSave(ActionEvent event) {
        boolean test = true;
        if (tf_tenTl.getText().equals("")) {
            test = false;
            tf_tenTl.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            tf_tenTl.setStyle("-fx-border-width:0px;");
        }
           if (cb_phong.getSelectionModel().getSelectedIndex()<0) {
            test = false;
            cb_phong.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            cb_phong.setStyle("-fx-border-width:0px;");
        }
        if (test == true) {

            try {
                cn = util.Connect_JDBC.getConnection();
                CallableStatement ps = null;
                if (tf_maTL.getText().equals("")) {
                    String str = "Insert into TheLoai (MaTheLoai,TenTheLoai) values(?,?)";
                    ps = cn.prepareCall("{call insert_theloai(?,?)}");

                    ps.setString(1, tf_tenTl.getText());
                    ps.setInt(2, cb_phong.getSelectionModel().getSelectedIndex() + 1);
                    ps.executeUpdate();
                } else {
//                   String str = "Insert into TheLoai (MaTheLoai,TenTheLoai) values(?,?)";
                    ps = cn.prepareCall("{call update_theloai(?,?,?)}");

                    ps.setString(1, tf_tenTl.getText());
                    ps.setInt(2, cb_phong.getSelectionModel().getSelectedIndex() + 1);
                    ps.setString(3, tf_maTL.getText());
                    ps.executeUpdate();
                }
             //   huy_edit(event);
                data.clear();
                Statement st = null;
                st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT a.*,b.* FROM TheLoai a,Phong b where a.MaPhong = b.MaPhong");
                while (rs.next()) {
                    data.add(new theloai(rs.getString("MaTheLoai"), rs.getNString("TenTheLoai"),
                            rs.getNString("TenPhong"), rs.getInt("Tang"), rs.getInt("stt_phong")));
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
        Alert alert1 = new Alert(Alert.AlertType.ERROR);

        alert1.setTitle(
                "Thông Báo");
        alert1.setHeaderText(
                "Bạn cần nhập đầy đủ thông tin");
        System.out.println(
                "Loi!");
        alert1.showAndWait();

        return 1;
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

                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "DELETE FROM TheLoai WHERE MaTheLoai= ?";

                ps = cn.prepareStatement(str);

                ps.setString(1, tl.getMaTL());
                ps.executeUpdate();
                data.remove(i);

            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class
                        .getName()).log(Level.SEVERE, null, ex);
                Alert alert1 = new Alert(Alert.AlertType.ERROR);

                alert1.setTitle(
                        "Cảnh Báo!");
                alert1.setHeaderText(
                        "Không thể xóa! Có ràng buộc với Sách");
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_maTL.setDisable(true);
        tf_tenTl.setDisable(false);
        tf_tang.setDisable(true);
        btn_save.setDisable(false);
        cb_phong.setDisable(false);
         tf_maTL.setEditable(true);
        tf_tenTl.setEditable(true);
        tf_tang.setEditable(true);
        btn_huy.setDisable(false);
        

    }
    @FXML
     private void huy_edit(ActionEvent event) {
        tf_maTL.setEditable(false);
        tf_tenTl.setEditable(false);
        tf_tang.setEditable(false);
        btn_huy.setDisable(true);
        btn_save.setDisable(true);
        cb_phong.setDisable(true);
         tf_tenTl.setStyle("-fx-border-width:0px;");
         cb_phong.setStyle("-fx-border-width:0px;");
        
    }

    /**
     * Initializes the controller class.
     *
     */
    public class theloai {

        String maTL;
        String TenTL;
        String Phong;
        Integer tang;
        int stt_phong;

        public int getStt_phong() {
            return stt_phong;
        }

        public void setStt_phong(int stt_phong) {
            this.stt_phong = stt_phong;
        }

        public theloai(String maTL, String TenTL, String Phong, Integer tang, int stt_phong) {
            this.maTL = maTL;
            this.TenTL = TenTL;
            this.Phong = Phong;
            this.tang = tang;
            this.stt_phong = stt_phong;
        }

        public Integer getTang() {
            return tang;
        }

        public void setTang(Integer tang) {
            this.tang = tang;
        }

        public String getMaTL() {
            return maTL;
        }

        public String getPhong() {
            return Phong;
        }

        public void setPhong(String Phong) {
            this.Phong = Phong;
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
        Statement st = null;
        cn = util.Connect_JDBC.getConnection();
        ObservableList data_phong = FXCollections.observableArrayList();
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("Select TenPhong From Phong");
            while (rs.next()) {
                data_phong.add(rs.getNString(1));

            }

        } catch (SQLException ex) {
            Logger.getLogger(SachController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        cb_phong.setItems(data_phong);
         tf_maTL.setEditable(false);
        tf_tenTl.setEditable(false);
        tf_tang.setEditable(false);
        btn_huy.setDisable(true);
        btn_save.setDisable(true);
        cb_phong.setDisable(true);
        TB_Theloai.setEditable(true);
        TableColumn<theloai, String> maTL = new TableColumn<>("Mã Thể Loại");
        maTL.setCellValueFactory(new PropertyValueFactory<>("maTL"));
        maTL.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_Theloai.getColumns().add(maTL);
        TableColumn<theloai, String> tenTL = new TableColumn<>("Tên Thể Loại");
        tenTL.setCellValueFactory(new PropertyValueFactory<>("TenTL"));
        tenTL.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_Theloai.getColumns().add(tenTL);
        TableColumn<theloai, String> phong = new TableColumn<>("Phòng");
        phong.setCellValueFactory(new PropertyValueFactory<>("Phong"));
        phong.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_Theloai.getColumns().add(phong);
        TableColumn<theloai, Integer> tang = new TableColumn<>("Tầng");
        tang.setCellValueFactory(new PropertyValueFactory<>("tang"));
        TB_Theloai.getColumns().add(tang);
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            st = cn.createStatement();
            ResultSet crs = st.executeQuery("SELECT a.*,b.* FROM TheLoai a,Phong b where a.MaPhong = b.MaPhong");
            while (crs.next()) {
                data.add(new theloai(crs.getString("MaTheLoai"), crs.getNString("TenTheLoai"),
                        crs.getNString("TenPhong"), crs.getInt("Tang"), crs.getInt("stt_phong")));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TheLoaiController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        TB_Theloai.setItems(data);
    }

    @FXML
    public void themTL(ActionEvent e) {

        tf_maTL.setText("");
        tf_tenTl.setText("");
        tf_tang.setText("");
        tf_maTL.setDisable(true);
        tf_tang.setDisable(true);
        cb_phong.getSelectionModel().select(-1);
        tf_tenTl.setDisable(false);
        btn_save.setDisable(false);
         tf_maTL.setEditable(true);
        tf_tenTl.setEditable(true);
        tf_tang.setEditable(true);
        btn_huy.setDisable(false);
        btn_save.setDisable(false);
        cb_phong.setDisable(false);

    }
}
