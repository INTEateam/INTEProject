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

    @Test
         public void testAdd(){
        Money m = new Money(10);
        m.add(1);
        assertEquals("11.00", m.toString());
    }

    @Test
    public void testAddDecimals(){
        Money m = new Money(10);
        m.add(1.01);
        assertEquals("11.01", m.toString());
    }

    @Test
    public void testAddMultiple(){
        Money m = new Money(10);
        m.add(1);
        m.add(1);
        assertEquals("12.00", m.toString());
    }

    @Test
    public void testSubtract(){
        Money m = new Money(10);
        m.subtract(1);
        assertEquals("9.00", m.toString());
    }

    @Test
    public void testSubtract(){
        Money m = new Money(10);
        m.subtract(1.01);
        assertEquals("8.99", m.toString());
    }

    @Test
    public void testSubtractMultiple(){
        Money m = new Money(10);
        m.subtract(1);
        m.subtract(1);
        assertEquals("8.00", m.toString());
    }

    @Test
    public void testDecreasePercent(){
        Money m = new Money(10);
        m.decreasePercent(10);
        assertEquals("9.00", m.toString());
        m.decreasePercent(10);
        assertEquals("8.10", m.toString());
    }

    @Test
    public void testIncreasePercent(){
        Money m = new Money(10);
        m
    }

}
