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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXComboBox;
import Action.Connect;
import Action.KiemTraText;
import Action.Message;
import Model.LoaiCauHoi;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class QuanLyCauHoiController implements Initializable {

    @FXML
    private JFXTextField txtCauhoi;
    @FXML
    private JFXTextField txtA;
    @FXML
    private JFXTextField txtB;
    @FXML
    private JFXTextField txtC;
    @FXML
    private JFXTextField txtD;
    @FXML
    private JFXTextField txtDapan;
    @FXML
    private JFXTextField txtUrlpicture;
    @FXML
    private JFXTextField txtUrlaudio;
    @FXML
    private Label errCauhoi;
    @FXML
    private Label errDapan;
    @FXML
    private Label errTheloai;
    @FXML
    private JFXButton btnThem;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnPicture;
    @FXML
    private JFXButton btnAudio;
    
    @FXML
    private JFXComboBox<LoaiCauHoi> cbTheloai;
    private ObservableList<LoaiCauHoi> list;
    private String idTheloai;
    private Connection connect;
    private PreparedStatement pStm;
    private ResultSet resultSet;
    private FileChooser opFile;
    private File file;
    private String nameTheloai;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = Connect.connection();
        comboboxLoad();
        btnPicture.setVisible(false);
        btnAudio.setVisible(false);
        txtUrlpicture.setVisible(false);
        txtUrlaudio.setVisible(false);
    }    
    
    public void rsText() {
        txtCauhoi.setText("");
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");
        txtD.setText("");
        txtDapan.setText("");
        txtUrlpicture.setText("");
        txtUrlaudio.setText("");
    }
    
    @FXML
    private void btPicture (ActionEvent event)
    {
        opFile = new FileChooser();
        opFile.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif"));
        file = opFile.showOpenDialog(null);  
        if(file != null)
        {
        txtUrlpicture.setText(file.getName());
        }
    }
    
    @FXML
    private void btAudio (ActionEvent event)
    {
        opFile = new FileChooser();
        opFile.getExtensionFilters().addAll(new ExtensionFilter("Audio File", "*.mp3"));
        file = opFile.showOpenDialog(null);  
        if(file != null)
        {
        txtUrlaudio.setText(file.getName());
        }
    }
    
    @FXML
    private void btReset (ActionEvent event){
        if(event.getSource() == btnReset)
            rsText();
    }
    
    public void comboboxLoad(){
        list = FXCollections.observableArrayList();
        try{
            String sql = "Select * From theloai";
            pStm = this.connect.prepareStatement(sql);
            resultSet = pStm.executeQuery();
            while (resultSet.next())
            {
                list.add(new LoaiCauHoi(resultSet.getString(1), resultSet.getString(2)));
            }
            cbTheloai.setItems(list);
            cbTheloai.setConverter(new StringConverter<LoaiCauHoi>() {
                
            @Override
            public String toString(LoaiCauHoi object) {
                //
                return object.getNameTheloai();
            }

            @Override
            public LoaiCauHoi fromString(String string) {
                return null;
            }
        });       
            cbTheloai.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    idTheloai = newValue.getIdTheloai();
                    nameTheloai= newValue.getNameTheloai();
                }
                if ("Photo Question".equals(nameTheloai))
                {
                    btnPicture.setVisible(true);
                    txtUrlaudio.setVisible(false);
                    btnAudio.setVisible(false);  
                    txtA.setDisable(false);
                    txtB.setDisable(false);
                    txtC.setDisable(false);
                    txtD.setDisable(false);
                    txtUrlpicture.setVisible(true);
                    txtUrlaudio.setText("");
                }
                else if ("Question Response".equals(nameTheloai))
                {
                    btnAudio.setVisible(true);
                    txtUrlpicture.setVisible(false);
                    btnPicture.setVisible(false);
                    txtUrlaudio.setVisible(true);
                    txtA.setDisable(false);
                    txtB.setDisable(false);
                    txtC.setDisable(false);
                    txtD.setDisable(false);
                    txtUrlpicture.setText("");
                }
                else if("Fill Sentences".equals(nameTheloai))
                {
                    txtA.setDisable(true);
                    txtB.setDisable(true);
                    txtC.setDisable(true);
                    txtD.setDisable(true);
                    txtUrlaudio.setVisible(false);
                    txtUrlpicture.setVisible(false);
                    btnAudio.setVisible(false);
                    btnPicture.setVisible(false);
                    txtA.setText("");
                    txtB.setText("");
                    txtC.setText("");
                    txtD.setText("");
                    
                }
                else
                {
                    btnPicture.setVisible(false);
                    btnAudio.setVisible(false);
                    txtUrlpicture.setVisible(false);
                    txtUrlaudio.setVisible(false);
                    txtA.setDisable(false);
                    txtB.setDisable(false);
                    txtC.setDisable(false);
                    txtD.setDisable(false);
                    txtUrlpicture.setText("");
                    txtUrlpicture.setText("");
                }
                
            });
            
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    @FXML
    private void btThem(ActionEvent event) throws SQLException{
        boolean ktCauhoi = KiemTraText.
                kiemTraTextFieldEmpty(txtCauhoi, errCauhoi, "*");
        boolean ktDapan = KiemTraText.
                kiemTraTextFieldEmpty(txtDapan, errDapan, "*");
        boolean ktLoai = KiemTraText.
                kiemTraCombobox(cbTheloai, errTheloai, "*");
        if (ktCauhoi && ktDapan && ktLoai)
        {
        try{    
                connect.setAutoCommit(false);
                String insertpic = "INSERT INTO cauhoi( id_theloai, cauhoi, A ,B ,C ,D ,dapan, image, audio) "
                + "VALUES( ? ,? ,? ,? ,? ,?, ?, ?,?)";
                pStm = this.connect.prepareStatement(insertpic);  
                pStm.setString(1, idTheloai.trim());
                pStm.setString(2, txtCauhoi.getText().trim());
                pStm.setString(3, txtA.getText().trim());
                pStm.setString(4, txtB.getText().trim());
                pStm.setString(5, txtC.getText().trim());
                pStm.setString(6, txtD.getText().trim());
                pStm.setString(7, txtDapan.getText().trim());
                pStm.setString(8, txtUrlpicture.getText().trim());
                pStm.setString(9, txtUrlaudio.getText().trim());
                int i =pStm.executeUpdate();
                if (i == 1) {
                    Message.thongTin(Alert.AlertType.INFORMATION,
                           "Thêm câu hỏi thành công ", null, "Thông báo");
                    rsText(); 
               } else {
                    Message.baoLoi(Alert.AlertType.ERROR,
                            "Thêm thất bại, vui lòng kiểm tra lại ", null, "Thông báo");
                }
                connect.commit();       
        }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());                
        }       
        }else {
              errCauhoi.getStyleClass().add("err");
              errDapan.getStyleClass().add("err");
              errTheloai.getStyleClass().add("err");
        }
    }
}
