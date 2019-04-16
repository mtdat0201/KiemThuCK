import org.junit.Test;
import static org.junit.Assert.*;
import toeicapp.User;
/**
 *
 * @author Angel
 */
public class UserTest {   
    User n = new User();
    @Test
    public void UserTest_correct() {
        
        assertEquals("bingo123456", n.getCorrect_nameAndPass());
    }
    
    @Test
    public void UserTest_nullName_fail() {
        User n = new User();
        assertEquals("123456", n.getFalse_nullName());
    }
    
    @Test
    public void UserTest_nullPass_fail() {
        User n = new User();
        assertEquals("bingo", n.getFalse_nullPass());
    }
    
    @Test
    public void UserTest_nullUserAndPass_fail() {
        User n = new User();
        assertEquals("", n.getFalse_nullUserAndPass());
    }
    
    @Test
    public void UserTest_wrongUserName_fail() {
        
        assertEquals("ningo123456", n.getCorrect_nameAndPass());
    }
    
    @Test
    public void UserTest_wrongUserPass_fail() {
        
        assertEquals("bingo12345", n.getCorrect_nameAndPass());
    }
    
    @Test
    public void UserTest_wrongUserAndPass_fail() {
        
        assertEquals("ningo12345", n.getCorrect_nameAndPass());
    }
    
    @Test
    public void Diem_minMark_fail() {
        int result = 2;
        int expected = 5;
        assertTrue(result < expected);
    }
    
    @Test
    public void Diem_passMark_pass() {
        int result = 5;
        int expected = 5;
        assertTrue(result >= expected);
    }
}
