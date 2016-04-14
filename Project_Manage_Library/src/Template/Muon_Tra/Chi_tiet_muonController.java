/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class Chi_tiet_muonController implements Initializable {
    @FXML
    private Button bt_load;

   

    public class ct_muon {

        private Integer stt;
        private String MaMuon;
        private String MaSach;
        private String tensach;
        private String MaDocGia;
        private String tendocgia;
        private String NgayMuon;
        private String Hantra;
        private String ngaytra;
        private String tinhtrang;
        private Date today;
        private Date deal;
        private Date datetra;

        public Date getDatetra() {
            return datetra;
        }

        public void setDatetra(Date datetra) {
            this.datetra = datetra;
        }

        public Integer getStt() {
            return stt;
        }

        public void setStt(Integer stt) {
            this.stt = stt;
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

        public String getMaDocGia() {
            return MaDocGia;
        }

        public void setMaDocGia(String MaDocGia) {
            this.MaDocGia = MaDocGia;
        }

        public String getTendocgia() {
            return tendocgia;
        }

        public void setTendocgia(String tendocgia) {
            this.tendocgia = tendocgia;
        }

        public String getNgayMuon() {
            return NgayMuon;
        }

        public void setNgayMuon(String NgayMuon) {
            this.NgayMuon = NgayMuon;
        }

        public String getHantra() {
            return Hantra;
        }

        public void setHantra(String Hantra) {
            this.Hantra = Hantra;
        }

        public String getNgaytra() {
            return ngaytra;
        }

        public void setNgaytra(String ngaytra) {
            this.ngaytra = ngaytra;
        }

        public String getTinhtrang() {
            return tinhtrang;
        }

        public void setTinhtrang(String tinhtrang) {
            this.tinhtrang = tinhtrang;
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

        public Image getHinhanh() {
            return hinhanh;
        }

        public void setHinhanh(Image hinhanh) {
            this.hinhanh = hinhanh;
        }

        public ct_muon(Integer stt, String MaMuon, String MaSach, String tensach, String MaDocGia, String tendocgia, String NgayMuon, String Hantra, String ngaytra, String tinhtrang, Date today, Date deal, String tentacgia, String theloai, String ngonngu, Double gia, String NXB, Image hinhanh,Date datetra) {
            this.stt = stt;
            this.MaMuon = MaMuon;
            this.MaSach = MaSach;
            this.tensach = tensach;
            this.MaDocGia = MaDocGia;
            this.tendocgia = tendocgia;
            this.NgayMuon = NgayMuon;
            this.Hantra = Hantra;
            this.ngaytra = ngaytra;
            this.tinhtrang = tinhtrang;
            this.today = today;
            this.deal = deal;
            this.tentacgia = tentacgia;
            this.theloai = theloai;
            this.ngonngu = ngonngu;
            this.gia = gia;
            this.NXB = NXB;
            this.hinhanh = hinhanh;
            this.datetra = datetra;
        }
        private String tentacgia;
        private String theloai;
        private String ngonngu;
        private Double gia;
        private String NXB;
        private Image hinhanh;
    }
    @FXML
    private TableView<ct_muon> Table_muon;
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
    private ImageView ct_image;
    @FXML
    private TextField ct_nhaxb;

    ObservableList<ct_muon> data = FXCollections.observableArrayList();
    Connection cn = null;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void focus_row(MouseEvent e) {
        
        int i = Table_muon.getFocusModel().getFocusedIndex();
        //   System.out.println(i);
        ct_muon pm = data.get(i);
        ct_masach.setText(pm.getMaSach());
        ct_tensach.setText(pm.getTensach());
        ct_tacgia.setText(pm.getTentacgia());
        ct_theloai.setText(pm.getTheloai());
        ct_ngonngu.setText(pm.getNgonngu());
        ct_dongia.setText(pm.getGia().toString());
        ct_nhaxb.setText(pm.getNXB());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ct_image.setImage(null);
            Image im = new Image(new InputStream() {

                @Override
                public int read() throws IOException {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            ct_dongia.setEditable(false);
            //ct_image.setEditable(false);
            ct_masach.setEditable(false);
            ct_ngonngu.setEditable(false);
            ct_nhaxb.setEditable(false);
            ct_tacgia.setEditable(false);
            ct_tensach.setEditable(false);
            ct_theloai.setEditable(false);
            Table_muon.setEditable(true);
            TableColumn<ct_muon, Integer> colstt = new TableColumn<>("STT");
            colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
            Table_muon.getColumns().add(colstt);
            TableColumn<ct_muon, String> mamuon = new TableColumn<>("Mã Mượn");
            mamuon.setCellValueFactory(new PropertyValueFactory<>("MaMuon"));
            mamuon.setCellFactory(TextFieldTableCell.forTableColumn());
            Table_muon.getColumns().add(mamuon);
            TableColumn<ct_muon, String> masach = new TableColumn<>("Mã Sách");
            masach.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
            masach.setCellFactory(TextFieldTableCell.forTableColumn());
            Table_muon.getColumns().add(masach);
            TableColumn<ct_muon, String> madocgia = new TableColumn<>("Mã Đọc Giả");
            madocgia.setCellFactory(TextFieldTableCell.forTableColumn());
            madocgia.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
            Table_muon.getColumns().add(madocgia);
            TableColumn<ct_muon, String> tensach = new TableColumn<>("Tên Sách");
            tensach.setCellValueFactory(new PropertyValueFactory<>("tensach"));
            tensach.setCellFactory(TextFieldTableCell.forTableColumn());
            Table_muon.getColumns().add(tensach);
            tensach.setMinWidth(150);
            TableColumn<ct_muon, String> tendocgia = new TableColumn<>("Tên Đọc Giả");
            tendocgia.setCellValueFactory(new PropertyValueFactory<>("tendocgia"));
            tendocgia.setCellFactory(TextFieldTableCell.forTableColumn());
            Table_muon.getColumns().add(tendocgia);
            tendocgia.setMinWidth(150);
            TableColumn<ct_muon, Date> ngaymuong = new TableColumn<>("Ngày Mượn");
            ngaymuong.setCellValueFactory(new PropertyValueFactory<>("today"));
            Table_muon.getColumns().add(ngaymuong);
            
            TableColumn<ct_muon, Date> hantra = new TableColumn<>("Hạn Trả");
            hantra.setCellValueFactory(new PropertyValueFactory<>("deal"));
            Table_muon.getColumns().add(hantra);
            TableColumn<ct_muon, Date> ngaytra = new TableColumn<>("Ngày Trả");
            ngaytra.setCellValueFactory(new PropertyValueFactory<>("datetra"));
            Table_muon.getColumns().add(ngaytra);
            TableColumn<ct_muon, String> tinhtrang = new TableColumn<>("Tình Trạng");
            tinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
            tinhtrang.setCellFactory(TextFieldTableCell.forTableColumn());
            Table_muon.getColumns().add(tinhtrang);
            cn = util.Connect_JDBC.getConnection();
            String str = "SELECT j.*,h.* FROM (SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,f.TenNXB,a.Hinhanh \n"
                    + "FROM dbo.Book a,dbo.TheLoai b,dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                    + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                    + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB) h, (SELECT l.*,m.HoVaTen FROM dbo.MuonSach l ,dbo.Doc_Gia m\n"
                    + "WHERE l.MaDocGia = m.MaDocGia ) j WHERE h.MaSach =j.MaSach";
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                
                String ngaymuon = util.date.convertStringToDate(rs.getDate("NgayMuon"));
                Date date = util.date.convertDatetoString(ngaymuon);
                Date date1 = rs.getDate("HanTra");
                String dealline = util.date.convertStringToDate(rs.getDate("HanTra"));
                String datetra = util.date.convertStringToDate(rs.getDate("NgayTra"));
                data.add(new ct_muon(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"), rs.getString("MaDocGia"), rs.getString("HoVaTen"),
                        ngaymuon, dealline, datetra, rs.getString("TinhTrang"),rs.getDate("NgayMuon"),
                        rs.getDate("HanTra"), rs.getString("TenTacGia"), rs.getString("TenTheLoai"),
                        rs.getString("TenNgonNgu"), rs.getDouble("Gia"), rs.getString("TenNXB"), null,rs.getDate("NgayTra")));
            }

            Table_muon.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Chi_tiet_muonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Chi_tiet_muonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void load_data(ActionEvent event) throws SQLException, ParseException {
        data.clear();
           cn = util.Connect_JDBC.getConnection();
            String str = "SELECT j.*,h.* FROM (SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,f.TenNXB,a.Hinhanh \n"
                    + "FROM dbo.Book a,dbo.TheLoai b,dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                    + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                    + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB) h, (SELECT l.*,m.HoVaTen FROM dbo.MuonSach l ,dbo.Doc_Gia m\n"
                    + "WHERE l.MaDocGia = m.MaDocGia ) j WHERE h.MaSach =j.MaSach";
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                
                String ngaymuon = util.date.convertStringToDate(rs.getDate("NgayMuon"));
                Date date = util.date.convertDatetoString(ngaymuon);
                Date date1 = rs.getDate("HanTra");
                String dealline = util.date.convertStringToDate(rs.getDate("HanTra"));
                String datetra = util.date.convertStringToDate(rs.getDate("NgayTra"));
                data.add(new ct_muon(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"), rs.getString("MaDocGia"), rs.getString("HoVaTen"),
                        ngaymuon, dealline, datetra, rs.getString("TinhTrang"),rs.getDate("NgayMuon"),
                        rs.getDate("HanTra"), rs.getString("TenTacGia"), rs.getString("TenTheLoai"),
                        rs.getString("TenNgonNgu"), rs.getDouble("Gia"), rs.getString("TenNXB"), null,rs.getDate("NgayTra")));
            }

            Table_muon.setItems(data);
        } 
        
    
}

