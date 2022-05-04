package controller;

import DAO.VeResponsity;
import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Ve;

import java.io.IOException;

public class FormVeController {
    public TextField vMaInput;
    public TextField vMaLCInput;
    public TextField vMaGheInput;
    public Text errorMsg;

    public Ve editData;

    public void setEditData(Ve editData){
        this.editData = editData;
        this.vMaInput.setText(editData.getMaVe().toString());
        this.vMaLCInput.setText(editData.getMaLC().toString());
        this.vMaGheInput.setText(editData.getMaGhe());

        this.vMaInput.setDisable(true);
    }

    public void backListVe() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListVe.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void submitVe(ActionEvent actionEvent) {
        String MaVe = this.vMaInput.getText();
        String MaLC = this.vMaLCInput.getText();
        String MaGhe = this.vMaGheInput.getText();

        try {
            if (MaLC.isEmpty() || MaGhe.isEmpty()){
                throw new Exception("Please enter full product information!");
            }

            VeResponsity vr = new VeResponsity();
            if (this.editData == null) {
                Ve v = new Ve(Integer.parseInt(MaVe), Integer.parseInt(MaLC), MaGhe);
                vr.addVe(v);
            } else {
                Ve v = new Ve(Integer.parseInt(MaVe), Integer.parseInt(MaLC), MaGhe);
                vr.editVe(v);
            }
            this.backListVe();

        } catch (NumberFormatException nf){
            errorMsg.setVisible(true);
            errorMsg.setFill(Color.RED);
            errorMsg.setText(nf.getMessage());
        }
        catch (Exception e) {
            errorMsg.setVisible(true);
            errorMsg.setFill(Color.RED);
            errorMsg.setText(e.getMessage());
        }
    }
}
