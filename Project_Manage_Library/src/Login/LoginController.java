/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

    private Stage stage;
    @FXML
    private TextField textuser;
    @FXML
    private PasswordField textpass;
    @FXML
    private Button bt_login;
    @FXML
    private Label tb;
    ActionEvent e;
    Connection cn = null;
    Statement stmt = null;
    ResultSet rs = null;
    // protected String pass;
    static public String MaNhanVien;
    @FXML
    private CheckBox check_mk;

    @FXML
    public int Enter(KeyEvent ke) throws IOException, SQLException {
        if(!(textpass.getText().equals("")||textuser.getText().equals("")))
        check_mk.setDisable(false);
        else{
            check_mk.setDisable(true);
        }
        cn = util.Connect_JDBC.getConnection();
        tb.setText("");
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String user = textuser.getText();
            String pass = textpass.getText();
            stmt = cn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM Member");
            while (rs.next()) {
                String salt = "VuDang";
                String securepass = util.Encode.getSecurePassword(pass, salt);
                if (user.equals(rs.getString("Username")) && securepass.equals(rs.getString("Password"))) {
                    MaNhanVien = rs.getString("MaNV");
                    Parent root = FXMLLoader.load(getClass().getResource("/Index/index.fxml"));
                    Scene scene = new Scene(root);
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    ((Node) (ke.getSource())).getScene().getWindow().hide();

                    PrintWriter pw = null;
                    if (check_mk.isSelected()) {
                        System.out.println("asdasd");

                        pw = new PrintWriter("save.dat");
                        pw.println("check" + ";" + textuser.getText() + ";" + textpass.getText());
                        pw.close();
                    } else {
                        System.out.println("asdasdas1");
                        pw = new PrintWriter("save.dat");
                        pw.println("no_check" + ";" + textuser.getText() + ";" + textpass.getText());
                        pw.close();
                    }
                    stage.show();
                    return 1;
                }
            }
            tb.setText("Username or Password wrong!");
        }

        return 0;

    }

    @FXML
    public int Login(ActionEvent event) throws IOException, SQLException {
        //  tb.setText("");
         cn = util.Connect_JDBC.getConnection();
        String user = textuser.getText();
        String pass = textpass.getText();
        stmt = cn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("SELECT * FROM Member");
        while (rs.next()) {
            String salt = "VuDang";
            String securepass = util.Encode.getSecurePassword(pass, salt);
            if (user.equals(rs.getString("Username")) && securepass.equals(rs.getString("Password"))) {
                MaNhanVien = rs.getString("MaNV");
                Parent root = FXMLLoader.load(getClass().getResource("/Index/index.fxml"));
                Scene scene = new Scene(root);
                stage = new Stage();
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                ((Node) (event.getSource())).getScene().getWindow().hide();

                stage.show();
                return 1;
            }
        }
        tb.setText("Username or Password wrong!");
        return 0;

    }
    @FXML
    private Button cancel;

    @FXML
    public void Cancel(ActionEvent event) {
        stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
          
        BufferedReader bf = null;
        System.out.println("asdasd");
        try {
            // TODO

            bf = new BufferedReader(new InputStreamReader(new FileInputStream("save.dat")));
            String line = bf.readLine();
            String[] split;
            split = line.split(";");
            System.out.println("2124");
            if (split[0].equalsIgnoreCase("check")) {
                System.out.println("check");
                check_mk.setSelected(true);
                textuser.setText(split[1]);
                textpass.setText(split[2]);
            } else {
                System.out.println("no_check");
                check_mk.setSelected(false);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bf.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!(textpass.getText().equals("")||textuser.getText().equals("")))
             check_mk.setDisable(false);
          else{
              check_mk.setDisable(true);
          }
    }

    @FXML
    private void save_mk(ActionEvent event) throws FileNotFoundException {
        
        PrintWriter pw = null;
        if (check_mk.isSelected()&&!textuser.equals("")&&!textpass.equals("")) {
            try {
                System.out.println("Check");
                
                pw = new PrintWriter("save.dat");
                pw.println("check" + ";" + textuser.getText() + ";" + textpass.getText());
                pw.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No_check");
            pw = new PrintWriter("save.dat");
            pw.println("no_check" + ";" + textuser.getText() + ";" + textpass.getText());
            pw.close();
        }
    }

}
