/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import Template.Bao_Cao.Report_VPController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private Button bt_them;
    @FXML
    private Button bt_huy;
    @FXML
    private Button bt_luu;

    ObservableList<viphamkhac> data_load = FXCollections.observableArrayList();
    ObservableList<viphamkhac> data_luu_DB = FXCollections.observableArrayList();
    Connection cn = null;
    @FXML
    private Button bt_ok;
    @FXML
    private Button bt_cancel;
    @FXML
    private Button bt_xuly;
    @FXML
    private TextField ngay_vi_pham;
    @FXML
    private TextField text_maDG;

    @FXML
    private void hien_nut_ok(KeyEvent event) {
        bt_ok.setVisible(true);
        bt_ok.setDisable(false);
        bt_cancel.setVisible(true);

    }

    @FXML
    private void xu_ly_vp(ActionEvent event) {
        int i = table_VP.getFocusModel().getFocusedIndex();
        data_luu_DB.add(data_load.get(i));
        data_load.remove(i);
        table_VP.setItems(data_load);

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
        ct_maDG.setEditable(false);
        ht_xu_ly.setEditable(false);
        Ly_do.setEditable(false);
        ngay_vi_pham.setEditable(false);
        ten_DG.setEditable(false);
        text_maDG.setEditable(false);
        table_VP.setEditable(true);
        
        TableColumn<viphamkhac, Integer> stt = new TableColumn<>("STT");
        stt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        table_VP.getColumns().add(stt);
        TableColumn<viphamkhac, String> maVP = new TableColumn<>("Mã Vi Phạm");
        maVP.setCellValueFactory(new PropertyValueFactory<>("Mavp"));
        maVP.setCellFactory(TextFieldTableCell.forTableColumn());
        table_VP.getColumns().add(maVP);
        TableColumn<viphamkhac, String> madocgia = new TableColumn<>("Mã Đọc Giả");
        madocgia.setCellValueFactory(new PropertyValueFactory<>("madocgia"));
        madocgia.setCellFactory(TextFieldTableCell.forTableColumn());
        table_VP.getColumns().add(madocgia);
        TableColumn<viphamkhac, Date> ngayvipham = new TableColumn<>("Ngày Vi Phạm");
        ngayvipham.setCellValueFactory(new PropertyValueFactory<>("ngayvipham"));
        table_VP.getColumns().add(ngayvipham);
        
        TableColumn<viphamkhac, String> lydo = new TableColumn<>("  Lý Do  ");
        lydo.setCellValueFactory(new PropertyValueFactory<>("lydo"));
        lydo.setCellFactory(TextFieldTableCell.forTableColumn());
        table_VP.getColumns().add(lydo);
        TableColumn<viphamkhac, String> htxuly = new TableColumn<>("Hình Thức Xử Lý");
        htxuly.setCellValueFactory(new PropertyValueFactory<>("htxuly"));
        htxuly.setCellFactory(TextFieldTableCell.forTableColumn());
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
            String str2 = "SELECT a.*,b.HoVaTen FROM dbo.ViPham a,dbo.Doc_Gia b"
                    + " WHERE a.MaDocGia = b.MaDocGia  and NgayXuLy is null";
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

    
    private int Load_data() {
        
        try {
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs2 = null;
            String str2 = "SELECT a.*,b.HoVaTen FROM dbo.ViPham a,dbo.Doc_Gia b"
                    + " WHERE a.MaDocGia = b.MaDocGia  and NgayXuLy is null and "
                    + "(a.MaDocGia like '%" + text_maDG.getText() + "' "
                    + "or a.MaDocGia like '%" + text_maDG.getText() + "%' "
                    + "or a.MaDocGia like '" + text_maDG.getText() + "%' )";
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
        return 0;
    }

    @FXML
    private void focus_ct(MouseEvent event) {
        bt_ok.setVisible(false);
        bt_cancel.setVisible(false);
        int i = table_VP.getFocusModel().getFocusedIndex();
        viphamkhac vp = data_load.get(i);

        ct_maDG.setText(vp.getMadocgia());
        ten_DG.setText(vp.getTendocgia());
        Ly_do.setText(vp.getLydo());
        ht_xu_ly.setText(vp.getHtxuly());
        String ngvipham = util.date.convertStringToDate(vp.getNgayvipham());
        ngay_vi_pham.setText(ngvipham);
    }

    @FXML
    private void them_sach(ActionEvent event) {
        bt_ok.setVisible(true);
        ct_maDG.setEditable(true);
        ht_xu_ly.setEditable(true);
        Ly_do.setEditable(true);
        ngay_vi_pham.setEditable(true);
        ten_DG.setEditable(true);
        text_maDG.setEditable(true);
        bt_ok.setDisable(true);
        ct_maDG.setText("");
        ten_DG.setText("");
        Ly_do.setText("");
        ht_xu_ly.setText("");
        Date today = new Date();

        ngay_vi_pham.setText(util.date.convertStringToDate(today));
    }

    @FXML
    private void huy_toan_bo(ActionEvent event) {
        data_load.clear();
       ct_maDG.setEditable(false);
        ht_xu_ly.setEditable(false);
        Ly_do.setEditable(false);
        ngay_vi_pham.setEditable(false);
        ten_DG.setEditable(false);
        text_maDG.setEditable(false);
        ngay_vi_pham.setText("");
        if (text_maDG.getText().equals("")) {
            load_bandau();
        } else {
            Load_data();
        }

    }

    @FXML
    private void luu_vaoDB(ActionEvent event) {
        ct_maDG.setEditable(false);
        ht_xu_ly.setEditable(false);
        Ly_do.setEditable(false);
        ngay_vi_pham.setEditable(false);
        ten_DG.setEditable(false);
        text_maDG.setEditable(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông Báo");
        alert.setHeaderText("Bạn Chắc Chắn Lưu Dữ Liệu ");
        //alert.setContentText("Thêm Thành Công!");
        alert.showAndWait();
        ButtonType result = alert.getResult();
        if (result.getText().equals("OK")) {
            for (int i = 0; i < data_luu_DB.size(); i++) {
                try {
                    PreparedStatement ps = null;
                    ps = cn.prepareStatement("UPDATE dbo.ViPham SET NgayXuLy = GETDATE() WHERE MaVP = ?");
                    ps.setString(1, data_luu_DB.get(i).getMavp());
                    ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ViphamController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            huy_toan_bo(event);
        }
    }

    @FXML
    private void luu_moi(ActionEvent event) throws ParseException {
        try {
            PreparedStatement ps = null;
            ps = cn.prepareStatement("Insert Into ViPham Values (?,?,?,?,?,?) ");
            ps.setString(1, "VP");
            ps.setString(2, ct_maDG.getText());
            ps.setNString(3, Ly_do.getText());
            ps.setNString(4, ht_xu_ly.getText());

            ps.setDate(5, null);

            Date today = new Date();
            java.sql.Date ngayvp = new java.sql.Date(today.getTime());
            ps.setDate(6, ngayvp);
            ps.executeUpdate();
            them_sach(event);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Thêm Vi Phạm Thành Công");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
            load_bandau();
        } catch (SQLException ex) {
            Logger.getLogger(ViphamController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Thông Tin Không Đầy Đủ");
            //alert.setContentText("Thêm Thành Công!");
            alert.showAndWait();
        }
    }
}
