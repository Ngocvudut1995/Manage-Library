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
public class Person {

    protected String CMND;
    protected String Hovaten;
    protected String SoDT;
    protected String DiaChi;
    protected String NgaySinh;
    protected String GioiTinh;
    protected String Email;

    public Person(String CMND, String Hovaten, String SoDT, String DiaChi, String NgaySinh, String GioiTinh, String Email) {
        this.CMND = CMND;
        this.Hovaten = Hovaten;
        this.SoDT = SoDT;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
    }
    
}
