package controller;

import DAO_repository.VeRepository;
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
import model.Ve;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VeController implements Initializable {
    public TableView<Ve> tbVe;
    public TableColumn<Ve, String> vMa;
    public TableColumn<Ve, String> vMaLC;
    public TableColumn<Ve, String> vMaGhe;
    public TableColumn<Ve, Button> vEdit;
    public TableColumn<Ve, Button> vDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vMa.setCellValueFactory(new PropertyValueFactory<>("maVe"));
        vMaLC.setCellValueFactory(new PropertyValueFactory<>("maLC"));
        vMaGhe.setCellValueFactory(new PropertyValueFactory<>("maGhe"));
        vEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        vDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        try {
            VeRepository vr = new VeRepository();
            ArrayList<Ve> arrayList = vr.listDataVe();   //gọi mảng chứa database từ DAO

            ObservableList<Ve> listViewVe = FXCollections.observableArrayList(); //khai báo mảng chứa data tbView
            listViewVe.addAll(arrayList);
            tbVe.setItems(listViewVe); //in ra table
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void backHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/HomeMenu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void addVe(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-form/FormVe.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }
}
