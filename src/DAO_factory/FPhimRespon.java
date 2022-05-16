package DAO_factory;

import InterfaceDAO.IPhimInterface;
import model.Phim;

import java.util.ArrayList;

public abstract class FPhimRespon implements IPhimInterface {
    @Override
    public abstract ArrayList<Phim> listDataPhim();

    @Override
    public abstract void addPhim(Phim phim);

    @Override
    public abstract void editPhim(Phim phim);

    @Override
    public abstract void deletePhim(Phim phim);
}
