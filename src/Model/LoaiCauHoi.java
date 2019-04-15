/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Angel
 */
public class LoaiCauHoi {
    private String idTheloai;
    private String nameTheloai;
    
    public LoaiCauHoi(String idTheloai,String nameTheloai)
    {
        this.idTheloai=idTheloai;
        this.nameTheloai=nameTheloai;
    }

    /**
     * @return the idTheloai
     */
    public String getIdTheloai() {
        return idTheloai;
    }

    /**
     * @param idTheloai the idTheloai to set
     */
    public void setIdTheloai(String idTheloai) {
        this.idTheloai = idTheloai;
    }

    /**
     * @return the nameTheloai
     */
    public String getNameTheloai() {
        return nameTheloai;
    }

    /**
     * @param nameTheloai the nameTheloai to set
     */
    public void setNameTheloai(String nameTheloai) {
        this.nameTheloai = nameTheloai;
    }
}
