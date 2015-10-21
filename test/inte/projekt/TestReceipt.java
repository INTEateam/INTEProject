package inte.projekt;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicklas on 2015-10-14.
 * test
 */
public class TestReceipt {
    private Receipt r;

    @Before
    public void before(){
        r = new Receipt();
        r.addProduct(new Product(1, new BigDecimal(3),"Produkt 1", "Typ 1"));
        r.addProduct(new Product(2, new BigDecimal(2),"Produkt 2", "Typ 1"));
        r.addProduct(new Product(3, new BigDecimal(5),"Produkt 3", "Typ 1"));
    }

    @Test
    public void testAddProduct(){
        Receipt receipt = new Receipt();
        receipt.addProduct(new Product(1 ,new BigDecimal(1), "Produkt 1", "Typ 1"));
        assertEquals(1, receipt.getProductCount());
    }

    @Test
    public void testPriceSum(){
        assertEquals(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP),r.getPriceSum());
    }

    @Test
    public void testPriceSumAddProduct(){
        r.getPriceSum();
        r.addProduct(new Product(3, new BigDecimal(5),"Produkt 3", "Typ 1"));
        assertEquals(new BigDecimal(15).setScale(2, BigDecimal.ROUND_HALF_UP), r.getPriceSum());
    }

    @Test
    public void testRemoveProduct(){
        assertEquals(true, r.removeProduct(1));
    }

    @Test
    public void addNULLProduct(){
        //Nothing should happen
        r.addProduct(null);
    }

    @Test
    public void testRemoveNonexistentProduct(){
        assertEquals(false, r.removeProduct(5));
    }

    @Test
    public void testPriceSumRemoveProduct(){
        r.getPriceSum();
        r.removeProduct(1);
        assertEquals(new BigDecimal(7.00).setScale(2, BigDecimal.ROUND_HALF_UP), r.getPriceSum());
    }

    @Test
    public void testCountRemoveProduct(){
        r.removeProduct(1);
        assertEquals(2, r.getProductCount());
    }

    @Test
    public void testRemoveProducts(){
        r.removeProduct(1);
        r.removeProduct(2);
        r.removeProduct(3);
        assertEquals(0, r.getProductCount());
        r.removeProduct(4);
        assertEquals(0, r.getProductCount());
    }

    @Test
    public void testRemoveSameProducts(){
        r.addProduct(new Product(3, new BigDecimal(5),"Produkt 3", "Typ 1"));
        assertEquals(4, r.getProductCount());

        r.removeProduct(3);
        assertEquals(3, r.getProductCount());

        r.removeProduct(3);
        assertEquals(2, r.getProductCount());
    }
    
    @Test
    public void testAddProductWithDiscount(){
        Product p = new Product(1, new BigDecimal(10), "product 1", "typ 2");
        Discount d = new Discount(new BigDecimal(0.2), p);
        p.price = d.getPriceWithDiscount();
        r.addProduct(p);
        assertEquals(new BigDecimal(18).setScale(2, BigDecimal.ROUND_HALF_UP),r.getPriceSum());
        
    }
     @Test
    public void testAddMultipleProductsWithDiscount(){
        Product p = new Product(1, new BigDecimal(10), "product 1", "typ 2");
        Discount d = new Discount(new BigDecimal(0.2), p);
        p.price = d.getPriceWithDiscount();
        r.addProduct(p);
        Product p1 = new Product(2, new BigDecimal(20), "product 1", "typ 2");
        Discount d1 = new Discount(new BigDecimal(0.5), p1);
        p1.price = d1.getPriceWithDiscount();
        r.addProduct(p1);
        assertEquals(new BigDecimal(28).setScale(2, BigDecimal.ROUND_HALF_UP),r.getPriceSum());
        
    }






}
