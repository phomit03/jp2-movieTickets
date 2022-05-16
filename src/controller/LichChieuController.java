package controller;

import DAO_repository.LichChieuRepository;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LichChieuController implements Initializable {
    public TableView<LichChieu> tbLC;
    public TableColumn<LichChieu, Integer> lcMa;
    public TableColumn<LichChieu, Integer> lcMaPhim;
    public TableColumn<LichChieu, Integer> lcMaPhong;
    public TableColumn<LichChieu, String> lcNgayChieu;
    public TableColumn<LichChieu, String> lcGioChieu;
    public TableColumn<LichChieu, Double> lcGiaVe;
    public TableColumn<LichChieu, Button> lcEdit;
    public TableColumn<LichChieu, Button> lcDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lcMa.setCellValueFactory(new PropertyValueFactory<>("maLC"));
        lcMaPhim.setCellValueFactory(new PropertyValueFactory<>("maPhim"));
        lcMaPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        lcNgayChieu.setCellValueFactory(new PropertyValueFactory<>("ngayChieu"));
        lcGioChieu.setCellValueFactory(new PropertyValueFactory<>("gioChieu"));
        lcGiaVe.setCellValueFactory(new PropertyValueFactory<>("giaVe"));
        lcEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        lcDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        try {
            LichChieuRepository lcr = new LichChieuRepository();
            ArrayList<LichChieu> arrayList = lcr.listDataLC();   //gọi mảng chứa database từ DAO

            ObservableList<LichChieu> listViewLC = FXCollections.observableArrayList(); //khai báo mảng chứa data tbView
            listViewLC.addAll(arrayList);
            tbLC.setItems(listViewLC); //in ra table
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void backHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/HomeMenu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void addLC(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-form/FormLichChieu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }
}



