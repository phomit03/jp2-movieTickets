package model;

public class DoanhThu {
    Integer maPhim;
    String tenPhim;
    Double doanhThu;

    //contructor
    public DoanhThu(){

    }
    public DoanhThu(Integer maPhim, String tenPhim, Double doanhThu) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.doanhThu = doanhThu;
    }

    //g&s
    public Integer getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(Integer maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public Double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Double doanhThu) {
        this.doanhThu = doanhThu;
    }
}
