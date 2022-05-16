package controller;

import DAO_repository.TheLoaiRepository;
import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.TheLoai;

public class FormTheLoaiController {
    public TextField tlMaInput, tlTenInput;
    public Text errorMsg;

    public TheLoai editData;

    public void setEditData(TheLoai editData) {
        this.editData = editData;
        this.tlMaInput.setText(editData.getMaTL().toString());
        this.tlTenInput.setText(editData.getTenTL());

        this.tlMaInput.setDisable(true);
    }

    public void backListTL() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view-list/ListTheLoai.fxml"));
        Main.rootStage.setScene(new Scene(root, 1200, 650));
    }

    public void submitTL(ActionEvent event){
        String MaTL = this.tlMaInput.getText();
        String TenTL = this.tlTenInput.getText();

        try {
            if (TenTL.isEmpty()){
                throw new Exception("Please enter full product information!");
            }

            TheLoaiRepository tlr = new TheLoaiRepository();
            if(this.editData == null){ //nếu input rỗng thì add
                TheLoai lc = new TheLoai(Integer.parseInt(MaTL), TenTL);
                tlr.addTL(lc);
            } else {    //edit
                TheLoai lc = new TheLoai(Integer.parseInt(MaTL), TenTL);
                tlr.editTL(lc);
            }
            //dữ liệu đổ vào assigment01.database, từ assigment01.database tiếp tục đổ ngược lại tbStudent
            this.backListTL();  //tự động back lại nếu dữ liệu up lên và đổ về thành công

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
