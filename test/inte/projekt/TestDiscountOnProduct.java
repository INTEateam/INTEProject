package inte.projekt;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestDiscountOnProduct {
	private DiscountOnProduct don;
	private List<Product> p = new ArrayList<Product>();

	@Before
	public void initialize() {

		Product p1 = new Product(2, new BigDecimal(100), "bread", "sweetbread");
		Product p2 = new Product(1, new BigDecimal(100), "br2ead", "sweetbread");
		Product p3 = new Product(2, new BigDecimal(100), "2hdsf", "sweetbread");
		p.add(p1);
		p.add(p2);
		p.add(p3);
	}

	@Test
	public void getDiscountProductSum() {
		int a=2;
		BigDecimal bd= new BigDecimal(0.3);
		don=new DiscountOnProduct(a,bd,p);
		assertEquals(new BigDecimal(70), don.getDiscountSum());
		
	}
	@Test 
	public void twoProductDiscountOnOneProduct(){
		int a=2;
		BigDecimal bd= new BigDecimal(0.3);
		don=new DiscountOnProduct(a,bd,p);
		assertEquals(new BigDecimal(70), don.getDiscountSum());
		
	}
	
    @Test (expected = IllegalArgumentException.class)
    public void testNegativeDiscount(){        
        DiscountOnProduct d = new DiscountOnProduct(2,new BigDecimal(-0.2),p);
        assertTrue(d.getDiscountSum().compareTo(BigDecimal.ZERO) > 0);
        
        
      
    }
    @Test (expected = IllegalArgumentException.class)
    public void testMaxDiscount(){        
        DiscountOnProduct d = new DiscountOnProduct(2,new BigDecimal(2),p);
        assertTrue(d.getDiscountSum().compareTo(new BigDecimal(0.7)) >= 0);
        
        
      
    }
}
