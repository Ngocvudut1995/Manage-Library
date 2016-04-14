/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Index;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//import quanLy.TacGiaController;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class IndexController extends Login.LoginController implements Initializable {
    Connection cn = null;
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
    private Button bt_logout;
    @FXML
    private Button reportVP;
    PreparedStatement ps = null;
    @FXML
    private Button vipham;
    @FXML
    private Button bt_qlnhanvien;
    @FXML
    private Tab tab_about;
    @FXML
    private void logout(ActionEvent e) throws IOException {
        Stage stage = (Stage) bt_logout.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/Login/Login.fxml"));
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        //   ((Node) (e.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    SingleSelectionModel<Tab> selectionModel;
    ContextMenu cm = new ContextMenu();

    @FXML
    private void chuotphai(MouseEvent e) {
        cm.getItems().remove(0, cm.getItems().size());
        if (e.getButton() == MouseButton.SECONDARY) {

            MenuItem menuItem1 = new MenuItem("Close");
            MenuItem menuItem2 = new MenuItem("Close all");
            MenuItem menuItem3 = new MenuItem("Close tab to right");
            MenuItem menuItem4 = new MenuItem("Close tab to left");
            menuItem1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
                }
            });

            menuItem2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    int max = tabPane.getTabs().size();
                    tabPane.getTabs().remove(1, max);
                }
            });
            menuItem3.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    int to = tabPane.getTabs().size();
                    int from = tabPane.getSelectionModel().getSelectedIndex();
                    tabPane.getTabs().remove(from + 1, to);
                }
            });
            menuItem4.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    int to = tabPane.getSelectionModel().getSelectedIndex();
                    tabPane.getTabs().remove(1, to);
                }
            });

            cm.getItems().add(menuItem1);
            cm.getItems().add(menuItem2);
            cm.getItems().add(menuItem3);
            cm.getItems().add(menuItem4);

            //  e.getSource().getScene().getWindow();
            cm.show(tabPane, e.getScreenX(), e.getScreenY());

        }
    }
    Tab tab_tra = new Tab();
    Tab tab_muon = new Tab();
    Tab tab_chitietmuon = new Tab();
    Tab tab_chitiettra = new Tab();
    Tab tab_vipham = new Tab();

    @FXML
    private void traSach(ActionEvent event) throws IOException {

        if (tabPane.getTabs().lastIndexOf(tab_tra) < 0) {
            tabPane.getTabs().add(tab_tra);
        }
        //   System.out.println(tabPane.getTabs().lastIndexOf(tab_tra) + "||" + tabPane.getTabs().size());
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_tra);
    }

    @FXML
    private void MuonSach(ActionEvent e) throws IOException {

        if (tabPane.getTabs().lastIndexOf(tab_muon) < 0) {
            tabPane.getTabs().add(tab_muon);
        }

        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_muon);

    }

    @FXML
    private void chitietmuon(ActionEvent e) throws IOException {
        if (tabPane.getTabs().lastIndexOf(tab_chitietmuon) < 0) {
            tabPane.getTabs().add(tab_chitietmuon);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_chitietmuon);
    }

    @FXML
    private void chitiettra(ActionEvent e) throws IOException {
        if (tabPane.getTabs().lastIndexOf(tab_chitiettra) < 0) {
            tabPane.getTabs().add(tab_chitiettra);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_chitiettra);
    }
    @FXML
    private Button reportNV;
    @FXML
    private Button reportDG;
    @FXML
    private Button reportMuon;

    @FXML
    private void reportNV(ActionEvent e) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Báo Cáo Nhân Viên    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Bao_Cao/report_NhanVien.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    @FXML
    private void reportDG(ActionEvent e) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Báo Cáo Đọc Giả   ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Bao_Cao/report_DocGia.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    @FXML
    private void reportMuon(ActionEvent e) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Báo Cáo Mượn Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Bao_Cao/report_MuonSach.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    @FXML
    private void searchBook(ActionEvent e) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_Sach.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    @FXML
    private void searchDG(ActionEvent e) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Đọc Giả    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_DocGia.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    @FXML
    private void searchMuon(ActionEvent e) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Mượn Trả Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_Muon_Tra.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    @FXML
    private void searchNV(ActionEvent e) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Tra Cứu Nhân Viên    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Tra_Cuu/TK_NhanVien.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }
    Tab tab_QLNV = new Tab();

    @FXML
    private void quanLyNV(ActionEvent e) throws IOException {

        if (tabPane.getTabs().lastIndexOf(tab_QLNV) < 0) {
            tabPane.getTabs().add(tab_QLNV);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_QLNV);
    }
    Tab tab_QLDG = new Tab();

    @FXML
    private void quanLyDG(ActionEvent e) throws IOException {

        if (tabPane.getTabs().lastIndexOf(tab_QLDG) < 0) {
            tabPane.getTabs().add(tab_QLDG);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_QLDG);
    }
    Tab tab_QLSach = new Tab();

    @FXML
    private void quanLySach(ActionEvent e) throws IOException {
        if (tabPane.getTabs().lastIndexOf(tab_QLSach) < 0) {
            tabPane.getTabs().add(tab_QLSach);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_QLSach);
    }
    Tab tab_QLNXB = new Tab();

    @FXML
    private void quanLyNXB(ActionEvent e) throws IOException {

        if (tabPane.getTabs().lastIndexOf(tab_QLNXB) < 0) {
            tabPane.getTabs().add(tab_QLNXB);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_QLNXB);
    }
    Tab tab_QLTG = new Tab();

    @FXML
    private void quanLyTG(ActionEvent e) throws IOException {
        if (tabPane.getTabs().lastIndexOf(tab_QLTG) < 0) {
            tabPane.getTabs().add(tab_QLTG);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_QLTG);
    }
    Tab tab_QLTL = new Tab();

    @FXML
    private void quanLyTL(ActionEvent e) throws IOException {

      if (tabPane.getTabs().lastIndexOf(tab_QLTL) < 0) {
            tabPane.getTabs().add(tab_QLTL);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_QLTL);
    }
    Tab tab_thongtinNV = new Tab();

    @FXML
    private void indexNV(ActionEvent e) throws IOException {

        if (tabPane.getTabs().lastIndexOf(tab_thongtinNV) < 0) {
            tabPane.getTabs().add(tab_thongtinNV);
        }

        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_thongtinNV);
    }
    Tab tab_DKTK = new Tab();

    @FXML
    private void indexDKTK(ActionEvent e) throws IOException {

        if (tabPane.getTabs().lastIndexOf(tab_DKTK) < 0) {
            tabPane.getTabs().add(tab_DKTK);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_DKTK);
    }       

    @FXML
    private void xulyVP(ActionEvent event) {
         if (tabPane.getTabs().lastIndexOf(tab_vipham) < 0) {
            tabPane.getTabs().add(tab_vipham);
        }
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab_vipham);
    }
    public class viphamquahan{
        private String mamuon;
        private String madocgia;
        private Date hantra;

        public viphamquahan(String mamuon, String madocgia,Date hantra) {
            this.mamuon = mamuon;
            this.madocgia = madocgia;
            this.hantra = hantra;
        }

        public Date getHantra() {
            return hantra;
        }

        public void setHantra(Date hantra) {
            this.hantra = hantra;
        }

        public String getMamuon() {
            return mamuon;
        }

        public void setMamuon(String mamuon) {
            this.mamuon = mamuon;
        }

        public String getMadocgia() {
            return madocgia;
        }

        public void setMadocgia(String madocgia) {
            this.madocgia = madocgia;
        }
        
    }
    
