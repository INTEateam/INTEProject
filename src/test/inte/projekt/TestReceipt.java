package test.inte.projekt;

import inte.projekt.Product;
import inte.projekt.Receipt;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicklas on 2015-10-14.
 * test
 */
public class TestReceipt {
    @Test
    public void testAddProduct(){
        Receipt r = new Receipt();
        r.addProduct(new Product(new BigDecimal(1), "Produkt 1", "Typ 1"));
        assertEquals(1, r.getProductCount());
    }

    @Test
    public void testPriceSum(){
        Receipt r = new Receipt();
        r.addProduct(new Product(new BigDecimal(3),"Produkt 1", "Typ 1"));
        r.addProduct(new Product(new BigDecimal(2),"Produkt 2", "Typ 1"));
        r.addProduct(new Product(new BigDecimal(5),"Produkt 3", "Typ 1"));
        assertEquals(10,r.getPriceSum());
    }


}
