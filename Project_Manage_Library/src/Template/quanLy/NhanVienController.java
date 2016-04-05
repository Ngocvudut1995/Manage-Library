/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanLy;

import Template.Bao_Cao.Report_DocGiaController;
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
public class NhanVienController implements Initializable {
    @FXML
    private TableView<nhanvien> TB_NV;
    public class nhanvien{
      private  String MaNV;
      private  String TenNV;

        public String getMaNV() {
            return MaNV;
        }

        public void setMaNV(String MaNV) {
            this.MaNV = MaNV;
        }

        public String getTenNV() {
            return TenNV;
        }

        public void setTenNV(String TenNV) {
            this.TenNV = TenNV;
        }

        public nhanvien(String MaNV, String TenNV) {
            this.MaNV = MaNV;
            this.TenNV = TenNV;
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<nhanvien, String> manvcol = new TableColumn("   Ma Nhan Vien   ");
        manvcol.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        TB_NV.getColumns().add(manvcol);
        TableColumn<nhanvien, String> tennvcol = new TableColumn("   Ten Nhan Vien   ");
        tennvcol.setCellValueFactory(new PropertyValueFactory<>("TenNV"));
        TB_NV.getColumns().add(tennvcol);
         ObservableList<nhanvien> data = FXCollections.observableArrayList();;
         data.add(new nhanvien("MHS2", "VU"));
         TB_NV.setItems(data);
    }    
    
}
