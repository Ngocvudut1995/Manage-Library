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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javax.sql.rowset.CachedRowSet;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class TacGiaController implements Initializable {

    @FXML
    private TableView<tacGia> TB_TG;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_xoa;
    @FXML
    private Button btn_them;
    @FXML
    private TextField tf_maTG;
    @FXML
    private TextField tf_tenTG;
   
    @FXML
    private DatePicker tf_namSinh;

    @FXML
    private void focus_CTTG(MouseEvent event) {
        int i = TB_TG.getFocusModel().getFocusedIndex();
        tacGia tg = data.get(i);
        tf_maTG.setText(tg.getMaTG());
        tf_tenTG.setText(tg.getTenTG());
        String dateNS = util.date.convertStringToDate(tg.getNamSinh());
        tf_namSinh.getEditor().setText(dateNS);
        
        tf_maTG.setDisable(false);
        btn_save.setDisable(true);

    }
    ObservableList<tacGia> data = FXCollections.observableArrayList();
    @FXML
    private Button btn_save;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_save.setDisable(true);

        String pattern = "dd/MM/yyyy";

        tf_namSinh.setPromptText(pattern.toLowerCase());

        tf_namSinh.setConverter(new StringConverter<LocalDate>() {
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
        TableColumn<tacGia, String> maTGcol = new TableColumn(" Mã Tác Giả");
        maTGcol.setCellValueFactory(new PropertyValueFactory<>("maTG"));
        TB_TG.getColumns().add(maTGcol);

        TableColumn<tacGia, String> tenTGcol = new TableColumn(" Tên Tác Giả");
        tenTGcol.setCellValueFactory(new PropertyValueFactory<>("tenTG"));
        TB_TG.getColumns().add(tenTGcol);

        TableColumn<tacGia, String> namSinhcol = new TableColumn(" Ngay sinh");
        namSinhcol.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        TB_TG.getColumns().add(namSinhcol);

        

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername(util.Connect_JDBC.userName);
            crs.setPassword(util.Connect_JDBC.password);
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("select * from TacGia");
            crs.execute();
            while (crs.next()) {
                data.add(new tacGia(crs.getString("MaTacGia"), crs.getString("TenTacGia"), crs.getDate("NgaySinh"), null));
            }
            crs.acceptChanges();
            crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TacGiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //data.add(new tacGia("TG001", "Tú MỠ", "1995", "nam"));
        TB_TG.setItems(data);
    }

    @FXML
    private void themTG(ActionEvent event) {
        tf_maTG.setText("");
        tf_tenTG.setText("");
        tf_namSinh.getEditor().setText("");
        
        //tf_maTG.setDisable(false);
        tf_maTG.setDisable(true);

        btn_save.setDisable(false);
    }
    Connection cn = null;

    @FXML
    private int saveNew(ActionEvent event) {
        String ten = tf_tenTG.getText();
        boolean test =  true;
        TextField[] mangTF = {tf_tenTG};
        
            if (mangTF[0].getText().equals("")) {
                mangTF[0].setStyle("-fx-border-color:red");
                test = false;
            }
        
        if (test==true) {

//            if (ten.equals("") || (tf_namSinh.getEditor().getText()).equals("")) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Thông Báo");
//                alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
//                System.out.println("Loi!");
//                alert.showAndWait();
//                return 1;
//            } 

                try {

                    Date datestr = util.date.convertDatetoString(tf_namSinh.getEditor().getText());
                    //System.out.println(datestr);
                    cn = util.Connect_JDBC.getConnection();
                    PreparedStatement ps = null;
                    if (tf_maTG.getText().equals("")) {
                        String str = "INSERT INTO dbo.TacGia( MaTacGia, TenTacGia, NgaySinh ) VALUES  ( 'TG',?,?) ";
                        ps = cn.prepareStatement(str);
                        java.sql.Date date = new java.sql.Date(datestr.getTime());
                        ps.setNString(1, tf_tenTG.getText());
                        ps.setDate(2, date);

                        ps.executeUpdate();
                        data.clear();
                        Statement st = null;
                        st = cn.createStatement();
                        ResultSet rs = st.executeQuery("Select * from TacGia");
                        while (rs.next()) {
                            data.add(new tacGia(rs.getString("MaTacGia"), rs.getString("TenTacGia"), rs.getDate("NgaySinh"), null));
                        }
                        TB_TG.setItems(data);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông Báo");
                        alert.setHeaderText("Lưu thành công");
                        alert.showAndWait();
                        return 1;
                    } else {
                        String str = "UPDATE TacGia SET TenTacGia = ? , NgaySinh = ? WHERE MaTacGia= ? ";
                        ps = cn.prepareStatement(str);
                        java.sql.Date date = new java.sql.Date(datestr.getTime());
                        ps.setNString(1, tf_tenTG.getText());
                        ps.setDate(2, date);
                        ps.setString(3, tf_maTG.getText());
                        ps.executeUpdate();
                        data.clear();
                        Statement st = null;
                        st = cn.createStatement();
                        ResultSet rs = st.executeQuery("Select * from TacGia");
                        while (rs.next()) {
                            data.add(new tacGia(rs.getString("MaTacGia"), rs.getString("TenTacGia"), rs.getDate("NgaySinh"), null));
                        }
                        TB_TG.setItems(data);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông Báo");
                        alert.setHeaderText("Lưu thành công");
                        alert.showAndWait();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(TacGiaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TacGiaController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn cần nhập đầy đủ và chính xác thông tin");
            System.out.println("Loi!");
              alert.showAndWait();
        }
        
        return 0;
    }

    @FXML
    private void xoa(ActionEvent event) {
        int i = TB_TG.getFocusModel().getFocusedIndex();
        tacGia tg = data.get(i);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa ?");
        Boolean yes = alert.showAndWait().isPresent();
        if (yes) {
            try {
                
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "DELETE FROM TacGia WHERE MaTacGia= ?";

                ps = cn.prepareStatement(str);

                ps.setString(1, tg.getMaTG());
                ps.executeUpdate();
                data.remove(i);
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_maTG.setDisable(true);
        btn_save.setDisable(false);
    }

    /**
     * Initializes the controller class.
     */
    public class tacGia {

        private String maTG;
        private String tenTG;
        private Date namSinh;
        private String gioiTinh;

        public String getMaTG() {
            return maTG;
        }

        public void setMaTG(String maTG) {
            this.maTG = maTG;
        }

        public String getTenTG() {
            return tenTG;
        }

        public void setTenTG(String tenTG) {
            this.tenTG = tenTG;
        }

        public Date getNamSinh() {
            return namSinh;
        }

        public void setNamSinh(Date namSinh) {
            this.namSinh = namSinh;
        }

        public String getGioiTinh() {
            return gioiTinh;
        }

        public void setGioiTinh(String gioiTinh) {
            this.gioiTinh = gioiTinh;
        }

        public tacGia(String maTG, String tenTG, Date namSinh, String gioiTinh) {
            this.maTG = maTG;
            this.tenTG = tenTG;
            this.namSinh = namSinh;
            this.gioiTinh = gioiTinh;

        }

    }
}
