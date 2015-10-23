package inte.projekt;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TestProductName {

    private static final int VALID_ID = 1;
    private static final BigDecimal VALID_PRICE = new BigDecimal(10);
    private static final String VALID_CATEGORY = "Soft drinks";
    private String aVeryLongString = "thisIsaStringThatConsistsOfValidCharactersButIsTooLong";

    @Test
    public void testSimpleName() {
        Product p = new Product(VALID_ID, VALID_PRICE, "Bread", VALID_CATEGORY);
        assertEquals("Bread", p.getName());
    }

    @Test
    public void testComplexName() {
        Product p = new Product(VALID_ID, VALID_PRICE, "Coca-cola 33cl", VALID_CATEGORY);
        assertEquals("Coca-cola 33cl", p.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSomeInvalidCharacters() {
        Product p = new Product(VALID_ID, VALID_PRICE, "Milk!?%/", VALID_CATEGORY);
        assertEquals("Milk!?%/", p.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLengthLong() {
        Product p = new Product(VALID_ID, VALID_PRICE, aVeryLongString, VALID_CATEGORY);
        assertEquals(aVeryLongString, p.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLengthShort() {
        Product p = new Product(VALID_ID, VALID_PRICE, "A", VALID_CATEGORY);
        assertEquals("A", p.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testAgainstNull() {
        Product p = new Product(VALID_ID, VALID_PRICE, null, VALID_CATEGORY);
        assertEquals(null, p.getName());
    }

}
