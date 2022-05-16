package controller;

import DAO_repository.PhimRepository;
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
import model.Phim;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhimController implements Initializable {
    public TableView<Phim> tbPhim;
    public TableColumn<Phim, String> pMa;
    public TableColumn<Phim, String> pTen;
    public TableColumn<Phim, String> pDaoDien;
    public TableColumn<Phim, String> pThoiLuong;
    public TableColumn<Phim, String> pHangSX;
    public TableColumn<Phim, String> pTheLoai;
    public TableColumn<Phim, String> pNgayKC;
    public TableColumn<Phim, String> pNgayKT;
    public TableColumn<Phim, String> pTrangThai;
    public TableColumn<Phim, Button> pEdit;
    public TableColumn<Phim, Button> pDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pMa.setCellValueFactory(new PropertyValueFactory<>("maPhim"));
        pTen.setCellValueFactory(new PropertyValueFactory<>("tenPhim"));
        pThoiLuong.setCellValueFactory(new PropertyValueFactory<>("thoiLuong"));
        pDaoDien.setCellValueFactory(new PropertyValueFactory<>("daoDien"));
        pHangSX.setCellValueFactory(new PropertyValueFactory<>("hangSanXuat"));
        pTheLoai.setCellValueFactory(new PropertyValueFactory<>("maTL"));
        pNgayKC.setCellValueFactory(new PropertyValueFactory<>("ngayKhoiChieu"));
        pNgayKT.setCellValueFactory(new PropertyValueFactory<>("ngayKetThuc"));
        pTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        pEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        pDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        try {
            PhimRepository pr = new PhimRepository();
            ArrayList<Phim> arrayList = pr.listDataPhim();   //gọi mảng chứa database từ DAO

            ObservableList<Phim> listViewPhim = FXCollections.observableArrayList(); //khai báo mảng chứa data tbView
            listViewPhim.addAll(arrayList);
            tbPhim.setItems(listViewPhim); //in ra table
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void backHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/HomeMenu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void addPhim(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-form/FormPhim.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }
}




