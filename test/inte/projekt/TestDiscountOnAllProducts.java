package inte.projekt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * DiscountOnAllProducts Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>okt 21, 2015</pre>
 */
public class TestDiscountOnAllProducts {
    private List<Product> p = new ArrayList<Product>();

    @Before
    public void before() throws Exception {
        Product p1 = new Product(2, new BigDecimal(100), "bread", "sweetbread");
        Product p2 = new Product(1, new BigDecimal(100), "br2ead", "sweetbread");
        Product p3 = new Product(2, new BigDecimal(100), "2hdsf", "sweetbread");
        p.add(p1);
        p.add(p2);
        p.add(p3);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: checkDiscount(List<Product> allProducts, boolean isMember)
     */
    @Test
    public void testCheckDiscountForAllProductsIsMember() throws Exception {
        DiscountOnAllProducts doap = new DiscountOnAllProducts(new BigDecimal(0.10), true);

        assertEquals(false, doap.checkDiscount(p, false));
        assertEquals(true, doap.checkDiscount(p, true));
    }

    /**
     * Method: checkDiscount(List<Product> allProducts)
     */
    @Test
    public void testCheckDiscountAllProducts() throws Exception {
        DiscountOnAllProducts doap = new DiscountOnAllProducts(new BigDecimal(0.10));

        assertEquals(true, doap.checkDiscount(p, false));
        assertEquals(true, doap.checkDiscount(p, true));
    }

    /**
     * Method: getDiscountSum()
     */
    @Test
    public void testGetDiscountSum() throws Exception {
        DiscountOnAllProducts doap = new DiscountOnAllProducts(new BigDecimal(10));
        assertEquals(true, doap.checkDiscount(p, true));

        assertEquals(new BigDecimal(30.00).setScale(2), doap.getDiscountSum(p));
    }

    /**
     * Method: getAffectedProducts()
     */
    @Test
    public void testGetAffectedProducts() throws Exception {
        DiscountOnAllProducts doap = new DiscountOnAllProducts(new BigDecimal(0.10));
        assertEquals(true, doap.checkDiscount(p, true));

        assertTrue(p.equals(doap.getAffectedProducts(p)));

    }


} 
