package com.example.webservice;

public class SinhVien {
    
    private int Id;
    private String HoTen;
    private int NamSinh;
    private String DiaChi;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public SinhVien(int id , String hoTen , int namSinh, String diaChi){

        this.Id=id;
        this.HoTen=hoTen;
        this.NamSinh=namSinh;
        this.DiaChi=diaChi;


    }
}
