package DAO_factory;

import InterfaceDAO.ITheLoaiInterface;
import model.TheLoai;

import java.util.ArrayList;

public abstract class FTheLoaiRespon implements ITheLoaiInterface {
    @Override
    public abstract ArrayList<TheLoai> listDataTL();

    @Override
    public abstract void addTL(TheLoai theLoai);

    @Override
    public abstract void editTL(TheLoai theLoai);

    @Override
    public abstract void deleteTL(TheLoai theLoai);
}
