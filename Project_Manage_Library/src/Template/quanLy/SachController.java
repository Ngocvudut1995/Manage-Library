/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import com.sun.rowset.CachedRowSetImpl;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.sql.rowset.CachedRowSet;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class SachController implements Initializable {

    @FXML
    private TableView<sach> TB_sach;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_xoa;
    @FXML
    private Button btn_them;
    @FXML
    private TextField tf_maSach;
    @FXML
    private TextField tf_tenSach;
    @FXML
    private TextField tf_NXB;
    @FXML
    private TextField tf_TL;
    @FXML
    private TextField tf_TG;
    @FXML
    private TextField tf_SL;
    @FXML
    private TextField tf_SLcon;
    @FXML
    private TextField tf_ngonNgu;
    @FXML
    private TextField tf_gia;
    @FXML
    private Button btn_luu;
    ObservableList<sach> data = FXCollections.observableArrayList();

    @FXML
    private void focus_CTsach(MouseEvent event) {

        int i = TB_sach.getFocusModel().getFocusedIndex();
        sach s = data.get(i);
        tf_maSach.setText(s.getMaSach());
        tf_tenSach.setText(s.getTenSach());
        tf_TL.setText(s.getTheLoai());
        tf_NXB.setText(s.getNXB());
        tf_TG.setText(s.getTacGia());
        tf_SL.setText(s.getSL());
        tf_SLcon.setText((s.getSLcon()));
        tf_ngonNgu.setText(s.getNgonNgu());
        tf_gia.setText(s.gia.toString());
        btn_luu.setDisable(true);
    }

    @FXML
    private int newSave(ActionEvent event) {
        if ((tf_SL.getText()).equals("") || (tf_tenSach.getText()).equals("") || (tf_gia.getText()).equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
            System.out.println("Loi!");
            alert.showAndWait();
            return 1;
        } else {
            try {
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                if(tf_maSach.getText().equals("")){
                    
                    //them zo nge :v 
                
                
                }
                String str = "UPDATE Book SET TieuDe = ? , Gia= ? , SLhientai = ? WHERE MaSach=? ";
                ps = cn.prepareStatement(str);
                ps.setNString(1, tf_tenSach.getText());
                ps.setDouble(2, Double.parseDouble(tf_gia.getText()));
                ps.setInt(3, Integer.parseInt(tf_SLcon.getText()));
                ps.setString(4, tf_maSach.getText());
                ps.executeUpdate();
                data.clear();
                Statement st = null;
                st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT a.MaSach,a.TieuDe,a.MaLoaiSach,c.TenNXB,d.TenTheLoai,b.TenTacGia,a.TongSL,a.SLhientai,e.TenNgonNgu,a.Gia FROM dbo.Book a,dbo.TacGia b,dbo.NhaXB c,dbo.TheLoai d,dbo.NgonNgu e \n"
                        + "WHERE a.MaTheLoai=d.MaTheLoai AND a.MaTacGia=b.MaTacGia AND a.MaNgonNgu=e.MaNgonNgu AND a.MaNXB=c.MaNXB");
                while (rs.next()) {
                    data.add(new sach(rs.getString("MaSach"), rs.getString("TieuDe"), rs.getString("TenNXB"), rs.getString("TenTheLoai"),
                            rs.getString("TenTacGia"), rs.getString("TongSL"), rs.getString("SLhientai"), rs.getString("MaLoaiSach"), rs.getString("TenNgonNgu"), rs.getDouble("Gia")));
                }
                TB_sach.setItems(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText("Lưu thành công");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
    }

    @FXML
    private void xoa(ActionEvent event) {
        int i = TB_sach.getFocusModel().getFocusedIndex();
        sach s = data.get(i);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa ?");
        Boolean yes = alert.showAndWait().isPresent();
        if (yes) {

            try {
                data.remove(i);
                cn = util.Connect_JDBC.getConnection();
                PreparedStatement ps = null;
                String str = "DELETE FROM Book WHERE MaSach= ?";

                ps = cn.prepareStatement(str);

                ps.setString(1, s.getMaSach());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        tf_maSach.setDisable(true);
        
        btn_luu.setDisable(false);
    }

    public class sach {

        private String maSach;
        private String tenSach;
        private String NXB;
        private String theLoai;
        private String tacGia;
        private String SL;
        private String SLcon;
        private String MaLS;
        private String ngonNgu;
        private Double gia;

        public String getMaSach() {
            return maSach;
        }

        public void setMaSach(String maSach) {
            this.maSach = maSach;
        }

        public String getTenSach() {
            return tenSach;
        }

        public void setTenSach(String tenSach) {
            this.tenSach = tenSach;
        }

        public String getNXB() {
            return NXB;
        }

        public void setNXB(String NXB) {
            this.NXB = NXB;
        }

        public String getTheLoai() {
            return theLoai;
        }

        public void setTheLoai(String theLoai) {
            this.theLoai = theLoai;
        }

        public String getTacGia() {
            return tacGia;
        }

        public void setTacGia(String tacGia) {
            this.tacGia = tacGia;
        }

        public String getMaLS() {
            return MaLS;
        }

        public void setMaLS(String MaLS) {
            this.MaLS = MaLS;
        }

        public String getSL() {
            return SL;
        }

        public void setSL(String SL) {
            this.SL = SL;
        }

        public String getSLcon() {
            return SLcon;
        }

        public void setSLcon(String SLcon) {
            this.SLcon = SLcon;
        }

        public String getNgonNgu() {
            return ngonNgu;
        }

        public void setNgonNgu(String ngonNgu) {
            this.ngonNgu = ngonNgu;
        }

        public Double getGia() {
            return gia;
        }

        public void setGia(Double gia) {
            this.gia = gia;
        }

        public sach(String maSach, String tenSach, String NXB, String theLoai, String tacGia, String SL, String SLcon, String maLS, String ngonNgu, Double gia) {
            this.maSach = maSach;
            this.tenSach = tenSach;
            this.NXB = NXB;
            this.theLoai = theLoai;
            this.tacGia = tacGia;
            this.SL = SL;
            this.SLcon = SLcon;
            this.MaLS = maLS;
            this.ngonNgu = ngonNgu;
            this.gia = gia;
        }

    }
    Connection cn = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<sach, String> maSachcol = new TableColumn("Ma Sach");
        maSachcol.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        TB_sach.getColumns().add(maSachcol);

        TableColumn<sach, String> tenSachcol = new TableColumn("Tên Sách");
        tenSachcol.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        TB_sach.getColumns().add(tenSachcol);

        TableColumn<sach, String> NXBcol = new TableColumn("NXB");
        NXBcol.setCellValueFactory(new PropertyValueFactory<>("NXB"));
        TB_sach.getColumns().add(NXBcol);

        TableColumn<sach, String> TLcol = new TableColumn("Thể Loại");
        TLcol.setCellValueFactory(new PropertyValueFactory<>("theLoai"));
        TB_sach.getColumns().add(TLcol);

        TableColumn<sach, String> TGcol = new TableColumn("Tác Giả");
        TGcol.setCellValueFactory(new PropertyValueFactory<>("tacGia"));
        TB_sach.getColumns().add(TGcol);

        TableColumn<sach, String> SLcol = new TableColumn("Số Lượng");
        SLcol.setCellValueFactory(new PropertyValueFactory<>("SL"));
        TB_sach.getColumns().add(SLcol);

        TableColumn<sach, String> SLconcol = new TableColumn("Số LƯợng còn");
        SLconcol.setCellValueFactory(new PropertyValueFactory<>("SLcon"));
        TB_sach.getColumns().add(SLconcol);

        TableColumn<sach, String> maLScol = new TableColumn("Ma loai sach");
        maLScol.setCellValueFactory(new PropertyValueFactory<>("MaLS"));
        TB_sach.getColumns().add(maLScol);

        TableColumn<sach, String> NNcol = new TableColumn("Ngôn Ngữ");
        NNcol.setCellValueFactory(new PropertyValueFactory<>("ngonNgu"));
        TB_sach.getColumns().add(NNcol);

        TableColumn<sach, Double> giacol = new TableColumn("Giá");
        giacol.setCellValueFactory(new PropertyValueFactory<>("gia"));
        TB_sach.getColumns().add(giacol);

        try {

            cn = util.Connect_JDBC.getConnection();
            Statement st = null;
            st = cn.createStatement();
            ResultSet crs = st.executeQuery("SELECT a.MaSach,a.TieuDe,a.MaLoaiSach,c.TenNXB,d.TenTheLoai,b.TenTacGia,a.TongSL,a.SLhientai,e.TenNgonNgu,a.Gia FROM dbo.Book a,dbo.TacGia b,dbo.NhaXB c,dbo.TheLoai d,dbo.NgonNgu e \n"
                    + "WHERE a.MaTheLoai=d.MaTheLoai AND a.MaTacGia=b.MaTacGia AND a.MaNgonNgu=e.MaNgonNgu AND a.MaNXB=c.MaNXB");
            while (crs.next()) {
                data.add(new sach(crs.getString("MaSach"), crs.getString("TieuDe"), crs.getString("TenNXB"), crs.getString("TenTheLoai"), crs.getString("TenTacGia"), crs.getString("TongSL"), crs.getString("SLhientai"), crs.getString("MaLoaiSach"), crs.getString("TenNgonNgu"), crs.getDouble("Gia")));
            }
            // crs.acceptChanges();
            crs.close();

        } catch (SQLException ex) {
            Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //data.add(new sach("MHS2", "VU", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null));
        TB_sach.setItems(data);
    }

    @FXML
    public void themSach(ActionEvent e) {

        // tf_maSach.setDisable(false);
        tf_maSach.setDisable(true);
        tf_maSach.setText("");
        tf_tenSach.setText("");
        tf_NXB.setText("");
        tf_TL.setText("");
        tf_TG.setText("");
        tf_ngonNgu.setText("");
        tf_SL.setText("");
        tf_SLcon.setText("");
        tf_gia.setText("");
        btn_luu.setDisable(false);

    }

}
