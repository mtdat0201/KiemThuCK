/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Action.Connect;
import Action.Message;
import Action.ForwardForm;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class MultipleChoiceGrammarController implements Initializable {

    @FXML
    private JFXButton btnketQua;
    @FXML
    private JFXButton btnCauketiep;
    @FXML
    private JFXButton btnExit;
    @FXML 
    private JFXTextField txtCautraloi;
    @FXML
    private Text noiDung;
    @FXML
    private Text cauSo;
    @FXML
    private Text tongCau;
    @FXML
    private Text diem;
    private Statement sta;
    private Connection connect;
    private ResultSet resultSet;
    private String number, cauTraloihientai;// so cau
    private Integer diemHientai, cauHientai; //diem hien tai
    private Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        cauHientai =0;
        diemHientai=0;
        btnketQua.setDisable(true);
        connect = Connect.connection();
        try{
            sta=connect.createStatement();
            String sqlCount = "Select count(*) From theloai a, cauhoi b "
                    + "Where a.id_theloai=b.id_theloai and a.name_theloai='Fill Sentences' Limit 10";
            resultSet=sta.executeQuery(sqlCount);
            resultSet.next();
            number=resultSet.getString(1);          
            tongCau.setText(number);
            resultSet.close();
            String sql = "Select * From theloai a, cauhoi b "
                    + "Where a.id_theloai=b.id_theloai and a.name_theloai='Fill Sentences' order by rand() Limit 10";
            resultSet = sta.executeQuery(sql);
            next();         
        } 
        catch (SQLException | IOException ex) {
                Logger.getLogger(MultipleChoiceGrammarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void handleKetqua(ActionEvent event) throws IOException, SQLException {
        stage = (Stage) btnketQua.getScene().getWindow();
        stage.hide();
        resultSet.close();
        ForwardForm ff = new ForwardForm();
        ff.transForm("toeicapp/KetQua.fxml", "Kết quả");
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
                    btnketQua.setDisable(false);
                }
                return;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        try {
            cauSo.setText((cauHientai += 1).toString());
            noiDung.setText(resultSet.getString("cauhoi"));
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
    private void handleNext(ActionEvent event) throws IOException {  
        String t = txtCautraloi.getText().trim();
        if (t.equals(cauTraloihientai)) {
            diemHientai += 1;
            updateScore(diemHientai);
            next();
        }
        else
        {
            next();
        }
    }
    
    @FXML
    private void handleExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Exit");
        alert.setContentText("Bạn có chắc muốn thoát");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    stage = (Stage) btnExit.getScene().getWindow();
                    stage.hide();
                    resultSet.close();
                    ForwardForm ff = new ForwardForm();
                    ff.transForm("toeicapp/TrangChu.fxml",
                            "Trang Chủ");
                } catch (IOException ex) {
                    Logger.getLogger(MultipleChoiceGrammarController.class.getName()).
                            log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MultipleChoiceGrammarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                alert.close();
            }
        });
    }
    private void updateScore(Integer passedscore) {
        diem.setText(passedscore.toString());
    }   

    
    public void textField() {
       btnCauketiep.setDisable(true);
       cauSo.setText("0");
       tongCau.setText("Tổng câu: 0");
       diemHientai = 0;
       noiDung.setText("Không có câu hỏi");
   }
}
