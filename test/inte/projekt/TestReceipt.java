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
    ArrayList<DiscountInterface> d;

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

        d = new ArrayList<>();


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
    public void testHalfPriceOnOneProduct(){
        DiscountOnProduct dop = new DiscountOnProduct(6,new BigDecimal(0.50));
        d.add(dop);
        assertEquals(new BigDecimal(TOTAL_INCLUDING_DISCOUNT - 2.5).setScale(2), r.getPriceSum());

        String s = "PRODUCTS\n" +
                "Produkt 1\t3kr \n" +
                "Produkt 2\t2kr \n" +
                "Produkt 3\t5kr \n" +
                "Produkt 4\t3kr \n" +
                "Produkt 4\t3kr \n" +
                "Produkt 4\t3kr \n" +
                "Produkt 5\t2kr \n" +
                "Produkt 6\t5kr \n" +
                "----------------------\n" +
                "Total: 17.50kr\n" +
                "\n" +
                "APPLIED DISCOUNTS\n" +
                "Category Discount, Category: Typ 1, Amount: 1, Only Members: false\n" +
                "One for free, Number of products: 3, Number of products to pay: 2, Affected Product Id: 4, Only Members: false\n" +
                "Discount on Product, Product Id: 6, Discount Percentage: 0.5, Only Members: false\n" +
                "----------------------\n" +
                "Total: 8.50kr\n";
        assertEquals(s, r.toString());
    }

    @Test
    public void testSetCustomer(){
        Customer c = new Customer("9001013318", "Namn", "Efternamn", "Gata", "nr2", "12345", true);
        r.setCustomer(c);
        assertEquals(c, r.getCustomer());
    }

    @Test
    public void testDiscountOnAllProducts(){
        DiscountOnAllProducts doap = new DiscountOnAllProducts(new BigDecimal(10), false);
        d.add(doap);
        assertEquals(new BigDecimal(17.40).setScale(2, BigDecimal.ROUND_HALF_UP), r.getPriceSum());
    }


    @Test
         public void testToString() {
        Customer c = new Customer("9001013318", "Namn", "Efternamn", "Gata", "nr2", "12345", true);
        r.setCustomer(c);
        //TODO automatic

        String s = "PRODUCTS\n" +
                "Produkt 1\t3kr \n" +
                "Produkt 2\t2kr \n" +
                "Produkt 3\t5kr \n" +
                "Produkt 4\t3kr \n" +
                "Produkt 4\t3kr \n" +
                "Produkt 4\t3kr \n" +
                "Produkt 5\t2kr \n" +
                "Produkt 6\t5kr \n" +
                "----------------------\n" +
                "Total: 20.00kr\n" +
                "\n" +
                "APPLIED DISCOUNTS\n" +
                "Category Discount, Category: Typ 1, Amount: 1, Only Members: false\n" +
                "One for free, Number of products: 3, Number of products to pay: 2, Affected Product Id: 4, Only Members: false\n" +
                "----------------------\n" +
                "Total: 6.00kr\n" +
                "\n" +
                "DELIVERY ADDRESS\n" +
                "Namn Efternamn\n" +
                "Gata nr2\n" +
                "12345\n";

        assertEquals(s, r.toString());
    }

}
