/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *
 * @author Angel
 */
public class Message {
    public static void thongTin(Alert.AlertType type,String infoMessage, String title, String header) {
        Alert ale = new Alert(type);
        ale.setTitle(title);
        ale.setHeaderText(header);
        ale.setContentText(infoMessage);
        ale.showAndWait();
    }
    
    public static void baoLoi (Alert.AlertType type,String errorMessage, String title, String header) {
        Alert ale = new Alert(type);
        ale.setTitle(title);
        ale.setHeaderText(header);
        ale.setContentText(errorMessage);
        ale.showAndWait();
    }
    
    public static Optional<ButtonType> xacNhan (Alert.AlertType type,String infoMessage, String title, String header) {
        Alert ale = new Alert(type);
        ale.setTitle(title);
        ale.setHeaderText(header);
        ale.setContentText(infoMessage);
        return ale.showAndWait();
    }
}
