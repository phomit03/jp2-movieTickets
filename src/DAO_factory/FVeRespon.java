package DAO_factory;

import InterfaceDAO.IVeInterface;
import model.Ve;

import java.util.ArrayList;

public abstract class FVeRespon implements IVeInterface {
    @Override
    public abstract ArrayList<Ve> listDataVe();

    @Override
    public abstract void addVe(Ve ve);

    @Override
    public abstract void editVe(Ve ve);

    @Override
    public abstract void deleteVe(Ve ve);
}
