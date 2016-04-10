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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class TK_DocGiaController implements Initializable {

    @FXML
    private TableView<timDocGia> TB_TimKiemDG;
    @FXML
    private TextField tf_tkmaPhieuMuon;
    @FXML
    private TextField tf_TKMadG;
    @FXML
    private Button btn_TKmaPM;
    @FXML
    private Button btn_TKmadG;
    @FXML
    private TextField tf_madG;
    @FXML
    private TextField tf_tenDG;
    @FXML
    private TextField tf_ngheNghiep;
    @FXML
    private TextField tf_NgaySinh;
    @FXML
    private TextField tf_gt;
    @FXML
    private TextField tf_dc;
    @FXML
    private TextField tf_sdt;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_ngaydK;
    @FXML
    private TextField tf_HanSD;
    ObservableList<timDocGia> data = FXCollections.observableArrayList();
    Connection cn = null;

    @FXML
    private void focus_CT(MouseEvent event) {
        int i = TB_TimKiemDG.getFocusModel().getFocusedIndex();
        timDocGia tdg = data.get(i);
        tf_madG.setText(tdg.getMaDG());
        tf_tenDG.setText(tdg.getTenDG());
        String dateNS = util.date.convertStringToDate(tdg.getNgaySinh());
        tf_NgaySinh.setText(dateNS);
        //tf_cmnd.setText(tdg.getCMND());
        tf_gt.setText(tdg.getGioiTinh());
        tf_dc.setText(tdg.getDiaChi());
        tf_sdt.setText(tdg.getSdt());
        tf_email.setText(tdg.getEmail());
        String dateDK = util.date.convertStringToDate(tdg.getNgayDK());
        tf_ngaydK.setText(dateDK);
        String dateHH = util.date.convertStringToDate(tdg.getNgayHet());
        tf_HanSD.setText(dateHH);
        tf_ngheNghiep.setText(tdg.getNgheNghiep());
    }

    @FXML
    private void timKiemTheoMaPM(ActionEvent event) {
        data.clear();
        cn = util.Connect_JDBC.getConnection();
        String str = "SELECT  a.* FROM dbo.Doc_Gia a,dbo.PhieuMuon b,dbo.MuonSach c WHERE a.MaDocGia=c.MaDocGia AND b.MaMuon=c.MaMuon "
                + "          AND ((b.MaPhieuMuon LIKE '" + tf_tkmaPhieuMuon.getText() + "%') OR (b.MaPhieuMuon LIKE '%" + tf_tkmaPhieuMuon.getText() + "' )OR(b.MaPhieuMuon LIKE'%" + tf_tkmaPhieuMuon.getText() + "%'))";
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new timDocGia(data.size() + 1, rs.getString("MaDocGia"), rs.getNString("HoVaTen"), rs.getDate("NgaySinh"), rs.getNString("NgheNghiep"), rs.getString("SoDT"), rs.getNString("GioiTinh"), rs.getNString("DiaChi"), rs.getString("CMND"), rs.getDate("NgayLamThe"), rs.getDate("HanSD"), rs.getString("Email"), null));
            }
            TB_TimKiemDG.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(TK_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void timKiemTheoMaDG(ActionEvent event) {
        data.clear();
        cn = util.Connect_JDBC.getConnection();
        String str = "SELECT   * FROM dbo.Doc_Gia WHERE MaDocGia LIKE '%" + tf_TKMadG.getText() + "' OR MaDocGia LIKE '" + tf_TKMadG.getText() + "%' OR MaDocGia LIKE '%" + tf_TKMadG.getText() + "%' ";
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new timDocGia(data.size() + 1, rs.getString("MaDocGia"), rs.getNString("HoVaTen"), rs.getDate("NgaySinh"), rs.getNString("NgheNghiep"), rs.getString("SoDT"), rs.getNString("GioiTinh"), rs.getNString("DiaChi"), rs.getString("CMND"), rs.getDate("NgayLamThe"), rs.getDate("HanSD"), rs.getString("Email"), null));
            }
            TB_TimKiemDG.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(TK_DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class timDocGia {

        private int stt;
        private String maDG;
        private String tenDG;
        private Date ngaySinh;
        private String ngheNghiep;
        private String sdt;
        private String gioiTinh;
        private String diaChi;
        private String CMND;
        private Date ngayDK;
        private Date ngayHet;
        private String email;
        private String trangThai;

        public timDocGia(int stt, String maDG, String tenDG, Date ngaySinh, String ngheNghiep, String sdt, String gioiTinh, String diaChi, String CMND, Date ngayDK, Date ngayHet, String email, String trangThai) {
            this.stt = stt;
            this.maDG = maDG;
            this.tenDG = tenDG;
            this.ngaySinh = ngaySinh;
            this.ngheNghiep = ngheNghiep;
            this.sdt = sdt;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
            this.CMND = CMND;
            this.ngayDK = ngayDK;
            this.ngayHet = ngayHet;
            this.email = email;
            this.trangThai = trangThai;
        }

        public int getStt() {
            return stt;
        }

        public void setStt(int stt) {
            this.stt = stt;
        }

        public String getMaDG() {
            return maDG;
        }

        public void setMaDG(String maDG) {
            this.maDG = maDG;
        }

        public String getTenDG() {
            return tenDG;
        }

        public void setTenDG(String tenDG) {
            this.tenDG = tenDG;
        }

        public Date getNgaySinh() {
            return ngaySinh;
        }

        public void setNgaySinh(Date ngaySinh) {
            this.ngaySinh = ngaySinh;
        }

        public String getNgheNghiep() {
            return ngheNghiep;
        }

        public void setNgheNghiep(String ngheNghiep) {
            this.ngheNghiep = ngheNghiep;
        }

        public String getSdt() {
            return sdt;
        }

        public void setSdt(String sdt) {
            this.sdt = sdt;
        }

        public String getGioiTinh() {
            return gioiTinh;
        }

        public void setGioiTinh(String gioiTinh) {
            this.gioiTinh = gioiTinh;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public void setDiaChi(String diaChi) {
            this.diaChi = diaChi;
        }

        public String getCMND() {
            return CMND;
        }

        public void setCMND(String CMND) {
            this.CMND = CMND;
        }

        public Date getNgayDK() {
            return ngayDK;
        }

        public void setNgayDK(Date ngayDK) {
            this.ngayDK = ngayDK;
        }

        public Date getNgayHet() {
            return ngayHet;
        }

        public void setNgayHet(Date ngayHet) {
            this.ngayHet = ngayHet;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(String trangThai) {
            this.trangThai = trangThai;
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<timDocGia, Integer> colstt = new TableColumn<>("STT");
        colstt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        TB_TimKiemDG.getColumns().add(colstt);
        TableColumn<timDocGia, String> maDGcol = new TableColumn<>(" Ma DG ");
        maDGcol.setCellValueFactory(new PropertyValueFactory<>("maDG"));
        TB_TimKiemDG.getColumns().add(maDGcol);

        TableColumn<timDocGia, String> tenDGcol = new TableColumn("   Tên DG   ");
        tenDGcol.setCellValueFactory(new PropertyValueFactory<>("tenDG"));
        TB_TimKiemDG.getColumns().add(tenDGcol);

        TableColumn<timDocGia, String> ngaySinhcol = new TableColumn<>("  Ngày Sinh  ");
        ngaySinhcol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        TB_TimKiemDG.getColumns().add(ngaySinhcol);

        TableColumn<timDocGia, String> ngheNghiepcol = new TableColumn<>("  Nghề Nghiệp  ");
        ngheNghiepcol.setCellValueFactory(new PropertyValueFactory<>("ngheNghiep"));
        TB_TimKiemDG.getColumns().add(ngheNghiepcol);

        TableColumn<timDocGia, String> sdtcol = new TableColumn("     SĐT     ");
        sdtcol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        TB_TimKiemDG.getColumns().add(sdtcol);

        TableColumn<timDocGia, String> gioiTinhcol = new TableColumn<>("   Giới tính   ");
        gioiTinhcol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        TB_TimKiemDG.getColumns().add(gioiTinhcol);

        TableColumn<timDocGia, String> dccol = new TableColumn("     Địa chỉ     ");
        dccol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TB_TimKiemDG.getColumns().add(dccol);

        TableColumn<timDocGia, String> cmndcol = new TableColumn("    Số CMND    ");
        cmndcol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        TB_TimKiemDG.getColumns().add(cmndcol);

        TableColumn<timDocGia, String> ngayDKcol = new TableColumn<>("Ngày đăng kí");
        ngayDKcol.setCellValueFactory(new PropertyValueFactory<>("ngayDK"));
        TB_TimKiemDG.getColumns().add(ngayDKcol);

        TableColumn<timDocGia, String> ngayHetcol = new TableColumn("Ngày hết hạng");
        ngayHetcol.setCellValueFactory(new PropertyValueFactory<>("ngayHet"));
        TB_TimKiemDG.getColumns().add(ngayHetcol);

        TableColumn<timDocGia, String> emailcol = new TableColumn<>("         Email         ");
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TB_TimKiemDG.getColumns().add(emailcol);

        TableColumn<timDocGia, String> trangThaicol = new TableColumn("  Trạng Thái  ");
        trangThaicol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        TB_TimKiemDG.getColumns().add(trangThaicol);
    }

}
