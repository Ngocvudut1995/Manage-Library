/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Index;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button loan;
    @FXML
    private TabPane tabPane;

    @FXML
    private void Muon(ActionEvent event) throws IOException {
        Tab tab = new Tab();
        tab.setText("    Mượn Sách    ");
        Parent root = FXMLLoader.load(getClass().getResource("/demo.fxml"));
//      
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
