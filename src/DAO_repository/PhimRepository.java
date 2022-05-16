package DAO_repository;

import database.Connector;
import DAO_factory.FPhimRespon;
import model.Phim;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

public class PhimRepository extends FPhimRespon {
    @Override
    public ArrayList<Phim> listDataPhim() {   //add data vào tableview
        try {
            //truy van sql
            String txt_sql = "SELECT * FROM tbphim";

            Connector conn = Connector.getInstance();   //connector
            PreparedStatement stt = conn.getStatement(txt_sql);

            ResultSet rs = stt.executeQuery(txt_sql);

            ArrayList<Phim> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new Phim(
                    rs.getInt("maPhim"),
                    rs.getString("tenPhim"),
                    Time.valueOf(rs.getString("thoiLuong")),
                    rs.getString("daoDien"),
                    rs.getString("hangSanXuat"),
                    rs.getInt("maTL"),
                    Date.valueOf(rs.getString("ngayKhoiChieu")),
                    Date.valueOf(rs.getString("ngayKetThuc")),
                    rs.getInt("trangThai")
                ));
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void addPhim(Phim phim) {
        String sql_txt = "insert into tbphim (maPhim,tenPhim,thoiLuong,daoDien,hangSanXuat,maTL,ngayKhoiChieu,ngayKetThuc,trangThai)"
                + "values(?,?,?,?,?,?,?,?,?)";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, phim.getMaPhim());
            stt.setString(2, phim.getTenPhim());
            stt.setString(3, phim.getThoiLuong().toString());
            stt.setString(4, phim.getDaoDien());
            stt.setString(5, phim.getHangSanXuat());
            stt.setInt(6, phim.getMaTL());
            stt.setString(7, phim.getNgayKhoiChieu().toString());
            stt.setString(8, phim.getNgayKetThuc().toString());
            if (phim.getTrangThai() == 0){
                throw new Exception("Movie not deacactive!");
            } else if (phim.getTrangThai() == 1 || phim.getTrangThai() == 2){
                stt.setInt(9, phim.getTrangThai());
            } else {
                throw new Exception("TrangThai does not exist!");
            }
            // insert
            stt.execute();
            System.out.println(sql_txt);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void editPhim(Phim phim) {
        String sql_txt = "UPDATE tbphim SET tenPhim=?,thoiLuong=?,daoDien=?,hangSanXuat=?,maTL=?,ngayKhoiChieu=?,ngayKetThuc=?,trangThai=? "
                + "where maPhim=?";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setString(1, phim.getTenPhim());
            stt.setString(2, phim.getThoiLuong().toString());
            stt.setString(3, phim.getDaoDien());
            stt.setString(4, phim.getHangSanXuat());
            stt.setInt(5, phim.getMaTL());
            stt.setString(6, phim.getNgayKhoiChieu().toString());
            stt.setString(7, phim.getNgayKetThuc().toString());
            if (phim.getTrangThai() == 0 || phim.getTrangThai() == 1 || phim.getTrangThai() == 2){
                stt.setInt(8, phim.getTrangThai());
            } else {
                throw new Exception("TrangThai does not exist!");
            }
            stt.setInt(9, phim.getMaPhim());

            // insert
            stt.execute();
            System.out.println(sql_txt);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletePhim(Phim phim) {
        String sql_txt = "delete from tbphim where maPhim=?";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, phim.getMaPhim());
            stt.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
