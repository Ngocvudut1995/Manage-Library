/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Tra_Cuu;

import Index.IndexController;
import Template.Bao_Cao.Report_MuonSachController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class TK_SachController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox<?> tracuu;
    @FXML
    private TextField tukhoa;
    @FXML
    private Button search;
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
    private TextField ct_nhaXB;
    @FXML
    private TableView<tksach> TB_Sach;

    @FXML
    private void focus_ct(MouseEvent event) {
        int i = TB_Sach.getFocusModel().getFocusedIndex();
        tksach tk = data.get(i);
        ct_masach.setText(tk.getMasach());
        ct_dongia.setText(tk.getGia().toString());
        ct_ngonngu.setText(tk.getTenngonngu());
        ct_nhaXB.setText(tk.getTennxb());
        ct_tacgia.setText(tk.getTentacgia());
        ct_theloai.setText(tk.getTheloai());
        ct_tensach.setText(tk.getTensach());
        
    }

    public class tksach {

        private Integer stt;
        private String masach;
        private String tensach;
        private String theloai;
        private String tentacgia;
        private String tenngonngu;
        private String tennxb;
        private Double gia;
        private Integer soht;
        private Image hinhanh;

        public Integer getStt() {
            return stt;
        }

        public void setStt(Integer stt) {
            this.stt = stt;
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

        public String getTheloai() {
            return theloai;
        }

        public void setTheloai(String theloai) {
            this.theloai = theloai;
        }

        public String getTentacgia() {
            return tentacgia;
        }

        public void setTentacgia(String tentacgia) {
            this.tentacgia = tentacgia;
        }

        public String getTenngonngu() {
            return tenngonngu;
        }

        public void setTenngonngu(String tenngonngu) {
            this.tenngonngu = tenngonngu;
        }

        public String getTennxb() {
            return tennxb;
        }

        public void setTennxb(String tennxb) {
            this.tennxb = tennxb;
        }

        public Double getGia() {
            return gia;
        }

        public void setGia(Double gia) {
            this.gia = gia;
        }

        public Integer getSoht() {
            return soht;
        }

        public void setSoht(Integer soht) {
            this.soht = soht;
        }

        public Image getHinhanh() {
            return hinhanh;
        }

        public void setHinhanh(Image hinhanh) {
            this.hinhanh = hinhanh;
        }

        public tksach() {

        }

        public tksach(Integer stt, String masach, String tensach, String theloai, String tentacgia, String tenngonngu, String tennxb, Double gia, Integer soht) {
            this.stt = stt;
            this.masach = masach;
            this.tensach = tensach;
            this.theloai = theloai;
            this.tentacgia = tentacgia;
            this.tenngonngu = tenngonngu;
            this.tennxb = tennxb;
            this.gia = gia;
            this.soht = soht;
        }
    }
    ObservableList<tksach> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll("              abc              ","               ccb                ");
        ObservableList cursors = FXCollections.observableArrayList("Mã Sách",
                "Tên Sách", "Thể Loại", "Ngôn Ngữ", "Nhà Xuất Bản"
        );
        //  search.setOpacity(0);
        tracuu.setItems(cursors);
        TableColumn<tksach, Integer> colstt = new TableColumn<>("STT");
        colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        TB_Sach.getColumns().add(colstt);
        TableColumn<tksach, String> masach = new TableColumn<>("Mã Sách");
        masach.setCellValueFactory(new PropertyValueFactory<>("masach"));
        TB_Sach.getColumns().add(masach);
        TableColumn<tksach, String> tensach = new TableColumn<>("Tiêu Đề");
        tensach.setCellValueFactory(new PropertyValueFactory<>("tensach"));
        tensach.setMinWidth(120);
        TB_Sach.getColumns().add(tensach);
        
        TableColumn<tksach, String> theloai = new TableColumn<>("Thể Loại");
        theloai.setCellValueFactory(new PropertyValueFactory<>("theloai"));
        TB_Sach.getColumns().add(theloai);
        TableColumn<tksach, String> tacgia = new TableColumn<>("Tác Giả");
        tacgia.setCellValueFactory(new PropertyValueFactory<>("tentacgia"));
        TB_Sach.getColumns().add(tacgia);
        TableColumn<tksach, String> ngonngu = new TableColumn<>("Ngôn Ngữ");
        ngonngu.setCellValueFactory(new PropertyValueFactory<>("tenngonngu"));
        TB_Sach.getColumns().add(ngonngu);
        TableColumn<tksach, String> tennxb = new TableColumn<>("Nhà Xuất Bản");
        tennxb.setCellValueFactory(new PropertyValueFactory<>("tennxb"));
        TB_Sach.getColumns().add(tennxb);
        TableColumn<tksach, String> gia = new TableColumn<>("Giá ");
        gia.setCellValueFactory(new PropertyValueFactory<>("gia"));
        TB_Sach.getColumns().add(gia);
        TableColumn<tksach, String> slht = new TableColumn<>("Số Lượng Còn");
        slht.setCellValueFactory(new PropertyValueFactory<>("soht"));
        TB_Sach.getColumns().add(slht);

        //  tracuu.setSelectionModel();
        System.out.println(tracuu.getSelectionModel().getSelectedItem());
        tracuu.getSelectionModel().getSelectedItem();

    }

    private void demo(MouseEvent event) {
        //  System.out.println("asdasd");
    }
    Connection cn = null;

    @FXML
    private void search_theoyeucau(ActionEvent event) {
        int i = tracuu.getSelectionModel().getSelectedIndex();
        data.clear();
        cn = util.Connect_JDBC.getConnection();
        String tk = tukhoa.getText();
        String str;
        PreparedStatement st = null;
        //    System.out.println(tk);
        switch (i) {
            case 0:

                str = "SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,a.SLhientai,f.TenNXB,a.Hinhanh "
                        + "FROM dbo.Book a,dbo.TheLoai b,dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                        + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                        + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB \n"
                        + "AND ((dbo.fChuyenCoDauThanhKhongDau(MaSach)  LIKE '" + tk + "%')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(MaSach)  LIKE '%" + tk + "')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(MaSach)  LIKE '%" + tk + "%'))";
                 {
                    try {
                        st = cn.prepareStatement(str);
                        //  st.setString(1, tk);
                        //   st.setString(2, tk);
                        //   st.setString(3, tk);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            data.add(new tksach(data.size() + 1, rs.getString("MaSach"),
                                    rs.getNString("TieuDe"), rs.getNString("TenTheLoai"), rs.getNString("TenTacGia"), rs.getString("TenNgonNgu"), rs.getNString("TenNXB"),
                                    rs.getDouble("Gia"), rs.getInt("SLhientai")));
                        }
                        TB_Sach.setItems(data);
                    } catch (SQLException ex) {
                        Logger.getLogger(TK_SachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 1:
            case -1:   
                str = "SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,a.SLhientai,f.TenNXB,a.Hinhanh \n"
                        + "FROM dbo.Book a,dbo.TheLoai b,dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                        + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                        + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB \n"
                        + "AND ((dbo.fChuyenCoDauThanhKhongDau(a.TieuDe)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(a.TieuDe)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(a.TieuDe)  LIKE '%"+tk+"%') \n"
                        + "		OR a.TieuDe LIKE N'%"+tk+"' OR a.TieuDe LIKE N'"+tk+"%' OR a.TieuDe LIKE N'%"+tk+"%' )";
                 {
                    try {
                        st = cn.prepareStatement(str);
                        //  st.setString(1, tk);
                        //   st.setString(2, tk);
                        //   st.setString(3, tk);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            data.add(new tksach(data.size() + 1, rs.getString("MaSach"),
                                    rs.getNString("TieuDe"), rs.getNString("TenTheLoai"), rs.getNString("TenTacGia"), rs.getString("TenNgonNgu"), rs.getNString("TenNXB"),
                                    rs.getDouble("Gia"), rs.getInt("SLhientai")));
                        }
                        TB_Sach.setItems(data);
                    } catch (SQLException ex) {
                        Logger.getLogger(TK_SachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 2:
                 str = "SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,a.SLhientai,f.TenNXB,a.Hinhanh \n"
                        + "FROM dbo.Book a,dbo.TheLoai b,dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                        + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                        + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB \n"
                        + "AND ((dbo.fChuyenCoDauThanhKhongDau(b.TenTheLoai)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(b.TenTheLoai)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(b.TenTheLoai)  LIKE '%"+tk+"%') \n"
                        + "		OR b.TenTheLoai LIKE N'%"+tk+"' OR b.TenTheLoai LIKE N'"+tk+"%' "
                         + "OR b.TenTheLoai LIKE N'%"+tk+"%' )";
                 {
                    try {
                        st = cn.prepareStatement(str);
                        //  st.setString(1, tk);
                        //   st.setString(2, tk);
                        //   st.setString(3, tk);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            data.add(new tksach(data.size() + 1, rs.getString("MaSach"),
                                    rs.getNString("TieuDe"), rs.getNString("TenTheLoai"), rs.getNString("TenTacGia"), rs.getString("TenNgonNgu"), rs.getNString("TenNXB"),
                                    rs.getDouble("Gia"), rs.getInt("SLhientai")));
                        }
                        TB_Sach.setItems(data);
                    } catch (SQLException ex) {
                        Logger.getLogger(TK_SachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 3:
                str = "SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,a.SLhientai,f.TenNXB,a.Hinhanh \n"
                        + "FROM dbo.Book a,dbo.TheLoai b,dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                        + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                        + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB \n"
                        + "AND ((dbo.fChuyenCoDauThanhKhongDau(d.TenNgonNgu)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(d.TenNgonNgu)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(d.TenNgonNgu)  LIKE '%"+tk+"%') \n"
                        + "		OR d.TenNgonNgu LIKE N'%"+tk+"' OR d.TenNgonNgu LIKE N'"+tk+"%' "
                         + "OR d.TenNgonNgu LIKE N'%"+tk+"%' )";
                 {
                    try {
                        st = cn.prepareStatement(str);
                        //  st.setString(1, tk);
                        //   st.setString(2, tk);
                        //   st.setString(3, tk);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            data.add(new tksach(data.size() + 1, rs.getString("MaSach"),
                                    rs.getNString("TieuDe"), rs.getNString("TenTheLoai"), rs.getNString("TenTacGia"), rs.getString("TenNgonNgu"), rs.getNString("TenNXB"),
                                    rs.getDouble("Gia"), rs.getInt("SLhientai")));
                        }
                        TB_Sach.setItems(data);
                    } catch (SQLException ex) {
                        Logger.getLogger(TK_SachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 4:
                  str = "SELECT a.MaSach,a.TieuDe,b.TenTheLoai,c.TenTacGia,d.TenNgonNgu,a.Gia,a.SLhientai,f.TenNXB,a.Hinhanh \n"
                        + "FROM dbo.Book a,dbo.TheLoai b,dbo.TacGia c,dbo.NgonNgu d,dbo.LoaiSach e,dbo.NhaXB f\n"
                        + "WHERE (a.MaTheLoai =b.MaTheLoai AND a.MaTacGia = c.MaTacGia) and (a.MaNgonNgu = d.MaNgonNgu) \n"
                        + "AND a.MaLoaiSach = e.MaLoaiSach AND a.MaNXB = f.MaNXB \n"
                        + "AND ((dbo.fChuyenCoDauThanhKhongDau(f.TenNXB)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(f.TenNXB)  LIKE '%"+tk+"')\n"
                        + "		OR (dbo.fChuyenCoDauThanhKhongDau(f.TenNXB)  LIKE '%"+tk+"%') \n"
                        + "		OR f.TenNXB LIKE N'%"+tk+"' OR f.TenNXB LIKE N'"+tk+"%' "
                         + "OR f.TenNXB LIKE N'%"+tk+"%' )";
                 {
                    try {
                        st = cn.prepareStatement(str);
                        //  st.setString(1, tk);
                        //   st.setString(2, tk);
                        //   st.setString(3, tk);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            data.add(new tksach(data.size() + 1, rs.getString("MaSach"),
                                    rs.getNString("TieuDe"), rs.getNString("TenTheLoai"), rs.getNString("TenTacGia"), rs.getString("TenNgonNgu"), rs.getNString("TenNXB"),
                                    rs.getDouble("Gia"), rs.getInt("SLhientai")));
                        }
                        TB_Sach.setItems(data);
                    } catch (SQLException ex) {
                        Logger.getLogger(TK_SachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                break;
        }
        System.out.println(tracuu.getSelectionModel().getSelectedIndex());
    }

}
