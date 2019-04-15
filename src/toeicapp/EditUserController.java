/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import Action.Connect;
import Action.KiemTraText;
import Action.Message;
import Model.NguoiDung;
import Model.Quyen;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class EditUserController implements Initializable {

    @FXML
    private TableView<NguoiDung> tableNguoidung;
    @FXML
    private JFXTextField txtFirstname;
    @FXML
    private JFXTextField txtLastname;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXComboBox<Quyen> cbQuyen;
    @FXML
    private Text userID;
    @FXML
    private Label errFirstname;
    @FXML
    private Label errLastname;
    @FXML
    private Label errUsername;
    @FXML
    private Label errPass;
    @FXML
    private Label errEmail;
    @FXML
    private Label errQuyen;
    @FXML
    private JFXButton btnCapNhat;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnXoa;
    @FXML
    private TableColumn<?, ?> colNo;
    @FXML
    private TableColumn<?, ?> colFirst;
    @FXML
    private TableColumn<?, ?> colLast;
    @FXML
    private TableColumn<?, ?> colUser;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colRole;
    @FXML
    private TableColumn<?, ?> colPass;
    private ObservableList<Quyen> listQuyen;
    private ObservableList<NguoiDung> listUser;
    private Connection connect;
    private PreparedStatement pStm;
    private ResultSet resultSet;
    private String quyenId;
    /**
     * Initializes the controller class.
     */
    private void start (final Stage stage) throws IOException{
        Parent pa = FXMLLoader.load(getClass().getResource("/toeicapp/EditUser.fxml"));
        Scene scene = new Scene(pa);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Cập nhật User");       
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connect = Connect.connection();
        listQuyen = FXCollections.observableArrayList();
        listUser = FXCollections.observableArrayList();
        loadDulieu();
        loadCol();
        colClick();
        loadCombobox();
    }    
    
    public void resetText() {
        txtFirstname.setText("");
        txtLastname.setText("");
        txtUsername.setText("");
        txtPassword.setText("");        
        txtEmail.setText("");
        userID.setText("");
    }
    
    private void loadCombobox()
    {
        try
        {
            pStm = connect.prepareStatement("Select * From phanquyen where id_phanquyen <> 'A' ");
            resultSet = pStm.executeQuery();
            while (resultSet.next())
            {
                listQuyen.add(new Quyen(resultSet.getString(1), resultSet.getString(2)));
            }
            cbQuyen.setItems(listQuyen);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        cbQuyen.setConverter(new StringConverter<Quyen>() {
            @Override
            public String toString(Quyen object) {
                return object.getName();
            }
            @Override
            public Quyen fromString(String string) {
                return null;
            }
        });
        
        cbQuyen.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                quyenId = newValue.getId();
            }           
        });
    }
    
    private void loadDulieu()
    {
        listUser.clear();
        try
        {
            String sql = "SELECT u.id_nguoidung, u.first_name, u.last_name, "
                    + "u.user_name, u.email,q.name,u.pass "
                    + "FROM nguoidung u INNER JOIN phanquyen q "
                    + "ON u.id_phanquyen = q.id_phanquyen "
                    + "where u.id_phanquyen <> 'A' "
                    + "order by q.name DESC";
            pStm = connect.prepareStatement(sql);
            resultSet = pStm.executeQuery();
            while (resultSet.next()) {
                listUser.add(new NguoiDung(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
            tableNguoidung.setItems(listUser);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    private void loadCol()
    {
        colNo.setCellValueFactory(new PropertyValueFactory<>("idNguoidung"));
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));        
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("nameQuyen"));
    }
    
    private void colClick()
    {
        try {
            tableNguoidung.setOnMouseClicked(e -> {
                NguoiDung userList = tableNguoidung.getItems().get(tableNguoidung.getSelectionModel().getSelectedIndex());
                userID.setText(userList.getIdNguoidung());
                txtFirstname.setText(userList.getFirstName());
                txtLastname.setText(userList.getLastName());
                txtUsername.setText(userList.getUserName());
                txtPassword.setText(userList.getPass());                
                txtEmail.setText(userList.getEmail());
            });
        } 
        catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    @FXML
    private void updateUser(ActionEvent event)
    {
        if (event.getSource() == btnCapNhat) 
        {        
            boolean kiemTrafirstname = KiemTraText.kiemTraTextFieldEmpty(txtFirstname, errFirstname, "*");
            boolean kiemTralastname = KiemTraText.kiemTraTextFieldEmpty(txtLastname, errLastname, "*");
            boolean kiemTrauser = KiemTraText.kiemTraTextFieldEmpty(txtUsername, errUsername, "*");
            boolean kiemTrapass = KiemTraText.kiemTraPasswordFieldEmpty(txtPassword, errPass, "*");          
            boolean kiemTraemail = KiemTraText.isValidEmail(txtEmail, errEmail, "*");
            boolean kiemTraquyen = KiemTraText.kiemTraCombobox(cbQuyen, errQuyen, "*");
            if (kiemTrauser && kiemTrapass &&
                kiemTrafirstname && kiemTralastname &&
                kiemTraemail && kiemTraquyen) {
                try {
                        String updateSql = "update nguoidung set user_name = ?,"
                                + " pass = ?, first_name= ? ,"
                                + "last_name = ?, email= ?,"
                                + " id_phanquyen = ?"
                                + "where id_nguoidung= ?";
                        pStm = connect.prepareStatement(updateSql);
                        pStm.setString(1, txtUsername.getText().trim());
                        pStm.setString(2, txtPassword.getText().trim());
                        pStm.setString(3, txtFirstname.getText().trim());
                        pStm.setString(4, txtLastname.getText().trim());
                        pStm.setString(5, txtEmail.getText().trim()); 
                        pStm.setString(6, quyenId.trim());
                        pStm.setString(7, userID.getText().trim());
                        int i = pStm.executeUpdate();
                        if (i == 1) {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật User thành công", "Thông báo!", "Cập nhật thành công!");
                            loadDulieu();
                            resetText();
                        } else {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật User thất bại vui lòng kiểm tra lại", 
                                "Thông báo!", "Cập nhật thất bại!");
                        }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

            } else {
                txtFirstname.getStyleClass().add("errLogin");
                txtLastname.getStyleClass().add("errLogin");
                txtUsername .getStyleClass().add("errLogin");
                txtPassword.getStyleClass().add("errLogin");                
                txtEmail.getStyleClass().add("errLogin");
                cbQuyen.getStyleClass().add("errLogin");
            }
        } else if (event.getSource() == btnReset) {
            resetText();
        }
        
          else if (event.getSource() == btnXoa){               
                if(userID.getText() != null)
                {                   
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Xác nhận");
                    alert.setHeaderText("Bạn có chắc muốn xóa User này không ?");
                    alert.setContentText(null);
                    alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                try {
                    String deleteSql = "Delete From nguoidung Where id_nguoidung= ?";
                    pStm = connect.prepareStatement(deleteSql);
                    pStm.setString(1, userID.getText().trim());               
                    int i = pStm.executeUpdate();
                    if (i == 1) 
                    {
                        Message.thongTin(Alert.AlertType.INFORMATION,
                        "Xóa User " + txtUsername.getText() + " thành công", 
                        "Thông báo!", "Xóa thành công!");
                        loadDulieu();
                        resetText();
                    }else {
                    Message.thongTin(Alert.AlertType.INFORMATION,
                        "Xóa User thất bại vui lòng kiểm tra lại", 
                        "Thông báo!", "Xóa thất bại!");
                    }
                }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());  
                }
                    }else{
                            alert.close();
                    }
                    });
                }else{
                    Message.thongTin(Alert.AlertType.INFORMATION,
                    "Username hiện đang rỗng không thể xóa", 
                    "Thông báo!", "Xóa thất bại!");
                }               
         }
        
        
    }
}
