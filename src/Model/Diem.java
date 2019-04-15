/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Angel
 */
public class Diem {
    private String idDiem;
    private String userName;
    private int diem;
    private Date ngayThuchien;
    
    public Diem(String userName, int diem, Date ngayThuchien, String idDiem)
    {       
        this.setUserName(userName);
        this.setDiem(diem);
        this.setNgayThuchien(ngayThuchien);
        this.setIdDiem(idDiem);
    }
    /**
     * @return the idDiem
     */
    public String getIdDiem() {
        return idDiem;
    }

    /**
     * @param idDiem the idDiem to set
     */
    public void setIdDiem(String idDiem) {
        this.idDiem = idDiem;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the diem
     */
    public int getDiem() {
        return diem;
    }

    /**
     * @param diem the diem to set
     */
    public void setDiem(int diem) {
        this.diem = diem;
    }

    /**
     * @return the ngayThuchien
     */
    public Date getNgayThuchien() {
        return ngayThuchien;
    }

    /**
     * @param ngayThuchien the ngayThuchien to set
     */
    public void setNgayThuchien(Date ngayThuchien) {
        this.ngayThuchien = ngayThuchien;
    }
    
}
