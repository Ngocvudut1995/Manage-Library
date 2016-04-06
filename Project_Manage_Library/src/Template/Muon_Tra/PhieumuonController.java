/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import com.sun.rowset.JdbcRowSetImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static util.Connect_JDBC.password;
import static util.Connect_JDBC.url;
import static util.Connect_JDBC.userName;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class PhieumuonController implements Initializable {

    @FXML
    private TextField text_PM;
    @FXML
    private TextField MaNV;
    @FXML
    private TextField ct_masach;
    @FXML
    private TextField ct_tensach;
    @FXML
    private TextField ct_tacgia;
    @FXML
    private TextField ct_theloai;
    @FXML
    private TextField ct_ngonngu;
    @FXML
    private TextField ct_dongia;
    @FXML
    private TextField ct_NXB;

    @FXML
    private void focus_ThongtinCT(MouseEvent event) {

        int i = TB_Muon.getFocusModel().getFocusedIndex();
        //   System.out.println(i);
        phieumuonsach pm = data.get(i);
        ct_masach.setText(pm.getMaSach());
        ct_tensach.setText(pm.getTensach());
        ct_tacgia.setText(pm.getTentacgia());
        ct_theloai.setText(pm.getTheloai());
        ct_ngonngu.setText(pm.getNgonngu());
        ct_dongia.setText(pm.getGia().toString());
        ct_NXB.setText(pm.getNXB());

    }

    public class phieumuonsach {

        private Integer stt;
        private String MaMuon;
        private String MaSach;
        private String NgayMuon;
        private Date today;
        private Date deal;
        private String tensach;
        private String tentacgia;
        private String theloai;
        private String ngonngu;
        private Double gia;
        private String NXB;

        public Integer getStt() {
            return stt;
        }

        public void setStt(Integer stt) {
            this.stt = stt;
        }
        private String HanTra;
        private String TinhTrang;

        public phieumuonsach(Integer stt, String MaMuon, String MaSach, String NgayMuon,
                Date today, Date deal, String tensach, String tentacgia, String theloai,
                String ngonngu, Double gia, String NXB, String HanTra, String TinhTrang) {
            this.stt = stt;
            this.MaMuon = MaMuon;
            this.MaSach = MaSach;
            this.NgayMuon = NgayMuon;
            this.today = today;
            this.deal = deal;
            this.tensach = tensach;
            this.tentacgia = tentacgia;
            this.theloai = theloai;
            this.ngonngu = ngonngu;
            this.gia = gia;
            this.NXB = NXB;
            this.HanTra = HanTra;
            this.TinhTrang = TinhTrang;
        }

        public String getMaMuon() {
            return MaMuon;
        }

        public String getTensach() {
            return tensach;
        }

        public void setTensach(String tensach) {
            this.tensach = tensach;
        }

        public String getTentacgia() {
            return tentacgia;
        }

        public void setTentacgia(String tentacgia) {
            this.tentacgia = tentacgia;
        }

        public String getTheloai() {
            return theloai;
        }

        public void setTheloai(String theloai) {
            this.theloai = theloai;
        }

        public String getNgonngu() {
            return ngonngu;
        }

        public void setNgonngu(String ngonngu) {
            this.ngonngu = ngonngu;
        }

        public Double getGia() {
            return gia;
        }

        public void setGia(Double gia) {
            this.gia = gia;
        }

        public String getNXB() {
            return NXB;
        }

        public void setNXB(String NXB) {
            this.NXB = NXB;
        }

        public void setMaMuon(String MaMuon) {
            this.MaMuon = MaMuon;
        }

        public Date getToday() {
            return today;
        }

        public void setToday(Date today) {
            this.today = today;
        }

        public Date getDeal() {
            return deal;
        }

        public void setDeal(Date deal) {
            this.deal = deal;
        }

        public String getNgayMuon() {
            return NgayMuon;
        }

        public void setNgayMuon(String NgayMuon) {
            this.NgayMuon = NgayMuon;
        }

        public String getHanTra() {
            return HanTra;
        }

        public void setHanTra(String HanTra) {
            this.HanTra = HanTra;
        }

        public String getTinhTrang() {
            return TinhTrang;
        }

        public void setTinhTrang(String TinhTrang) {
            this.TinhTrang = TinhTrang;
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
    Connection cn = null;

    @FXML
    private int them_sach(ActionEvent event) throws IOException {
        // Date today = new Date();
        Locale currentLocale;
        currentLocale = new Locale("vi", "VN");

        DateFormat currentDateFormat = DateFormat.getDateInstance(3, currentLocale);
        Calendar calendar = Calendar.getInstance();
        String today = currentDateFormat.format(calendar.getTime());

        for (int i = 0; i < data.size(); i++) {
            phieumuonsach pm = data.get(i);
            if (MaSach.getText().equalsIgnoreCase(pm.MaSach)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Đã Có Sách Trong Bảng");
                //alert.setContentText("Thêm Thành Công!");
                alert.showAndWait();
                return 1;
            }
        }

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = util.Connect_JDBC.getConnection();
            // cn = DriverManager.getConnection("jdbc:sqlserver://VUDANG:1433;databaseName = QLThuVien","admin","123456");
            String queryString = "SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.HoVaTen,d.TenNgonNgu,a.Gia,f.TenNXB,e.TGMuon "
                    + "FROM dbo.Book a,dbo.TheLoai b, dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                    + "WHERE (a.MaTheLoai = b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB";
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(queryString);

            String s = MaSach.getText();
            while (rs.next()) {
                //       System.out.println(s+"|"+rs.getString(1)+"|");
                if (s.equalsIgnoreCase(rs.getString("MaSach").trim())) {
                    // System.out.println(rs.getString(1));
                    calendar.add(calendar.MONTH, rs.getInt("TGMuon"));
                    String dealline = currentDateFormat.format(calendar.getTime());
                    String stt = "" + data.size();
                    data.add(new phieumuonsach(data.size() + 1, "", rs.getString(1), today, Calendar.getInstance().getTime(), calendar.getTime(), rs.getString("TieuDe"), rs.getString("Hovaten"), rs.getString("TenTheLoai"), rs.getString("TenNgonNgu"), rs.getDouble("Gia"), rs.getString("TenNXB"), dealline, "Binh Thuong"));
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông Báo");
                    alert.setHeaderText("Thêm Thành Công");
                    //alert.setContentText("Thêm Thành Công!");
                    alert.showAndWait();
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TB_Muon.setItems(data);
        //    MaSach.setText("");

        return 0;

    }

    @FXML
    public void luu_vaoDB(ActionEvent event) {
        Locale currentLocale;
        currentLocale = new Locale("vi", "VN");
        DateFormat currentDateFormat = DateFormat.getDateInstance(3, currentLocale);
        Calendar calendar = Calendar.getInstance();
        if (data.size() <= 0 || Ma_DG.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Thông Tin Chưa Đầy Đủ");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
        } else {
            for (int i = 0; i < data.size(); i++) {
                try {
                    phieumuonsach pm = data.get(i);
                    System.out.println(pm.getMaSach());
                    CallableStatement cs = null;
                    cs = cn.prepareCall("{call INSERT_MuonSach(?,?,?,?,?,?,?)}");
                    cs.setString(1, text_PM.getText());
                    cs.setString(2, pm.getMaSach());
                    cs.setString(3, Ma_DG.getText());
                    java.sql.Date sqldate = new java.sql.Date(pm.getToday().getTime());
                    cs.setDate(4, sqldate);
                    java.sql.Date sqldeal = new java.sql.Date(pm.getDeal().getTime());
                    cs.setDate(5, sqldeal);
                    cs.setString(6, "NV1021001");
                    cs.setString(7, "Binh Thuong");

                    cs.executeUpdate();
                    Ma_DG.setText("");
                    data.clear();
                    cn = util.Connect_JDBC.getConnection();
                    String queryString = "SELECT RIGHT(a.MaPhieuMuon,LEN(a.MaPhieuMuon)-2) FROM(SELECT TOP 1 MaPhieuMuon FROM dbo.PhieuMuon\n"
                            + "ORDER BY MaPhieuMuon DESC) a";
                    Statement st;
                    try {
                        st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = st.executeQuery(queryString);
                        rs.next();
                        text_PM.setText("PM" + (rs.getInt(1) + 1));
                    } catch (SQLException ex) {
                        Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Thông Báo");
                    alert.setHeaderText("Thông Tin Chưa Đúng");
                    //alert.setContentText("Thêm Thành Công!");
                    alert.showAndWait();
                }
            }
        }

        System.out.println("asdas11");
//                for (int i = 0; i < data.size(); i++) {
//                    System.out.println("sdasd");
//                }
//              try {
//                  phieumuonsach p = data.get(0);
//                  System.out.println(p.getMaSach());
//                  CallableStatement cs = null;
//                  cs = cn.prepareCall("{call INSERT_MuonSach(?,?,?,?,?,?,?)}");
//                  cs.setString(1,text_PM.getText());
//                  cs.setString(2,p.getMaSach());
//                  cs.setString(3,Ma_DG.getText());
//                  java.sql.Date sqldate = new java.sql.Date(p.getToday().getTime());
//                  cs.setDate(4, sqldate);
//                  
//                  cs.setDate(5, sqldate);
//                  cs.setString(6, "NV1021001");
//                  cs.setString(7, "Binh Thuong");
//                  
//                  cs.executeUpdate();
//                  Ma_DG.setText("");
//                  data.clear();
//                  TB_Muon.setItems(data);
//                  Alert alert = new Alert(AlertType.INFORMATION);
//                  alert.setTitle("Thông Báo");
//                  alert.setHeaderText("Đã Lưu Thành Công");
//                  //alert.setContentText("Thêm Thành Công!");
//                  alert.showAndWait();
//              } catch (SQLException ex) {
//                  Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
//              }
        // }

    }

    @FXML
    private void xuat_excel(ActionEvent event) {
    }

    @FXML
    private void huy_toan_bo(ActionEvent event) {
        //System.out.println("ádad");
        data.clear();
        TB_Muon.setItems(data);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<phieumuonsach, Integer> colstt = new TableColumn<>("STT");
        colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        TB_Muon.getColumns().add(colstt);
//        TableColumn<phieumuonsach, String> col = new TableColumn<>("Mã Mượn");
//        col.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
//        TB_Muon.getColumns().add(col);
        TableColumn<phieumuonsach, String> MScol = new TableColumn<>("Mã Sách");
        MScol.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        TB_Muon.getColumns().add(MScol);
        TableColumn<phieumuonsach, String> NMcol = new TableColumn<>("Ngày Mượn");
        NMcol.setCellValueFactory(new PropertyValueFactory<>("NgayMuon"));
        TB_Muon.getColumns().add(NMcol);
        TableColumn<phieumuonsach, String> Han = new TableColumn<>("Hạn Trả");
        Han.setCellValueFactory(new PropertyValueFactory<>("HanTra"));
        TB_Muon.getColumns().add(Han);
        TableColumn<phieumuonsach, String> Tinhtrang = new TableColumn<>("Tình Trạng");
        Tinhtrang.setCellValueFactory(new PropertyValueFactory<>("TinhTrang"));
        TB_Muon.getColumns().add(Tinhtrang);
        cn = util.Connect_JDBC.getConnection();
        String queryString = "SELECT RIGHT(a.MaPhieuMuon,LEN(a.MaPhieuMuon)-2) FROM(SELECT TOP 1 MaPhieuMuon FROM dbo.PhieuMuon\n"
                + "ORDER BY MaPhieuMuon DESC) a";
        Statement st;
        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(queryString);
            rs.next();
            text_PM.setText("PM" + (rs.getInt(1) + 1));
        } catch (SQLException ex) {
            Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
