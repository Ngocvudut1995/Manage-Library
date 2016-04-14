/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.index;

import Template.quanLy.SachController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import static util.Encode.getSecurePassword;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class TaoNVController implements Initializable {

    @FXML
    private ImageView image_view;
    @FXML
    private Button them_anh;
    @FXML
    private Button tao_NV;
    @FXML
    private TextField tf_tennv;
    @FXML
    private TextField tf_diachi;
    @FXML
    private TextField tf_sdt;
    @FXML
    private TextField tf_cmnd;
    @FXML
    private TextField tf_email;
    @FXML
    private ChoiceBox<String> cb_giotinh;
    @FXML
    private TextField tf_user;
    @FXML
    private PasswordField tf_pass;
    @FXML
    private PasswordField tf_pass_again;
    @FXML
    private DatePicker dp_ngaysinh;
    ObservableList<String> data_gioitinh = FXCollections.observableArrayList("Nam", "Nữ", "Khác");
    @FXML
    private TextField tf_chucvu;
    @FXML
    private Button bt_huy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String pattern = "dd/MM/yyyy";

        dp_ngaysinh.setPromptText(pattern.toLowerCase());

        dp_ngaysinh.setConverter(new StringConverter<LocalDate>() {
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

        cb_giotinh.setItems(data_gioitinh);
        image_view.setStyle("-fx-border-color:black;-fx-border-width: 1px ;");
    }
    Connection cn = null;
    String localUrl;

    @FXML
    private void load_file_anh(ActionEvent event) throws MalformedURLException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Pictures");
        //fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        File file = fileChooser.showOpenDialog(new Stage());
        localUrl = file.getAbsolutePath();
        FileInputStream fis = new FileInputStream(file);
        Image localImage = new Image(fis);

        image_view.setImage(localImage);

    }

    @FXML
    private void luu_vao_DB(ActionEvent event) throws FileNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông Báo");
        alert.setHeaderText("Bạn Chắc Chắn Lưu Dữ Liệu ");
        //alert.setContentText("Thêm Thành Công!");
        alert.showAndWait();
      
        
        ButtonType result = alert.getResult();
          boolean test = true;
        if (tf_tennv.getText().equals("")) {
            test = false;
            tf_tennv.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            tf_tennv.setStyle("-fx-border-width:0px;");
        }
        if (tf_chucvu.getText().equals("")) {
            test = false;
            tf_chucvu.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            tf_chucvu.setStyle("-fx-border-width:0px;");
        }
        if (tf_cmnd.getText().equals("")) {
            test = false;
            tf_cmnd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            tf_cmnd.setStyle("-fx-border-width:0px;");
        }
        if(test == true){
        if (result.getText().equals("OK")) {
            cn = util.Connect_JDBC.getConnection();
            CallableStatement st;
            FileInputStream fis = null;
            File image = new File(localUrl);
            System.out.println(localUrl);
            //   Blob blob = this.cn.createBlob();
            fis = new FileInputStream(image);
            try {

                st = cn.prepareCall("{call INSERT_member (?,?,?,?,?,?,?,?,?,?,?,?)}");
                LocalDate date = dp_ngaysinh.getValue();
                java.sql.Date ngaysinh = java.sql.Date.valueOf(date);

                st.setNString(1, tf_tennv.getText());
                st.setNString(2, tf_sdt.getText());
                st.setNString(3, data_gioitinh.get(cb_giotinh.getSelectionModel().getSelectedIndex()));
                st.setDate(4, ngaysinh);
                st.setString(5, tf_email.getText());
                st.setString(6, tf_chucvu.getText());
                st.setString(7, tf_cmnd.getText());
                st.setString(8, tf_diachi.getText());
                st.setString(9, tf_user.getText());
               String salt = "VuDang";
                String securePass = getSecurePassword(tf_pass.getText(), salt);
                st.setString(10, tf_pass.getText());
                st.setBlob(11, fis);
                st.registerOutParameter(12, Types.INTEGER);
                st.executeUpdate();

                fis.close();

                if (st.getInt(12) == 1) {
                    // don't load in the background
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Thông Báo");
                    alert1.setHeaderText("Tạo thành công");
                    alert1.showAndWait();
                    huy(event);
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Thông Báo");
                    alert1.setHeaderText("Nhân Viên Đã Tồn Tại");
                    alert1.showAndWait();
                    //  tf_cmnd.setStyle();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaoNVController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TaoNVController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }else{
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Thông Báo");
                    alert1.setHeaderText("Thông Tin Không Đầy Đủ");
                    alert1.showAndWait();
        }
    }

    @FXML
    private void huy(ActionEvent event) {
        tf_chucvu.setText("");
        tf_cmnd.setText("");
        tf_diachi.setText("");
        tf_email.setText("");
        tf_pass.setText("");
        tf_pass_again.setText("");
        tf_sdt.setText("");
        tf_tennv.setText("");
        tf_user.setText("");
        dp_ngaysinh.getEditor().setText("");
    }

}
