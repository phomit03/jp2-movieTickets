package model;

import DAO_repository.LichChieuRepository;
import app.Main;
import controller.FormLichChieuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

public class LichChieu {
    public Integer maLC, maPhim, maPhong;
    public Date ngayChieu;
    public Time gioChieu;
    public Double giaVe;
    public Button edit, delete;

    //contructor
    public LichChieu(){

    }
    public LichChieu(Integer maLC, Integer maPhim, Integer maPhong, Date ngayChieu, Time gioChieu, Double giaVe) {
        this.maLC = maLC;
        this.maPhim = maPhim;
        this.maPhong = maPhong;
        this.ngayChieu = ngayChieu;
        this.gioChieu = gioChieu;
        this.giaVe = giaVe;
        this.edit = new Button("Edit");
        this.delete = new Button("Delete");

        this.edit.setOnAction(event -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-form/FormLichChieu.fxml"));
                Parent root = loader.load();
                FormLichChieuController flc = loader.getController();    //gọi controller kèm giao diện
                flc.setEditData(this);
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        this.delete.setOnAction(event -> {
            try {
                LichChieuRepository lcr = new LichChieuRepository();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete File");
                alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
                alert.setContentText("MaLichChieu: " + this.getMaLC());

                // option != null.
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {
                    this.delete.setText("No selection!");
                } else if (option.get() == ButtonType.OK) {
                    this.delete.setText("File deleted!");
                    lcr.deleteLC(this); //delete
                } else if (option.get() == ButtonType.CANCEL) {
                    this.delete.setText("Cancelled!");
                } else {
                    this.delete.setText("-");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-list/ListLichChieu.fxml"));
                Parent root = loader.load();
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){

            }
        });

    }

    //g&s
    public Integer getMaLC() {
        return maLC;
    }

    public void setMaLC(Integer maLC) {
        this.maLC = maLC;
    }

    public Integer getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(Integer maPhim) {
        this.maPhim = maPhim;
    }

    public Integer getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(Integer maPhong) {
        this.maPhong = maPhong;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public Time getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(Time gioChieu) {
        this.gioChieu = gioChieu;
    }

    public Double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(Double giaVe) {
        this.giaVe = giaVe;
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

    @Override
    public String toString() {
        return this.getMaLC() + ": MaPhim: " + this.getMaPhim() + " - MaPhong: " + this.getMaPhong()
                + " - NgayChieu: " + this.getNgayChieu() + " - GioChieu: " + this.getGioChieu();
    }
}
