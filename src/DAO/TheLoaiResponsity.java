package DAO;

import InterfaceDAO.ITheLoaiInterface;
import database.Connector;
import model.TheLoai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TheLoaiResponsity implements ITheLoaiInterface {
    @Override
    public ArrayList<TheLoai> listDataTL() {
        try {
            String txt_sql = "SELECT * FROM tbtheloai";

            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(txt_sql);

            ArrayList<TheLoai> list = new ArrayList<>();
            ResultSet rs = stt.executeQuery(txt_sql);
            while (rs.next()) {
                list.add(new TheLoai(
                        rs.getInt("maTL"),
                        rs.getString("tenTL")
                ));
            }
            return list;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addTL(TheLoai theLoai) {
        //truy vấn sql: thêm sv vào assigment01.database
        String sql_txt = "insert into tbtheloai (maTL, tenTL) values(?,?)";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1,theLoai.getMaTL());
            stt.setString(2,theLoai.getTenTL());

            stt.execute();
            System.out.println(sql_txt);
        }catch (Exception e){

        }
    }

    @Override
    public void editTL(TheLoai theLoai) {
        String sql_txt = "UPDATE tbtheloai SET tenTL=? where maTL=?";
        try{
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setString(1,theLoai.getTenTL());
            stt.setInt(2,theLoai.getMaTL());
            stt.execute();
            // insert
            stt.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTL(TheLoai theLoai) {
        String sql_txt = "delete from tbtheloai where maTL=?";
        try {
            Connector conn = Connector.getInstance();
            PreparedStatement stt = conn.getStatement(sql_txt);
            stt.setInt(1, theLoai.getMaTL());
            stt.execute();
        }catch (Exception e){

        }
    }

}
