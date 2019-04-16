/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

/**
 *
 * @author Angel
 */
public class User {
    String userName;
    String userPass;
    public String getCorrect_nameAndPass()
    {
        userName = "bingo";
        userPass = "123456";
        return userName+userPass;
    }
    
    public String getFalse_nullName()
    {
        userName = "";
        userPass = "123456";
        return userName+userPass;
    }
    
    public String getFalse_nullPass()
    {
        userName = "bingo";
        userPass = "";
        return userName+userPass;
    }
    
    public String getFalse_nullUserAndPass()
    {
        userName = "";
        userPass = "";
        return userName+userPass;
    }
}
