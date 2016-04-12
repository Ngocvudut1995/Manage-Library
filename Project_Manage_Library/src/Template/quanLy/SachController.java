/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    private TextField tf_TG;
    @FXML
    private TextField tf_SL;
    @FXML
    private TextField tf_SLcon;

    @FXML
    private TextField tf_gia;

    @FXML
    private Button btn_luu;
    ObservableList<sach> data = FXCollections.observableArrayList();
    @FXML
    private ComboBox<?> cb_tl;
    @FXML
    private ComboBox<?> cb_ngonngu;
    @FXML
    private ComboBox<?> cb_thoigianmuon;

    @FXML
    private void focus_CTsach(MouseEvent event) {

        int i = TB_sach.getFocusModel().getFocusedIndex();
        sach s = data.get(i);
        tf_maSach.setText(s.getMaSach());
        tf_tenSach.setText(s.getTenSach());
        cb_thoigianmuon.getSelectionModel().select(s.getStt_tgmuon());
        cb_tl.getSelectionModel().select(s.getStt_tl() - 1);
        tf_NXB.setText(s.getNXB());
        tf_TG.setText(s.getTacGia());
        tf_SL.setText(s.getSL());
        tf_SLcon.setText((s.getSLcon()));
        cb_ngonngu.getSelectionModel().select(s.getStt_nn() - 1);
        tf_gia.setText(s.gia.toString());
        btn_luu.setDisable(true);
    }

    @FXML
    private int newSave(ActionEvent event) {
        boolean test = true;
        
            
        
        if (tf_tenSach.getText().equals("")) {
            test = false;
            tf_tenSach.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_tenSach.setStyle("-fx-border-width:0px;");
        }
        if (tf_NXB.getText().equals("")) {
            test = false;
            tf_NXB.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_NXB.setStyle("-fx-border-width:0px;");
        }
        if (tf_SL.getText().equals("")) {
            test = false;
            tf_SL.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_SL.setStyle("-fx-border-width:0px;");
        }
        if (tf_SLcon.getText().equals("")) {
            test = false;
            tf_SLcon.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_SLcon.setStyle("-fx-border-width:0px;");
        }
        if (tf_TG.getText().equals("")) {
            test = false;
            tf_TG.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_TG.setStyle("-fx-border-width:0px;");
        }
        if (tf_gia.getText().equals("")) {
            test = false;
            tf_gia.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            tf_gia.setStyle("-fx-border-width:0px;");
        }
        if(cb_ngonngu.getSelectionModel().getSelectedIndex()<0){
            test = false;
            cb_ngonngu.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            cb_ngonngu.setStyle(" -fx-border-width: 0px ;");
        }
        if(cb_thoigianmuon.getSelectionModel().getSelectedIndex()<0){
            test = false;
            cb_thoigianmuon.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            cb_thoigianmuon.setStyle(" -fx-border-width: 0px ;");
        }
        if(cb_tl.getSelectionModel().getSelectedIndex()<0){
            test = false;
            cb_tl.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
            cb_tl.setStyle(" -fx-border-width: 0px ;");
        }
        if(Integer.parseInt(tf_SLcon.getText())>Integer.parseInt(tf_SL.getText())){
         test = false;
        //  tf_SL.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
          tf_SLcon.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else{
        //    tf_SL.setStyle(" -fx-border-width: 0px ;");
            tf_SLcon.setStyle(" -fx-border-width: 0px ;");
            
        }
        if (test == true) {

            try {
                cn = util.Connect_JDBC.getConnection();
                CallableStatement cs = null;
                if (tf_maSach.getText().equals("")) {
                    cs = cn.prepareCall("{call Insert_Sach(?,?,?,?,?,?,?,?,?)}");
                    cs.setNString(1, tf_tenSach.getText());
                    cs.setNString(2, tf_TG.getText());
                    cs.setInt(3, Integer.parseInt(tf_SL.getText()));
                    cs.setInt(4, Integer.parseInt(tf_SLcon.getText()));
                    cs.setDouble(5, Double.parseDouble(tf_gia.getText()));
                    cs.setInt(6, cb_tl.getSelectionModel().getSelectedIndex() + 1);
                    cs.setInt(7, cb_ngonngu.getSelectionModel().getSelectedIndex() + 1);
                    cs.setNString(8, tf_NXB.getText());
                    cs.setInt(9, cb_thoigianmuon.getSelectionModel().getSelectedIndex() + 1);
                    cs.executeUpdate();

                } else {
                    cs = cn.prepareCall("{call Update_Sach(?,?,?,?,?,?,?,?,?,?)}");
                    cs.setNString(1, tf_tenSach.getText());
                    cs.setNString(2, tf_TG.getText());
                    cs.setInt(3, Integer.parseInt(tf_SL.getText()));
                    cs.setInt(4, Integer.parseInt(tf_SLcon.getText()));
                    cs.setDouble(5, Double.parseDouble(tf_gia.getText()));
                    cs.setInt(6, cb_tl.getSelectionModel().getSelectedIndex() + 1);
                    cs.setInt(7, cb_ngonngu.getSelectionModel().getSelectedIndex() + 1);
                    cs.setNString(8, tf_NXB.getText());
                    cs.setInt(9, cb_thoigianmuon.getSelectionModel().getSelectedIndex() + 1);
                    cs.setString(10, tf_maSach.getText());
                    cs.executeUpdate();
                }
                data.clear();
                //Load lai database
                loaddata();
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông Báo");
        alert.setHeaderText("Bạn cần nhập đầy đủ thông tin");
      //  System.out.println("Loi!");
        alert.showAndWait();
        return 1;

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
                String str = "Update Book Set TongSL = -1 where MaSach = ?";

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

    @FXML
    private void gochu(KeyEvent event) {
        
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
        private int stt_tl;
        private int stt_nn;
        private int stt_tgmuon;

        public int getStt_tgmuon() {
            return stt_tgmuon;
        }

        public void setStt_tgmuon(int stt_tgmuon) {
            this.stt_tgmuon = stt_tgmuon;
        }

        public int getStt_tl() {
            return stt_tl;
        }

        public void setStt_tl(int stt_tl) {
            this.stt_tl = stt_tl;
        }

        public int getStt_nn() {
            return stt_nn;
        }

        public void setStt_nn(int stt_nn) {
            this.stt_nn = stt_nn;
        }

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

        public sach(String maSach, String tenSach, String NXB, String theLoai, String tacGia, String SL, String SLcon, String MaLS, String ngonNgu, Double gia, int stt_tl, int stt_nn, int stt_tgmuon) {
            this.maSach = maSach;
            this.tenSach = tenSach;
            this.NXB = NXB;
            this.theLoai = theLoai;
            this.tacGia = tacGia;
            this.SL = SL;
            this.SLcon = SLcon;
            this.MaLS = MaLS;
            this.ngonNgu = ngonNgu;
            this.gia = gia;
            this.stt_tl = stt_tl;
            this.stt_nn = stt_nn;
            this.stt_tgmuon = stt_tgmuon;
        }

    }
    Connection cn = null;
    Statement st = null;

    public void loaddata() {
        //  cn = util.Connect_JDBC.getConnection();
        try {

            st = cn.createStatement();
            ResultSet crs = st.executeQuery("SELECT a.MaSach,a.TieuDe,a.MaLoaiSach,c.TenNXB,d.TenTheLoai,b.TenTacGia,a.TongSL,a.SLhientai,e.TenNgonNgu,a.Gia,d.stt_tl,e.stt_nn,f.stt_loai FROM dbo.Book a,"
                    + "dbo.TacGia b,dbo.NhaXB c,dbo.TheLoai d,dbo.NgonNgu e ,dbo.LoaiSach f\n"
                    + "WHERE a.MaLoaiSach = f.MaLoaiSach and a.MaTheLoai=d.MaTheLoai "
                    + "AND a.MaTacGia=b.MaTacGia AND a.MaNgonNgu=e.MaNgonNgu "
                    + "AND a.MaNXB=c.MaNXB and a.TongSL >=0");
            while (crs.next()) {
                data.add(new sach(crs.getString("MaSach"), crs.getString("TieuDe"), crs.getString("TenNXB"), crs.getString("TenTheLoai"),
                        crs.getString("TenTacGia"), crs.getString("TongSL"), crs.getString("SLhientai"),
                        crs.getString("MaLoaiSach"), crs.getString("TenNgonNgu"),
                        crs.getDouble("Gia"), crs.getInt("stt_tl"), crs.getInt("stt_nn"), crs.getInt("stt_loai")));
            }
            // crs.acceptChanges();
            crs.close();

        } catch (SQLException ex) {
            Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList data_thmuon = FXCollections.observableArrayList("1 tháng", "3 tháng", "6 tháng", "9 tháng");
        cb_thoigianmuon.setItems(data_thmuon);
        ObservableList cursors = FXCollections.observableArrayList();
        cn = util.Connect_JDBC.getConnection();

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("Select TenTheLoai From TheLoai");
            while (rs.next()) {
                cursors.add(rs.getNString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_tl.setItems(cursors);
        ObservableList data_ngonngu = FXCollections.observableArrayList();
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("Select TenNgonNgu From NgonNgu");
            while (rs.next()) {
                data_ngonngu.add(rs.getNString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_ngonngu.setItems(data_ngonngu);
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

        TableColumn<sach, Integer> SLcol = new TableColumn("Số Lượng");
        SLcol.setCellValueFactory(new PropertyValueFactory<>("SL"));
        TB_sach.getColumns().add(SLcol);

        TableColumn<sach, Integer> SLconcol = new TableColumn("Số Lượng còn");
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
        loaddata();

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
        cb_tl.getSelectionModel().select(-1);
        cb_ngonngu.getSelectionModel().select(-1);
        cb_thoigianmuon.getSelectionModel().select(-1);
        tf_TG.setText("");
        tf_SL.setText("");
        tf_SLcon.setText("");
        tf_gia.setText("");
        btn_luu.setDisable(false);

    }

}
