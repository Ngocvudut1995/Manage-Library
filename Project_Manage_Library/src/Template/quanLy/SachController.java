/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.quanLy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author TU
 */
public class SachController implements Initializable {
    @FXML
    private TableView<sach> TB_sach;
    public class sach{
        private String maSach;
        private String tenSach;
        private String NXB;
        private String theLoai;
        private String tacGia;
        private String SL;
        private String SLcon;
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
        
        
        
        public sach(String maSach, String tenSach, String NXB, String theLoai, String tacGia, String SL, String SLcon, String ngonNgu,Double gia) {
            this.maSach = maSach;
            this.tenSach = tenSach;
            this.NXB = NXB;
            this.theLoai = theLoai;
            this.tacGia = tacGia;
            this.SL = SL;
            this.SLcon = SLcon;
            this.ngonNgu = ngonNgu;
            this.gia=gia;
        }
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<sach,String> maSachcol=new  TableColumn("Ma Sach");
        maSachcol.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        TB_sach.getColumns().add(maSachcol);
        
        TableColumn<sach,String> tenSachcol=new  TableColumn("Tên Sách");
        tenSachcol.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        TB_sach.getColumns().add(tenSachcol);
        
        TableColumn<sach,String> NXBcol=new  TableColumn("NXB");
        NXBcol.setCellValueFactory(new PropertyValueFactory<>("NXB"));
        TB_sach.getColumns().add(NXBcol);
        
        TableColumn<sach,String> TLcol=new  TableColumn("Thể Loại");
        TLcol.setCellValueFactory(new PropertyValueFactory<>("theLoai"));
        TB_sach.getColumns().add(TLcol);
        
        TableColumn<sach,String> TGcol=new  TableColumn("Tác Giả");
        TGcol.setCellValueFactory(new PropertyValueFactory<>("tacGia"));
        TB_sach.getColumns().add(TGcol);
        
        TableColumn<sach,String> SLcol=new  TableColumn("Số Lượng");
        SLcol.setCellValueFactory(new PropertyValueFactory<>("SL"));
        TB_sach.getColumns().add(SLcol);
        
        TableColumn<sach,String> SLconcol=new  TableColumn("Số LƯợng còn");
        SLconcol.setCellValueFactory(new PropertyValueFactory<>("SLcon"));
        TB_sach.getColumns().add(SLconcol);
        
        TableColumn<sach,String> NNcol=new  TableColumn("Ngôn Ngữ");
         NNcol.setCellValueFactory(new PropertyValueFactory<>("ngonNgu"));
        TB_sach.getColumns().add(NNcol);
        
        TableColumn<sach,Double> giacol=new  TableColumn("Giá");
        giacol.setCellValueFactory(new PropertyValueFactory<>("gia"));
        TB_sach.getColumns().add(giacol);
            
        ObservableList<sach> data=FXCollections.observableArrayList();
        data.add(new sach("MHS2", "VU", "Sinh Viên", "ĐH Bách Khoa Đà Nẵng", null, null, null, null, null));
        TB_sach.setItems(data);
    }    
    
    
    
}
