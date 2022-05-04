package model;

import DAO.PhimResponsity;
import DAO.PhongChieuResponsity;
import app.Main;
import controller.FormPhimController;
import controller.FormPhongChieuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PhongChieu {
    Integer MaPhong;
    String TenPhong;
    Integer TongSoGhe;
    Button edit, delete;


    //contructor
    public PhongChieu(){

    }
    public PhongChieu(Integer maPhong, String tenPhong, Integer tongSoGhe) {
        MaPhong = maPhong;
        TenPhong = tenPhong;
        TongSoGhe = tongSoGhe;
        this.edit = new Button("Edit");
        this.delete = new Button("Delete");

        this.edit.setOnAction(event -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-form/FormPhongChieu.fxml"));
                Parent root = loader.load();
                FormPhongChieuController fpcc = loader.getController();    //gọi controller kèm giao diện
                fpcc.setEditData(this);
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        this.delete.setOnAction(event -> {
            try {
                PhongChieuResponsity pr = new PhongChieuResponsity();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete File");
                alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
                alert.setContentText("MaPhongChieu: " + this.getMaPhong());

                // option != null.
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {
                    this.delete.setText("No selection!");
                } else if (option.get() == ButtonType.OK) {
                    this.delete.setText("File deleted!");
                    pr.deletePC(this); //delete
                } else if (option.get() == ButtonType.CANCEL) {
                    this.delete.setText("Cancelled!");
                } else {
                    this.delete.setText("-");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-list/ListPhongChieu.fxml"));
                Parent root = loader.load();
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){

            }
        });
    }

    //g&s
    public Integer getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(Integer maPhong) {
        MaPhong = maPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public Integer getTongSoGhe() {
        return TongSoGhe;
    }

    public void setTongSoGhe(Integer tongSoGhe) {
        TongSoGhe = tongSoGhe;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
