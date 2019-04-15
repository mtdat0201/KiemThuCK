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
public class CauHoi {
    private String idCauhoi;
    private String idTheloai;
    private String cauHoi;
    private String a;
    private String b;
    private String c;
    private String d;
    private String dapAn;
    private String image;
    private String audio;
    private String tenLoai; // trường name của loại câu hỏi

    public CauHoi(String idCauhoi, String tenLoai, String cauHoi, String a, String b, String c, String d,
            String dapAn, String image, String audio, String idTheloai){
        this.idCauhoi=idCauhoi;
        this.tenLoai=tenLoai;      
        this.cauHoi=cauHoi;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.dapAn=dapAn;
        this.image=image;
        this.audio=audio;
        this.idTheloai=idTheloai;
    }
    /**
     * @return the idCauhoi
     */
    public String getIdCauhoi() {
        return idCauhoi;
    }

    /**
     * @param idCauhoi the idCauhoi to set
     */
    public void setIdCauhoi(String idCauhoi) {
        this.idCauhoi = idCauhoi;
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
     * @return the cauHoi
     */
    public String getCauHoi() {
        return cauHoi;
    }

    /**
     * @param cauHoi the cauHoi to set
     */
    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    /**
     * @return the a
     */
    public String getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(String a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public String getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(String b) {
        this.b = b;
    }

    /**
     * @return the c
     */
    public String getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(String c) {
        this.c = c;
    }

    /**
     * @return the d
     */
    public String getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(String d) {
        this.d = d;
    }

    /**
     * @return the dapAn
     */
    public String getDapAn() {
        return dapAn;
    }

    /**
     * @param dapAn the dapAn to set
     */
    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    /**
     * @return the name
     */
    public String getTenLoai() {
        return tenLoai;
    }

    /**
     * @param name the name to set
     */
    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the audio
     */
    public String getAudio() {
        return audio;
    }

    /**
     * @param audio the audio to set
     */
    public void setAudio(String audio) {
        this.audio = audio;
    }
}
