package inte.projekt;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCampaign {

    private Receipt r;
    private Campaign c;

    @Before
    public void before() {
        r = new Receipt();
        r.addProduct(new Product(1, new BigDecimal(5), "Produkt 1", "Typ 1"));
        r.addProduct(new Product(1, new BigDecimal(5), "Produkt 2", "Typ 1"));
        r.addProduct(new Product(1, new BigDecimal(5), "Produkt 3", "Typ 1"));
    }

    @Test
    public void testTotalDiscount() {
        c = new Campaign(r);
        c.setPercentageDiscount(new BigDecimal(0.5));
        assertEquals(new BigDecimal(0.5), c.getTotalDiscount());

    }

    @Test
    public void getPriceByDiscount() {
        c = new Campaign(r);
        c.setPercentageDiscount(new BigDecimal(0.5));
        assertEquals(new BigDecimal(8), c.getPriceByDiscount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDiscount() {
        c = new Campaign(r);
        c.setPercentageDiscount(new BigDecimal(-0.5));
        assertTrue(c.getTotalDiscount().compareTo(BigDecimal.ZERO) > 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxDiscount() {
        c = new Campaign(r);
        c.setPercentageDiscount(new BigDecimal(0.6));
        assertTrue(c.getTotalDiscount().compareTo(new BigDecimal(0.5)) >= 0);

    }

    @Test
    public void testThreeForTwo() {
        c = new Campaign(r);
        c.setThreeForTwoDiscount(1);
        assertEquals(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP), r.getPriceSum());
    }

}
