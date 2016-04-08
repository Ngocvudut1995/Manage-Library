/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Bao_Cao;

import UserCase.Person;
import com.sun.rowset.CachedRowSetImpl;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javax.sql.rowset.CachedRowSet;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class Report_DocGiaController implements Initializable {

    @FXML
    private Label lable_themmoi;
    @FXML
    private TableView<DocGia> TBdocgia_moi;
    @FXML
    private Label label_hethan;
    @FXML
    private TableView<DocGia> TBdocgia_hethan;
    @FXML
    private Button bt_tuan;

    public class DocGia {

        private Integer stt;
        private String MaDocGia;

        private String Hovaten;
        private String NgheNghiep;
        private String CoQuan;
        private String SoDt;
        private String diachi;
        private Date ngaysinh;
        private String gioitinh;
        private String email;
        private Date ngaylamthe;
        private Date HanSD;
        private Image Anh;
        private String CMND;

        public String getMaDocGia() {
            return MaDocGia;
        }

        public void setMaDocGia(String MaDocGia) {
            this.MaDocGia = MaDocGia;
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

        public String getSoDt() {
            return SoDt;
        }

        public void setSoDt(String SoDt) {
            this.SoDt = SoDt;
        }

        public String getDiachi() {
            return diachi;
        }

        public void setDiachi(String diachi) {
            this.diachi = diachi;
        }

        public Date getNgaysinh() {
            return ngaysinh;
        }

        public void setNgaysinh(Date ngaysinh) {
            this.ngaysinh = ngaysinh;
        }

        public String getGioitinh() {
            return gioitinh;
        }

        public void setGioitinh(String gioitinh) {
            this.gioitinh = gioitinh;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getHanSD() {
            return HanSD;
        }

        public void setHanSD(Date HanSD) {
            this.HanSD = HanSD;
        }

        public Image getAnh() {
            return Anh;
        }

        public void setAnh(Image Anh) {
            this.Anh = Anh;
        }

        public Integer getStt() {
            return stt;
        }

        public void setStt(Integer stt) {
            this.stt = stt;
        }

        public DocGia(Integer stt, String MaDocGia, String Hovaten, String NgheNghiep,
                String CoQuan, String SoDt, String diachi, Date ngaysinh, String gioitinh,
                String email, Date NgayLT, Date HanSD, Image Anh, String CMND) {
            this.MaDocGia = MaDocGia;
            this.CMND = CMND;
            this.Hovaten = Hovaten;
            this.NgheNghiep = NgheNghiep;
            this.CoQuan = CoQuan;
            this.SoDt = SoDt;
            this.diachi = diachi;
            this.ngaysinh = ngaysinh;
            this.gioitinh = gioitinh;
            this.email = email;
            this.HanSD = HanSD;
            this.Anh = Anh;
            this.stt = stt;
            this.ngaylamthe = NgayLT;
        }

        public Date getNgaylamthe() {
            return ngaylamthe;
        }

        public void setNgaylamthe(Date ngaylamthe) {
            this.ngaylamthe = ngaylamthe;
        }

        public String getCMND() {
            return CMND;
        }

        public void setCMND(String CMND) {
            this.CMND = CMND;
        }

    }
    Connection cn = null;
    ObservableList<DocGia> data_new = FXCollections.observableArrayList();
    ObservableList<DocGia> data_old = FXCollections.observableArrayList();

    @FXML
    private Button bt_docgia_thang;
    @FXML
    private Button bt_docgia_qui;
    @FXML
    private Button bt_docgia_nam;

    @FXML
    public void getDocGia_Tuan(ActionEvent e) {
        data_new.clear();
        data_old.clear();
        lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Thêm Mới Trong Tuần");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn Trong Tuần");
        Date today = new Date();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, -7);
        Date date = new Date(calendar.getTime().getTime());
        java.sql.Date sqltungay = new java.sql.Date(date.getTime());
        java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
        try {

            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "Select * From Doc_Gia Where NgayLamThe between '" + sqltungay + "' and '" + sqldenngay + "'";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                data_new.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str);
            while (rs.next()) {
                data_old.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TBdocgia_moi.setItems(data_new);
        TBdocgia_hethan.setItems(data_old);

    }

    @FXML
    public void getDocGia_Thang(ActionEvent e) {
        data_new.clear();
        data_old.clear();
        lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Thêm Mới Trong Tuần");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn Trong Tuần");
        Date today = new Date();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, -1);
        Date date = new Date(calendar.getTime().getTime());
        java.sql.Date sqltungay = new java.sql.Date(date.getTime());
        java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
        try {

            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "Select * From Doc_Gia Where NgayLamThe between '" + sqltungay + "' and '" + sqldenngay + "'";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                data_new.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str);
            while (rs.next()) {
                data_old.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TBdocgia_moi.setItems(data_new);
        TBdocgia_hethan.setItems(data_old);

    }

    @FXML
    private void getDocGia_Qui(ActionEvent event) {
        data_new.clear();
        data_old.clear();
        lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Thêm Mới Trong Tuần");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn Trong Tuần");
        Date today = new Date();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, -6);
        Date date = new Date(calendar.getTime().getTime());
        java.sql.Date sqltungay = new java.sql.Date(date.getTime());
        java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
        try {

            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "Select * From Doc_Gia Where NgayLamThe between '" + sqltungay + "' and '" + sqldenngay + "'";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                data_new.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str);
            while (rs.next()) {
                data_old.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TBdocgia_moi.setItems(data_new);
        TBdocgia_hethan.setItems(data_old);

    }

    @FXML
    public void getDocGia_Nam(ActionEvent e) {
        data_new.clear();
        data_old.clear();
        lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Thêm Mới Trong Tuần");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn Trong Tuần");
        Date today = new Date();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, -1);
        Date date = new Date(calendar.getTime().getTime());
        java.sql.Date sqltungay = new java.sql.Date(date.getTime());
        java.sql.Date sqldenngay = new java.sql.Date(today.getTime());
        try {

            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "Select * From Doc_Gia Where NgayLamThe between '" + sqltungay + "' and '" + sqldenngay + "'";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                data_new.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str);
            while (rs.next()) {
                data_old.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TBdocgia_moi.setItems(data_new);
        TBdocgia_hethan.setItems(data_old);

    }

    @FXML
    public void focuscol(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
            int i = TBdocgia_moi.getFocusModel().getFocusedIndex();
            System.out.println(i);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Column Doc Gia
            
            TableColumn MaDGCol = new TableColumn("Mã Độc Giả");
            MaDGCol.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
            TBdocgia_moi.getColumns().add(MaDGCol);
            TBdocgia_hethan.getColumns().add(MaDGCol);
            //   MaDGCol.setCellFactory(new );
            //Column CMND
            TableColumn<DocGia, String> CMNDCol = new TableColumn("   CMND   ");
            CMNDCol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
            TBdocgia_moi.getColumns().add(CMNDCol);
            TBdocgia_hethan.getColumns().add(CMNDCol);
            //Column Hovaten
            TableColumn<DocGia, String> TenCol = new TableColumn("    Họ Và Tên   ");
            TenCol.setMinWidth(120);
            TenCol.setCellValueFactory(new PropertyValueFactory<>("Hovaten"));
            TBdocgia_moi.getColumns().add(TenCol);
            TBdocgia_hethan.getColumns().add(TenCol);
            //Column NgheNghiep
            TableColumn<DocGia, String> jobCol = new TableColumn("Nghề Nghiệp");
            jobCol.setMinWidth(120);
            jobCol.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
            TBdocgia_moi.getColumns().add(jobCol);
            TBdocgia_hethan.getColumns().add(jobCol);
            //Column CoQuan
            TableColumn<DocGia, String> coquanCol = new TableColumn("Cơ Quan");
            coquanCol.setMinWidth(120);
            coquanCol.setCellValueFactory(new PropertyValueFactory<>("CoQuan"));
            TBdocgia_moi.getColumns().add(coquanCol);
            TBdocgia_hethan.getColumns().add(coquanCol);
            //Column SoDT
            TableColumn<DocGia, String> sdtCol = new TableColumn("Số Điện Thoại");
            
            sdtCol.setCellValueFactory(new PropertyValueFactory<>("SoDT"));
            TBdocgia_moi.getColumns().add(sdtCol);
            TBdocgia_hethan.getColumns().add(sdtCol);
            //Column DiaChi
            TableColumn<DocGia, String> diachiCol = new TableColumn("Địa Chỉ");
            diachiCol.setMinWidth(120);
            diachiCol.setCellValueFactory(new PropertyValueFactory<>("diachi"));
            TBdocgia_moi.getColumns().add(diachiCol);
            TBdocgia_hethan.getColumns().add(diachiCol);
            //Column NgaySinh
            TableColumn<DocGia, Date> ngaysinhCol = new TableColumn("Ngày Sinh");
            
            ngaysinhCol.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
            TBdocgia_moi.getColumns().add(ngaysinhCol);
            TBdocgia_hethan.getColumns().add(ngaysinhCol);
            //Column GioiTinh
            TableColumn<DocGia, String> gtCol = new TableColumn("Giới Tính");
            
            gtCol.setCellValueFactory(new PropertyValueFactory<>("gioitinh"));
            TBdocgia_moi.getColumns().add(gtCol);
            TBdocgia_hethan.getColumns().add(gtCol);
            //Column Email
            TableColumn<DocGia, Date> emailCol = new TableColumn("Email");
            
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            TBdocgia_moi.getColumns().add(emailCol);
            TBdocgia_hethan.getColumns().add(emailCol);
            //Column HanSD
            TableColumn<DocGia, Date> ngaylamthe = new TableColumn("Ngày Làm Thẻ");
            
            ngaylamthe.setCellValueFactory(new PropertyValueFactory<>("ngaylamthe"));
            TBdocgia_moi.getColumns().add(ngaylamthe);
            TBdocgia_hethan.getColumns().add(ngaylamthe);
            TableColumn<DocGia, Date> hansdCol = new TableColumn("Hạn Sử Dụng");
            hansdCol.setMinWidth(120);
            hansdCol.setCellValueFactory(new PropertyValueFactory<>("HanSD"));
            TBdocgia_moi.getColumns().add(hansdCol);
            TBdocgia_hethan.getColumns().add(hansdCol);
//         //Column Anh
            TableColumn<DocGia, String> imageCol = new TableColumn("Ảnh");
            imageCol.setMinWidth(120);
            imageCol.setCellValueFactory(new PropertyValueFactory<>("Anh"));
            TBdocgia_moi.getColumns().add(imageCol);
            TBdocgia_hethan.getColumns().add(imageCol);
             Date today = new Date();
            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "Select * From Doc_Gia Where HanSD > '"+today+"'";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                data_new.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }
            
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD < '"+today+"'";
            rs = st.executeQuery(str);
            while (rs.next()) {
                data_old.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("Anh"), rs.getString("CMND")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
