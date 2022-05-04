package DAO;

import InterfaceDAO.ILichChieuInterface;
import database.Connector;
import model.LichChieu;

import java.sql.*;
import java.util.ArrayList;

public class LichChieuResponsity implements ILichChieuInterface {
    @Override
    public ArrayList<LichChieu> listDataLC() {   //add data vào tableview
        try {
            //truy van sql
            String txt_sql = "SELECT * FROM tblichchieu";

            Connector conn = Connector.getInstance();   //connector
            PreparedStatement stt = conn.getStatement(txt_sql);

            ResultSet rs = stt.executeQuery(txt_sql);

            ArrayList<LichChieu> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new LichChieu(
                        rs.getInt("maLC"),
                        rs.getInt("maPhim"),
                        rs.getInt("maPhong"),
                        Date.valueOf(rs.getString("ngayChieu")),
                        Time.valueOf(rs.getString("gioChieu")),
                        rs.getDouble("giaVe")
                ));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addLC(LichChieu lichchieu) {
        //truy vấn sql: thêm sv vào assigment01.database
        String sql_txt = "insert into tblichchieu (maLC,maPhim,maPhong,ngayChieu,gioChieu,giaVe) values(?,?,?,?,?,?)";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1,lichchieu.getMaLC());
            stt.setInt(2,lichchieu.getMaPhim());
            stt.setInt(3,lichchieu.getMaPhong());
            stt.setString(4,lichchieu.getNgayChieu().toString());
            stt.setString(5,lichchieu.getGioChieu().toString());
            stt.setDouble(6,lichchieu.getGiaVe());

            stt.execute();
            System.out.println(sql_txt);
        }catch (Exception e){

        }
    }

    @Override
    public void editLC(LichChieu lichchieu) {
        String sql_txt = "UPDATE tblichchieu SET maPhim=?,maPhong=?,ngayChieu=?,gioChieu=?,giaVe=? where maLC=?";
        try{
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1,lichchieu.getMaPhim());
            stt.setInt(2,lichchieu.getMaPhong());
            stt.setString(3,lichchieu.getNgayChieu().toString());
            stt.setString(4,lichchieu.getGioChieu().toString());
            stt.setDouble(5,lichchieu.getGiaVe());
            stt.setInt(6,lichchieu.getMaLC());
            // insert
            stt.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteLC(LichChieu lichchieu) {
        String sql_txt = "delete from tblichchieu where maLC=?";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, lichchieu.getMaLC());
            stt.execute();
        }catch (Exception e){

        }
    }
}
