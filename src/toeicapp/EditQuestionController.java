/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import Action.Connect;
import Action.KiemTraText;
import Action.Message;
import Model.CauHoi;
import Model.LoaiCauHoi;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class EditQuestionController implements Initializable {

    @FXML
    private TableView<CauHoi> tableCauhoi;
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
    private JFXTextField txtTimkiem;
    @FXML
    private JFXComboBox<LoaiCauHoi> cbLoaicauhoi;
    @FXML
    private Text questionID;
    @FXML
    private Label errCauhoi;
    @FXML
    private Label errA;
    @FXML
    private Label errB;
    @FXML
    private Label errC;
    @FXML
    private Label errD;
    @FXML
    private Label errDapan;
    @FXML
    private Label errPic;
    @FXML
    private Label errAudio;
    @FXML
    private Label errLoaicauhoi;
    @FXML
    private JFXButton btnCapNhat;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnXoa;
    @FXML
    private JFXButton btnPicture;
    @FXML
    private JFXButton btnAudio;
    @FXML
    private TableColumn<?, ?> colNo;
    @FXML
    private TableColumn<?, ?> colLoai;
    @FXML
    private TableColumn<?, ?> colCauhoi;
    @FXML
    private TableColumn<?, ?> colA;
    @FXML
    private TableColumn<?, ?> colB;
    @FXML
    private TableColumn<?, ?> colC;
    @FXML
    private TableColumn<?, ?> colD;
    @FXML
    private TableColumn<?, ?> colDapan;
    @FXML
    private TableColumn<?, ?> colPic;
    @FXML
    private TableColumn<?, ?> colIDtheloai;
    @FXML
    private TableColumn<?, ?> colAudio;
    private ObservableList<CauHoi> listCauhoi;
    private ObservableList<LoaiCauHoi> listLoaicauhoi;
    private Connection connect;
    private PreparedStatement pStm;
    private ResultSet resultSet;
    private String loaiCauhoiid;
    private FileChooser opFile;
    private File file;
    /**
     * Initializes the controller class.
     */
    private void start (final Stage stage) throws IOException{
        Parent pa = FXMLLoader.load(getClass().getResource("/toeicapp/EditQuestion.fxml"));
        Scene scene = new Scene(pa);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Cập nhật Câu hỏi");       
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = Connect.connection();
        listCauhoi = FXCollections.observableArrayList();
        listLoaicauhoi = FXCollections.observableArrayList();
        txtA.setDisable(true);
        txtB.setDisable(true);
        txtC.setDisable(true);
        txtD.setDisable(true);
        btnPicture.setDisable(true);
        btnAudio.setDisable(true);
        loadDulieu();
        loadCol();
        colClick();
        loadCombobox();
    }    
    public void resetText() {
        txtCauhoi.setText("");
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");        
        txtD.setText("");
        txtDapan.setText("");
        txtUrlaudio.setText("");
        txtUrlpicture.setText("");
        questionID.setText("");
    }
    
    @FXML
    private void btPicture (ActionEvent event)
    {
        opFile = new FileChooser();
        opFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif"));
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
        opFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Audio File", "*.mp3"));
        file = opFile.showOpenDialog(null);  
        if(file != null)
        {
        txtUrlaudio.setText(file.getName());
        }
    }
    
    private void loadCombobox()
    {
        try
        {
            pStm = connect.prepareStatement("SELECT * FROM theloai ");
            resultSet = pStm.executeQuery();
            while (resultSet.next())
            {
                listLoaicauhoi.add(new LoaiCauHoi(resultSet.getString(1), resultSet.getString(2)));
            }
            cbLoaicauhoi.setItems(listLoaicauhoi);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        cbLoaicauhoi.setConverter(new StringConverter<LoaiCauHoi>() {
            @Override
            public String toString(LoaiCauHoi object) {
                return object.getNameTheloai();
            }
            @Override
            public LoaiCauHoi fromString(String string) {
                return null;
            }
        });
        
        cbLoaicauhoi.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                loaiCauhoiid = newValue.getIdTheloai();
            }       
            if("DK".equals(loaiCauhoiid))
            {      
            txtA.setDisable(true);
            txtB.setDisable(true);
            txtC.setDisable(true);        
            txtD.setDisable(true);
            txtA.setText("");
            txtB.setText("");
            txtC.setText("");        
            txtD.setText("");
            btnPicture.setDisable(true);
            btnAudio.setDisable(true);
            txtUrlaudio.setText("");
            txtUrlpicture.setText("");
            }
            else if ("TN".equals(loaiCauhoiid))
            {       
            txtA.setDisable(false);
            txtB.setDisable(false);
            txtC.setDisable(false);        
            txtD.setDisable(false);
            btnPicture.setDisable(true);
            btnAudio.setDisable(true);
            txtUrlaudio.setText("");
            txtUrlpicture.setText("");
            }
            else if ("P".equals(loaiCauhoiid))
            {       
            txtA.setDisable(false);
            txtB.setDisable(false);
            txtC.setDisable(false);        
            txtD.setDisable(false);
            btnPicture.setDisable(false);
            btnAudio.setDisable(true);
            txtUrlaudio.setText("");
            }
            else if ("A".equals(loaiCauhoiid))
            {       
            txtA.setDisable(false);
            txtB.setDisable(false);
            txtC.setDisable(false);        
            txtD.setDisable(false);
            btnPicture.setDisable(true);
            btnAudio.setDisable(false);
            txtUrlpicture.setText("");
            }
        });
    }
    private void loadDulieu()
    {
        listCauhoi.clear();
        try
        {
            String sql = "SELECT q.id_cauhoi, t.name_theloai, q.cauhoi, q.A, q.B, "
                    + "q.C, q.D, q.dapan,"
                    + "q.image, q.audio, q.id_theloai "
                    + "FROM cauhoi q INNER JOIN theloai t "
                    + "ON q.id_theloai = t.id_theloai "
                    + "ORDER BY t.name_theloai DESC";
            pStm = connect.prepareStatement(sql);
            resultSet = pStm.executeQuery();
            while (resultSet.next()) {
                listCauhoi.add(new CauHoi(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11)));
            }
            tableCauhoi.setItems(listCauhoi);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    private void loadCol()
    {
        colNo.setCellValueFactory(new PropertyValueFactory<>("idCauhoi"));
        colLoai.setCellValueFactory(new PropertyValueFactory<>("tenLoai")); 
        colCauhoi.setCellValueFactory(new PropertyValueFactory<>("cauHoi"));
        colA.setCellValueFactory(new PropertyValueFactory<>("a"));
        colB.setCellValueFactory(new PropertyValueFactory<>("b"));
        colC.setCellValueFactory(new PropertyValueFactory<>("c"));
        colD.setCellValueFactory(new PropertyValueFactory<>("d"));
        colDapan.setCellValueFactory(new PropertyValueFactory<>("dapAn"));
        colPic.setCellValueFactory(new PropertyValueFactory<>("image"));
        colAudio.setCellValueFactory(new PropertyValueFactory<>("audio"));
        colIDtheloai.setCellValueFactory(new PropertyValueFactory<>("idTheloai"));
    }
    
    private void colClick()
    {
        try {
            tableCauhoi.setOnMouseClicked(e -> {
                CauHoi questionList = tableCauhoi.getItems().get(tableCauhoi.getSelectionModel().getSelectedIndex());
                questionID.setText(questionList.getIdCauhoi());
                txtCauhoi.setText(questionList.getCauHoi());
                txtA.setText(questionList.getA());
                txtB.setText(questionList.getB());
                txtC.setText(questionList.getC());
                txtD.setText(questionList.getD());
                txtDapan.setText(questionList.getDapAn());
                txtUrlpicture.setText(questionList.getImage());
                txtUrlaudio.setText(questionList.getAudio());                
            });
        } 
        catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    @FXML
    private void handleSearch(KeyEvent event) {

        if (txtTimkiem.getText().equals("")) {
            loadDulieu();
        } else {
            listCauhoi.clear();
  /*Tim Cau hoi*/   String searchSql = "SELECT q.id_cauhoi, t.name_theloai, q.cauhoi, q.A, q.B, "
                    + "q.C, q.D, q.dapan,"
                    + "q.image, q.audio, q.id_theloai "
                    + "FROM cauhoi q INNER JOIN theloai t "
                    + "ON q.id_theloai = t.id_theloai WHERE q.cauhoi like '%" + txtTimkiem.getText() + "%'"
  /*Tim A*/         + "UNION SELECT q.id_cauhoi, t.name_theloai, q.cauhoi, q.A, q.B, "
                    + "q.C, q.D, q.dapan,"
                    + "q.image, q.audio, q.id_theloai "
                    + "FROM cauhoi q INNER JOIN theloai t "
                    + "ON q.id_theloai = t.id_theloai WHERE q.A like '%" + txtTimkiem.getText() + "%'"
   /*Tim B*/        + "UNION SELECT q.id_cauhoi, t.name_theloai, q.cauhoi, q.A, q.B, "
                    + "q.C, q.D, q.dapan,"
                    + "q.image, q.audio, q.id_theloai "
                    + "FROM cauhoi q INNER JOIN theloai t "
                    + "ON q.id_theloai = t.id_theloai WHERE q.B like '%" + txtTimkiem.getText() + "%'"
   /*Tim C*/        + "UNION SELECT q.id_cauhoi, t.name_theloai, q.cauhoi, q.A, q.B, "
                    + "q.C, q.D, q.dapan,"
                    + "q.image, q.audio, q.id_theloai "
                    + "FROM cauhoi q INNER JOIN theloai t "
                    + "ON q.id_theloai = t.id_theloai WHERE q.C like '%" + txtTimkiem.getText() + "%'"
   /*Tim D*/        + "UNION SELECT q.id_cauhoi, t.name_theloai, q.cauhoi, q.A, q.B, "
                    + "q.C, q.D, q.dapan,"
                    + "q.image, q.audio, q.id_theloai "
                    + "FROM cauhoi q INNER JOIN theloai t "
                    + "ON q.id_theloai = t.id_theloai WHERE q.D like '%" + txtTimkiem.getText() + "%'"
   /*Tim Dapan*/    + "UNION SELECT q.id_cauhoi, t.name_theloai, q.cauhoi, q.A, q.B, "
                    + "q.C, q.D, q.dapan,"
                    + "q.image, q.audio, q.id_theloai "
                    + "FROM cauhoi q INNER JOIN theloai t "
                    + "ON q.id_theloai = t.id_theloai WHERE q.dapan like '%" + txtTimkiem.getText() + "%'";
            try {
                pStm = connect.prepareStatement(searchSql);
                resultSet = pStm.executeQuery();
                while (resultSet.next()) {
                    listCauhoi.add(new CauHoi(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11)));
                }
                tableCauhoi.setItems(listCauhoi);
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }
    
    @FXML
    private void updateCauhoi(ActionEvent event)
    {
        if (event.getSource() == btnCapNhat) 
        {   boolean kiemTracauhoi = KiemTraText.kiemTraTextFieldEmpty(txtCauhoi, errCauhoi, "*");
            boolean kiemTraA = KiemTraText.kiemTraTextFieldEmpty(txtA, errA, "*");
            boolean kiemTraB = KiemTraText.kiemTraTextFieldEmpty(txtB, errB, "*");
            boolean kiemTraC = KiemTraText.kiemTraTextFieldEmpty(txtC, errC, "*");
            boolean kiemTraD = KiemTraText.kiemTraTextFieldEmpty(txtD, errD, "*");
            boolean kiemTradapan = KiemTraText.kiemTraTextFieldEmpty(txtDapan, errDapan, "*");
            boolean kiemTrapic = KiemTraText.kiemTraTextFieldEmpty(txtUrlpicture, errPic, "*");
            boolean kiemTraaudio = KiemTraText.kiemTraTextFieldEmpty(txtUrlaudio, errAudio, "*");
            boolean kiemTraloaicauhoi = KiemTraText.kiemTraCombobox(cbLoaicauhoi, errLoaicauhoi, "*");           
            if("DK".equals(loaiCauhoiid))
            {                
            if (kiemTracauhoi && kiemTradapan && kiemTraloaicauhoi) 
            {
                try {                   
                        String updateSqldk = "update cauhoi set cauhoi = ?,"
                                + " dapan = ?,"
                                + " id_theloai = ?"
                                + "where id_cauhoi= ?";
                        pStm = connect.prepareStatement(updateSqldk);
                        pStm.setString(1, txtCauhoi.getText().trim());
                        pStm.setString(2, txtDapan.getText().trim());
                        pStm.setString(3, loaiCauhoiid.trim());
                        pStm.setString(4, questionID.getText().trim());
                        int i = pStm.executeUpdate();
                        if (i == 1) {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thành công", "Thông báo!", "Cập nhật thành công!");
                            loadDulieu();
                            resetText();
                        } else {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thất bại vui lòng kiểm tra lại", 
                                "Thông báo!", "Cập nhật thất bại!");
                        }                    
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }}

            } else {
                txtCauhoi.getStyleClass().add("errLogin");
                txtDapan.getStyleClass().add("errLogin");
                cbLoaicauhoi.getStyleClass().add("errLogin");
            }
            
            if("TN".equals(loaiCauhoiid))
            {                
            if (kiemTracauhoi && kiemTradapan && kiemTraloaicauhoi && kiemTraA && kiemTraB
                    && kiemTraC && kiemTraD) 
            {
                try {                   
                        String updateSqltn = "update cauhoi set cauhoi = ?, A = ?, "
                                + "B = ?, C = ?, D = ?,"
                                + " dapan = ?,"
                                + " id_theloai = ?"
                                + "where id_cauhoi= ?";
                        pStm = connect.prepareStatement(updateSqltn);
                        pStm.setString(1, txtCauhoi.getText().trim());
                        pStm.setString(2, txtA.getText().trim());
                        pStm.setString(3, txtB.getText().trim());
                        pStm.setString(4, txtC.getText().trim());
                        pStm.setString(5, txtD.getText().trim());
                        pStm.setString(6, txtDapan.getText().trim());
                        pStm.setString(7, loaiCauhoiid.trim());
                        pStm.setString(8, questionID.getText().trim());
                        int i = pStm.executeUpdate();
                        if (i == 1) {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thành công", "Thông báo!", "Cập nhật thành công!");
                            loadDulieu();
                            resetText();
                        } else {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thất bại vui lòng kiểm tra lại", 
                                "Thông báo!", "Cập nhật thất bại!");
                        }                    
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }}

            } else {
                txtCauhoi.getStyleClass().add("errLogin");
                txtDapan.getStyleClass().add("errLogin");
                txtA.getStyleClass().add("errLogin");
                txtB.getStyleClass().add("errLogin");
                txtC.getStyleClass().add("errLogin");
                txtD.getStyleClass().add("errLogin");
                cbLoaicauhoi.getStyleClass().add("errLogin");
            }
            
            if("P".equals(loaiCauhoiid))
            {
            if (kiemTracauhoi && kiemTradapan && kiemTraloaicauhoi && kiemTraA && kiemTraB
                    && kiemTraC && kiemTraD && kiemTrapic) 
            {
                try {                   
                        String updateSqlp = "update cauhoi set cauhoi = ?, A = ?, "
                                + "B = ?, C = ?, D = ?, image = ?,"
                                + " dapan = ?,"
                                + " id_theloai = ?"
                                + "where id_cauhoi= ?";
                        pStm = connect.prepareStatement(updateSqlp);
                        pStm.setString(1, txtCauhoi.getText().trim());
                        pStm.setString(2, txtA.getText().trim());
                        pStm.setString(3, txtB.getText().trim());
                        pStm.setString(4, txtC.getText().trim());
                        pStm.setString(5, txtD.getText().trim());
                        pStm.setString(6, txtUrlpicture.getText().trim());
                        pStm.setString(7, txtDapan.getText().trim());
                        pStm.setString(8, loaiCauhoiid.trim());
                        pStm.setString(9, questionID.getText().trim());
                        int i = pStm.executeUpdate();
                        if (i == 1) {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thành công", "Thông báo!", "Cập nhật thành công!");
                            loadDulieu();
                            resetText();
                        } else {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thất bại vui lòng kiểm tra lại", 
                                "Thông báo!", "Cập nhật thất bại!");
                        }                    
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }}

            } else {
                txtCauhoi.getStyleClass().add("errLogin");
                txtDapan.getStyleClass().add("errLogin");
                txtA.getStyleClass().add("errLogin");
                txtB.getStyleClass().add("errLogin");
                txtC.getStyleClass().add("errLogin");
                txtD.getStyleClass().add("errLogin");
                txtUrlpicture.getStyleClass().add("errLogin");
                cbLoaicauhoi.getStyleClass().add("errLogin");
            }
            if("A".equals(loaiCauhoiid))
            {
            if (kiemTracauhoi && kiemTradapan && kiemTraloaicauhoi && kiemTraA && kiemTraB
                    && kiemTraC && kiemTraD && kiemTraaudio) 
            {
                try {                   
                        String updateSqla = "update cauhoi set cauhoi = ?, A = ?, "
                                + "B = ?, C = ?, D = ?, audio = ?,"
                                + " dapan = ?,"
                                + " id_theloai = ?,"
                                + "where id_cauhoi= ?";
                        pStm = connect.prepareStatement(updateSqla);
                        pStm.setString(1, txtCauhoi.getText().trim());
                        pStm.setString(2, txtA.getText().trim());
                        pStm.setString(3, txtB.getText().trim());
                        pStm.setString(4, txtC.getText().trim());
                        pStm.setString(5, txtD.getText().trim());
                        pStm.setString(6, txtUrlaudio.getText().trim());
                        pStm.setString(7, txtDapan.getText().trim());
                        pStm.setString(8, loaiCauhoiid.trim());
                        pStm.setString(9, questionID.getText().trim());
                        int i = pStm.executeUpdate();
                        if (i == 1) {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thành công", "Thông báo!", "Cập nhật thành công!");
                            loadDulieu();
                            resetText();
                        } else {
                            Message.thongTin(Alert.AlertType.INFORMATION,
                                "Cập nhật Câu hỏi thất bại vui lòng kiểm tra lại", 
                                "Thông báo!", "Cập nhật thất bại!");
                        }                    
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }}

            } else {
                txtCauhoi.getStyleClass().add("errLogin");
                txtDapan.getStyleClass().add("errLogin");
                txtA.getStyleClass().add("errLogin");
                txtB.getStyleClass().add("errLogin");
                txtC.getStyleClass().add("errLogin");
                txtD.getStyleClass().add("errLogin");
                txtUrlaudio.getStyleClass().add("errLogin");
                cbLoaicauhoi.getStyleClass().add("errLogin");
            }
        } else if (event.getSource() == btnReset) {
            resetText();
        }
          else if (event.getSource() == btnXoa){              
                if(questionID.getText() != null)
                {                   
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Xác nhận");
                    alert.setHeaderText("Bạn có chắc muốn xóa Câu hỏi này không ?");
                    alert.setContentText(null);
                    alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                try {
                    String deleteSql = "Delete From cauhoi Where id_cauhoi= ?";
                    pStm = connect.prepareStatement(deleteSql);
                    pStm.setString(1, questionID.getText().trim());               
                    int i = pStm.executeUpdate();
                    if (i == 1) 
                    {
                        Message.thongTin(Alert.AlertType.INFORMATION,
                        "Xóa câu hỏi " + txtCauhoi.getText() + " thành công", 
                        "Thông báo!", "Xóa thành công!");
                        loadDulieu();
                        resetText();
                    }else {
                    Message.thongTin(Alert.AlertType.INFORMATION,
                        "Xóa câu hỏi thất bại vui lòng kiểm tra lại", 
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
                    "Question ID hiện đang rỗng không thể xóa", 
                    "Thông báo!", "Xóa thất bại!");
                }               
         }             
        
    }
}
