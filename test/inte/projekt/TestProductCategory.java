package inte.projekt;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TestProductCategory {

	private static final int VALID_ID = 1;
	private static final BigDecimal VALID_PRICE = new BigDecimal(10);
	private static final String VALID_NAME = "Milk";
	private final String aVeryLongString = "thisIsaStringThatConsistsOfValidCharactersButIsTooLong";

	@Test
	public void testSimpleCategory() {
		Product p = new Product(VALID_ID, VALID_PRICE, VALID_NAME,
				"Household products");
		assertEquals("Household products", p.getCategory());
	}

	@Test
	public void testComplexCategory() {
		Product p = new Product(VALID_ID, VALID_PRICE, VALID_NAME, "ISO 5775 - Bicycle tires and rims");
		assertEquals("ISO 5775 - Bicycle tires and rims", p.getCategory());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSomeInvalidCharacters() {
		Product p = new Product(VALID_ID, VALID_PRICE, VALID_NAME, "Category_%?!*/");
		assertEquals("Category_%?!*/", p.getCategory());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidLengthLong() {
		Product p = new Product(VALID_ID, VALID_PRICE, VALID_NAME,
				aVeryLongString);
		assertEquals(aVeryLongString, p.getCategory());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidLengthShort() {
		Product p = new Product(VALID_ID, VALID_PRICE, VALID_NAME, "C");
		assertEquals("C", p.getCategory());
	}

	@Test(expected = NullPointerException.class)
	public void testAgainstNull() {
		Product p = new Product(VALID_ID, VALID_PRICE, VALID_NAME, null);
		assertEquals(null, p.getCategory());
	}
}
