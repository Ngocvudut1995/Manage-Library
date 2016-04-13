/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class NhanVienController extends Login.LoginController implements Initializable {

    @FXML
    private TextField tf_tenNV;
    @FXML
    private TextField tf_diachi;
    @FXML
    private TextField tf_sdt;
    @FXML
    private TextField tf_cmnd;
    @FXML
    private TextField tf_chucvu;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_manv;
    @FXML
    private Button bt_luu;
    @FXML
    private Button bt_sua;
    @FXML
    private TextField tf_taikhoan;
    @FXML
    private PasswordField tf_pass;
    @FXML
    private Button bt_huy;
    @FXML
    private ImageView image_view;

    /**
     * Initializes the controller class.
     */
    Connection cn = null;
 
    @FXML
    private ComboBox<String> cb_gioitinh;
    @FXML
    private TextField db_brithday;
    @FXML
    private Button bt_doianh;

    void load_data() {

        try {
            Statement st = cn.createStatement();
            String sql = "Select a.*,b.Username,b.Password from NhanVien a,Member b where a.MaNV = '" + MaNhanVien + "' and a.MaNV = b.MaNV";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            tf_tenNV.setText(rs.getNString("HoVaTen"));
            tf_chucvu.setText(rs.getNString("ChucVu"));
            tf_cmnd.setText(rs.getString("CMND"));
            tf_diachi.setText(rs.getNString("DiaChi"));
            tf_email.setText(rs.getString("Email"));
            tf_manv.setText(rs.getString("MaNV"));
            tf_sdt.setText(rs.getString("SoDT"));
            tf_taikhoan.setText(rs.getString("Username"));
            tf_pass.setText(rs.getString("Password"));
            String ngaysinh = util.date.convertStringToDate(rs.getDate("NgaySinh"));
            System.out.println(ngaysinh);
            db_brithday.setText(ngaysinh);
      //      db_brithday.getEditor().setText(ngaysinh);
            for (int i = 0; i < data_gioitinh.size(); i++) {
                if (rs.getNString("Gioitinh").equals(data_gioitinh.get(i))) {
                    cb_gioitinh.getSelectionModel().select(i);
                    break;
                }
            }
            InputStream stream = rs.getBinaryStream("Anh");
            Image image = new Image(stream);

            image_view.setImage(image);
            image_view.setFitWidth(180);
            // image_view.seti
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ObservableList<String> data_gioitinh = FXCollections.observableArrayList("Nam", "Nữ", "Khác");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // db_brithday.getEditor().setText("asdaaaas");
        bt_luu.setDisable(true);
        bt_huy.setDisable(true);
        tf_manv.setDisable(true);
        bt_doianh.setDisable(true);
        tf_taikhoan.setDisable(true);
        tf_pass.setDisable(true);
        cb_gioitinh.setItems(data_gioitinh);
//        String pattern = "dd/MM/yyyy";
//
//   //    db_brithday.setPromptText(pattern.toLowerCase());
//        db_brithday.setConverter(new StringConverter<LocalDate>() {
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
//
//            @Override
//            public String toString(LocalDate date) {
//
//                if (date != null) {
//                    return dateFormatter.format(date);
//                } else {
//                    return "";
//                }//To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                if (string != null && !string.isEmpty()) {
//                    return LocalDate.parse(string, dateFormatter);
//                } else {
//                    return null;
//                }
//            }
//        });
//          db_brithday.getEditor().setText("20/04/1995");

        cn = util.Connect_JDBC.getConnection();
        load_data();
    }

    @FXML
    private void luu_DB(ActionEvent event) throws FileNotFoundException {
         try {
             FileInputStream fis = null;
            File image = new File(localUrl);
            System.out.println(localUrl);
            //   Blob blob = this.cn.createBlob();
            fis = new FileInputStream(image);
            CallableStatement st = cn.prepareCall("{call Update_member (?,?,?,?,?,?,?,?,?,?,?,?)}");
                Date ngaysinh = util.date.convertDatetoString(db_brithday.getText());
                java.sql.Date birthday = new java.sql.Date(ngaysinh.getTime());

                st.setNString(1, tf_tenNV.getText());
                st.setNString(2, tf_sdt.getText());
                st.setNString(3, data_gioitinh.get(cb_gioitinh.getSelectionModel().getSelectedIndex()));
                st.setDate(4, birthday);
                st.setString(5, tf_email.getText());
                st.setString(6, tf_chucvu.getText());
                st.setString(7, tf_cmnd.getText());
                st.setString(8, tf_diachi.getText());
                st.setString(9, tf_taikhoan.getText());
                st.setString(10, tf_pass.getText());
                st.setBlob(11, fis);
                st.setString(12, tf_manv.getText());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Sua(ActionEvent event) throws FileNotFoundException {
        bt_luu.setDisable(false);
        bt_huy.setDisable(false);
        bt_doianh.setDisable(false);
         tf_taikhoan.setDisable(false);
        tf_pass.setDisable(false);
       
    }

    @FXML
    private void huy(ActionEvent event) {
        load_data();
         bt_luu.setDisable(true);
        bt_huy.setDisable(true);
        tf_manv.setDisable(true);
        bt_doianh.setDisable(true);
         tf_taikhoan.setDisable(true);
        tf_pass.setDisable(true);
    }
     String localUrl;
    @FXML
    private void load_anh(ActionEvent event) throws FileNotFoundException {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Pictures");
        //fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        File file = fileChooser.showOpenDialog(new Stage());
        localUrl = file.getAbsolutePath();
        FileInputStream fis = new FileInputStream(file);
        Image localImage = new Image(fis);

        image_view.setImage(localImage);
    }

}
