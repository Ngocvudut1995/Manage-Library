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
public class TheLoaiController implements Initializable {
    @FXML
    private TableView<theloai> TB_Theloai;

    /**
     * Initializes the controller class.
     * 
     */
    public class theloai{
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn<theloai,String> maTL = new TableColumn<>("Mã Thể Loại");
        maTL.setCellValueFactory(new PropertyValueFactory<>("maTL"));
        TB_Theloai.getColumns().add(maTL);
         TableColumn<theloai,String> tenTL = new TableColumn<>("Tên Thể Loại");
        tenTL.setCellValueFactory(new PropertyValueFactory<>("TenTL"));
        TB_Theloai.getColumns().add(tenTL);
           ObservableList<theloai> data = FXCollections.observableArrayList();
           data.add(new theloai("TL001", "VĂn HỌc"));
         TB_Theloai.setItems(data);
    }    
    
}
