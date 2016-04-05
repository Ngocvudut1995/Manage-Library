/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template.Tra_Cuu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vu Dang
 */
public class TK_SachController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ChoiceBox<String> tracuu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll("              abc              ","               ccb                ");
        ObservableList cursors = FXCollections.observableArrayList(
                Cursor.DEFAULT,
                Cursor.CROSSHAIR,
                Cursor.WAIT,
                Cursor.TEXT,
                Cursor.HAND,
                Cursor.MOVE,
                Cursor.N_RESIZE,
                Cursor.NE_RESIZE,
                Cursor.E_RESIZE,
                Cursor.SE_RESIZE,
                Cursor.S_RESIZE,
                Cursor.SW_RESIZE,
                Cursor.W_RESIZE,
                Cursor.NW_RESIZE,
                Cursor.NONE
        );
        
        tracuu.setItems(cursors);

    }

    @FXML
    private void demo(MouseEvent event) {
        System.out.println("asdasd");
    }

}
