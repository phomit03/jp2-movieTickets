package controller;

import DAO_repository.PhimRepository;
import DAO_repository.TheLoaiRepository;
import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Phim;
import model.TheLoai;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FormPhimController implements Initializable {
    public TextField pMaInput, pTenInput, pThoiLuongInput, pDaoDienInput,
            pHangSXInput;
    public DatePicker pNgayKCInput, pNgayKTInput;
    public ComboBox<TheLoai> pMaTLInput;
    public ComboBox<String> pTrangThaiInput;

    public Text errorMsg;

    public Phim editData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ObservableList<String> comboboxMaTL = FXCollections.observableArrayList("201", "202", "203", "204", "205");
        TheLoaiRepository tlr = new TheLoaiRepository();
        ArrayList<TheLoai> arrayTL = tlr.listDataTL();
        ObservableList<TheLoai> comboboxMaTL = FXCollections.observableArrayList();
        comboboxMaTL.addAll(arrayTL);
        pMaTLInput.setItems(comboboxMaTL);
        pMaTLInput.getValue();

        ObservableList<String> comboboxTT = FXCollections.observableArrayList("Stop showing", "Now showing", "Coming soon");
        pTrangThaiInput.setItems(comboboxTT);
    }


    public void setEditData(Phim editData) {
        this.editData = editData;
        this.pMaInput.setText(editData.getMaPhim().toString());
        this.pTenInput.setText(editData.getTenPhim());
        this.pThoiLuongInput.setText(editData.getThoiLuong().toString());
        this.pDaoDienInput.setText(editData.getDaoDien());
        this.pHangSXInput.setText(editData.getHangSanXuat());
        //set value combobox
        for (int i = 0; i < this.pMaTLInput.getItems().size(); i++) {
//          System.out.println(this.pMaTLInput.getItems().get(i).getMaTL());

            //chay vòng lặp để set tất cả value có trong combobox
            if (this.pMaTLInput.getItems().get(i).getMaTL().equals(editData.getMaTL())) {
                //nếu value có trong mảng (combobox) = value được get (editData)
                //thì setValue (hiển thị value đó)
                pMaTLInput.setValue(this.pMaTLInput.getItems().get(i));
                break;
            }
        }
        //set value date picker
        this.pNgayKCInput.setValue(editData.getNgayKhoiChieu().toLocalDate()); //convert kiểu dữ liệu sang LocalDate
        this.pNgayKTInput.setValue(editData.getNgayKetThuc().toLocalDate());
        //set value combobox
        for (int i = 0; i < this.pTrangThaiInput.getItems().size(); i++) {
            if (this.pTrangThaiInput.getItems().get(i).equals(editData.getTrangThai())) {
                pTrangThaiInput.setValue(this.pTrangThaiInput.getItems().get(i));
                break;
            }
        }

        this.pMaInput.setDisable(true); //setDisable: không cho chỉnh sửa mã
    }


    public void backListPhim() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListPhim.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void submitPhim(ActionEvent event) {
        String MaPhim = this.pMaInput.getText();
        String TenPhim = this.pTenInput.getText();
        String ThoiLuong = this.pThoiLuongInput.getText();
        String DaoDien = this.pDaoDienInput.getText();
        String HangSX = this.pHangSXInput.getText();
        //get value combobox
        TheLoai MaTL = pMaTLInput.getSelectionModel().getSelectedItem();
        //DatePicker
        LocalDate NgayKC = this.pNgayKCInput.getValue();
        LocalDate NgayKT = this.pNgayKTInput.getValue();
        //get value combobox
        String TrangThai = this.pTrangThaiInput.getSelectionModel().getSelectedItem();

        try {
            if (MaPhim.equals("") || TenPhim.equals("") || ThoiLuong.isEmpty() || DaoDien.equals("") ||
                    HangSX.equals("")) {
                throw new Exception("Please enter full product information!");
            }

            PhimRepository pr = new PhimRepository();
            if (this.editData == null) {
                Phim p = new Phim(Integer.parseInt(MaPhim), TenPhim, Time.valueOf(ThoiLuong), DaoDien, HangSX,
                        MaTL.getMaTL(), Date.valueOf(NgayKC), Date.valueOf(NgayKT), TrangThai);
                pr.addPhim(p);
            } else {
                Phim p = new Phim(Integer.parseInt(MaPhim), TenPhim, Time.valueOf(ThoiLuong), DaoDien, HangSX,
                        MaTL.getMaTL(), Date.valueOf(NgayKC), Date.valueOf(NgayKT), TrangThai);
                pr.editPhim(p);
            }
            this.backListPhim();    //tu dong back ve list sau khi duoc add hoac edit

        } catch (NumberFormatException nf) {
            errorMsg.setVisible(true);
            errorMsg.setFill(Color.RED);
            errorMsg.setText(nf.getMessage());
        } catch (Exception e) {
            errorMsg.setVisible(true);
            errorMsg.setFill(Color.RED);
            errorMsg.setText(e.getMessage());
        }

    }
}
