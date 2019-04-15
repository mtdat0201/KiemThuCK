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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import Action.Message;
import Action.Connect;
import Action.KiemTraText;
import Model.Quyen;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class DangKyController implements Initializable {

    @FXML
    private JFXButton btnCreateAccount;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXTextField txtFirstname;
    @FXML
    private JFXTextField txtLastname;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXComboBox<Quyen> cmbQuyen;
    private ObservableList<Quyen> quyen;
    @FXML
    private Label errUser;
    @FXML
    private Label errPassword;
    @FXML
    private Label errFirstname;
    @FXML
    private Label errLastname;
    @FXML
    private Label errEmail;
    @FXML
    private Label errQuyen;    
    
    private String quyenId;
    private PreparedStatement pStm;
    private Connection connect;
    private ResultSet resultSet;
    private Stage stage;
    /**
     * Initializes the controller class.
     */
    
   
    private void start (final Stage stage) throws IOException{
        Parent pa = FXMLLoader.load(getClass().getResource("/toeicapp/DangKy.fxml"));
        Scene scene = new Scene(pa);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Đăng ký");       
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         load Combobox
        connect = Connect.connection();
        quyen= FXCollections.observableArrayList();
        try{
            PreparedStatement pre = connect.prepareStatement("Select * From phanquyen where id_phanquyen <> 'A' ");
            resultSet=pre.executeQuery();
            while (resultSet.next())
            {
                quyen.add(new Quyen(resultSet.getString(1),resultSet.getString(2)));
            }
            cmbQuyen.setItems(quyen);
//             Đến đây nó chỉ hiển thị id theo mã máy + Model.Quyen mà thôi chưa hiển thị được name
        }
        catch (SQLException ex) {
             Logger.getLogger(DangKyController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
        //hiển thị name 
        cmbQuyen.setConverter(new StringConverter<Quyen>()
        {
            @Override
            public String toString(Quyen object) {
                return object.getName();
            }
            // phải khai báo thuộc tính vì sử dụng lại lớp Quyền để chuyển chuỗi name
            @Override
            public Quyen fromString(String string) {
                return null;
            }
        });
        // lấy giá trị khi add. Addlister khi giá trị của obs thay đổi 
        cmbQuyen.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                quyenId = newValue.getId();
            }
        });
    } 
    
    @FXML
    private void dkButton(ActionEvent event){
        boolean kiemTrauser = KiemTraText.kiemTraTextFieldEmpty(txtUsername, errUser, "Rỗng");
        boolean kiemTrapass = KiemTraText.kiemTraPasswordFieldEmpty(txtPass, errPassword, "Sai định dạng");
        boolean kiemTrafirstname = KiemTraText.kiemTraTextFieldEmpty(txtFirstname, errFirstname, "Rỗng");
        boolean kiemTralastname = KiemTraText.kiemTraTextFieldEmpty(txtLastname, errLastname, "Rỗng");
        boolean kiemTraemail = KiemTraText.isValidEmail(txtEmail, errEmail, "Sai định dạng");
        boolean kiemTraquyen = KiemTraText.kiemTraCombobox(cmbQuyen, errQuyen, "Chọn quyền");
        if (kiemTrauser && kiemTrapass &&
                kiemTrafirstname && kiemTralastname &&
                kiemTraemail && kiemTraquyen) {
            try {
                connect.setAutoCommit(false);
                PreparedStatement pre1 = connect.prepareStatement("Select user_name From nguoidung where user_name= ? ");
                pre1.setString(1, txtUsername.getText().trim());
                ResultSet resultSet1=pre1.executeQuery();
                if(resultSet1.next())
                {
                    Message.baoLoi(Alert.AlertType.ERROR,
                            "Username đã tồn tại ", "Thông báo!", "Thêm thất bại");
                    txtUsername.requestFocus();
                }
                else
                {
                String insert = "Insert IGNORE into nguoidung(user_name,"
                        + " pass, first_name, last_name,"
                        + "email,id_phanquyen) "
                        + "VALUES(?, ?, ?, ?, ?, ?)";
                pStm = this.connect.prepareStatement(insert);           
                pStm.setString(1, txtUsername.getText().trim());
                pStm.setString(2, txtPass.getText().trim());
                pStm.setString(3, txtFirstname.getText().trim());
                pStm.setString(4, txtLastname.getText().trim());
                pStm.setString(5, txtEmail.getText().trim()); 
                pStm.setString(6, quyenId.trim());
                int i = pStm.executeUpdate();  
//                String insertSql = "INSERT INTO diem(user_name) values (?)";
//                pStm = this.connect.prepareStatement(insertSql);
//                pStm.setString(1, txtUsername.getText().trim());
//                int j = pStm.executeUpdate();
                if (i == 1) {
                    Message.thongTin(Alert.AlertType.INFORMATION,
                            "Thêm User thành công", "Thông báo!", "Thêm thành công!");
                    stage = (Stage) btnCreateAccount.getScene().getWindow();
                        stage.hide();

                } else {
                    Message.baoLoi(Alert.AlertType.ERROR,
                            "Có lỗi xảy ra thêm User thất bại", "Thông báo!", "Thêm thất bại");
                    txtUsername.requestFocus();
                }
                }
                connect.commit();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }
    
    public void rsText() {
        txtUsername.setText("");
        txtPass.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        txtEmail.setText("");
    }
    
    @FXML
    private void resetButton(ActionEvent event) throws IOException {
        if (event.getSource() == btnReset) {
            rsText();
        }
    }
}
