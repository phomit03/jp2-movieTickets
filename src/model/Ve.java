package model;

import DAO.VeResponsity;
import app.Main;
import controller.FormVeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Ve {
    public Integer maVe;
    public Integer maLC;
    public String maGhe;
    public Button edit;
    public Button delete;

    //contructor
    public Ve(){
    }
    public Ve(Integer maVe, Integer maLC, String maGhe) {
        this.maVe = maVe;
        this.maLC = maLC;
        this.maGhe = maGhe;

        this.edit = new Button("Edit");
        this.edit.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-form/FormVe.fxml"));
                Parent root = loader.load();
                FormVeController fvc = loader.getController();
                fvc.setEditData(this);
                Main.rootStage.setScene(new Scene(root,1200,650));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        this.delete = new Button("Delete");
        this.delete.setOnAction(event -> {
            try {
                VeResponsity vr = new VeResponsity();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete File");
                alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
                alert.setContentText("MaVe: " + this.getMaVe());

                // option != null.
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {
                    this.delete.setText("No selection!");
                } else if (option.get() == ButtonType.OK) {
                    this.delete.setText("File deleted!");
                    vr.deleteVe(this); //delete
                } else if (option.get() == ButtonType.CANCEL) {
                    this.delete.setText("Cancelled!");
                } else {
                    this.delete.setText("-");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view-list/ListVe.fxml"));
                Parent root = loader.load();
                Main.rootStage.setScene(new Scene(root,1200,650));
            }catch (Exception e){

            }
        });
    }

    //get&setter
    public Integer getMaVe() {
        return maVe;
    }

    public void setMaVe(Integer maVe) {
        this.maVe = maVe;
    }

    public Integer getMaLC() {
        return maLC;
    }

    public void setMaLC(Integer maLC) {
        this.maLC = maLC;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
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
