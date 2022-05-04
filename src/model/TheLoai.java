package model;

import DAO.TheLoaiResponsity;
import app.Main;
import controller.FormTheLoaiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class TheLoai {
    public Integer maTL;
    public String tenTL;
    public Button edit, delete;

    //contructor
    public TheLoai(){

    }
    public TheLoai(Integer maTL, String tenTL) {
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.edit = new Button("Edit");
        this.delete = new Button("Delete");

        this.edit.setOnAction(event -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-form/FormTheLoai.fxml"));
                Parent root = loader.load();
                FormTheLoaiController ftl = loader.getController();    //gọi controller kèm giao diện
                ftl.setEditData(this);
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        this.delete.setOnAction(event -> {
            try{
                TheLoaiResponsity tlr = new TheLoaiResponsity();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete File");
                alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
                alert.setContentText("MaTheLoai: " + this.getMaTL());

                // option != null.
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {
                    this.delete.setText("No selection!");
                } else if (option.get() == ButtonType.OK) {
                    this.delete.setText("File deleted!");
                    tlr.deleteTL(this); //delete
                } else if (option.get() == ButtonType.CANCEL) {
                    this.delete.setText("Cancelled!");
                } else {
                    this.delete.setText("-");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-list/ListTheLoai.fxml"));
                Parent root = loader.load();
                Main.rootStage.setScene(new Scene(root,1200,650));
            } catch (Exception e){

            }
        });
    }

    //g&s
    public Integer getMaTL() {
        return maTL;
    }

    public void setMaTL(Integer maTL) {
        this.maTL = maTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
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
