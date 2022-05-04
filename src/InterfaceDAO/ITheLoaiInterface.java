package InterfaceDAO;

import model.TheLoai;

import java.util.ArrayList;

public interface ITheLoaiInterface {
    ArrayList<TheLoai> listDataTL();
    void addTL(TheLoai theLoai);
    void editTL(TheLoai theLoai);
    void deleteTL(TheLoai theLoai);
}
