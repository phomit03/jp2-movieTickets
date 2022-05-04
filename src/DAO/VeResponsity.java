package DAO;

import InterfaceDAO.IVeInterface;
import database.Connector;
import model.Ve;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeResponsity implements IVeInterface {
    @Override
    public ArrayList<Ve> listDataVe() {
        try {
            String txt_sql = "SELECT * FROM tbve";

            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(txt_sql);

            ArrayList<Ve> list = new ArrayList<>();
            ResultSet rs = stt.executeQuery(txt_sql);
            while (rs.next()) {
                list.add(new Ve(
                        rs.getInt("maVe"),
                        rs.getInt("maLC"),
                        rs.getString("maGhe")
                ));
            }
            return list;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addVe(Ve ve) {
        String sql_txt = "insert into tbve (maVe,maLC,maGhe) values(?,?,?)";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, ve.getMaVe());
            stt.setInt(2, ve.getMaLC());
            stt.setString(3, ve.getMaGhe());
            stt.execute();
        }catch (Exception e){

        }
    }

    @Override
    public void editVe(Ve ve) {
        String sql_txt = "update tbve set maLC = ?,maGhe = ? where maVe = ?";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, ve.getMaLC());
            stt.setString(2, ve.getMaGhe());
            stt.setInt(3, ve.getMaVe());
            stt.execute();
        }catch (Exception e){

        }
    }

    @Override
    public void deleteVe(Ve ve) {
        String sql_txt = "delete from tbve where maVe=?";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, ve.getMaVe());
            stt.execute();
        }catch (Exception e){

        }
    }
}
