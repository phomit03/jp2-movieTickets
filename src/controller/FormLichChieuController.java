package controller;

import DAO_repository.LichChieuRepository;
import DAO_repository.PhimRepository;
import DAO_repository.PhongChieuRepository;
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
import model.LichChieu;
import model.Phim;
import model.PhongChieu;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FormLichChieuController implements Initializable {
    public TextField lcMaInput, lcGioChieuInput, lcGiaVeInput;
    public ComboBox<Phim> lcMaPhimInput;
    public ComboBox<PhongChieu> lcMaPhongInput;
    public DatePicker lcNgayChieuInput;
    public Text errorMsg;

    public LichChieu editData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PhimRepository pr = new PhimRepository();
        ArrayList<Phim> arrayPhim = pr.listDataPhim();
        ObservableList<Phim> comboboxMaPhim = FXCollections.observableArrayList();
        comboboxMaPhim.addAll(arrayPhim);
        lcMaPhimInput.setItems(comboboxMaPhim);
        lcMaPhimInput.getValue();

        PhongChieuRepository pcr = new PhongChieuRepository();
        ArrayList<PhongChieu> arrayPC = pcr.listDataPC();
        ObservableList<PhongChieu> comboboxMaPhong = FXCollections.observableArrayList();
        comboboxMaPhong.addAll(arrayPC);
        lcMaPhongInput.setItems(comboboxMaPhong);
        lcMaPhongInput.getValue();
    }

    public void setEditData(LichChieu editData) {
        this.editData = editData;
        this.lcMaInput.setText(editData.getMaLC().toString());
        //set value combobox
        for (int i = 0; i < this.lcMaPhimInput.getItems().size(); i++) {
            //chay vòng lặp để set tất cả value có trong combobox
            if (this.lcMaPhimInput.getItems().get(i).getMaPhim().equals(editData.getMaPhim())) {
                //nếu value có trong mảng (combobox) = value được get (editData)
                //thì setValue (hiển thị value đó)
                lcMaPhimInput.setValue(this.lcMaPhimInput.getItems().get(i));
                break;
            }
        }
        for (int i = 0; i < this.lcMaPhongInput.getItems().size(); i++) {
            //chay vòng lặp để set tất cả value có trong combobox
            if (this.lcMaPhongInput.getItems().get(i).getMaPhong().equals(editData.getMaPhong())) {
                //nếu value có trong mảng (combobox) = value được get (editData)
                //thì setValue (hiển thị value đó)
                lcMaPhongInput.setValue(this.lcMaPhongInput.getItems().get(i));
                break;
            }
        }
        //set value DatePicker
        this.lcNgayChieuInput.setValue(editData.getNgayChieu().toLocalDate());  //convert value sang LocalDate
        this.lcGioChieuInput.setText(editData.getGioChieu().toString());
        this.lcGiaVeInput.setText(editData.getGiaVe().toString());

        this.lcMaInput.setDisable(true);
    }

    public void backListLC() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListLichChieu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void submitLC(ActionEvent event){
        String malc = this.lcMaInput.getText();
        Phim maphim = lcMaPhimInput.getSelectionModel().getSelectedItem();
        PhongChieu maphong = lcMaPhongInput.getSelectionModel().getSelectedItem();
        LocalDate ngaychieu = this.lcNgayChieuInput.getValue();
        String giochieu = this.lcGioChieuInput.getText();
        String giave = this.lcGiaVeInput.getText();

        try {
            if (malc.equals("") || giochieu.equals("") || giave.isEmpty()){
                throw new Exception("Please enter full product information!");
            }
            if (Double.parseDouble(giave) < 0){
                throw new Exception("Fare must be positive");
            }

            LichChieuRepository lcr = new LichChieuRepository();
            if(this.editData == null){ //nếu input rỗng thì add
                LichChieu lc = new LichChieu(Integer.parseInt(malc), maphim.getMaPhim(), maphong.getMaPhong(),
                        Date.valueOf(ngaychieu), Time.valueOf(giochieu), Double.parseDouble(giave));
                lcr.addLC(lc);
            } else {    //edit
                LichChieu lc = new LichChieu(Integer.parseInt(malc), maphim.getMaPhim(), maphong.getMaPhong(),
                        Date.valueOf(ngaychieu), Time.valueOf(giochieu), Double.parseDouble(giave));
                lcr.editLC(lc);
            }

            //dữ liệu đổ vào assigment01.database, từ assigment01.database tiếp tục đổ ngược lại tbStudent
            this.backListLC();  //tự động back lại nếu dữ liệu up lên và đổ về thành công

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
