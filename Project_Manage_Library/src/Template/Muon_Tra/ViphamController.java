/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import Template.Bao_Cao.Report_VPController;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class ViphamController implements Initializable {

    @FXML
    private Button bt_search;
    @FXML
    private DatePicker text_tungay;
    @FXML
    private DatePicker text_denngay;
    @FXML
    private TableView<viphamkhac> table_VP;
    @FXML
    private TextField ct_maDG;
    @FXML
    private TextField ten_DG;
    @FXML
    private TextField Ly_do;
    @FXML
    private TextField ht_xu_ly;
    @FXML
    private TextField ngay_xu_ly;
    @FXML
    private Button bt_them;
    @FXML
    private Button bt_huy;
    @FXML
    private Button bt_luu;
    @FXML
    private Button bt_them1;

    ObservableList<viphamkhac> data_load = FXCollections.observableArrayList();
    Connection cn = null;
    @FXML
    private Button bt_ok;
    @FXML
    private Button bt_cancel;

    @FXML
    private void hien_nut_ok(KeyEvent event) {
        bt_ok.setVisible(true);
        bt_cancel.setVisible(true);

    }

    public class viphamkhac {

        private Integer stt;
        private String Mavp;
        private String madocgia;
        private String lydo;
        private String htxuly;
        private Date ngayxuly;
        private Date ngayvipham;
        private String tendocgia;

        public String getTendocgia() {
            return tendocgia;
        }

        public void setTendocgia(String tendocgia) {
            this.tendocgia = tendocgia;
        }

        public String getMavp() {
            return Mavp;
        }

        public void setMavp(String Mavp) {
            this.Mavp = Mavp;
        }

        public String getMadocgia() {
            return madocgia;
        }

        public void setMadocgia(String madocgia) {
            this.madocgia = madocgia;
        }

        public String getLydo() {
            return lydo;
        }

        public void setLydo(String lydo) {
            this.lydo = lydo;
        }

        public String getHtxuly() {
            return htxuly;
        }

        public Integer getStt() {
            return stt;
        }

        public void setStt(Integer stt) {
            this.stt = stt;
        }

        public void setHtxuly(String htxuly) {
            this.htxuly = htxuly;
        }

        public Date getNgayxuly() {
            return ngayxuly;
        }

        public void setNgayxuly(Date ngayxuly) {
            this.ngayxuly = ngayxuly;
        }

        public Date getNgayvipham() {
            return ngayvipham;
        }

        public void setNgayvipham(Date ngayvipham) {
            this.ngayvipham = ngayvipham;
        }

        public viphamkhac(Integer stt, String Mavp, String madocgia, String lydo, String htxuly, Date ngayxuly, Date ngayvipham, String tendocgia) {
            this.Mavp = Mavp;
            this.stt = stt;
            this.madocgia = madocgia;
            this.lydo = lydo;
            this.htxuly = htxuly;
            this.ngayxuly = ngayxuly;
            this.ngayvipham = ngayvipham;
            this.tendocgia = tendocgia;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<viphamkhac, Integer> stt = new TableColumn<>("STT");
        stt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        table_VP.getColumns().add(stt);
        TableColumn<viphamkhac, String> maVP = new TableColumn<>("Mã Vi Phạm");
        maVP.setCellValueFactory(new PropertyValueFactory<>("Mavp"));
        table_VP.getColumns().add(maVP);
        TableColumn<viphamkhac, String> madocgia = new TableColumn<>("Mã Đọc Giả");
        madocgia.setCellValueFactory(new PropertyValueFactory<>("madocgia"));
        table_VP.getColumns().add(madocgia);
        TableColumn<viphamkhac, Date> ngayvipham = new TableColumn<>("Ngày Vi Phạm");
        ngayvipham.setCellValueFactory(new PropertyValueFactory<>("ngayvipham"));
        table_VP.getColumns().add(ngayvipham);
        TableColumn<viphamkhac, String> lydo = new TableColumn<>("  Lý Do  ");
        lydo.setCellValueFactory(new PropertyValueFactory<>("lydo"));
        table_VP.getColumns().add(lydo);
        TableColumn<viphamkhac, String> htxuly = new TableColumn<>("Hình Thức Xử Lý");
        htxuly.setCellValueFactory(new PropertyValueFactory<>("htxuly"));
        table_VP.getColumns().add(htxuly);
        TableColumn<viphamkhac, Date> ngayxuly = new TableColumn<>("Ngày Xử Lý");
        ngayxuly.setCellValueFactory(new PropertyValueFactory<>("ngayxuly"));
        table_VP.getColumns().add(ngayxuly);
        load_bandau();

    }

    private void load_bandau() {
        cn = util.Connect_JDBC.getConnection();
        Statement st = null;
        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs2 = null;
            String str2 = "SELECT a.*,b.HoVaTen FROM dbo.ViPham a,dbo.Doc_Gia b WHERE a.MaDocGia = b.MaDocGia ";
            rs2 = st.executeQuery(str2);
            while (rs2.next()) {
                data_load.add(new viphamkhac(data_load.size() + 1, rs2.getString("MaVP"),
                        rs2.getString("MaDocGia"), rs2.getNString("LyDo"),
                        rs2.getNString("HinhThucXuLy"), rs2.getDate("NgayXuLy"), rs2.getDate("NgayViPham"),
                        rs2.getNString("HoVaten")));
            }
            table_VP.setItems(data_load);

        } catch (SQLException ex) {
            Logger.getLogger(Report_VPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Load_data(ActionEvent event) {
    }

    @FXML
    private void focus_ct(MouseEvent event) {
        int i = table_VP.getFocusModel().getFocusedIndex();
        viphamkhac vp = data_load.get(i);

        ct_maDG.setText(vp.getMadocgia());
        ten_DG.setText(vp.getTendocgia());
        Ly_do.setText(vp.getLydo());
        ht_xu_ly.setText(vp.getHtxuly());
        String ngxuly = util.date.convertStringToDate(vp.getNgayxuly());
        ngay_xu_ly.setText(ngxuly);
    }

    @FXML
    private void them_sach(ActionEvent event) {
        bt_ok.setVisible(true);

        bt_ok.setDisable(true);
        ct_maDG.setText("");
        ten_DG.setText("");
        Ly_do.setText("");
        ht_xu_ly.setText("");
        ngay_xu_ly.setText("");
    }

    @FXML
    private void huy_toan_bo(ActionEvent event) {
        data_load.clear();
        LocalDate tungay = text_tungay.getValue();

        LocalDate denngay = text_denngay.getValue();
        if (!tungay.equals(null) && !denngay.equals(null)) {
            load_bandau();
        } else {
            Load_data(event);
        }

    }

    @FXML
    private void luu_vaoDB(ActionEvent event) {
    }

    @FXML
    private void luu_moi(ActionEvent event) {
        
    }
}
