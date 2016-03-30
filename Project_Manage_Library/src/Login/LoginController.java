/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class LoginController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private TextField textuser;
    @FXML
    private PasswordField textpass;
    @FXML
    private Button bt_login;
    @FXML
    private Label tb;
    @FXML
    ActionEvent e;

    @FXML
    public void Enter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            tb.setText("");
            String user = textuser.getText();
            String pass = textpass.getText();
            if (user.equals("admin") && (pass.equals("admin"))) {
                Parent root = FXMLLoader.load(getClass().getResource("/Index/index.fxml"));
                Scene scene = new Scene(root);
                stage = new Stage();
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                ((Node) (ke.getSource())).getScene().getWindow().hide();
                
                stage.show();
                
            } else {
                tb.setText("Username or Password wrong!");
            }
        }

    }
    
    
    @FXML
    public void Login(ActionEvent event) throws IOException {
        tb.setText("");
        String user = textuser.getText();
        String pass = textpass.getText();
        if (user.equals("admin") && (pass.equals("admin"))) {
            Parent root = FXMLLoader.load(getClass().getResource("/Index/index.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
           ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.show();
        } else {
            tb.setText("Username or Password wrong!");
        }

    }

    @FXML
    private void Cancel(ActionEvent event) {
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
