/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import com.sun.rowset.CachedRowSetImpl;
import java.net.URL;
import java.sql.SQLException;
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
    private TextField tf_gt;
    @FXML
    private TextField tf_namSinh;

    @FXML
    private void focus_CTTG(MouseEvent event) {
    }
    ObservableList<tacGia> data = FXCollections.observableArrayList();
    @FXML
    private Button btn_save;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
           TableColumn<tacGia, String> maTGcol = new TableColumn(" Mã Tác Giả");
            maTGcol.setCellValueFactory(new PropertyValueFactory<>("maTG"));
            TB_TG.getColumns().add(maTGcol);

            TableColumn<tacGia, String> tenTGcol = new TableColumn(" Tên Tác Giả");
            tenTGcol.setCellValueFactory(new PropertyValueFactory<>("tenTG"));
            TB_TG.getColumns().add(tenTGcol);

            TableColumn<tacGia, String> namSinhcol = new TableColumn(" Ngay sinh");
            namSinhcol.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
            TB_TG.getColumns().add(namSinhcol);

            TableColumn<tacGia, String> gioiTinhcol = new TableColumn(" Gioi Tinh");
            gioiTinhcol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
            TB_TG.getColumns().add(gioiTinhcol);

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                CachedRowSet crs = new CachedRowSetImpl();
                crs.setUsername("admin");
                crs.setPassword("123456");
                crs.setUrl(util.Connect_JDBC.url);
                crs.setCommand("select * from NhanVien");
                crs.execute();
                while (crs.next()) {
                    data.add(new tacGia(crs.getString(1), crs.getString(2), crs.getString(3), crs.getString(4)));
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
        
    }

    /**
     * Initializes the controller class.
     */
    public class tacGia {

        private String maTG;
        private String tenTG;
        private String namSinh;
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

        public String getNamSinh() {
            return namSinh;
        }

        public void setNamSinh(String namSinh) {
            this.namSinh = namSinh;
        }

        public String getGioiTinh() {
            return gioiTinh;
        }

        public void setGioiTinh(String gioiTinh) {
            this.gioiTinh = gioiTinh;
        }

        public tacGia(String maTG, String tenTG, String namSinh, String gioiTinh) {
            this.maTG = maTG;
            this.tenTG = tenTG;
            this.namSinh = namSinh;
            this.gioiTinh = gioiTinh;

        }
    

        @FXML
        private void focus_CTTG(ActionEvent e) {
            int i = TB_TG.getFocusModel().getFocusedIndex();
            tacGia tg = data.get(i);
            tf_maTG.setText(tg.getMaTG());
            tf_tenTG.setText(tg.getTenTG());
            tf_namSinh.setText(tg.getNamSinh());
            tf_gt.setText(tg.getGioiTinh());
        }

     

        @FXML
        public void themTG(ActionEvent e) {
            ObservableList<tacGia> data = FXCollections.observableArrayList();
            tf_maTG.setText("");
            tf_tenTG.setText("");
            tf_namSinh.setText("");
            tf_gt.setText("");

        }
    }
}
