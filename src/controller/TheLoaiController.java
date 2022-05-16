package controller;

import DAO_repository.TheLoaiRepository;
import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TheLoai;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TheLoaiController implements Initializable {
    public TableView<TheLoai> tbTL;
    public TableColumn<TheLoai, Integer> tlMa;
    public TableColumn<TheLoai, String> tlTen;
    public TableColumn<TheLoai, Button> tlEdit;
    public TableColumn<TheLoai, Button> tlDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tlMa.setCellValueFactory(new PropertyValueFactory<>("maTL"));
        tlTen.setCellValueFactory(new PropertyValueFactory<>("tenTL"));
        tlEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        tlDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        try {
            TheLoaiRepository tlr = new TheLoaiRepository();
            ArrayList<TheLoai> arrayList = tlr.listDataTL();   //gọi mảng chứa database từ DAO

            ObservableList<TheLoai> listViewTL = FXCollections.observableArrayList(); //khai báo mảng chứa data tbView
            listViewTL.addAll(arrayList);
            tbTL.setItems(listViewTL); //in ra table
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void backHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/HomeMenu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void addTL(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-form/FormTheLoai.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

}

