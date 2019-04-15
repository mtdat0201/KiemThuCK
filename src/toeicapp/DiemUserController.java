/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import Action.Connect;
import Model.Diem;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class DiemUserController implements Initializable {

    @FXML
    private TableView<Diem> tableDiem;
    @FXML
    private TableColumn<?, ?> colUsername;
    @FXML
    private TableColumn<?, ?> colDiem;
    @FXML
    private TableColumn<?, ?> colNgaythuchien;
    @FXML
    private TableColumn<?, ?> colIdDiem;
    private ObservableList<Diem> listDiem;
    private Connection connect;
    private PreparedStatement pStm;
    private ResultSet resultSet;
    
    private void start (final Stage stage) throws IOException{
        Parent pa = FXMLLoader.load(getClass().getResource("/toeicapp/DiemUser.fxml"));
        Scene scene = new Scene(pa);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Bảng điểm User");       
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = Connect.connection();
        listDiem = FXCollections.observableArrayList();
        loadDulieu();
        loadCol();
    }    
    
    private void loadDulieu()
    {
        listDiem.clear();
        try
        {            
            String sql = "SELECT user_name, diem, ngaythuchien, id_diem From diem";
            pStm = connect.prepareStatement(sql);
            resultSet = pStm.executeQuery();
            while (resultSet.next()) {
                listDiem.add(new Diem(resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getDate(3),
                        resultSet.getString(4)));
            }
            tableDiem.setItems(listDiem);
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    private void loadCol()
    {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colDiem.setCellValueFactory(new PropertyValueFactory<>("diem"));        
        colNgaythuchien.setCellValueFactory(new PropertyValueFactory<>("ngayThuchien"));
        colIdDiem.setCellValueFactory(new PropertyValueFactory<>("idDiem"));
    }
    
}
