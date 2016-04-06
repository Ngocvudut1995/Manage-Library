/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Muon_Tra;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class TrasachController implements Initializable {
    @FXML
    private Button bt_tra;
    @FXML
    private Button bt_huy;
    @FXML
    private Button bt_luu;
    @FXML
    private TableView<?> TB_TraSach;
    @FXML
    private TextField text_MaPM;
    @FXML
    private TextField text_MaDG;
    @FXML
    private Button bt_load_by_PM;
    @FXML
    private Button bt_load_by_DG;
    @FXML
    private TextField ct_theloai;
    @FXML
    private TextField ct_ngonngu;
    @FXML
    private TextField ct_dongia;
    @FXML
    private TextField ct_NXB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void trasach(ActionEvent event) {
    }

    @FXML
    private void Huy_action(ActionEvent event) {
    }

    @FXML
    private void Luu_vao_DB(ActionEvent event) {
    }

    @FXML
    private void focus_click_CT(MouseEvent event) {
    }

    @FXML
    private void load_by_PM(ActionEvent event) {
    }

    @FXML
    private void load_by_DocGia(ActionEvent event) {
    }

    @FXML
    private void ct_masach(ActionEvent event) {
    }

    @FXML
    private void ct_tensach(ActionEvent event) {
    }

    @FXML
    private void ct_tacgia(ActionEvent event) {
    }
    
}
