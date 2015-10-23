package inte.projekt;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class TestProductPrice {

    private static final int VALID_ID = 1;
    private static final String VALID_NAME = "Milk";
    private static final String VALID_CATEGORY = "Soft drinks";


    @Test
    public void testValidPrice() {
        Product p = new Product(VALID_ID, new BigDecimal(10), VALID_NAME, VALID_CATEGORY);
        //assertEquals(new BigDecimal(10), p.getPrice());
    }

    @Test
    public void testPricewithValidDecimals() {
        Product p = new Product(VALID_ID, new BigDecimal(10.90).setScale(2, RoundingMode.HALF_UP), VALID_NAME, VALID_CATEGORY);
        //assertEquals(new BigDecimal(10.90).setScale(2, RoundingMode.HALF_UP), p.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativePrice() {
        Product p = new Product(VALID_ID, new BigDecimal(-10), VALID_NAME, VALID_CATEGORY);
        //assertEquals(new BigDecimal(-10), p.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroPrice() {
        Product p = new Product(VALID_ID, new BigDecimal(0), VALID_NAME, VALID_CATEGORY);
        //assertEquals(new BigDecimal(0), p.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPricewithTooManyDecimals() {
        Product p = new Product(VALID_ID, new BigDecimal("39.9876").setScale(4, RoundingMode.HALF_UP), VALID_NAME, VALID_CATEGORY);
        assertEquals(new BigDecimal("39.9876").setScale(4, RoundingMode.HALF_UP), p.getPrice());
    }

}
