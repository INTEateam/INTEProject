package inte.projekt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicklas on 2015-10-24.
 */
public class TestMoney {
    @Test
    public void testCreate(){
        Money m = new Money(10);
        assertEquals("10.00", m.toString());
    }
}
