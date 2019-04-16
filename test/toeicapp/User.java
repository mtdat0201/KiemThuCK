/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toeicapp;

import java.util.StringTokenizer;

/**
 *
 * @author Angel
 */
public class User {
    String firstName;
    String lastName;
    String email;
    String userName;
    String userPass;
    
    
    // Dữ liệu Test Đăng nhập
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
    
    
    // Dữ liệu Test Đăng ký
    public static boolean isValidEmailAddress(String emailAddress)
  {
    if ( emailAddress == null )
      return false;

    if ( emailAddress.indexOf("@") < 0 )
      return false;

    if ( emailAddress.indexOf(".") < 0 )
      return false;

    if ( lastEmailFieldTwoCharsOrMore(emailAddress) == false )
      return false;
    
    return true;
  }



  private static boolean lastEmailFieldTwoCharsOrMore(String emailAddress)
  {
    if (emailAddress == null) 
        return false;
    StringTokenizer st = new StringTokenizer(emailAddress,".");
    String lastToken = null;
    while ( st.hasMoreTokens() )
    {
      lastToken = st.nextToken();
    }

    if ( lastToken.length() >= 2 )
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
