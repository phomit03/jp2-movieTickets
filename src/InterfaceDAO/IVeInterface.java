package InterfaceDAO;

import model.Ve;

import java.util.ArrayList;

public interface IVeInterface {
    ArrayList<Ve> listDataVe();
    void addVe(Ve ve);
    void editVe(Ve ve);
    void deleteVe(Ve ve);
}
