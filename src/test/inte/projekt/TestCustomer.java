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
        Customer c = new Customer("198001013311","Namn Efternamn", "Gata", "nr", true);
        assertEquals("198001013311",c.getPersonnr());
    }

    @Test
    public void testPersonnrTen(){
        Customer c = new Customer("9001013311","Namn Efternamn", "Gata", "nr", true);
        assertEquals("9001013311",c.getPersonnr());
    }
    /*
    @Test (expected = IllegalArgumentException.class)
    public void testPersonnrMonthValidity(){
        Customer c = new Customer("9013013311","Namn Efternamn", "Gata", "nr", true);
    }

    @Test (expected = Exception.class)
    public void testPersonnrDayValidity(){
        Customer c = new Customer("9201353311","Namn Efternamn", "Gata", "nr", true);
    }*/

}
