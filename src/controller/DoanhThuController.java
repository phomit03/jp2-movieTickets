package controller;

import app.Main;
import database.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DoanhThu;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DoanhThuController implements Initializable {
    public TableView<DoanhThu> tbDoanhThuPhim;
    public TableColumn<DoanhThu, String> dtMaPhim;
    public TableColumn<DoanhThu, String> dtTenPhim;
    public TableColumn<DoanhThu, String> dtDoanhThu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dtMaPhim.setCellValueFactory(new PropertyValueFactory<>("maPhim"));
        dtTenPhim.setCellValueFactory(new PropertyValueFactory<>("tenPhim"));
        dtDoanhThu.setCellValueFactory(new PropertyValueFactory<>("doanhThu"));

        try {
            //truy vấn lấy ra tên phim
            String txt_sql = "SELECT * FROM tbphim";
            //truy vấn lấy mã phim để tổng doanh thu
            String txt_sql1 = "SELECT tblichchieu.MaPhim, SUM(tblichchieu.GiaVe) AS DoanhThu FROM tblichchieu INNER JOIN tbve ON tblichchieu.MaLC=tbve.MaLC GROUP BY tblichchieu.MaPhim";

            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(txt_sql);
            PreparedStatement stt1 = conn.getStatement(txt_sql1);

            ResultSet rs = stt.executeQuery(txt_sql);
            ResultSet rs1 = stt1.executeQuery(txt_sql1);

            ArrayList<DoanhThu> listDataDT = new ArrayList<>();   //mảng database
            while (rs.next()&& rs1.next()) {
                model.DoanhThu ctm = new model.DoanhThu(
                        rs1.getInt("maPhim"),
                        rs.getString("tenPhim"),
                        rs1.getDouble("doanhThu")
                );
                listDataDT.add(ctm);
            }
            ObservableList<DoanhThu> ViewDT = FXCollections.observableArrayList();  //mảng table view
            ViewDT.addAll(listDataDT);
            tbDoanhThuPhim.setItems(ViewDT);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void backHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/HomeMenu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

}
