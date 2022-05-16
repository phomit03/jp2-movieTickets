package controller;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void listPhim(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListPhim.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void listLC(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListLichChieu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void listPC(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListPhongChieu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void listTL(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListTheLoai.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void listVe(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListVe.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void doanhThu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/DoanhThu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }
}
