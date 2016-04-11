/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Bao_Cao;

import Template.Muon_Tra.Chi_tiet_traController;
import Template.Muon_Tra.PhieumuonController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import util.date;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class Report_MuonSachController implements Initializable {

    @FXML
    private TextField SL_sach_muon;
    @FXML
    private TextField SL_DG_muon;
    @FXML
    private TextField SL_sach_max;
    @FXML
    private TextField SL_DG_max;

    @FXML
    private void xuat_file(ActionEvent event) throws IOException {
        File file = new File("F:\\Document\\JAVA\\Project_Manage_Library\\src\\DB\\table1.xls");
        File temp = File.createTempFile("table", ".xls");
        System.out.println(temp.getAbsolutePath());
        String fileName = "/DB/phieumuon.xls";
        WritableWorkbook workbook;

        try {
            workbook = Workbook.createWorkbook(temp);
            WritableSheet sheet1 = workbook.createSheet("Demo", 0);
            //    WritableSheet sheet1 = workbook.getSheet(0);
            WritableFont tahomaboldpt = new WritableFont(WritableFont.TAHOMA, 14, WritableFont.BOLD);
//            WritableCellFormat tahomaBold = new WritableCellFormat(tahomaboldpt);
//            tahomaBold.setAlignment(Alignment.CENTRE);
            WritableCellFormat cf = new WritableCellFormat();
            cf.setFont(tahomaboldpt);
            cf.setAlignment(Alignment.CENTRE);
            WritableCellFormat cf2 = new WritableCellFormat();
            cf2.setAlignment(Alignment.CENTRE);
            sheet1.addCell(new jxl.write.Label(5, 1, Title.getText(), cf));
            sheet1.addCell(new jxl.write.Label(2, 3, "STT", cf2));
            sheet1.setColumnView(2, 15);
            sheet1.addCell(new jxl.write.Label(3, 3, "Mã Mươn", cf2));
            sheet1.setColumnView(3, 15);
            sheet1.addCell(new jxl.write.Label(4, 3, "Mã Sách", cf2));
            sheet1.setColumnView(4, 30);
            sheet1.addCell(new jxl.write.Label(5, 3, "Mã Độc Giả", cf2));
            sheet1.setColumnView(5, 30);
            sheet1.addCell(new jxl.write.Label(6, 3, "Tên Sách", cf2));
            sheet1.setColumnView(6, 30);
            sheet1.addCell(new jxl.write.Label(7, 3, "Tên Độc Giả", cf2));
            sheet1.setColumnView(7, 15);
            sheet1.addCell(new jxl.write.Label(8, 3, "Ngày Mượn", cf2));
            sheet1.setColumnView(8, 15);
            sheet1.addCell(new jxl.write.Label(9, 3, "Hạn Trả Mượn", cf2));
            sheet1.setColumnView(9, 15);
            sheet1.addCell(new jxl.write.Label(10, 3, "Ngày Trả", cf2));
            sheet1.setColumnView(10, 15);
            sheet1.addCell(new jxl.write.Label(11, 3, "Tình Trạng", cf2));
            sheet1.setColumnView(11, 15);
            for (int i = 4; i < data.size() + 4; i++) {
                //   String ngaymuon = util.date.
                sheet1.addCell(new jxl.write.Number(2, i, data.get(i - 4).getStt(), cf2));
                sheet1.addCell(new jxl.write.Label(3, i, data.get(i - 4).getMaMuon(), cf2));
                sheet1.addCell(new jxl.write.Label(4, i, data.get(i - 4).getMaSach(), cf2));
                sheet1.addCell(new jxl.write.Label(5, i, data.get(i - 4).getNgayMuon(), cf2));
                sheet1.addCell(new jxl.write.Label(6, i, data.get(i - 4).getTensach(), cf2));
                sheet1.addCell(new jxl.write.Label(7, i, data.get(i - 4).getTendocgia(), cf2));
                sheet1.addCell(new jxl.write.Label(8, i, data.get(i - 4).getNgayMuon(), cf2));
                sheet1.addCell(new jxl.write.Label(9, i, data.get(i - 4).getHantra(), cf2));
                sheet1.addCell(new jxl.write.Label(10, i, data.get(i - 4).getNgaytra(), cf2));
                sheet1.addCell(new jxl.write.Label(11, i, data.get(i - 4).getTinhtrang(), cf2));

            }
            int i = 6 + data.size();
            sheet1.addCell(new jxl.write.Label(4, i, "Sách được mượn nhiều nhất:", cf2));
            sheet1.addCell(new jxl.write.Label(5, i, Sach_muon_max.getText(), cf2));
            sheet1.addCell(new jxl.write.Label(6, i, "Mã sách:", cf2));
            sheet1.addCell(new jxl.write.Label(7, i, Ma_sach.getText(), cf2));
            sheet1.addCell(new jxl.write.Label(8, i, "SL:", cf2));
            sheet1.addCell(new jxl.write.Label(9, i++, SL_sach_max.getText(), cf2));

            sheet1.addCell(new jxl.write.Label(4, i, "Đoc giả mượn nhiều nhất:", cf2));
            sheet1.addCell(new jxl.write.Label(5, i, Doc_gia_muon_max.getText(), cf2));
            sheet1.addCell(new jxl.write.Label(6, i, "Mã đọc giả:", cf2));
            sheet1.addCell(new jxl.write.Label(7, i, Ma_DG.getText(), cf2));
            sheet1.addCell(new jxl.write.Label(8, i, "SL:", cf2));
            sheet1.addCell(new jxl.write.Label(9, i++, SL_DG_max.getText(), cf2));

            sheet1.addCell(new jxl.write.Label(4, i, "Tổng số lượng mượn:", cf2));
            sheet1.addCell(new jxl.write.Label(5, i, tong_luot.getText(), cf2));
            sheet1.addCell(new jxl.write.Label(6, i,"Số lượng đọc giả mượn:", cf2));
            sheet1.addCell(new jxl.write.Label(7, i, SL_DG_muon.getText(), cf2));
            sheet1.addCell(new jxl.write.Label(8, i, "Số lượng sách mượn:", cf2));
            sheet1.addCell(new jxl.write.Label(9, i++, SL_sach_muon.getText(), cf2));

//            for(int i = 2;i<7;i++){
//                 
//            }
            // for(int i = 2;i<data.size();i++)
            workbook.write();
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(PhieumuonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Desktop desktop = null;
        desktop = Desktop.getDesktop();
        desktop.open(temp);
        temp.deleteOnExit();
    }

    public class bangmuonsach {

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

        public Date getDatetra() {
            return datetra;
        }

        public void setDatetra(Date datetra) {
            this.datetra = datetra;
        }

        public bangmuonsach(Integer stt, String MaMuon, String MaSach, String tensach, String MaDocGia, String tendocgia, String NgayMuon, String Hantra, String ngaytra, String tinhtrang, Date today, Date deal, Date datetra) {
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
            this.datetra = datetra;
        }
    }
    ObservableList<bangmuonsach> data = FXCollections.observableArrayList();
    Connection cn = null;
    @FXML
    private Label Title;
    @FXML
    private TableView<bangmuonsach> BangTK;
    @FXML
    private TextField Sach_muon_max;
    @FXML
    private TextField Doc_gia_muon_max;
    @FXML
    private TextField tong_luot;
    @FXML
    private TextField Ma_sach;
    @FXML
    private TextField Ma_DG;
    @FXML
    private Button bt_tuan;
    @FXML
    private Button bt_thang;
    @FXML
    private Button bt_qui;
    @FXML
    private Button bt_nam;
    @FXML
    private Button xuat_excel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Title.setText("Bảng Báo Cáo Thống Kê Mượn Sách ");
            TableColumn<bangmuonsach, Integer> colstt = new TableColumn<>("STT");
            colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
            BangTK.getColumns().add(colstt);
            TableColumn<bangmuonsach, String> mamuon = new TableColumn<>("Mã Mượn");
            mamuon.setCellValueFactory(new PropertyValueFactory<>("MaMuon"));
            BangTK.getColumns().add(mamuon);
            TableColumn<bangmuonsach, String> masach = new TableColumn<>("Mã Sách");
            masach.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
            BangTK.getColumns().add(masach);
            TableColumn<bangmuonsach, String> madocgia = new TableColumn<>("Mã Đọc Giả");
            madocgia.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
            BangTK.getColumns().add(madocgia);
            TableColumn<bangmuonsach, String> tensach = new TableColumn<>("Tên Sách");
            tensach.setCellValueFactory(new PropertyValueFactory<>("tensach"));
            BangTK.getColumns().add(tensach);
            tensach.setMinWidth(100);
            TableColumn<bangmuonsach, String> tendocgia = new TableColumn<>("Tên Đọc Giả");
            tendocgia.setCellValueFactory(new PropertyValueFactory<>("tendocgia"));
            BangTK.getColumns().add(tendocgia);
            tendocgia.setMinWidth(100);
            TableColumn<bangmuonsach, Date> ngaymuong = new TableColumn<>("Ngày Mượn");
            ngaymuong.setCellValueFactory(new PropertyValueFactory<>("today"));
            BangTK.getColumns().add(ngaymuong);

            TableColumn<bangmuonsach, Date> hantra = new TableColumn<>("Hạn Trả");
            hantra.setCellValueFactory(new PropertyValueFactory<>("deal"));
            BangTK.getColumns().add(hantra);
            TableColumn<bangmuonsach, Date> ngaytra = new TableColumn<>("Ngày Trả");
            ngaytra.setCellValueFactory(new PropertyValueFactory<>("datetra"));
            BangTK.getColumns().add(ngaytra);
            TableColumn<bangmuonsach, Integer> tinhtrang = new TableColumn<>("Tình Trạng");
            tinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
            BangTK.getColumns().add(tinhtrang);
            cn = util.Connect_JDBC.getConnection();
            String str = "SELECT a.*,b.TieuDe,c.HoVaTen FROM dbo.MuonSach a, dbo.Book b,dbo.Doc_Gia c "
                    + "WHERE a.MaSach = b.MaSach AND a.MaDocGia = c.MaDocGia";
            Statement st = null;
            try {
                st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            } catch (SQLException ex) {
                Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = null;
            try {
                rs = st.executeQuery(str);
            } catch (SQLException ex) {
                Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (rs.next()) {

                String ngaymuon = util.date.convertStringToDate(rs.getDate("NgayMuon"));
                Date date = util.date.convertDatetoString(ngaymuon);
                Date date1 = rs.getDate("HanTra");
                String dealline = util.date.convertStringToDate(rs.getDate("HanTra"));
                String datetra = util.date.convertStringToDate(rs.getDate("NgayTra"));
                data.add(new bangmuonsach(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"), rs.getString("MaDocGia"), rs.getString("HoVaTen"),
                        ngaymuon, dealline, datetra, rs.getString("TinhTrang"), rs.getDate("NgayMuon"),
                        rs.getDate("HanTra"), rs.getDate("NgayTra")));
            }

            BangTK.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TK_Tuan(ActionEvent event) {
        data.clear();
        Title.setText("Bảng Báo Cáo Thống Kê Mượn Sách Trong Tuần");
        try {
            Date today = new Date();
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DATE, -7);
            Date date = new Date(calendar.getTime().getTime());
            java.sql.Date sqltungay = new java.sql.Date(date.getTime());
            java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "SELECT a.*,b.TieuDe,c.HoVaTen FROM dbo.MuonSach a, dbo.Book b,dbo.Doc_Gia c "
                    + "WHERE a.MaSach = b.MaSach AND a.MaDocGia = c.MaDocGia "
                    + "and (a.NgayMuon between '" + sqltungay + "' and '" + sqldenngay + "')";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                String ngaymuon = util.date.convertStringToDate(rs.getDate("NgayMuon"));

                String dealline = util.date.convertStringToDate(rs.getDate("HanTra"));
                String datetra = util.date.convertStringToDate(rs.getDate("NgayTra"));
                data.add(new bangmuonsach(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"), rs.getString("MaDocGia"), rs.getString("HoVaTen"),
                        ngaymuon, dealline, datetra, rs.getString("TinhTrang"), rs.getDate("NgayMuon"),
                        rs.getDate("HanTra"), rs.getDate("NgayTra")));
            }
            st.close();
            BangTK.setItems(data);
            CallableStatement cs = null;
            cs = cn.prepareCall("{call TK_SachMuon(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setDate(1, sqltungay);
            cs.setDate(2, sqldenngay);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.NVARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.NVARCHAR);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.VARCHAR);
            cs.registerOutParameter(11, Types.VARCHAR);
            cs.executeUpdate();
            String Ma_Sach_muon_max = cs.getString(3);
            String Ten_Sach_muon_max = cs.getNString(4);
            String Ma_DG_muon_max = cs.getString(5);
            String Ten_DG_muon_max = cs.getNString(6);
            String SLSachMuon = cs.getString(7);
            String SLDGMuon = cs.getString(8);
            String sl_sach_max = cs.getString(9);
            String sl_dg_max = cs.getString(10);
            String tl_muon = cs.getString(11);
            Sach_muon_max.setText(Ten_Sach_muon_max);
            Ma_sach.setText(Ma_Sach_muon_max);
            Doc_gia_muon_max.setText(Ten_DG_muon_max);
            Ma_DG.setText(Ma_DG_muon_max);
            SL_DG_max.setText(sl_dg_max);
            SL_sach_max.setText(sl_sach_max);
            SL_DG_muon.setText(SLDGMuon);
            SL_sach_muon.setText(SLSachMuon);
            tong_luot.setText(tl_muon);

            //System.out.println(util.date.convertStringToDate(today));
        } catch (SQLException ex) {
            Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void TK_Thang(ActionEvent event) {
        data.clear();
        Title.setText("Bảng Báo Cáo Thống Kê Mượn Sách Trong Tháng");
        try {
            Date today = new Date();
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.MONTH, -1);
            Date date = new Date(calendar.getTime().getTime());
            java.sql.Date sqltungay = new java.sql.Date(date.getTime());
            java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "SELECT a.*,b.TieuDe,c.HoVaTen FROM dbo.MuonSach a, dbo.Book b,dbo.Doc_Gia c "
                    + "WHERE a.MaSach = b.MaSach AND a.MaDocGia = c.MaDocGia "
                    + "and (a.NgayMuon between '" + sqltungay + "' and '" + sqldenngay + "')";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                String ngaymuon = util.date.convertStringToDate(rs.getDate("NgayMuon"));

                String dealline = util.date.convertStringToDate(rs.getDate("HanTra"));
                String datetra = util.date.convertStringToDate(rs.getDate("NgayTra"));
                data.add(new bangmuonsach(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"), rs.getString("MaDocGia"), rs.getString("HoVaTen"),
                        ngaymuon, dealline, datetra, rs.getString("TinhTrang"), rs.getDate("NgayMuon"),
                        rs.getDate("HanTra"), rs.getDate("NgayTra")));
            }
            st.close();
            BangTK.setItems(data);
            CallableStatement cs = null;
            cs = cn.prepareCall("{call TK_SachMuon(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setDate(1, sqltungay);
            cs.setDate(2, sqldenngay);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.NVARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.NVARCHAR);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.VARCHAR);
            cs.registerOutParameter(11, Types.VARCHAR);
            cs.executeUpdate();
            String Ma_Sach_muon_max = cs.getString(3);
            String Ten_Sach_muon_max = cs.getNString(4);
            String Ma_DG_muon_max = cs.getString(5);
            String Ten_DG_muon_max = cs.getNString(6);
            String SLSachMuon = cs.getString(7);
            String SLDGMuon = cs.getString(8);
            String sl_sach_max = cs.getString(9);
            String sl_dg_max = cs.getString(10);
            String tl_muon = cs.getString(11);
            Sach_muon_max.setText(Ten_Sach_muon_max);
            Ma_sach.setText(Ma_Sach_muon_max);
            Doc_gia_muon_max.setText(Ten_DG_muon_max);
            Ma_DG.setText(Ma_DG_muon_max);
            SL_DG_max.setText(sl_dg_max);
            SL_sach_max.setText(sl_sach_max);
            SL_DG_muon.setText(SLDGMuon);
            SL_sach_muon.setText(SLSachMuon);
            tong_luot.setText(tl_muon);

            //System.out.println(util.date.convertStringToDate(today));
        } catch (SQLException ex) {
            Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TK_qui(ActionEvent event) {
        data.clear();
        Title.setText("Bảng Báo Cáo Thống Kê Mượn Sách Trong 6 Tháng");
        try {
            Date today = new Date();
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.MONTH, -6);
            Date date = new Date(calendar.getTime().getTime());
            java.sql.Date sqltungay = new java.sql.Date(date.getTime());
            java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "SELECT a.*,b.TieuDe,c.HoVaTen FROM dbo.MuonSach a, dbo.Book b,dbo.Doc_Gia c "
                    + "WHERE a.MaSach = b.MaSach AND a.MaDocGia = c.MaDocGia "
                    + "and (a.NgayMuon between '" + sqltungay + "' and '" + sqldenngay + "')";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                String ngaymuon = util.date.convertStringToDate(rs.getDate("NgayMuon"));

                String dealline = util.date.convertStringToDate(rs.getDate("HanTra"));
                String datetra = util.date.convertStringToDate(rs.getDate("NgayTra"));
                data.add(new bangmuonsach(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"), rs.getString("MaDocGia"), rs.getString("HoVaTen"),
                        ngaymuon, dealline, datetra, rs.getString("TinhTrang"), rs.getDate("NgayMuon"),
                        rs.getDate("HanTra"), rs.getDate("NgayTra")));
            }
            st.close();
            BangTK.setItems(data);
            CallableStatement cs = null;
            cs = cn.prepareCall("{call TK_SachMuon(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setDate(1, sqltungay);
            cs.setDate(2, sqldenngay);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.NVARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.NVARCHAR);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.VARCHAR);
            cs.registerOutParameter(11, Types.VARCHAR);
            cs.executeUpdate();
            String Ma_Sach_muon_max = cs.getString(3);
            String Ten_Sach_muon_max = cs.getNString(4);
            String Ma_DG_muon_max = cs.getString(5);
            String Ten_DG_muon_max = cs.getNString(6);
            String SLSachMuon = cs.getString(7);
            String SLDGMuon = cs.getString(8);
            String sl_sach_max = cs.getString(9);
            String sl_dg_max = cs.getString(10);
            String tl_muon = cs.getString(11);
            Sach_muon_max.setText(Ten_Sach_muon_max);
            Ma_sach.setText(Ma_Sach_muon_max);
            Doc_gia_muon_max.setText(Ten_DG_muon_max);
            Ma_DG.setText(Ma_DG_muon_max);
            SL_DG_max.setText(sl_dg_max);
            SL_sach_max.setText(sl_sach_max);
            SL_DG_muon.setText(SLDGMuon);
            SL_sach_muon.setText(SLSachMuon);
            tong_luot.setText(tl_muon);

            //System.out.println(util.date.convertStringToDate(today));
        } catch (SQLException ex) {
            Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TK_nam(ActionEvent event) {
        data.clear();
        Title.setText("Bảng Báo Cáo Thống Kê Mượn Sách Trong Năm");
        try {
            Date today = new Date();
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.YEAR, -1);
            Date date = new Date(calendar.getTime().getTime());
            java.sql.Date sqltungay = new java.sql.Date(date.getTime());
            java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "SELECT a.*,b.TieuDe,c.HoVaTen FROM dbo.MuonSach a, dbo.Book b,dbo.Doc_Gia c "
                    + "WHERE a.MaSach = b.MaSach AND a.MaDocGia = c.MaDocGia "
                    + "and (a.NgayMuon between '" + sqltungay + "' and '" + sqldenngay + "')";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                String ngaymuon = util.date.convertStringToDate(rs.getDate("NgayMuon"));

                String dealline = util.date.convertStringToDate(rs.getDate("HanTra"));
                String datetra = util.date.convertStringToDate(rs.getDate("NgayTra"));
                data.add(new bangmuonsach(data.size() + 1, rs.getString("MaMuon"), rs.getString("MaSach"),
                        rs.getString("TieuDe"), rs.getString("MaDocGia"), rs.getString("HoVaTen"),
                        ngaymuon, dealline, datetra, rs.getString("TinhTrang"), rs.getDate("NgayMuon"),
                        rs.getDate("HanTra"), rs.getDate("NgayTra")));
            }
            st.close();
            BangTK.setItems(data);
            CallableStatement cs = null;
            cs = cn.prepareCall("{call TK_SachMuon(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setDate(1, sqltungay);
            cs.setDate(2, sqldenngay);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.NVARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.NVARCHAR);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.VARCHAR);
            cs.registerOutParameter(11, Types.VARCHAR);
            cs.executeUpdate();
            String Ma_Sach_muon_max = cs.getString(3);
            String Ten_Sach_muon_max = cs.getNString(4);
            String Ma_DG_muon_max = cs.getString(5);
            String Ten_DG_muon_max = cs.getNString(6);
            String SLSachMuon = cs.getString(7);
            String SLDGMuon = cs.getString(8);
            String sl_sach_max = cs.getString(9);
            String sl_dg_max = cs.getString(10);
            String tl_muon = cs.getString(11);
            Sach_muon_max.setText(Ten_Sach_muon_max);
            Ma_sach.setText(Ma_Sach_muon_max);
            Doc_gia_muon_max.setText(Ten_DG_muon_max);
            Ma_DG.setText(Ma_DG_muon_max);
            SL_DG_max.setText(sl_dg_max);
            SL_sach_max.setText(sl_sach_max);
            SL_DG_muon.setText(SLDGMuon);
            SL_sach_muon.setText(SLSachMuon);
            tong_luot.setText(tl_muon);

            //System.out.println(util.date.convertStringToDate(today));
        } catch (SQLException ex) {
            Logger.getLogger(Report_MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
