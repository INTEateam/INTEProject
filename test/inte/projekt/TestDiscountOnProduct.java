package inte.projekt;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestDiscountOnProduct {
	private DiscountOnProduct don;
	private Receipt r = new Receipt();
	
	
	@Before
	public void initialize() {

		Product p1 = new Product(2, new BigDecimal(100), "bread", "sweetbread");
		Product p2 = new Product(1, new BigDecimal(100), "br2ead", "sweetbread");
		Product p3 = new Product(2, new BigDecimal(100), "2hdsf", "sweetbread");
		r.addProduct(p1);
		r.addProduct(p2);
		r.addProduct(p3);
	}

	@Test
	public void getDiscountProductSum() {
		don=new DiscountOnProduct(2,new BigDecimal(0.3));
		assertEquals(new BigDecimal(60), don.getDiscountSum(r.getProductList()));
		
	}
	@Test 
	public void twoProductDiscountOnOneProduct(){
		don=new DiscountOnProduct(2,new BigDecimal(0.5));
		assertEquals(new BigDecimal(100), don.getDiscountSum(r.getProductList()));
		
	}
	
    @Test (expected = IllegalArgumentException.class)
    public void testNegativeDiscount(){        
        DiscountOnProduct d = new DiscountOnProduct(2,new BigDecimal(-0.2));
        assertTrue(d.getDiscountSum(r.getProductList()).compareTo(BigDecimal.ZERO) > 0);
        
        
      
    }
    @Test (expected = IllegalArgumentException.class)
    public void testMaxDiscount(){        
        DiscountOnProduct d = new DiscountOnProduct(2,new BigDecimal(2));
        assertTrue(d.getDiscountSum(r.getProductList()).compareTo(new BigDecimal(0.7)) >= 0);
        
        
      
    }
}
