/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Action.Connect;
import Action.ForwardForm;
import Action.Message;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class DangNhapController implements Initializable {

    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnClose;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    
    private PreparedStatement pStm;
    private Connection connect;
    private ResultSet resultSet;
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect= Connect.connection();
    }    
    
    @FXML
    private void dangNhapbutton(ActionEvent event) throws IOException{
        try{
            String query = "Select * " + "From nguoidung " + "where user_name= ? and pass= ?";
            pStm= this.connect.prepareStatement(query); // goi chuoi ket noi va thuc hien chuoi lenh query o tren
            pStm.setString(1, txtUserName.getText().trim()); // lay gia tri cua txt
            pStm.setString(2, txtPassword.getText().trim());
            resultSet=pStm.executeQuery();
            if(resultSet.next())
            {
                TrangChuController.idCurrent=resultSet.getInt("id_nguoidung"); //lấy id và user name sang trang chủ
                TrangChuController.userName=resultSet.getString("user_name");
                stage = (Stage) btnSignIn.getScene().getWindow(); // mở sàn diễn sau khi click đăng nhập
                stage.hide();
                ForwardForm ff = new ForwardForm();
                ff.transForm("toeicapp/TrangChu.fxml", "Welcome to Trang Chủ");
            }
            else
            {
                if(txtUserName.getText().length()==0 || txtPassword.getText().isEmpty())
                {
                    Message.thongTin(Alert.AlertType.INFORMATION,"Lỗi tài khoản mật khẩu rỗng", "Có một thông tin quan trọng dành cho bạn như sau: ", "Tài khoản và mật khẩu không được rỗng nhé");
                    txtUserName.getStyleClass().add("errLogin"); // gọi css
                    txtPassword.getStyleClass().add("errLogin");
                    txtUserName.requestFocus(); //trả về con nháy đúng vào chỗ txt
                }
                else{
                    Message.thongTin(Alert.AlertType.INFORMATION, "Lỗi sai tài khoản và mật khẩu hoặc đang cố tình xâm nhập bất hợp pháp account người khác", "Có một thông tin quan trọng dành cho bạn như sau: ", "Tài khoản hoặc mật khẩu sai rồi ráng nhớ lại nhé");
                    txtUserName.getStyleClass().add("errLogin");
                    txtPassword.getStyleClass().add("errLogin");
                    txtUserName.requestFocus();
                    }
            }      
            }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        }
    @FXML
    private void dangKybutton(ActionEvent event) throws IOException {
        ForwardForm.loadStage(getClass().
                getResource("/toeicapp/DangKy.fxml"), "Đăng ký", null);
    }
    @FXML
    private void huyButton(ActionEvent event){
        if(event.getSource()== btnClose)
            System.exit(0);
    }
}
