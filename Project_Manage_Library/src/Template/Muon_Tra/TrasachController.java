/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class TrasachController implements Initializable {

    @FXML
    private Button bt_giahan;
    @FXML
    private TextField ct_masach;
    @FXML
    private TextField ct_tensach;
    @FXML
    private TextField ct_tacgia;

    public class trasach {

        private Integer stt;
        private String MaMuon;
        private String MaSach;
        private String tensach;
        private String ngaymuon;
        private String Hantra;
        private String tinhtrang;
        private Date datemuon;
        private Date dealine;
        private String tacgia;
        private String theloai;
        private String ngonngu;
        private Double dongia;
        private String NXB;
        private Integer TGmuon;

        public Integer getTGmuon() {
            return TGmuon;
        }

        public void setTGmuon(Integer TGmuon) {
            this.TGmuon = TGmuon;
        }

        public String getTheloai() {
            return theloai;
        }

        public void setTheloai(String theloai) {
            this.theloai = theloai;
        }

        public String getTacgia() {
            return tacgia;
        }

        public void setTacgia(String tacgia) {
            this.tacgia = tacgia;
        }

        public String getNgonngu() {
            return ngonngu;
        }

        public void setNgonngu(String ngonngu) {
            this.ngonngu = ngonngu;
        }

        public Double getDongia() {
            return dongia;
        }

        public void setDongia(Double dongia) {
            this.dongia = dongia;
        }

        public String getNXB() {
            return NXB;
        }

        public void setNXB(String NXB) {
            this.NXB = NXB;
        }

        public String getMaMuon() {
            return MaMuon;
        }

        public void setMaMuon(String MaMuon) {
            this.MaMuon = MaMuon;
        }

        public String getMaSach() {
            return MaSach;
        }

        public void setMaSach(String MaSach) {
            this.MaSach = MaSach;
        }

        public String getTensach() {
            return tensach;
        }

        public void setTensach(String tensach) {
            this.tensach = tensach;
        }

        public String getNgaymuon() {
            return ngaymuon;
        }

        public void setNgaymuon(String ngaymuon) {
            this.ngaymuon = ngaymuon;
        }

        public String getHantra() {
            return Hantra;
        }

        public void setHantra(String Hantra) {
            this.Hantra = Hantra;
        }

        public Integer getStt() {
            return stt;
        }

        public void setStt(Integer stt) {
            this.stt = stt;
        }

        public String getTinhtrang() {
            return tinhtrang;
        }

        public void setTinhtrang(String tinhtrang) {
            this.tinhtrang = tinhtrang;
        }

        public Date getDatemuon() {
            return datemuon;
        }

        public void setDatemuon(Date datemuon) {
            this.datemuon = datemuon;
        }

        public Date getDealine() {
            return dealine;
        }

        public void setDealine(Date dealine) {
            this.dealine = dealine;
        }

        public trasach(Integer stt, String MaMuon, String MaSach, String tensach,
                String ngaymuon, String Hantra, String tinhtrang, Date datemuon,
                Date dealine, String tacgia, String theloai, String ngonngu, Double dongia,
                String NXB, Integer tgmuon) {
            this.stt = stt;
            this.MaMuon = MaMuon;
            this.MaSach = MaSach;
            this.tensach = tensach;
            this.ngaymuon = ngaymuon;
            this.Hantra = Hantra;
            this.tinhtrang = tinhtrang;
            this.datemuon = datemuon;
            this.dealine = dealine;
            this.tacgia = tacgia;
            this.theloai = theloai;
            this.ngonngu = ngonngu;
            this.dongia = dongia;
            this.NXB = NXB;
            this.TGmuon = tgmuon;
        }
    }
    @FXML
    private Button bt_tra;
    @FXML
    private Button bt_huy;
    @FXML
    private Button bt_luu;
    @FXML
    private TableView<trasach> TB_TraSach;
    @FXML
    private TextField text_MaPM;
    @FXML
    private TextField text_MaDG;
    @FXML
    private Button bt_load_by_PM;
    @FXML
    private Button bt_load_by_DG;
    @FXML
    private TextField ct_theloai;
    @FXML
    private TextField ct_ngonngu;
    @FXML
    private TextField ct_dongia;
    @FXML
    private TextField ct_NXB;
    ObservableList<trasach> data = FXCollections.observableArrayList();
    ObservableList<trasach> data_luu_DB = FXCollections.observableArrayList();
    Connection cn = null;
    Locale currentLocale = new Locale("vi", "VN");

    DateFormat currentDateFormat = DateFormat.getDateInstance(3, currentLocale);
    Calendar calendar = Calendar.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ct_dongia.setEditable(false);
        //ct_image.setEditable(false);
        ct_masach.setEditable(false);
        ct_ngonngu.setEditable(false);
        ct_NXB.setEditable(false);
        ct_tacgia.setEditable(false);
        ct_tensach.setEditable(false);
        ct_theloai.setEditable(false);

        TB_TraSach.setEditable(true);
        TableColumn<trasach, Integer> colstt = new TableColumn<>("STT");
        colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        TB_TraSach.getColumns().add(colstt);
        TableColumn<trasach, String> colMaMuon = new TableColumn<>("Mã Mượn");
        colMaMuon.setCellValueFactory(new PropertyValueFactory<>("MaMuon"));
        colMaMuon.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_TraSach.getColumns().add(colMaMuon);
        TableColumn<trasach, String> colMaSach = new TableColumn<>("Mã Sách");
        colMaSach.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        colMaSach.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_TraSach.getColumns().add(colMaSach);
        TableColumn<trasach, String> colTenSach = new TableColumn<>("Tên Sách");
        colTenSach.setCellValueFactory(new PropertyValueFactory<>("tensach"));
        colTenSach.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_TraSach.getColumns().add(colTenSach);
        TableColumn<trasach, String> colNgayMuon = new TableColumn<>("Ngày Mượn");
        colNgayMuon.setCellValueFactory(new PropertyValueFactory<>("ngaymuon"));
        //   colNgayMuon.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_TraSach.getColumns().add(colNgayMuon);
        TableColumn<trasach, String> colHanTra = new TableColumn<>("Hạn Trả");
        colHanTra.setCellValueFactory(new PropertyValueFactory<>("Hantra"));

        TB_TraSach.getColumns().add(colHanTra);
        TableColumn<trasach, String> colTinhtrang = new TableColumn<>("Tình Trạng");
        colTinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
        colTinhtrang.setCellFactory(TextFieldTableCell.forTableColumn());
        TB_TraSach.getColumns().add(colTinhtrang);
        //  colTinhtrang.setEditable(true);

//        TableColumn<PhieumuonController.phieumuonsach, String> MScol = new TableColumn<>("Mã Sách");
//        MScol.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
//        TB_Muon.getColumns().add(MScol);
//        TableColumn<PhieumuonController.phieumuonsach, String> NMcol = new TableColumn<>("Ngày Mượn");
//        NMcol.setCellValueFactory(new PropertyValueFactory<>("NgayMuon"));
//        TB_Muon.getColumns().add(NMcol);
//        TableColumn<PhieumuonController.phieumuonsach, String> Han = new TableColumn<>("Hạn Trả");
//        Han.setCellValueFactory(new PropertyValueFactory<>("HanTra"));
//        TB_Muon.getColumns().add(Han);
//        TableColumn<PhieumuonController.phieumuonsach, String> Tinhtrang = new TableColumn<>("Tình Trạng");
//        Tinhtrang.setCellValueFactory(new PropertyValueFactory<>("TinhTrang"));
//        TB_Muon.getColumns().add(Tinhtrang);
    }

    @FXML
    private void trasach(ActionEvent event) {
        int i = TB_TraSach.getFocusModel().getFocusedIndex();
        trasach ts = data.get(i);
        ts.setTinhtrang("Đã Trả");

        data_luu_DB.add(ts);
        data.remove(i);
        TB_TraSach.setItems(data);

    }

    @FXML
    private void Huy_action(ActionEvent event) {
        if (text_MaDG.getText().equals("")) {
            load_by_PM(event);
        } else if (text_MaPM.getText().equals("")) {
            load_by_DocGia(event);
        }
    }

    @FXML
    private void Luu_vao_DB(ActionEvent event) {
        cn = util.Connect_JDBC.getConnection();
//        Locale currentLocale;
//        currentLocale = new Locale("vi", "VN");
//        DateFormat currentDateFormat = DateFormat.getDateInstance(3, currentLocale);
//        Calendar calendar = Calendar.getInstance();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông Báo");
        alert.setHeaderText("Bạn Chắc Chắn Lưu Dữ Liệu ");
        //alert.setContentText("Thêm Thành Công!");
        alert.showAndWait();
        ButtonType result = alert.getResult();
        if (result.getText().equals("OK")) {
            if (data_luu_DB.size() > 0) {
                for (int i = 0; i < data_luu_DB.size(); i++) {
                    try {
                        trasach ts = data_luu_DB.get(i);
                        if (ts.getTinhtrang().equals("Đã Trả")) {
                            System.out.println("sdsa");
                            CallableStatement cs = null;
                            cs = cn.prepareCall("{call TraSach(?,?,?)}");
                            Date today = calendar.getTime();
                            java.sql.Date ngaytra = new java.sql.Date(today.getTime());

                            cs.setString(1, data_luu_DB.get(i).getMaMuon());
                            cs.setDate(2, ngaytra);
                            cs.setString(3, ts.getTinhtrang());

                            cs.executeUpdate();
                        } else if (ts.getTinhtrang().equals("Đã Gia Hạn")) {
                            System.out.println("sdsa2");
                            PreparedStatement ps = null;
                            String sql = "UPDATE dbo.MuonSach SET HanTra = ? , TinhTrang = ? where MaMuon = ? ";
                            ps = cn.prepareStatement(sql);

                            java.sql.Date hantra = new java.sql.Date(ts.getDealine().getTime());
                            ps.setDate(1, hantra);
                            ps.setString(2, ts.getTinhtrang());
                            ps.setString(3, ts.getMaMuon());
                            ps.executeUpdate();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TrasachController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Thông Báo");
                    alert2.setHeaderText("Đã Lưu Thành Công");
                    //alert.setContentText("Thêm Thành Công!");
                    alert2.showAndWait();
                    data_luu_DB.clear();
                    if (text_MaDG.getText().equals("")) {
                        load_by_PM(event);
                    } else if (text_MaPM.getText().equals("")) {
                        load_by_DocGia(event);
                    }
                }
            }

        } else {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Thông Báo");
            alert3.setHeaderText("Chưa có thay đổi");
            //alert.setContentText("Thêm Thành Công!");
            alert3.showAndWait();
        }
    }

    @FXML
    private void focus_click_CT(MouseEvent event) {
        int i = TB_TraSach.getFocusModel().getFocusedIndex();
        trasach ts = data.get(i);
        ct_masach.setText(ts.getMaSach());
        ct_tensach.setText(ts.getTensach());
        ct_tacgia.setText(ts.getTacgia());
        ct_ngonngu.setText(ts.getNgonngu());
        ct_theloai.setText(ts.getTheloai());
        ct_dongia.setText(ts.getDongia().toString());
        ct_NXB.setText(ts.getNXB());
    }

    @FXML
    private int load_by_PM(ActionEvent event) {
        //  System.out.println(text_MaDG.getText()+"Sds");
        text_MaDG.setText("");
        data.clear();
        try {
            cn = util.Connect_JDBC.getConnection();
            String queryStr = "SELECT j.MaMuon,h.*,j.NgayMuon,j.HanTra,j.TinhTrang FROM (SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,f.TenNXB,e.TGMuon \n"
                    + "FROM dbo.Book a,dbo.TheLoai b, dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                    + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                    + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB) h, (SELECT l.* FROM dbo.PhieuMuon k, "
                    + "dbo.MuonSach l WHERE MaPhieuMuon = '" + text_MaPM.getText() + "' AND l.MaMuon = k.MaMuon AND l.NgayTra IS NULL) j WHERE h.MaSach =j.MaSach";
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(queryStr);

            while (rs.next()) {
                Date today = new Date();
                String tinhtrang = null;
                if (today.after(rs.getDate("HanTra"))) {
                    tinhtrang = "Quá Hạn";
                } else {
                    tinhtrang = "Bình Thường";
                }
                String ngaymuon = currentDateFormat.format(rs.getDate("NgayMuon"));
                String hantra = currentDateFormat.format(rs.getDate("HanTra"));
                data.add(new trasach(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"),
                        ngaymuon, hantra,
                        rs.getString("TinhTrang"), rs.getDate("NgayMuon"), rs.getDate("HanTra"),
                        rs.getString("TenTacGia"), rs.getString("TenTheLoai"), rs.getString("TenNgonNgu"),
                        rs.getDouble("Gia"), rs.getString("TenNXB"), rs.getInt("TGMuon")));

            }
            st.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Tải Dữ Liệu Thành Công");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Lỗi Server");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
        } finally {
            try {

                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrasachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        TB_TraSach.setItems(data);
        return 1;
    }

    @FXML
    private int load_by_DocGia(ActionEvent event) {
        text_MaPM.setText("");
        data.clear();
        try {
            cn = util.Connect_JDBC.getConnection();
            String queryStr = "SELECT j.MaMuon,h.*,j.NgayMuon,j.HanTra,j.TinhTrang FROM (SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,f.TenNXB,e.TGMuon \n"
                    + "FROM dbo.Book a,dbo.TheLoai b, dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                    + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                    + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB) h, (SELECT * FROM dbo.MuonSach l WHERE l.MaDocGia = '" + text_MaDG.getText() + "' AND l.NgayTra IS NULL) j WHERE h.MaSach =j.MaSach";
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(queryStr);

            while (rs.next()) {
                //   Date ngaymuon = rs.getDate("NgayMuon");
                Date today = new Date();
                String tinhtrang = null;
                if (today.after(rs.getDate("HanTra"))) {
                    tinhtrang = "Quá Hạn";
                } else {
                    tinhtrang = "Bình Thường";
                }
                String ngaymuon = currentDateFormat.format(rs.getDate("NgayMuon"));
                String hantra = currentDateFormat.format(rs.getDate("HanTra"));
                data.add(new trasach(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"),
                        ngaymuon, hantra,
                        tinhtrang, rs.getDate("NgayMuon"), rs.getDate("HanTra"), rs.getString("TenTacGia"), rs.getString("TenTheLoai"),
                        rs.getString("TenNgonNgu"), rs.getDouble("Gia"), rs.getString("TenNXB"), rs.getInt("TGMuon")));

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Tải Dữ Liệu Thành Công");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
            st.close();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Không Có Dữ Liệu");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
        } finally {
            try {

                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrasachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        TB_TraSach.setItems(data);
        return 1;
    }

    @FXML
    private void giahanSach(ActionEvent event) {
        int i = TB_TraSach.getFocusModel().getFocusedIndex();
        trasach ts = data.get(i);
        if (ts.getTinhtrang().equals("Đã Gia Hạn") || ts.getTinhtrang().equals("Quá Hạn")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Mã Mượn Này Đã Gia Hạn");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
        } else {
            calendar.setTime(ts.getDealine());
            calendar.add(Calendar.MONTH, ts.getTGmuon());
            String hantra = currentDateFormat.format(calendar.getTime());
            ts.setHantra(hantra);
            // ts.setTinhtrang("Đã Gia Hạn");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn Chắc Gia Hạn Mã Mượn Này Chứ");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
            ButtonType result = alert.getResult();
            if (result.getText().equals("OK")) {
                ts.setTinhtrang("Đã Gia Hạn");
                data.set(i, ts);
                TB_TraSach.setItems(data);
                data_luu_DB.add(ts);
            }
            //  System.out.println(result.getText());
            // bt_giahan.setDisable(true);
        }
    }
}
