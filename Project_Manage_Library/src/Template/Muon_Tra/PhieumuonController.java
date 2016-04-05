/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import Template.Bao_Cao.Report_DocGiaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class PhieumuonController implements Initializable{
     public class phieumuonsach{
        private String MaSach;

        public phieumuonsach(String MaSach) {
            this.MaSach = MaSach;
        }

        public String getMaSach() {
            return MaSach;
        }

        public void setMaSach(String MaSach) {
            this.MaSach = MaSach;
        }
        

       
    }
    @FXML
    private TextField Ma_DG;
    @FXML
    protected TextField TenNV;
    @FXML
    private Button bt_them;
    @FXML
    private Button bt_huy;
    @FXML
    private Button bt_luu;
    @FXML
    private Button bt_xuat;
    @FXML
    private TableView<phieumuonsach> TB_Muon;
   
    ObservableList<phieumuonsach> data = FXCollections.observableArrayList();
    @FXML
    private TextField MaSach;
    @FXML
    private void them_sach(ActionEvent event) throws IOException {
        if(!MaSach.getText().equals("")){
            
            data.add(new phieumuonsach(MaSach.getText()));
            TB_Muon.setItems(data);
            MaSach.setText("");
        }

    }

    @FXML
    public void luu_vaoDB(ActionEvent event) {
        ObservableList<String> data1 = FXCollections.observableArrayList();
        data1.add(new String("Hello"));
      //  TB_Muon.setItems(data1);
        
    }

    @FXML
    private void xuat_excel(ActionEvent event) {
    }

    @FXML
    private void huy_toan_bo(ActionEvent event) {
    }

    /**
     * Initializes the controller class.
     */
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<phieumuonsach,String> col = new TableColumn<>("Ma Sach");
        col.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        TB_Muon.getColumns().add(col);
        //   MaDGCol.setCellFactory(new );
        //Column CMND
//        TableColumn CMNDCol = new TableColumn("   CMND   ");
//        CMNDCol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
//       TB_Muon.getColumns().add(CMNDCol);
        //Column Hovaten
//        TableColumn<Report_DocGiaController.DocGia, String> TenCol = new TableColumn("    Họ Và Tên   ");
//        TenCol.setMinWidth(120);
//        TenCol.setCellValueFactory(new PropertyValueFactory<>("Hovaten"));
//       TB_Muon.getColumns().add(TenCol);
        //Column NgheNghiep
//        TableColumn<Report_DocGiaController.DocGia, String> jobCol = new TableColumn("Nghề Nghiệp");
//        jobCol.setMinWidth(120);
//        jobCol.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
//        TB_Muon.getColumns().add(jobCol);
//        //Column CoQuan
//        TableColumn<Report_DocGiaController.DocGia, String> coquanCol = new TableColumn("Cơ Quan");
//        coquanCol.setMinWidth(120);
//        coquanCol.setCellValueFactory(new PropertyValueFactory<>("CoQuan"));
//        TB_Muon.getColumns().add(coquanCol);
//        //Column SoDT
//        TableColumn<Report_DocGiaController.DocGia, String> sdtCol = new TableColumn("Số Điện Thoại");
//        sdtCol.setMinWidth(120);
//        sdtCol.setCellValueFactory(new PropertyValueFactory<>("SoDT"));
//       TB_Muon.getColumns().add(sdtCol);
//        //Column DiaChi
//        TableColumn<Report_DocGiaController.DocGia, String> diachiCol = new TableColumn("Địa Chỉ");
//        diachiCol.setMinWidth(120);
//        diachiCol.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
//        TB_Muon.getColumns().add(diachiCol);
//        //Column NgaySinh
//        TableColumn<Report_DocGiaController.DocGia, String> ngaysinhCol = new TableColumn("Ngày Sinh");
//        ngaysinhCol.setMinWidth(120);
//        ngaysinhCol.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
//       TB_Muon.getColumns().add(ngaysinhCol);
//        //Column GioiTinh
//        TableColumn<Report_DocGiaController.DocGia, String> gtCol = new TableColumn("Giới Tính");
//        gtCol.setMinWidth(120);
//        gtCol.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
//        TB_Muon.getColumns().add(gtCol);
//        //Column Email
//        TableColumn<Report_DocGiaController.DocGia, String> emailCol = new TableColumn("Email");
//        emailCol.setMinWidth(120);
//        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
//        TB_Muon.getColumns().add(emailCol);
//        //Column HanSD
//        TableColumn<Report_DocGiaController.DocGia, String> hansdCol = new TableColumn("Hạn Sử Dụng");
//        hansdCol.setMinWidth(120);
//        hansdCol.setCellValueFactory(new PropertyValueFactory<>("HanSD"));
//        TB_Muon.getColumns().add(hansdCol);
    }

}
