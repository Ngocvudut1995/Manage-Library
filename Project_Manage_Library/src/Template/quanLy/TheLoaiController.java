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
    private void focus_CTTL(MouseEvent event) {
    }

  @FXML
    private Button btn_save;

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
            this.TenTL = TenTL;    }
        
    }
    ObservableList<theloai> data = FXCollections.observableArrayList();
    @FXML
    public void focus_CTTL(ActionEvent e) {
        int i= TB_Theloai.getFocusModel().getFocusedIndex();
        theloai tl= data.get(i);
        tf_maTL.setText(tl.getMaTL());
        tf_tenTl.setText(tl.getTenTL());
        
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
        try {
        
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                CachedRowSet crs=new CachedRowSetImpl();
                crs.setUsername("admin");
                crs.setPassword("123456");
                crs.setUrl(util.Connect_JDBC.url);
                crs.setCommand("SELECT * FROM TheLoai");
                crs.execute();
                while(crs.next()){
                    data.add(new theloai(crs.getString(1), crs.getString(2)));
                }
                crs.acceptChanges();
                crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
        }

            
           data.add(new theloai("TL001", "VĂn HỌc"));
         TB_Theloai.setItems(data);
    }    
    @FXML
    public void themTL(ActionEvent e) {
        
        tf_maTL.setText("");
        tf_tenTl.setText("");
        
    }
}
