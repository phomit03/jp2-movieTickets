package controller;

import DAO.PhongChieuResponsity;
import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.PhongChieu;

public class FormPhongChieuController {
    public TextField pcMaInput, pcTenInput, pcSoGheInput;
    public Text errorMsg;

    public PhongChieu editData;

    public void setEditData(PhongChieu editData) {
        this.editData = editData;
        this.pcMaInput.setText(editData.getMaPhong().toString());
        this.pcTenInput.setText(editData.getTenPhong());
        this.pcSoGheInput.setText(editData.getTongSoGhe().toString());

        this.pcMaInput.setDisable(true);
    }

    public void backListPC() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListPhongChieu.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void submitPC(ActionEvent event) {
        String MaPhong = this.pcMaInput.getText();
        String TenPhong = this.pcTenInput.getText();
        String TongSoGhe = this.pcSoGheInput.getText();

        try {
            if (TenPhong.equals("") || TongSoGhe.isEmpty()) {
                throw new Exception("Please enter full product information!");
            }

            PhongChieuResponsity pcr = new PhongChieuResponsity();
            if (this.editData == null) { //nếu input rỗng thì add
                PhongChieu pc = new PhongChieu(Integer.parseInt(MaPhong), TenPhong, Integer.parseInt(TongSoGhe));
                pcr.addPC(pc);
            } else {    //edit
                PhongChieu pc = new PhongChieu(Integer.parseInt(MaPhong), TenPhong, Integer.parseInt(TongSoGhe));
                pcr.editPC(pc);
            }

            //dữ liệu đổ vào assigment01.database, từ assigment01.database tiếp tục đổ ngược lại tbStudent
            this.backListPC();  //tự động back lại nếu dữ liệu up lên và đổ về thành công

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
