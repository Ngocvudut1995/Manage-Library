/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import java.net.URL;
import java.util.ResourceBundle;
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

        public NXB(String maNXB, String tenNXB, String namTL, String diaChi, String email) {
            this.maNXB = maNXB;
            this.tenNXB = tenNXB;
            this.namTL = namTL;
            this.diaChi = diaChi;
            this.email = email;
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<NXB,String> maNXB = new TableColumn<>("Mã NXB");
        maNXB.setCellValueFactory(new PropertyValueFactory<>("maNXB"));
        TB_NXB.getColumns().add(maNXB);
        // TODO
         TableColumn<NXB,String> tenNXB = new TableColumn<>("Tên NXB");
        tenNXB.setCellValueFactory(new PropertyValueFactory<>("tenNXB"));
        TB_NXB.getColumns().add(tenNXB);
        TableColumn<NXB,String> namTL = new TableColumn<>("Tên NXB");
        namTL.setCellValueFactory(new PropertyValueFactory<>("namTL"));
        TB_NXB.getColumns().add(namTL);
        TableColumn<NXB,String> diachia = new TableColumn<>("Địa Chỉ");
        diachia.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TB_NXB.getColumns().add(diachia);
        TableColumn<NXB,String> email = new TableColumn<>("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TB_NXB.getColumns().add(email);
        ObservableList<NXB> data = FXCollections.observableArrayList();
        data.add(new NXB("NXB001", "Kim Đồng", null, null, null));
        TB_NXB.setItems(data);
    }
public void themNXB(ActionEvent e) {
        ObservableList<NXB> data = FXCollections.observableArrayList();
        tf_maNXB.setText("");
        tf_tenNXB.setText("");
        tf_namThanhLap.setText("");
        tf_dc.setText("");  
        tf_email.setText("");  
        tf_sdt.setText("");  
         
    }
}
