package inte.projekt;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicklas on 2015-10-14.
 * test
 */
public class TestReceipt {
    private Receipt r;
    private final int TOTAL_INCLUDING_DISCOUNT = 20;
    private final int PRODUCT_COUNT = 8;

    @Before
    public void before() {
        r = new Receipt();
        //int id, BigDecimal price, String name, String category
        r.addProduct(new Product(1, new BigDecimal(3), "Produkt 1", "Typ 1"));
        r.addProduct(new Product(2, new BigDecimal(2), "Produkt 2", "Typ 1"));
        r.addProduct(new Product(3, new BigDecimal(5), "Produkt 3", "Typ 1"));

        r.addProduct(new Product(4, new BigDecimal(3), "Produkt 4", "Typ 2"));
        r.addProduct(new Product(4, new BigDecimal(3), "Produkt 4", "Typ 2"));
        r.addProduct(new Product(4, new BigDecimal(3), "Produkt 4", "Typ 2"));

        r.addProduct(new Product(5, new BigDecimal(2), "Produkt 5", "Typ 3"));
        r.addProduct(new Product(6, new BigDecimal(5), "Produkt 6", "Typ 3"));

        ArrayList<DiscountInterface> d = new ArrayList<>();

        //DiscountOnAllProducts doap = new DiscountOnAllProducts(new BigDecimal(.01));
        DiscountOnCategory doc = new DiscountOnCategory("Typ 1", BigDecimal.ONE);
        d.add(doc);

        DiscountOneForFree doff = new DiscountOneForFree(3,2,4);
        d.add(doff);

        r.setDiscountsFromDB(d);

        //r is equal to 20 with discounts
    }

    @Test
    public void testAddProduct() {
        Receipt receipt = new Receipt();
        receipt.addProduct(new Product(1, new BigDecimal(1), "Produkt 1", "Typ 1"));
        assertEquals(1, receipt.getProductCount());
    }

    @Test
    public void testPriceSum() {
        assertEquals(new BigDecimal(TOTAL_INCLUDING_DISCOUNT).setScale(2, BigDecimal.ROUND_HALF_UP), r.getPriceSum());
    }

    @Test
    public void testPriceSumAddProduct() {
        assertEquals(new BigDecimal(TOTAL_INCLUDING_DISCOUNT).setScale(2), r.getPriceSum());
        r.addProduct(new Product(3, new BigDecimal(5), "Produkt 3", "Typ 1"));
        //Product of typ 1 discount 1
        assertEquals(new BigDecimal(TOTAL_INCLUDING_DISCOUNT + 4).setScale(2), r.getPriceSum());
    }

    @Test
    public void testRemoveProduct() {
        assertEquals(true, r.removeProduct(1));
    }

    @Test
    public void addNULLProduct() {
        //Nothing should happen
        r.addProduct(null);
    }

    @Test
    public void testRemoveNonexistentProduct() {
        assertEquals(false, r.removeProduct(7));
    }

    @Test
    public void testPriceSumRemoveProduct() {
        assertEquals(new BigDecimal(TOTAL_INCLUDING_DISCOUNT).setScale(2), r.getPriceSum());
        r.removeProduct(1); //Product 1 price = 3, not including discount of 1
        assertEquals(new BigDecimal(TOTAL_INCLUDING_DISCOUNT - 2).setScale(2), r.getPriceSum());
    }

    @Test
    public void testCountRemoveProduct() {
        r.removeProduct(1);
        assertEquals(PRODUCT_COUNT - 1, r.getProductCount());
    }

    @Test
    public void testRemoveProducts() {
        r.removeProduct(1);
        r.removeProduct(2);
        r.removeProduct(3);
        assertEquals(PRODUCT_COUNT - 3, r.getProductCount());
        r.removeProduct(4); // Remove one instane of product with id 4
        assertEquals(PRODUCT_COUNT - 4, r.getProductCount());
    }

    @Test
    public void testRemoveSameProducts() {
        r.addProduct(new Product(3, new BigDecimal(5), "Produkt 3", "Typ 1"));
        assertEquals(PRODUCT_COUNT + 1, r.getProductCount());

        r.removeProduct(3); //Remove product added for this test
        assertEquals(PRODUCT_COUNT, r.getProductCount());

        r.removeProduct(3); //Remove the product in the recipt before this test
        assertEquals(PRODUCT_COUNT - 1, r.getProductCount());
    }

    @Test
    public void testToString() {
        //TODO automatic
        r.toString();
    }

    @Test
    public void testSetCustomer(){
        Customer c = new Customer("9001013318", "Namn", "Efternamn", "Gata", "nr2", "12345", true);
        r.setCustomer(c);
        assertEquals(c, r.getCustomer());
        System.out.print(r.toString());
    }

}
