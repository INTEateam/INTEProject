package inte.projekt;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDiscountOnCategory {

    private List<Product> p = new ArrayList<>();

    @Before
    public void before() throws Exception {
        Product p1 = new Product(147, new BigDecimal(30), "Barbaque Chicken", "Meat");
        Product p2 = new Product(147, new BigDecimal(30), "T-Bone", "Meat");
        Product p3 = new Product(258, new BigDecimal(15), "Coca Cola 1L", "Beverage");
        Product p4 = new Product(369, new BigDecimal(25), "Cheese Wheel", "Dairy");

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
    }

    @Test
    public void testCheckDiscountExists() {
        DiscountOnCategory d = new DiscountOnCategory("Meat", new BigDecimal(5));
        assertEquals(true, d.checkDiscount(p));
    }

    @Test
    public void testCheckDiscountDoesNotExists() {
        DiscountOnCategory d = new DiscountOnCategory("Electronics", new BigDecimal(5));
        assertEquals(false, d.checkDiscount(p));
    }

    @Test
    public void testCheckDiscountSum() {
        DiscountOnCategory d = new DiscountOnCategory("Meat", new BigDecimal(5));
        assertEquals(new BigDecimal(10), d.getDiscountSum(p));
    }

    @Test
    public void testGetAffectedProducts(){
        DiscountOnCategory doc = new DiscountOnCategory("Meat", new BigDecimal(5));
        assertEquals(2, doc.getAffectedProducts(p).size());
    }

    @Test
    public void testCheckDiscountForMembersIsMember(){
        DiscountOnCategory doc = new DiscountOnCategory("Meat", new BigDecimal(5),true);
        assertEquals(true,doc.checkDiscount(p,true));
    }

    @Test
    public void testCheckDiscountForMembersIsNotMember(){
        DiscountOnCategory doc = new DiscountOnCategory("Meat", new BigDecimal(5),true);
        assertEquals(false,doc.checkDiscount(p,false));
    }

    @Test
    public void testCheckDiscountNotForMembersIsMember(){
        DiscountOnCategory doc = new DiscountOnCategory("Meat", new BigDecimal(5),false);
        assertEquals(true,doc.checkDiscount(p,true));
    }

    @Test
    public void testToString(){
        DiscountOnCategory doc = new DiscountOnCategory("Meat", new BigDecimal(5));
        assertEquals("Category Discount, Category: Meat, Amount: 5, Only Members: false",doc.toString());
    }

}