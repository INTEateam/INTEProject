package inte.projekt;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TestDiscountOneForFree {
    private Receipt r = new Receipt();
    private DiscountOneForFree doff;

    @Before
    public void initialize() {

        Product p1 = new Product(2, new BigDecimal(100), "bread", "sweetbread");
        Product p2 = new Product(2, new BigDecimal(100), "br2ead", "sweetbread");
        Product p3 = new Product(2, new BigDecimal(100), "2hdsf", "sweetbread");
        Product p4 = new Product(3, new BigDecimal(100), "bread", "sweetbread");
        r.addProduct(p1);
        r.addProduct(p2);
        r.addProduct(p3);
        r.addProduct(p4);

    }

    @Test
    public void TreeForOne () {
        doff= new DiscountOneForFree(3, 2, 2);
        assertEquals(new BigDecimal(100), doff.getDiscountSum(r.getProductList()));
    }
    @Test
    public void TreeForOneWith () {
        Product p4 = new Product(2, new BigDecimal(100), "bread", "sweetbread");
        Product p5 = new Product(2, new BigDecimal(100), "br2ead", "sweetbread");
        Product p6 = new Product(2, new BigDecimal(100), "2hdsf", "sweetbread");
        r.addProduct(p4);
        r.addProduct(p5);
        r.addProduct(p6);

        doff= new DiscountOneForFree(3, 2, 2);
        assertEquals(new BigDecimal(200), doff.getDiscountSum(r.getProductList()));
    }

}