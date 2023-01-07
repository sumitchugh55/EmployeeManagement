import static org.junit.jupiter.api.Assertions.*;

import com.gynaly.expenses.exceptions.InvalidEmployeeIdException;
import com.gynaly.expenses.utility.EmployeeUtilities;
import org.junit.jupiter.api.Test;

public class SampleTests {
    @Test
    public void testSomting(){

        int a = 4;
        int b = 45;
        int total = a + b;

        assertEquals(49,total);
    }

    @Test
    public void testEmpId() throws InvalidEmployeeIdException {
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();
        int result = employeeUtilities.validateEmployeeId("123");
        assertEquals(123,result);
//        fail();
    }

    @Test
    public void testThatExceptionIsThrowingInvalidEmployeeIdException(){
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();
        assertThrows(InvalidEmployeeIdException.class,()->{
            int result = employeeUtilities.validateEmployeeId("fdgafhjkds");
        });
    }
}
