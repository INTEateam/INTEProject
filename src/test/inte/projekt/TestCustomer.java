package test.inte.projekt;
import inte.projekt.Customer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nicklas on 2015-10-14.
 * test?
 */
public class TestCustomer {
    @Test
    public void testPersonnr(){
        Customer c = new Customer("192001013311","Namn Efternamn", "Gata", "nr", true);
        assertEquals("192001013311",c.getPersonnr());
    }

    @Test (expected = Exception.class)
    public void testPersonnrMonthValidity(){
        Customer c = new Customer("192013013311","Namn Efternamn", "Gata", "nr", true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDateValidity(){
        Customer c = new Customer("192001353311","Namn Efternamn", "Gata", "nr", true);
    }
    
}
