package model;

import DAO_repository.PhimRepository;
import app.Main;
import controller.FormPhimController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

public class Phim {
    Integer maPhim;
    String tenPhim;
    Time thoiLuong;
    String daoDien;
    String hangSanXuat;
    Integer maTL;
    Date ngayKhoiChieu, ngayKetThuc;
    String trangThai;
    Button edit, delete;

    //contructor
    public Phim(){

    }

    public Phim(Integer maPhim, String tenPhim, Time thoiLuong, String daoDien, String hangSanXuat, Integer maTL, Date ngayKhoiChieu, Date ngayKetThuc, String trangThai) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
        this.daoDien = daoDien;
        this.hangSanXuat = hangSanXuat;
        this.maTL = maTL;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;

        this.edit = new Button("Edit");
        this.delete = new Button("Delete");

        this.edit.setOnAction(event -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-form/FormPhim.fxml"));
                Parent root = loader.load();
                FormPhimController fpc = loader.getController();    //gọi controller kèm giao diện
                fpc.setEditData(this);
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        this.delete.setOnAction(event -> {
            try {
                PhimRepository pr = new PhimRepository();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete File");
                alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
                alert.setContentText("MaPhim: " + this.getMaPhim());

                // option != null.
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {
                    this.delete.setText("No selection!");
                } else if (option.get() == ButtonType.OK) {
                    this.delete.setText("File deleted!");
                    pr.deletePhim(this); //delete
                } else if (option.get() == ButtonType.CANCEL) {
                    this.delete.setText("Cancelled!");
                } else {
                    this.delete.setText("-");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-list/ListPhim.fxml"));
                Parent root = loader.load();
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){

            }
        });
    }


    //getter & setter
    public Integer getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(Integer maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public Time getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(Time thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public Integer getMaTL() {
        return maTL;
    }

    public void setMaTL(Integer maTL) {
        this.maTL = maTL;
    }

    public Date getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(Date ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
        return this.getMaPhim() + " - Phim: " + getTenPhim() + " - TrangThai: " + getTrangThai();
    }
}
