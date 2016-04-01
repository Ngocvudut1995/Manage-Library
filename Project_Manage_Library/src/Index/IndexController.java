/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Index;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button trasach;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button muonsach;
    @FXML
    private Button chitietmuon;
    @FXML
    private Button chitiettra;
    @FXML
    private void traSach(ActionEvent event) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Trả Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/trasach.fxml"));
//      
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void MuonSach(ActionEvent e) throws IOException{  
        Tab tab = new Tab();
        tab.setText("    Mượn Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/phieumuon.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void chitietmuon(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Chi Tiết Mượn Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/chi_tiet_muon.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
      @FXML
    private void chitiettra(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Chi Tiết Trả Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/chi_tiet_tra.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private Button reportNV;
    @FXML
    private Button reportDG;
     @FXML
    private Button reportMuon;
        @FXML
    private void reportNV(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Báo Cáo Nhân Viên    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Bao_Cao/report_NhanVien.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
        @FXML
    private void reportDG(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Báo Cáo Đọc Giả   ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Bao_Cao/report_DocGia.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
        @FXML
    private void reportMuon(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Báo Cáo Mượn Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Bao_Cao/report_MuonSach.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }    
    @FXML
    private void searchBook(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_Sach.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void searchDG(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Đọc Giả    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_DocGia.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
     @FXML
    private void searchMuon(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Mượn Trả Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_Muon_Tra.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
     @FXML
    private void searchNV(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Nhân Viên    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_NhanVien.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
     @FXML
    private void quanLyNV(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Quan ly Nhân Viên    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/quanLy/nhanVien.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void quanLyDG(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Quan ly Doc Gia    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/quanLy/docGia.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void quanLySach(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Quan ly Sach  ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/quanLy/sach.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void quanLyNXB(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Quan ly NXB    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/quanLy/NXB.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void quanLyTG(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Quan ly TG    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/quanLy/tacGia.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void quanLyTL(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Quan ly thể loại    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/quanLy/theLoai.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void indexNV(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Thông tin của nhân viên   ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/index/nhanVien.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @FXML
    private void indexDKTK(ActionEvent e) throws IOException{
        Tab tab = new Tab();
        tab.setText("    Đăng kí tài khoản NV   ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/index/taoNV.fxml"));     
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
