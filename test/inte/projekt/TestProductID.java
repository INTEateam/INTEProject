package inte.projekt;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import project.Product;
import org.junit.Test;

public class TestProductID {

	private static final BigDecimal VALID_PRICE = new BigDecimal(10);
	private static final String VALID_NAME = "Milk";
	private static final String VALID_CATEGORY = "Soft drinks";

	@Test
	public void testValidProductId() {
		Product p = new Product(5, VALID_PRICE, VALID_NAME, VALID_CATEGORY);
		assertEquals(5, p.getId());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testNegativeProductId() {
		Product p = new Product(-10, VALID_PRICE, VALID_NAME, VALID_CATEGORY);
		assertEquals(-10, p.getId());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testZeroProductId() {
		Product p = new Product(0, VALID_PRICE, VALID_NAME, VALID_CATEGORY);
		assertEquals(0, p.getId());
}

}