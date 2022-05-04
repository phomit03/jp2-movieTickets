package controller;

import DAO.PhimResponsity;
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
import model.Phim;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;

import java.util.ResourceBundle;

public class FormPhimController implements Initializable {
    public TextField pMaInput, pTenInput, pThoiLuongInput, pDaoDienInput,
            pHangSXInput, pNgayKCInput, pNgayKTInput, pTrangThaiInput;
    public Text errorMsg;

    public Phim editData;

    public ComboBox<String> pMaTLInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> ls = FXCollections.observableArrayList("201", "202", "203", "204", "205");

        pMaTLInput.setItems(ls);
        System.out.println(pMaTLInput.getItems().size());
    }



    public void setEditData(Phim editData) {
        this.editData = editData;
        this.pMaInput.setText(editData.getMaPhim().toString());
        this.pTenInput.setText(editData.getTenPhim());
        this.pThoiLuongInput.setText(editData.getThoiLuong().toString());
        this.pDaoDienInput.setText(editData.getDaoDien());
        this.pHangSXInput.setText(editData.getHangSanXuat());

        this.pNgayKCInput.setText(editData.getNgayKhoiChieu().toString());
        this.pNgayKTInput.setText(editData.getNgayKetThuc().toString());
        this.pTrangThaiInput.setText(editData.getTrangThai());

        this.pMaInput.setDisable(true);
    }


    public void backListPhim() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListPhim.fxml"));
        Main.rootStage.setScene(new Scene(root, 1300, 650));
    }

    public void submitPhim(ActionEvent event){
        String MaPhim = this.pMaInput.getText();
        String TenPhim = this.pTenInput.getText();
        String ThoiLuong = this.pThoiLuongInput.getText();
        String DaoDien = this.pDaoDienInput.getText();
        String HangSX = this.pHangSXInput.getText();

        String MaTL = this.pMaTLInput.getSelectionModel().toString();

        String NgayKC = this.pNgayKCInput.getText();
        String NgayKT = this.pNgayKTInput.getText();
        String TrangThai = this.pTrangThaiInput.getText();

        try{
            if (TenPhim.equals("") || ThoiLuong.isEmpty() || DaoDien.equals("") ||
            HangSX.equals("") || NgayKC.equals("") || NgayKT.equals("") || TrangThai.equals("")){
                throw new Exception("Please enter full product information!");
            }

            PhimResponsity pr = new PhimResponsity();
            if(this.editData == null) {
                Phim p = new Phim(Integer.parseInt(MaPhim), TenPhim, Time.valueOf(ThoiLuong), DaoDien, HangSX,
                        Integer.parseInt(MaTL), Date.valueOf(NgayKC), Date.valueOf(NgayKT), TrangThai);
                pr.addPhim(p);
            } else {
                Phim p = new Phim(Integer.parseInt(MaPhim), TenPhim, Time.valueOf(ThoiLuong), DaoDien, HangSX,
                        Integer.parseInt(MaTL), Date.valueOf(NgayKC), Date.valueOf(NgayKT), TrangThai);
                pr.editPhim(p);
            }
            this.backListPhim();    //tu dong back ve list sau khi duoc add hoac edit

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
