package InterfaceDAO;

import model.LichChieu;
import model.Phim;

import java.util.ArrayList;

public interface IPhimInterface {
    ArrayList<Phim> listDataPhim();
    void addPhim(Phim phim);
    void editPhim(Phim phim);
    void deletePhim(Phim phim);

}
