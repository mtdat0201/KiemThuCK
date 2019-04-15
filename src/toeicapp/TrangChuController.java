/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import Action.Connect;
import Action.ForwardForm;


/**
 * FXML Controller class
 *
 * @author hieut
 */
public class TrangChuController implements Initializable {

    @FXML
    private ImageView btnGrammar;
    @FXML
    private ImageView btnFill;
    @FXML
    private ImageView btnPicture;
    @FXML
    private ImageView btnAudio;
    @FXML
    private MenuItem Diem;
    @FXML
    private MenuItem miClose;
    @FXML
    private MenuItem editUser;
    @FXML
    private MenuItem addQuestion;
    @FXML
    private MenuItem editQuestion;
    @FXML
    private Menu mEdit;
    @FXML
    private Label ngay;
    @FXML
    private Label gio;
    @FXML
    private Label tenUser;

    
    
    private Statement sta;
    private Connection connect;
    private ResultSet resultSet;
    private Stage stage;
    
    static String userName;
    static Integer idCurrent; 
    private String firstName, lastName, idPhanquyen;
    /**
     * Initializes the controller class.
     */
     
    
    // Khai báo định dạng ngày
    public String ngay(){
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("dd MMM yyyy");
        return (sf.format(date));
    } 
    
    private void thoiGian() {
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
            Calendar tg = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            gio.setText(simpleDateFormat.format(tg.getTime()));
        }),
        new KeyFrame(Duration.seconds(1))
    );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    @FXML
    private void menuItemdiem( ActionEvent event){
        ForwardForm.loadStage(getClass().
                getResource("DiemUser.fxml"), "Bảng điểm User", null);
    }
    
    @FXML
    private void menuItemclose( ActionEvent event){
        if (event.getSource() == miClose) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn có chắc muốn thoát ứng dụng không ?");
            alert.setContentText(null);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    exit(0);
                } else {
                    alert.close();
                }
            });      
        } 
    }
    
    @FXML
    private void menuItemaddquestion( ActionEvent event){
        ForwardForm.loadStage(getClass().
                getResource("QuanLyCauHoi.fxml"), "Thêm câu hỏi", null);
    }
    
    @FXML
    private void menuItemedituser( ActionEvent event){
        ForwardForm.loadStage(getClass().
                getResource("EditUser.fxml"), "Cập nhật User", null);
    }
    
    @FXML
    private void menuItemeditquestion( ActionEvent event){
        ForwardForm.loadStage(getClass().
                getResource("EditQuestion.fxml"), "Cập nhật Câu hỏi", null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        thoiGian();
        ngay();
        ngay.setText(ngay());
        connect = Connect.connection();
        try{
            sta = connect.createStatement();
            resultSet = sta.executeQuery("Select * From nguoidung where id_nguoidung='" + idCurrent + "'");
            resultSet.next();
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            idPhanquyen = resultSet.getString("id_phanquyen");
            tenUser.setText("Xin chào : " + firstName + " " + lastName + "   Nhóm: " + idPhanquyen);
            // phan quyen phát triển chức năng thêm
            if ("S".equals(idPhanquyen)) {
//                miSetting.setDisable(true);
                  mEdit.setVisible(false);
            }
//            else if ("T".equals(idPhanquyen))
//            {
//                miSetting.setDisable(true);
//            }          
        }
        catch (SQLException ex) {
            Logger.getLogger(TrangChuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void picAction(MouseEvent event) throws IOException {        
        if (event.getSource() == btnFill) {
            stage = (Stage) btnFill.getScene().getWindow();
            stage.hide();
            ForwardForm ff = new ForwardForm();
            ff.transForm("toeicapp/DienKhuyet.fxml", "Multiple-Choice Grammar");
        } 
        else if (event.getSource() == btnGrammar) {
            stage = (Stage) btnGrammar.getScene().getWindow();
            stage.hide();
            ForwardForm ff = new ForwardForm();
            ff.transForm("toeicapp/MultipleChoiceGrammar.fxml", "Fill Sentences");
        } 
        else if (event.getSource() == btnPicture) {
            stage = (Stage) btnPicture.getScene().getWindow();
            stage.hide();
            ForwardForm ff = new ForwardForm();
            ff.transForm("toeicapp/Picture.fxml", "Picture");
        }
         else if (event.getSource() == btnAudio) {
            stage = (Stage) btnAudio.getScene().getWindow();
            stage.hide();
            ForwardForm ff = new ForwardForm();
            ff.transForm("toeicapp/Audio.fxml", "Question Response");
        }

    }
    
}
