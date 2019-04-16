
import toeicapp.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angel
 */
public class TestDangKy {

    @Test
    public void TestAll() {
    String address = "md@ou.com";
    boolean expectedResult = true;
    boolean actualResult = User.isValidEmailAddress(address);
    assertSame(expectedResult,actualResult);       
    }
    
    @Test
    public void TestBlank() {
    String address = "";
    boolean expectedResult = false;
    boolean actualResult = User.isValidEmailAddress(address);
    assertSame(expectedResult,actualResult);       
    }
    
    @Test
    public void TestNull() {
    String address = "";
    boolean expectedResult = false;
    boolean actualResult = User.isValidEmailAddress(address);
    assertSame(expectedResult,actualResult);       
    }
    
    @Test
     public void TestSymbols()
     {
        assertTrue(!User.isValidEmailAddress("@") );
        assertTrue(!User.isValidEmailAddress("@ou") );
        assertFalse(!User.isValidEmailAddress("@ou.com") );
        assertFalse(!User.isValidEmailAddress(" @ou.com") );
        assertFalse(!User.isValidEmailAddress("md @ou.com") );
        assertFalse(!User.isValidEmailAddress("@ou .com") );
        assertFalse(!User.isValidEmailAddress("@ou. com") );
        assertTrue(!User.isValidEmailAddress("md@ou") );
        assertTrue(!User.isValidEmailAddress("md@ ou") );
     }
     
     @Test
       public void testSingleName()
     {
        assertTrue(!User.isValidEmailAddress("md") );
     }
       
      @Test
       public void testLongBlank()
       {
           assertTrue(!User.isValidEmailAddress(" ") );
           assertTrue(!User.isValidEmailAddress("          ") );
           assertTrue(!User.isValidEmailAddress("                                                     ") ); 
       }
       
       @Test
       public void testCrazyStuff()
       {
           assertTrue(!User.isValidEmailAddress(".@") );
           assertTrue(!User.isValidEmailAddress("@.") );
           assertTrue(!User.isValidEmailAddress("@.@.@.@.") );
           assertTrue(!User.isValidEmailAddress("a@b.c") );
           assertTrue(!User.isValidEmailAddress("md@yahoo.c") );
           assertFalse(!User.isValidEmailAddress("@@@.cc") );
           assertFalse(!User.isValidEmailAddress("ala@foo@bar.com") );
       }
       
       @Test
       public void testIpAddresses()
      {
        assertFalse(User.isValidEmailAddress("md@127.0.0.1") );
      }
}
