/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Bao_Cao;

import UserCase.Person;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.sql.rowset.CachedRowSet;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class Report_DocGiaController implements Initializable {

    public class DocGia extends Person{

        private String MaDocGia;
        private String NgheNghiep;
        private String CoQuan;
        private String HanSD;
        private String Anh;

        public DocGia(String MaDocGia, String NgheNghiep, String CoQuan, String HanSD, String Anh, String CMND, String Hovaten, String SoDT, String DiaChi, String NgaySinh, String GioiTinh, String Email) {
            super(CMND, Hovaten, SoDT, DiaChi, NgaySinh, GioiTinh, Email);
            this.MaDocGia = MaDocGia;
            this.NgheNghiep = NgheNghiep;
            this.CoQuan = CoQuan;
            this.HanSD = HanSD;
            this.Anh = Anh;
        }
      

        public String getMaDocGia() {
            return MaDocGia;
        }

        public void setMaDocGia(String MaDocGia) {
            this.MaDocGia = MaDocGia;
        }

        public String getCMND() {
            return CMND;
        }

        public void setCMND(String CMND) {
            this.CMND = CMND;
        }

        public String getHovaten() {
            return Hovaten;
        }

        public void setHovaten(String Hovaten) {
            this.Hovaten = Hovaten;
        }

        public String getNgheNghiep() {
            return NgheNghiep;
        }

        public void setNgheNghiep(String NgheNghiep) {
            this.NgheNghiep = NgheNghiep;
        }

        public String getCoQuan() {
            return CoQuan;
        }

        public void setCoQuan(String CoQuan) {
            this.CoQuan = CoQuan;
        }

        public String getSoDT() {
            return SoDT;
        }

        public void setSoDT(String SoDT) {
            this.SoDT = SoDT;
        }

        public String getDiaChi() {
            return DiaChi;
        }

        public void setDiaChi(String DiaChi) {
            this.DiaChi = DiaChi;
        }

        public String getNgaySinh() {
            return NgaySinh;
        }

        public void setNgaySinh(String NgaySinh) {
            this.NgaySinh = NgaySinh;
        }

        public String getGioiTinh() {
            return GioiTinh;
        }

        public void setGioiTinh(String GioiTinh) {
            this.GioiTinh = GioiTinh;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getHanSD() {
            return HanSD;
        }

        public void setHanSD(String HanSD) {
            this.HanSD = HanSD;
        }

        public String getAnh() {
            return Anh;
        }

        public void setAnh(String Anh) {
            this.Anh = Anh;
        }
    }
    @FXML
    private TableView<DocGia> TBdocgia;
    @FXML
    private Button bt_docgia_tuan;
    @FXML
    private Button bt_docgia_thang;
    @FXML
    private Button bt_docgia_qui;
    @FXML
    private Button bt_docgia_nam;
    @FXML
    public void getDocGia_Tuan(ActionEvent e) {
        try {
            ObservableList<DocGia> data = FXCollections.observableArrayList();;
            //Column MaDocGia
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUsername("admin");
            crs.setPassword("toilangocvu");
            crs.setUrl(util.Connect_JDBC.url);
            crs.setCommand("select * from Coffees");
            crs.execute();
            while(crs.next()){
                data.add(new DocGia("DG002", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", crs.getString(1), crs.getString(2), null, null, null, null, null));
            }
                    data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            data.add(new DocGia("DG002", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            data.add(new DocGia("DG003", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            data.add(new DocGia("DG004", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            data.add(new DocGia("DG005", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            data.add(new DocGia("DG006", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            data.add(new DocGia("DG007", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
            TBdocgia.setItems(data);
            crs.acceptChanges();
            crs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
     @FXML
    public void getDocGia_Thang(ActionEvent e) {
        ObservableList<DocGia> data = FXCollections.observableArrayList();;
        //Column MaDocGia
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        TBdocgia.setItems(data);
       
       
    }
     @FXML
    public void getDocGia_Qui(ActionEvent e) {
        ObservableList<DocGia> data = FXCollections.observableArrayList();;
        //Column MaDocGia
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        TBdocgia.setItems(data);
       
       
    }
     @FXML
    public void getDocGia_Nam(ActionEvent e) {
        ObservableList<DocGia> data = FXCollections.observableArrayList();;
        //Column MaDocGia
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        data.add(new DocGia("DG001", "201694551", "Đặng Ngọc Vũ", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null, null, null));
        TBdocgia.setItems(data);
       
       
    }
    @FXML
    public void focuscol(MouseEvent e){
        int i = TBdocgia.getFocusModel().getFocusedIndex();
        System.out.println(i);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Column Doc Gia

        TableColumn MaDGCol = new TableColumn("Mã Độc Giả");
        MaDGCol.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
        TBdocgia.getColumns().add(MaDGCol);
        //   MaDGCol.setCellFactory(new );
        //Column CMND
        TableColumn<DocGia, String> CMNDCol = new TableColumn("   CMND   ");
        CMNDCol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        TBdocgia.getColumns().add(CMNDCol);
        //Column Hovaten
        TableColumn<DocGia, String> TenCol = new TableColumn("    Họ Và Tên   ");
        TenCol.setMinWidth(120);
        TenCol.setCellValueFactory(new PropertyValueFactory<>("Hovaten"));
        TBdocgia.getColumns().add(TenCol);
        //Column NgheNghiep
        TableColumn<DocGia, String> jobCol = new TableColumn("Nghề Nghiệp");
        jobCol.setMinWidth(120);
        jobCol.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
        TBdocgia.getColumns().add(jobCol);
        //Column CoQuan
        TableColumn<DocGia, String> coquanCol = new TableColumn("Cơ Quan");
        coquanCol.setMinWidth(120);
        coquanCol.setCellValueFactory(new PropertyValueFactory<>("CoQuan"));
        TBdocgia.getColumns().add(coquanCol);
        //Column SoDT
        TableColumn<DocGia, String> sdtCol = new TableColumn("Số Điện Thoại");
        sdtCol.setMinWidth(120);
        sdtCol.setCellValueFactory(new PropertyValueFactory<>("SoDT"));
        TBdocgia.getColumns().add(sdtCol);
        //Column DiaChi
        TableColumn<DocGia, String> diachiCol = new TableColumn("Địa Chỉ");
        diachiCol.setMinWidth(120);
        diachiCol.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        TBdocgia.getColumns().add(diachiCol);
        //Column NgaySinh
        TableColumn<DocGia, String> ngaysinhCol = new TableColumn("Ngày Sinh");
        ngaysinhCol.setMinWidth(120);
        ngaysinhCol.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        TBdocgia.getColumns().add(ngaysinhCol);
        //Column GioiTinh
        TableColumn<DocGia, String> gtCol = new TableColumn("Giới Tính");
        gtCol.setMinWidth(120);
        gtCol.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        TBdocgia.getColumns().add(gtCol);
        //Column Email
        TableColumn<DocGia, String> emailCol = new TableColumn("Email");
        emailCol.setMinWidth(120);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        TBdocgia.getColumns().add(emailCol);
        //Column HanSD
        TableColumn<DocGia, String> hansdCol = new TableColumn("Hạn Sử Dụng");
        hansdCol.setMinWidth(120);
        hansdCol.setCellValueFactory(new PropertyValueFactory<>("HanSD"));
        TBdocgia.getColumns().add(hansdCol);
//         //Column Anh
//        TableColumn<DocGia, String> imageCol = new TableColumn("Ảnh");
//        imageCol.setMinWidth(120);
//        imageCol.setCellValueFactory(new PropertyValueFactory<>("Ảnh"));
//        TBdocgia.getColumns().add(TenCol);
       
    }

}
