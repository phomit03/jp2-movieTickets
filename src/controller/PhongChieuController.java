package controller;

import DAO.LichChieuResponsity;
import DAO.PhongChieuResponsity;
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
import model.LichChieu;
import model.PhongChieu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhongChieuController implements Initializable {
    public TableView<PhongChieu> tbPC;
    public TableColumn<PhongChieu, String> pcMa;
    public TableColumn<PhongChieu, String> pcTen;
    public TableColumn<PhongChieu, String> pcSoGhe;
    public TableColumn<PhongChieu, Button> pcEdit;
    public TableColumn<PhongChieu, Button> pcDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pcMa.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        pcTen.setCellValueFactory(new PropertyValueFactory<>("tenPhong"));
        pcSoGhe.setCellValueFactory(new PropertyValueFactory<>("tongSoGhe"));
        pcEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        pcDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        try {
            PhongChieuResponsity pcr = new PhongChieuResponsity();
            ArrayList<PhongChieu> arrayList = pcr.listDataPC();   //gọi mảng chứa database từ DAO

            ObservableList<PhongChieu> listViewPC = FXCollections.observableArrayList(); //khai báo mảng chứa data tbView
            listViewPC.addAll(arrayList);
            tbPC.setItems(listViewPC); //in ra table
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void backHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/HomeMenu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void addPC(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-form/FormPhongChieu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

}