ObservableList<viphamquahan> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cn = util.Connect_JDBC.getConnection();
        Statement st = null;
        String sql = "select ChucVu From NhanVien where MaNV = '"+MaNhanVien+"'";
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            String chucvu = rs.getNString(1);
            if(chucvu.equalsIgnoreCase("Nhân Viên")){
                bt_qlnhanvien.setDisable(true);
                reportNV.setDisable(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/trasach.fxml"));
            tab_tra.setContent(root);
            tab_tra.setText("    Trả Sách    ");
            tab_tra.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_tra);
                    
                }
            });
            //
              root = FXMLLoader.load(getClass().getResource("/Index/about.fxml"));   
              tab_about.setContent(root);
              ////
            tab_vipham.setText("  Vi Phạm  ");
             root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/vipham.fxml"));
            tab_vipham.setContent(root);
            tab_vipham.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_vipham);
                   
                }
            });
                    //
            tab_muon.setText("    Mượn Sách    ");
            root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/phieumuon.fxml"));
            tab_muon.setContent(root);
            tab_muon.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_muon);
                }
            });
            //

            tab_chitietmuon.setText("    Chi Tiết Mượn Sách    ");
            root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/chi_tiet_muon.fxml"));
            tab_chitietmuon.setContent(root);
            tab_chitietmuon.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_chitietmuon);
                }
            });
            ///
            tab_chitiettra.setText("    Chi Tiết Trả Sách    ");
            root = FXMLLoader.load(getClass().getResource("/Template/Muon_Tra/chi_tiet_tra.fxml"));
            tab_chitiettra.setContent(root);
            tab_chitiettra.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_chitiettra);
                }
            });
            //Tab thong tin ca nha
            tab_thongtinNV.setText("    Thông tin của nhân viên   ");
            root = FXMLLoader.load(getClass().getResource("/Template/index/nhanVien.fxml"));
            tab_thongtinNV.setContent(root);
            tab_thongtinNV.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_thongtinNV);
                }
            });
            //Tab Dang ki tai khoan

            tab_DKTK.setText("    Đăng kí tài khoản NV   ");
            root = FXMLLoader.load(getClass().getResource("/Template/index/taoNV.fxml"));
            tab_DKTK.setContent(root);
            tab_DKTK.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_DKTK);
                }
            });
            //Quan ly nhan vien
            tab_QLNV.setText("    Quản Lý Nhân Viên    ");
            root = FXMLLoader.load(getClass().getResource("/Template/quanLy/nhanVien.fxml"));
            tab_QLNV.setContent(root);
            tab_QLNV.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_QLNV);
                }
            });
            //Quan ly doc gia
            tab_QLDG.setText("    Quan ly Doc Gia    ");
            root = FXMLLoader.load(getClass().getResource("/Template/quanLy/docGia.fxml"));
            tab_QLDG.setContent(root);
            tab_QLDG.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_QLDG);
                }
            });
            //Quan Ly Sach
            tab_QLSach.setText("    Quan ly Sach  ");
            root = FXMLLoader.load(getClass().getResource("/Template/quanLy/sach.fxml"));
            tab_QLSach.setContent(root);
            tab_QLSach.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_QLSach);
                }
            });
            //Quan Ly NXB
            tab_QLNXB.setText("    Quản lý NXB    ");
            root = FXMLLoader.load(getClass().getResource("/Template/quanLy/NXB.fxml"));
            tab_QLNXB.setContent(root);
            tab_QLNXB.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_QLNXB);
                }
            });
            //Quan Ly Tac Gia
            tab_QLTG.setText("    Quản lý TG    ");
            root = FXMLLoader.load(getClass().getResource("/Template/quanLy/tacGia.fxml"));
            tab_QLTG.setContent(root);
            tab_QLTG.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_QLTG);
                }
            });
            //Quan Ly the loai
            tab_QLTL.setText("    Quản lý thể loại    ");
            root = FXMLLoader.load(getClass().getResource("/Template/quanLy/theLoai.fxml"));
            tab_QLTL.setContent(root);
            tab_QLTL.setOnClosed(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tabPane.getTabs().remove(tab_QLTL);
                }
            });
            cn = util.Connect_JDBC.getConnection();
             
           st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
       
          String str = "SELECT MaMuon,MaDocGia,HanTra FROM dbo.MuonSach WHERE NgayTra IS NULL AND HanTra < GETDATE() "
                  + " AND TinhTrang NOT LIKE N'Quá Hạn'";
           ResultSet rs = st.executeQuery(str);
           while(rs.next()){
               data.add(new viphamquahan(rs.getString("MaMuon"), rs.getString("MaDocGia"),rs.getDate("HanTra")));
           }
           st.close();
                          
           
           for(int i = 0;i<data.size();i++){
               CallableStatement cs = null;
               cs = cn.prepareCall("{call Load_vp(?,?,?)}");
               cs.setString(1, data.get(i).getMamuon());
               cs.setString(2,data.get(i).getMadocgia());
               java.sql.Date datetra = new java.sql.Date(data.get(i).getHantra().getTime());
               cs.setDate(3, datetra);
               
              
               cs.executeUpdate();
               cs.close();
           }
              // crs.moveToCurrentRow();
//                ps.close();
            
           
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reportVP(ActionEvent event) throws IOException {
         Tab tab = new Tab();
        tab.setText("    Báo Cáo Vi Phạm    ");
        Parent root = FXMLLoader.load(getClass().getResource("/Template/Bao_Cao/report_VP.fxml"));
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

}
