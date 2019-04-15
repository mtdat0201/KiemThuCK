/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import Action.Connect;
import Action.Message;
import Action.ForwardForm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class AudioController implements Initializable {
    @FXML
    private Text cauSo;
    @FXML
    private Text tongCau;
    @FXML
    private Text diem;
    @FXML
    private Text noiDung;
    @FXML
    private JFXButton btnKetqua;
    @FXML
    private JFXButton btnCauketiep;
    @FXML
    private JFXButton btnExit;    
    private Statement sta;
    private Connection connect;
    private ResultSet resultSet;
    private String audioURL;
    private AudioClip audioClip;
    private String number, cauTraloihientai;// so cau

    private Integer diemHientai, cauHientai; //diem hien tai
    private Stage stage;
    @FXML
    private JFXRadioButton radA;
    @FXML
    private JFXRadioButton radB;
    @FXML
    private JFXRadioButton radC;
    @FXML
    private JFXRadioButton radD;
    @FXML
    private ToggleGroup chon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cauHientai = 0;
        diemHientai = 0;
        btnKetqua.setDisable(true);
        connect = Connect.connection();
        try {
            sta = connect.createStatement();
            String sqlCount = "Select count(*) From cauhoi a, theloai b "
                    + "where a.id_theloai=b.id_theloai "
                    + "and b.name_theloai='Question Response' Limit 10 ";
            resultSet = sta.executeQuery(sqlCount);
            resultSet.next();
            number = resultSet.getString(1);
            tongCau.setText(number);
            resultSet.close();
            String sql = "Select * from cauhoi a, theloai b "
                    + "where a.id_theloai=b.id_theloai "
                    + "and b.name_theloai='Question Response' and audio is not null order by rand() Limit 10 ";
            resultSet = sta.executeQuery(sql);
            next();               
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DienKhuyetController.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    private void next() throws IOException {
        try {
            resultSet.next();         
            try {
                // last row
                if (resultSet.isAfterLast()) {
                String sqlUpdate = "INSERT INTO diem(diem, user_name, ngaythuchien) values (?,?,NOW())";
                PreparedStatement pStm = connect.prepareStatement(sqlUpdate);
                pStm.setInt(1, diemHientai); 
                pStm.setString(2, TrangChuController.userName);               
                int i = pStm.executeUpdate();
                if (i == 1) {
                    textField();
                    Message.thongTin(Alert.AlertType.INFORMATION, "Thông báo!",
                            "Click button kết quả để biết kết quả bài thi",
                            "Bạn đã hoàn tất bài test");
                    btnKetqua.setDisable(false);
                }
                return;

                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

            try {       
                audioURL="/Audios/" + resultSet.getString("audio");
                audioClip= new AudioClip(ToeicApp.class.getResource(audioURL).toString());                
                audioClip.play();
                cauSo.setText((cauHientai +=1).toString());   
                noiDung.setText(resultSet.getString("cauhoi"));
                radA.setText(resultSet.getString("A"));
                radB.setText(resultSet.getString("B"));
                radC.setText(resultSet.getString("C"));
                radD.setText(resultSet.getString("D"));
                cauTraloihientai = resultSet.getString("dapan");               

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    private void handleResult(ActionEvent event) throws IOException {
        audioClip.stop();
        stage = (Stage) btnKetqua.getScene().getWindow();
        stage.hide();
        ForwardForm ff = new ForwardForm();
        ff.transForm("toeicapp/KetQua.fxml",
                "Kết quả");
    }

    @FXML
    private void handleNext(ActionEvent event) throws IOException {
        audioClip.stop();
        if (radA.isSelected())
        {
            if ("a".equals(cauTraloihientai)) {
                diemHientai += 1;       
            updateScore(diemHientai);}
        }
        if (radB.isSelected())
        {
            if ("b".equals(cauTraloihientai)) {
                diemHientai += 1;
            updateScore(diemHientai);}
        }
        if (radC.isSelected())
        {
            if ("c".equals(cauTraloihientai)) {
                diemHientai += 1;
            updateScore(diemHientai);}
        }
        if (radD.isSelected())
        {
            if ("d".equals(cauTraloihientai)) {
            diemHientai += 1;
            updateScore(diemHientai);}   
        }        
        next();   
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Exit");
        alert.setContentText("Bạn có chắc chắn muốn thoát");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    audioClip.stop();
                    stage = (Stage) btnExit.getScene().getWindow();
                    stage.hide();
                    ForwardForm ff = new ForwardForm();
                    ff.transForm("toeicapp/TrangChu.fxml",
                            "Trang Chủ");

                } catch (IOException ex) {
                    Logger.getLogger(DienKhuyetController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else {
                alert.close();
            }
        });
    }

    private void updateScore(Integer passedscore) {
        diem.setText(passedscore.toString());
    }

//    @FXML
//    private void handleAnswerA(ActionEvent event) throws IOException {
//        if ("a".equals(cauTraloihientai)) {
//            diemHientai += 1;
//        }
//        updateScore(diemHientai);
//        audioClip.stop();
//    }
//
//    @FXML
//    private void handleAnswerB(ActionEvent event) throws IOException {
//        if ("b".equals(cauTraloihientai)) {
//            diemHientai += 1;
//
//        }
//        updateScore(diemHientai);
//        next();
//    }
//
//    @FXML
//    private void handleAnswerC(ActionEvent event) throws IOException {
//        if ("c".equals(cauTraloihientai)) {
//            diemHientai += 1;
//
//        }
//        updateScore(diemHientai);
//        next();
//    }
//
//    @FXML
//    private void handleAnswerD(ActionEvent event) throws IOException {
//        if ("d".equals(cauTraloihientai)) {
//            diemHientai += 1;
//
//        }
//        updateScore(diemHientai);
//        next();
//    }
// clear

    public void textField() {
        btnCauketiep.setDisable(true);
        radA.setDisable(true);
        radB.setDisable(true);
        radC.setDisable(true);
        radD.setDisable(true);
        radA.setSelected(false);
        radB.setSelected(false);
        radC.setSelected(false);
        radD.setSelected(false);
        radA.setText("No answer");
        radB.setText("No answer");
        radC.setText("No answer");
        radD.setText("No answer");
        cauSo.setText("0");
        tongCau.setText("Tổng câu: 0");
        diemHientai = 0;
        noiDung.setText("Không có câu hỏi");
    }   
}
