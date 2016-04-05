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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class TacGiaController implements Initializable {
    @FXML
    private TableView<tacGia> TB_TG;

    /**
     * Initializes the controller class.
     */
    public class tacGia{
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
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            TableColumn<tacGia, String> maTGcol = new TableColumn(" Mã Tác Giả");
            maTGcol.setCellValueFactory(new PropertyValueFactory<>("maTG"));
            TB_TG.getColumns().add(maTGcol);
            
            TableColumn<tacGia, String> tenTGcol = new TableColumn(" Tên Tác Giả");
            tenTGcol.setCellValueFactory(new PropertyValueFactory<>("tenTG"));
            TB_TG.getColumns().add(tenTGcol);
            
            TableColumn<tacGia, String> namSinhcol = new TableColumn(" Mã Tác Giả");
            namSinhcol.setCellValueFactory(new PropertyValueFactory<>("maTG"));
            TB_TG.getColumns().add(namSinhcol);
            
            TableColumn<tacGia, String> gioiTinhcol = new TableColumn(" Tên Tác Giả");
            gioiTinhcol.setCellValueFactory(new PropertyValueFactory<>("tenTG"));
            TB_TG.getColumns().add(gioiTinhcol);
            
            ObservableList<tacGia> data= FXCollections.observableArrayList();
            
    }    
    
}
