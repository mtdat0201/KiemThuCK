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
public class NguoiDung {
   
    private String idNguoidung;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String nameQuyen;
    private String pass;
            
    public NguoiDung (String idNguoidung, String firstName, String lastName, String userName, String email, String nameQuyen, String pass){
        this.setIdNguoidung(idNguoidung);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);        
        this.setEmail(email);
        this.setNameQuyen(nameQuyen);
        this.setPass(pass);

        
    }
    /**
     * @return the idNguoidung
     */
    public String getIdNguoidung() {
        return idNguoidung;
    }

    /**
     * @param idNguoidung the idNguoidung to set
     */
    public void setIdNguoidung(String idNguoidung) {
        this.idNguoidung = idNguoidung;
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
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the nameQuyen
     */
    public String getNameQuyen() {
        return nameQuyen;
    }

    /**
     * @param nameQuyen the nameQuyen to set
     */
    public void setNameQuyen(String nameQuyen) {
        this.nameQuyen = nameQuyen;
    }
}


