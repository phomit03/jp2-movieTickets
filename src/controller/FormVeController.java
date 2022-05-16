package controller;

import DAO_repository.LichChieuRepository;
import DAO_repository.VeRepository;
import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.LichChieu;
import model.Ve;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FormVeController implements Initializable {
    public TextField vMaInput;
    public ComboBox<LichChieu> vMaLCInput;
    public TextField vMaGheInput;
    public Text errorMsg;

    public Ve editData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LichChieuRepository lcr = new LichChieuRepository();
        ArrayList<LichChieu> arrayLC = lcr.listDataLC();
        ObservableList<LichChieu> comboboxMaLC = FXCollections.observableArrayList();
        comboboxMaLC.addAll(arrayLC);
        vMaLCInput.setItems(comboboxMaLC);
        vMaLCInput.getValue();
    }

    public void setEditData(Ve editData){
        this.editData = editData;
        this.vMaInput.setText(editData.getMaVe().toString());
        //set value combobox
        for (int i = 0; i < this.vMaLCInput.getItems().size(); i++) {
            //chay vòng lặp để set tất cả value có trong combobox
            if (this.vMaLCInput.getItems().get(i).getMaLC().equals(editData.getMaLC())) {
                //nếu value có trong mảng (combobox) = value được get (editData)
                //thì setValue (hiển thị value đó)
                vMaLCInput.setValue(this.vMaLCInput.getItems().get(i));
                break;
            }
        }
        this.vMaGheInput.setText(editData.getMaGhe());

        this.vMaInput.setDisable(true);
    }

    public void backListVe() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListVe.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void submitVe(ActionEvent actionEvent) {
        String MaVe = this.vMaInput.getText();
        LichChieu MaLC = this.vMaLCInput.getSelectionModel().getSelectedItem();
        String MaGhe = this.vMaGheInput.getText();

        try {
            if (MaVe.equals("") || MaGhe.equals("")){
                throw new Exception("Please enter full product information!");
            }

            VeRepository vr = new VeRepository();
            if (this.editData == null) {
                Ve v = new Ve(Integer.parseInt(MaVe), MaLC.getMaLC(), MaGhe);
                vr.addVe(v);
            } else {
                Ve v = new Ve(Integer.parseInt(MaVe), MaLC.getMaLC(), MaGhe);
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
