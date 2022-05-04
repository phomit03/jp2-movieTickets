package controller;

import DAO.LichChieuResponsity;
import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.LichChieu;

import java.sql.Date;
import java.sql.Time;

public class FormLichChieuController {
    public TextField lcMaInput, lcMaPhongInput, lcMaPhimInput,
            lcNgayChieuInput, lcGioChieuInput, lcGiaVeInput;
    public Text errorMsg;

    public LichChieu editData;

    public void setEditData(LichChieu editData) {
        this.editData = editData;
        this.lcMaInput.setText(editData.getMaLC().toString());
        this.lcMaPhimInput.setText(editData.getMaPhim().toString());
        this.lcMaPhongInput.setText(editData.getMaPhong().toString());
        this.lcNgayChieuInput.setText(editData.getNgayChieu().toString());
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
        String maphim = this.lcMaPhimInput.getText();
        String maphong = this.lcMaPhongInput.getText();
        String ngaychieu = this.lcNgayChieuInput.getText();
        String giochieu = this.lcGioChieuInput.getText();
        String giave = this.lcGiaVeInput.getText();

        try {
            if (maphim.isEmpty() || maphong.isEmpty() || ngaychieu.equals("")  || giochieu.equals("") || giave.isEmpty()){
                throw new Exception("Please enter full product information!");
            }

            LichChieuResponsity lcr = new LichChieuResponsity();
            if(this.editData == null){ //nếu input rỗng thì add
                LichChieu lc = new LichChieu(Integer.parseInt(malc), Integer.parseInt(maphim), Integer.parseInt(maphong),
                        Date.valueOf(ngaychieu), Time.valueOf(giochieu), Double.parseDouble(giave));
                lcr.addLC(lc);
            } else {    //edit
                LichChieu lc = new LichChieu(Integer.parseInt(malc), Integer.parseInt(maphim), Integer.parseInt(maphong),
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
