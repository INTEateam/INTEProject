/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inte.projekt;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andre
 */
public class TestDiscount {
    Product p;
    Discount d;
         
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
  
    }
    
    @Test
    public void testDiscountOnProduct(){
        p = new Product(1, new BigDecimal(100), "p1", "p2");
        d = new Discount(new BigDecimal(0.3), p);
        assertEquals(new BigDecimal(100), p.getPrice());
        assertEquals(new BigDecimal(70), d.getPriceWithDiscount());
 
        
    }
    @Test (expected = IllegalArgumentException.class)
    public void testNegativeDiscount(){
        p = new Product(1, new BigDecimal(100), "p1", "p2");        
        d = new Discount(new BigDecimal(-0.2),p);
        assertTrue(d.getDiscountAmount().compareTo(BigDecimal.ZERO) > 0);
        
        
      
    }
    @Test (expected = IllegalArgumentException.class)
    public void testMaxDiscount(){
        p = new Product(1, new BigDecimal(100), "p1", "p2");        
        d = new Discount(new BigDecimal(0.71),p);
        assertTrue(d.getDiscountAmount().compareTo(new BigDecimal(0.7)) >= 0);
        
        
      
    }
    @Test
    public void testDiscountAmount(){
        p = new Product(1, new BigDecimal(100), "p1", "p2");        
        d = new Discount(new BigDecimal(0.6),p);
        assertEquals(new BigDecimal(0.4), d.getDiscountAmount());
        d.setDiscountAmount(new BigDecimal(0.4));
        assertEquals(new BigDecimal(0.6), d.getDiscountAmount());
        
        
      
    }
    
 
    
    
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
