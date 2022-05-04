package InterfaceDAO;

import model.PhongChieu;

import java.util.ArrayList;

public interface IPhongChieuInterface {
    ArrayList<PhongChieu> listDataPC();
    void addPC(PhongChieu phongChieu);
    void editPC(PhongChieu phongChieu);
    void deletePC(PhongChieu phongChieu);
}
