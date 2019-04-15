/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Action.Connect;
import Action.ForwardForm;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class KetQuaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
 
    @FXML
    private Label lbKetqua;
    @FXML
    private Label lbTen;
        
    private Connection connect;
    private Statement sta;
    private ResultSet resultSet;
    @FXML
    private Text kq;
    @FXML
    private JFXButton btTrangchu;
    
    @FXML
    private ImageView img;
    @Override
    public void initialize(URL loc, ResourceBundle rb) {
        connect = Connect.connection();
        String firstname;
        String lastname;
        String diem;
        try {
            sta = connect.createStatement();
            resultSet = sta.executeQuery("select u.* , p.diem "
                    + "from diem p , nguoidung u "
                    + "where p.user_name= u.user_name and "
                    + "u.id_nguoidung='" + TrangChuController.idCurrent + "' "
                            + "order by p.id_diem DESC limit 1");

            resultSet.next();
            firstname = resultSet.getString("u.first_name");
            lastname = resultSet.getString("u.last_name");
            diem = resultSet.getString("p.diem");
            lbTen.setText("Chúc mừng bạn! : " + firstname + " " + lastname);
            lbKetqua.setText("Số câu đúng : " + diem);
            // load gif
            String stringName;
            if (Integer.valueOf(diem) < 5) {
                stringName = "good";
                String bad="Điểm thấp cần cố gắng lên";
                String url = String.format("Picture/Icon/%s.gif", stringName);
                Image i = new Image(url); 
                i.isBackgroundLoading();
                kq.setText(bad);
                img.setImage(i);

            } else {
                stringName = "good";
                String good="Điểm tốt đấy cố gắng duy trì nhé";
                String url = String.format("Picture/Icon/%s.gif", stringName);
                Image i = new Image(url);
                kq.setText(good);
                img.setImage(i);
            }

        } catch (SQLException ex) {
            Logger.getLogger(KetQuaController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }   

        @FXML
        private void handleButtonAction(ActionEvent event) throws IOException {
            if (event.getSource() == btTrangchu) {
                stage = (Stage) btTrangchu.getScene().getWindow();
                stage.hide();
                ForwardForm ff = new ForwardForm();
                ff.transForm("toeicapp/TrangChu.fxml",
                        "Trang Chủ");
            }
        }

}
