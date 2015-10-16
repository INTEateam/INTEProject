package test.inte.projekt;

import inte.projekt.Product;
import inte.projekt.Receipt;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicklas on 2015-10-14.
 * test
 */
public class TestReceipt {
    Receipt r;

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
        assertEquals(new BigDecimal(10),r.getPriceSum());
    }

    @Test
    public void testPriceSumAddProduct(){
        r.getPriceSum();
        r.addProduct(new Product(3, new BigDecimal(5),"Produkt 3", "Typ 1"));
        assertEquals(new BigDecimal(15), r.getPriceSum());
    }

    @Test
    public void testRemoveProduct(){
        assertEquals(true, r.removeProduct(1));
    }

    @Test
    public void testRemoveNonexistentProduct(){
        assertEquals(false, r.removeProduct(5));
    }

    @Test
    public void testPriceSumRemoveProduct(){
        r.getPriceSum();
        r.removeProduct(1);
        assertEquals(new BigDecimal(7), r.getPriceSum());
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




}
