package DAO_factory;

import InterfaceDAO.IPhongChieuInterface;
import model.PhongChieu;

import java.util.ArrayList;

public abstract class FPhongChieuRespon implements IPhongChieuInterface {
    @Override
    public abstract ArrayList<PhongChieu> listDataPC();

    @Override
    public abstract void addPC(PhongChieu phongChieu);

    @Override
    public abstract void editPC(PhongChieu phongChieu);

    @Override
    public abstract void deletePC(PhongChieu phongChieu);
}
