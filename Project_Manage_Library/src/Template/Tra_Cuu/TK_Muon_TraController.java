/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Tra_Cuu;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class TK_Muon_TraController implements Initializable {
    
    @FXML
    private Button btn_tim;
    @FXML
    private DatePicker dp_from;
    @FXML
    private DatePicker dp_to;
    @FXML
    private TextField tf_maMuon;
    @FXML
    private TextField tf_tenDG;
    @FXML
    private TextField tf_Tensach;
    @FXML
    private TextField tf_ngayMuon;
    @FXML
    private TextField tf_ngayTra;
    @FXML
    private TextField tf_NV;
    ObservableList<muonTra> data = FXCollections.observableArrayList();
    Connection cn = null;
    @FXML
    private TableView<muonTra> TB_TimKiemMuonTra;
    @FXML
    private TextField tf_hanTra;

    @FXML
    private void focus_ct(MouseEvent event) {
        int i = TB_TimKiemMuonTra.getFocusModel().getFocusedIndex();
        muonTra mt = data.get(i);
        tf_maMuon.setText(mt.getMaMuon());
        tf_Tensach.setText(mt.getTensach());
        tf_tenDG.setText(mt.getTenDG());
        String dateMuon = util.date.convertStringToDate(mt.getNgayMuon());
        tf_ngayMuon.setText(dateMuon);
        String dateMuon1 = util.date.convertStringToDate(mt.getHanTra());
        tf_hanTra.setText(dateMuon1);
        String dateMuon2 = util.date.convertStringToDate(mt.getNgayTra());
        tf_ngayTra.setText(dateMuon2);
        tf_NV.setText(mt.getNhanVien());
        
    }
    
    @FXML
    private void timKiem(ActionEvent event) {
        cn = util.Connect_JDBC.getConnection();
        
        try {
            LocalDate datestr = dp_from.getValue();
            java.sql.Date From_date = java.sql.Date.valueOf(datestr);
            LocalDate datestr1 = dp_to.getValue();
            java.sql.Date to_date = java.sql.Date.valueOf(datestr1);
            String str = "SELECT DISTINCT a.MaMuon,c.HoVaTen,d.TieuDe,a.NgayMuon,a.HanTra,a.NgayTra,b.HoVaTen ten FROM dbo.MuonSach a,dbo.NhanVien b,dbo.Doc_Gia c,dbo.Book d "
                    + "WHERE (a.MaSach=d.MaSach AND a.MaDocGia=c.MaDocGia AND a.MaNV=b.MaNV) AND (a.NgayMuon BETWEEN '" + From_date + "' AND '" + to_date + "' )";
            PreparedStatement ps = null;
            ps = cn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new muonTra(data.size() + 1, rs.getString("MaMuon"), rs.getString("TieuDe"), rs.getString("HoVaTen"), rs.getDate("NgayMuon"), rs.getDate("HanTra"), rs.getDate("NgayTra"), rs.getNString("ten")));
            }
            TB_TimKiemMuonTra.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(TK_Muon_TraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class muonTra {
        
        private int stt;
        private String maMuon;
        private String tensach;
        private String tenDG;
        private Date ngayMuon;
        private Date hanTra;
        private Date ngayTra;
        private String nhanVien;
        
        public muonTra(int stt, String maMuon, String tensach, String tenDG, Date ngayMuon, Date hanTra, Date ngayTra, String nhanVien) {
            this.stt = stt;
            this.maMuon = maMuon;
            this.tensach = tensach;
            this.tenDG = tenDG;
            this.ngayMuon = ngayMuon;
            this.hanTra = hanTra;
            this.ngayTra = ngayTra;
            this.nhanVien = nhanVien;
        }
        
        public int getStt() {
            return stt;
        }
        
        public void setStt(int stt) {
            this.stt = stt;
        }
        
        public String getMaMuon() {
            return maMuon;
        }
        
        public void setMaMuon(String maMuon) {
            this.maMuon = maMuon;
        }
        
        public String getTensach() {
            return tensach;
        }
        
        public void setTensach(String tensach) {
            this.tensach = tensach;
        }
        
        public String getTenDG() {
            return tenDG;
        }
        
        public void setTenDG(String tenDG) {
            this.tenDG = tenDG;
        }
        
        public Date getNgayMuon() {
            return ngayMuon;
        }
        
        public void setNgayMuon(Date ngayMuon) {
            this.ngayMuon = ngayMuon;
        }
        
        public Date getHanTra() {
            return hanTra;
        }
        
        public void setHanTra(Date hanTra) {
            this.hanTra = hanTra;
        }
        
        public Date getNgayTra() {
            return ngayTra;
        }
        
        public void setNgayTra(Date ngayTra) {
            this.ngayTra = ngayTra;
        }
        
        public String getNhanVien() {
            return nhanVien;
        }
        
        public void setNhanVien(String nhanVien) {
            this.nhanVien = nhanVien;
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<muonTra, Integer> colstt = new TableColumn<>("STT");
        colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        TB_TimKiemMuonTra.getColumns().add(colstt);
        TableColumn<muonTra, String> colMamuon = new TableColumn<>("Mã Mượn");
        colMamuon.setCellValueFactory(new PropertyValueFactory<>("maMuon"));
        TB_TimKiemMuonTra.getColumns().add(colMamuon);
        TableColumn<muonTra, String> colMasach = new TableColumn<>("  Tên Sách  ");
        colMasach.setCellValueFactory(new PropertyValueFactory<>("tensach"));
        TB_TimKiemMuonTra.getColumns().add(colMasach);
        TableColumn<muonTra, String> coltensach = new TableColumn<>("Tên Đọc Giả");
        coltensach.setCellValueFactory(new PropertyValueFactory<>("tenDG"));
        coltensach.setMinWidth(120);
        TB_TimKiemMuonTra.getColumns().add(coltensach);
        TableColumn<muonTra, Date> colngaymuon = new TableColumn<>(" Ngày Mượn ");
        colngaymuon.setCellValueFactory(new PropertyValueFactory<>("ngayMuon"));
        TB_TimKiemMuonTra.getColumns().add(colngaymuon);
        TableColumn<muonTra, Date> colhantra = new TableColumn<>(" Hạn Trả ");
        colhantra.setCellValueFactory(new PropertyValueFactory<>("hanTra"));
        TB_TimKiemMuonTra.getColumns().add(colhantra);
        TableColumn<muonTra, Date> colngaytra = new TableColumn<>(" Ngày Trả ");
        colngaytra.setCellValueFactory(new PropertyValueFactory<>("ngayTra"));
        TB_TimKiemMuonTra.getColumns().add(colngaytra);
        TableColumn<muonTra, Date> coltinhtrang = new TableColumn<>("Tên Nhân Viên");
        coltinhtrang.setCellValueFactory(new PropertyValueFactory<>("nhanVien"));
        TB_TimKiemMuonTra.getColumns().add(coltinhtrang);
    }
    
}
