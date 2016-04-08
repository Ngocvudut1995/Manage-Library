/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Bao_Cao;

import Template.Muon_Tra.Chi_tiet_muonController;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author Vu Dang
 */
public class Report_VPController implements Initializable {

    public class vipham {

        private Integer stt;
        private String mavipham;
        private String mamuon;
        private String masach;
        private String tensach;
        private Date ngaymuon;
       
        private Date hantra;
        private Date ngaytra;
        private String tinhtrang;
        private String madocgia;
        private String tendocgia;
        private String lydo;
        private String htxuphat;
        private Date ngayxuly;

        public Integer getStt() {
            return stt;
        }

        public void setStt(Integer stt) {
            this.stt = stt;
        }

        public String getMavipham() {
            return mavipham;
        }

        public void setMavipham(String mavipham) {
            this.mavipham = mavipham;
        }

        public String getMamuon() {
            return mamuon;
        }

        public void setMamuon(String mamuon) {
            this.mamuon = mamuon;
        }

        public String getMasach() {
            return masach;
        }

        public void setMasach(String masach) {
            this.masach = masach;
        }

        public String getTensach() {
            return tensach;
        }

        public void setTensach(String tensach) {
            this.tensach = tensach;
        }

        public Date getNgaymuon() {
            return ngaymuon;
        }

        public void setNgaymuon(Date ngaymuon) {
            this.ngaymuon = ngaymuon;
        }

        public Date getHantra() {
            return hantra;
        }

        public void setHantra(Date hantra) {
            this.hantra = hantra;
        }

        public String getTinhtrang() {
            return tinhtrang;
        }

        public void setTinhtrang(String tinhtrang) {
            this.tinhtrang = tinhtrang;
        }

        public String getMadocgia() {
            return madocgia;
        }

        public void setMadocgia(String madocgia) {
            this.madocgia = madocgia;
        }

        public String getTendocgia() {
            return tendocgia;
        }

        public void setTendocgia(String tendocgia) {
            this.tendocgia = tendocgia;
        }

        public String getLydo() {
            return lydo;
        }

        public void setLydo(String lydo) {
            this.lydo = lydo;
        }

        public String getHtxuphat() {
            return htxuphat;
        }

        public void setHtxuphat(String htxuphat) {
            this.htxuphat = htxuphat;
        }

        public Date getNgayxuly() {
            return ngayxuly;
        }

        public void setNgayxuly(Date ngayxuly) {
            this.ngayxuly = ngayxuly;
        }

        public Date getNgaytra() {
            return ngaytra;
        }

        public void setNgaytra(Date ngaytra) {
            this.ngaytra = ngaytra;
        }

        public vipham(Integer stt, String mamuon, String masach, String tensach, Date ngaymuon, 
                Date hantra,Date ngaytra, String tinhtrang,String madocgia,String tendocgia) {
            this.stt = stt;
            this.mamuon = mamuon;
            this.masach = masach;
            this.tensach = tensach;
            this.ngaymuon = ngaymuon;
            this.hantra = hantra;
            this.tinhtrang = tinhtrang;
            this.ngaytra = ngaytra;
            this.madocgia = madocgia;
            this.tendocgia = tendocgia;
        }
    }
    @FXML
    private TableView<vipham> table_VP;
    @FXML
    private Button bt_search;
    @FXML
    private DatePicker text_tungay;
    @FXML
    private DatePicker text_denngay;
    @FXML
    private TextField ct_maDG;
    @FXML
    private TextField ten_DG;
    @FXML
    private TextField Ly_do;
    @FXML
    private TextField ht_xu_ly;
    @FXML
    private TextField ngay_xu_ly;
    @FXML
    private Button bt_xuat;
    ObservableList<vipham> data_load = FXCollections.observableArrayList();
    Connection cn = null;
    Date today = new Date();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<vipham, Integer> colstt = new TableColumn<>("STT");
        colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        table_VP.getColumns().add(colstt);
        TableColumn<vipham, String> colMamuon = new TableColumn<>("Mã Mượn");
        colMamuon.setCellValueFactory(new PropertyValueFactory<>("mamuon"));
        table_VP.getColumns().add(colMamuon);
        TableColumn<vipham, String> colMasach = new TableColumn<>("Mã Sách");
        colMasach.setCellValueFactory(new PropertyValueFactory<>("masach"));
        table_VP.getColumns().add(colMasach);
        TableColumn<vipham, String> coltensach = new TableColumn<>("Tên Sách");
        coltensach.setCellValueFactory(new PropertyValueFactory<>("tensach"));
        coltensach.setMinWidth(120);
        table_VP.getColumns().add(coltensach);
        TableColumn<vipham, Date> colngaymuon = new TableColumn<>("Ngày Mượn");
        colngaymuon.setCellValueFactory(new PropertyValueFactory<>("ngaymuon"));
        table_VP.getColumns().add(colngaymuon);
        TableColumn<vipham, Date> colhantra = new TableColumn<>("Hạn Trả");
        colhantra.setCellValueFactory(new PropertyValueFactory<>("hantra"));
        table_VP.getColumns().add(colhantra);
        TableColumn<vipham, Date> coltinhtrang = new TableColumn<>("Tình Trạng");
        coltinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
        table_VP.getColumns().add(coltinhtrang);
        cn = util.Connect_JDBC.getConnection();
        Statement st = null;
        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "SELECT a.*,b.TieuDe,c.HoVaTen FROM dbo.MuonSach a ,dbo.Book b, dbo.Doc_Gia c \n" +
"				WHERE a.TinhTrang = N'Quá Hạn'  AND a.MaSach = b.MaSach AND a.MaDocGia = c.MaDocGia";
            ResultSet rs = st.executeQuery(str);
            while(rs.next()){
                data_load.add(new vipham(data_load.size()+1,rs.getString("MaMuon"), rs.getString("MaSach"), 
                        rs.getString("TieuDe"), rs.getDate("NgayMuon"), rs.getDate("HanTra"),rs.getDate("NgayTra"), 
                        rs.getString("TinhTrang"),rs.getString("MaDocGia"),rs.getString("HoVaTen")));
                
            }
            table_VP.setItems(data_load);
        } catch (SQLException ex) {
            Logger.getLogger(Report_VPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void focus_ct(MouseEvent event) {
         int i = table_VP.getFocusModel().getFocusedIndex();
        vipham vp = data_load.get(i);
        ct_maDG.setText(vp.getMadocgia());
        ten_DG.setText(vp.getTendocgia());
        Ly_do.setText("Quá Hạn Trả Sách");
        ht_xu_ly.setText("Phạt Tiền");
        
        
    }

    @FXML
    private void Load_data(ActionEvent event) {

        LocalDate tungay = text_tungay.getValue();
        java.sql.Date From_date = java.sql.Date.valueOf(tungay);
        LocalDate denngay = text_denngay.getValue();
        java.sql.Date To_date = java.sql.Date.valueOf(denngay);

        //  date.valueOf(gt);
        // System.out.println(date);
    }

    @FXML
    private void xuat_Excel(ActionEvent event) {
    }

}
