package InterfaceDAO;

import model.LichChieu;

import java.util.ArrayList;

public interface ILichChieuInterface {
    ArrayList<LichChieu> listDataLC();
    void addLC(LichChieu lichchieu);
    void editLC(LichChieu lichchieu);
    void deleteLC(LichChieu lichchieu);
}
