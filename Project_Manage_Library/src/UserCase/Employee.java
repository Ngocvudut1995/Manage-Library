/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserCase;

/**
 *
 * @author Vu Dang
 */
public class Employee extends Person{
    private String MaNV;
    private Double luong;
    private String chucvu;
    private String maphong;

    public Employee(String MaNV, Double luong, String chucvu, String maphong, String CMND, String Hovaten, String SoDT, String DiaChi, String NgaySinh, String GioiTinh, String Email) {
        super(CMND, Hovaten, SoDT, DiaChi, NgaySinh, GioiTinh, Email);
        this.MaNV = MaNV;
        this.luong = luong;
        this.chucvu = chucvu;
        this.maphong = maphong;
    }
    
    
    
}
