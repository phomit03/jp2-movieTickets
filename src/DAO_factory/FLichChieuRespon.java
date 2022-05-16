package DAO_factory;

import InterfaceDAO.ILichChieuInterface;
import model.LichChieu;

import java.util.ArrayList;

public abstract class FLichChieuRespon implements ILichChieuInterface {
    @Override
    public abstract ArrayList<LichChieu> listDataLC();

    @Override
    public abstract void addLC(LichChieu lichchieu);

    @Override
    public abstract void editLC(LichChieu lichchieu);

    @Override
    public abstract void deleteLC(LichChieu lichchieu);
}
