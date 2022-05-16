package DAO_repository;

import database.Connector;
import DAO_factory.FPhongChieuRespon;
import model.PhongChieu;

import java.sql.*;
import java.util.ArrayList;

public class PhongChieuRepository extends FPhongChieuRespon {
    @Override
    public ArrayList<PhongChieu> listDataPC() {
        try {
            String txt_sql = "SELECT * FROM tbphongchieu";

            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(txt_sql);

            ArrayList<PhongChieu> list = new ArrayList<>();
            ResultSet rs = stt.executeQuery(txt_sql);
            while (rs.next()) {
                list.add(new PhongChieu(
                        rs.getInt("maPhong"),
                        rs.getString("tenPhong"),
                        rs.getInt("tongSoGhe")
                ));
            }
            return list;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addPC(PhongChieu phongChieu) {
        //truy vấn sql: thêm sv vào assigment01.database
        String sql_txt = "INSERT INTO tbphongchieu (MaPhong,TenPhong,TongSoGhe) VALUES(?,?,?)";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1,phongChieu.getMaPhong());
            stt.setString(2, phongChieu.getTenPhong());
            stt.setInt(3, phongChieu.getTongSoGhe());

            stt.execute();
            System.out.println(sql_txt);
        }catch (Exception e){

        }
    }

    @Override
    public void editPC(PhongChieu phongChieu) {
        String sql_txt = "UPDATE tbphongchieu SET TenPhong=?,TongSoGhe=? where MaPhong=?";
        try{
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setString(1, phongChieu.getTenPhong());
            stt.setInt(2, phongChieu.getTongSoGhe());
            stt.setInt(3, phongChieu.getMaPhong());
            // insert
            stt.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletePC(PhongChieu phongChieu) {
        String sql_txt = "DELETE FROM tbphongchieu where MaPhong=?";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, phongChieu.getMaPhong());
            stt.execute();
        }catch (Exception e){

        }
    }
}
