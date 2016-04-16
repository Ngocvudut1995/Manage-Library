/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Bao_Cao;

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
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str2);
            while (rs.next()) {
                data_old.add(new DocGia(data_old.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
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
        lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Thêm Mới Trong Tháng");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn Trong Tháng");
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
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str2);
            while (rs.next()) {
                data_old.add(new DocGia(data_old.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
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
        lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Thêm Mới Trong Quí");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn Trong Quí");
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
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str2);
            while (rs.next()) {
                data_old.add(new DocGia(data_old.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
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
        lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Thêm Mới Trong Năm");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn Trong Năm");
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
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
            }

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str2 = "Select * From Doc_Gia Where HanSD between '" + sqltungay + "' and '" + sqldenngay + "'";
            rs = st.executeQuery(str2);
            while (rs.next()) {
                data_old.add(new DocGia(data_old.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TBdocgia_moi.setItems(data_new);
        TBdocgia_hethan.setItems(data_old);

    }

    public void focuscol(MouseEvent e) {
//        if (e.getButton() == MouseButton.SECONDARY) {
//            int i = TBdocgia_moi.getFocusModel().getFocusedIndex();
//            System.out.println(i);
//        }

    }
    
    /**
     * Initializes the controller class.
     */
    Statement st = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Column Doc Gia
             lable_themmoi.setText("Bảng Báo Cáo Thống Kê Bạn Đọc");
        label_hethan.setText("Bảng Báo Cáo Thống Kê Bạn Đọc Hết Hạn");
            TableColumn MaDGCol = new TableColumn("Mã Độc Giả");
            MaDGCol.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
            TBdocgia_moi.getColumns().add(MaDGCol);
           
            //   MaDGCol.setCellFactory(new );
            //Column CMND
            TableColumn<DocGia, String> CMNDCol = new TableColumn("   CMND   ");
            CMNDCol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
            TBdocgia_moi.getColumns().add(CMNDCol);
           
            //Column Hovaten
            TableColumn<DocGia, String> TenCol = new TableColumn("    Họ Và Tên   ");
            TenCol.setMinWidth(120);
            TenCol.setCellValueFactory(new PropertyValueFactory<>("Hovaten"));
            TBdocgia_moi.getColumns().add(TenCol);
          
            //Column NgheNghiep
            TableColumn<DocGia, String> jobCol = new TableColumn("Nghề Nghiệp");
            jobCol.setMinWidth(120);
            jobCol.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
            TBdocgia_moi.getColumns().add(jobCol);
         
           
            TableColumn<DocGia, String> sdtCol = new TableColumn("Số Điện Thoại");
            
            sdtCol.setCellValueFactory(new PropertyValueFactory<>("SoDT"));
            TBdocgia_moi.getColumns().add(sdtCol);
            
            //Column DiaChi
            TableColumn<DocGia, String> diachiCol = new TableColumn("Địa Chỉ");
            diachiCol.setMinWidth(120);
            diachiCol.setCellValueFactory(new PropertyValueFactory<>("diachi"));
            TBdocgia_moi.getColumns().add(diachiCol);
           
            //Column NgaySinh
            TableColumn<DocGia, Date> ngaysinhCol = new TableColumn("Ngày Sinh");
            
            ngaysinhCol.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
            TBdocgia_moi.getColumns().add(ngaysinhCol);
         
            //Column GioiTinh
            TableColumn<DocGia, String> gtCol = new TableColumn("Giới Tính");
            
            gtCol.setCellValueFactory(new PropertyValueFactory<>("gioitinh"));
            TBdocgia_moi.getColumns().add(gtCol);
          
            //Column Email
            TableColumn<DocGia, Date> emailCol = new TableColumn("Email");
            
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            TBdocgia_moi.getColumns().add(emailCol);
          
            //Column HanSD
            TableColumn<DocGia, Date> ngaylamthe = new TableColumn("Ngày Làm Thẻ");
            
            ngaylamthe.setCellValueFactory(new PropertyValueFactory<>("ngaylamthe"));
            TBdocgia_moi.getColumns().add(ngaylamthe);
            
            TableColumn<DocGia, Date> hansdCol = new TableColumn("Hạn Sử Dụng");
            hansdCol.setMinWidth(120);
            hansdCol.setCellValueFactory(new PropertyValueFactory<>("HanSD"));
            TBdocgia_moi.getColumns().add(hansdCol);
           
//         //Column Anh
//            TableColumn<DocGia, String> imageCol = new TableColumn("Ảnh");
//            imageCol.setMinWidth(120);
//            imageCol.setCellValueFactory(new PropertyValueFactory<>("Anh"));
//            TBdocgia_moi.getColumns().add(imageCol);
//            TBdocgia_hethan.getColumns().add(imageCol);
            
             TableColumn MaDGCol2 = new TableColumn("Mã Độc Giả");
            MaDGCol2.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
            TBdocgia_hethan.getColumns().add(MaDGCol2);
           
            //   MaDGCol.setCellFactory(new );
            //Column CMND
            TableColumn<DocGia, String> CMNDCol2 = new TableColumn("   CMND   ");
            CMNDCol2.setCellValueFactory(new PropertyValueFactory<>("CMND"));
            TBdocgia_hethan.getColumns().add(CMNDCol2);
           
            //Column Hovaten
            TableColumn<DocGia, String> TenCol2 = new TableColumn("    Họ Và Tên   ");
            TenCol2.setMinWidth(120);
            TenCol2.setCellValueFactory(new PropertyValueFactory<>("Hovaten"));
            TBdocgia_hethan.getColumns().add(TenCol2);
          
            //Column NgheNghiep
            TableColumn<DocGia, String> jobCol2 = new TableColumn("Nghề Nghiệp");
            jobCol2.setMinWidth(120);
            jobCol2.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
            TBdocgia_hethan.getColumns().add(jobCol2);
         
           
            TableColumn<DocGia, String> sdtCol2 = new TableColumn("Số Điện Thoại");
            
            sdtCol2.setCellValueFactory(new PropertyValueFactory<>("SoDT"));
            TBdocgia_hethan.getColumns().add(sdtCol2);
            
            //Column DiaChi
            TableColumn<DocGia, String> diachiCol2 = new TableColumn("Địa Chỉ");
            diachiCol2.setMinWidth(120);
            diachiCol2.setCellValueFactory(new PropertyValueFactory<>("diachi"));
            TBdocgia_hethan.getColumns().add(diachiCol2);
           
            //Column NgaySinh
            TableColumn<DocGia, Date> ngaysinhCol2 = new TableColumn("Ngày Sinh");
            
            ngaysinhCol2.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
            TBdocgia_hethan.getColumns().add(ngaysinhCol2);
         
            //Column GioiTinh
            TableColumn<DocGia, String> gtCol2 = new TableColumn("Giới Tính");
            
            gtCol2.setCellValueFactory(new PropertyValueFactory<>("gioitinh"));
            TBdocgia_hethan.getColumns().add(gtCol2);
          
            //Column Email
            TableColumn<DocGia, Date> emailCol2 = new TableColumn("Email");
            
            emailCol2.setCellValueFactory(new PropertyValueFactory<>("email"));
            TBdocgia_hethan.getColumns().add(emailCol2);
          
            //Column HanSD
            TableColumn<DocGia, Date> ngaylamthe2 = new TableColumn("Ngày Làm Thẻ");
            
            ngaylamthe2.setCellValueFactory(new PropertyValueFactory<>("ngaylamthe"));
            TBdocgia_hethan.getColumns().add(ngaylamthe2);
            
            TableColumn<DocGia, Date> hansdCol2 = new TableColumn("Hạn Sử Dụng");
            hansdCol2.setMinWidth(120);
            hansdCol2.setCellValueFactory(new PropertyValueFactory<>("HanSD"));
            TBdocgia_hethan.getColumns().add(hansdCol2);
             Date today = new Date();
             java.sql.Date hansd= new java.sql.Date(today.getTime());
            cn = util.Connect_JDBC.getConnection();
            
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String str = "Select * From Doc_Gia Where HanSD > GETDATE()";
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                data_new.add(new DocGia(data_new.size() + 1, rs.getString("MaDocGia"), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11),
                        (Image) rs.getBlob("AnhThe"), rs.getString("CMND")));
            }
            rs.close();
            st.close();
            TBdocgia_moi.setItems(data_new);
          //  st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
              //      ResultSet.CONCUR_UPDATABLE);
            cn = util.Connect_JDBC.getConnection();
            Statement st2 = cn.createStatement();
          //  st= cn.createStatement();
            String str2 = "Select * From Doc_Gia Where HanSD < GETDATE()";
            ResultSet rs2 = st2.executeQuery(str2);
            while (rs2.next()) {
                data_old.add(new DocGia(data_old.size() + 1, rs2.getString("MaDocGia"), rs2.getString(2),
                        rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getDate(7),
                        rs2.getString(8), rs2.getString(9), rs2.getDate(10), rs2.getDate(11),
                        (Image) rs2.getBlob("AnhThe"), rs2.getString("CMND")));
            }
            TBdocgia_hethan.setItems(data_old);
        } catch (SQLException ex) {
            Logger.getLogger(Report_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
