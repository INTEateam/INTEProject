package inte.projekt;

import java.math.BigDecimal;
import java.util.ArrayList;

public class random {

    public static void main(String[] args){
        Product p1 = new Product(1471,new BigDecimal(969),"Samsung SSD 850 Evo","Computer Hardware");
        Product p2 = new Product(2582,new BigDecimal(1690),"Jaybird Bluebuds X2","Headphones");
        Product p3 = new Product(3693,new BigDecimal(5789),"Sony Xperia Z5 Compact","Mobile Phones");
        Product p4 = new Product(1231,new BigDecimal(699),"Moses Hairtrimmer Procut","Personal Hygiene");
        Product p5 = new Product(4564,new BigDecimal(999),"Explorer Parka","Jackets");
        Product p6 = new Product(7897,new BigDecimal(799),"Blizzard Jacket","Jackets");
        Product p7 = new Product(7946,new BigDecimal(15),"Banan","Frukt",new BigDecimal(0.3));

        // Total value: 10 949kr


        Receipt r = new Receipt();
        r.addProduct(p1);
        r.addProduct(p2);
        r.addProduct(p3);
        r.addProduct(p4);
        r.addProduct(p5);
        r.addProduct(p6);
        r.addProduct(p7);

        Customer c = new Customer("9001013318","Frodo","Baggins","Bag End","1","12345",true);
        r.setCustomer(c);

        Database db = new Database();
        ArrayList<DiscountInterface> discounts = new ArrayList<>();
        DiscountOnCategory d1 = new DiscountOnCategory("Jackets",new BigDecimal(100));
        DiscountOnAllProducts d2 = new DiscountOnAllProducts(new BigDecimal(15));
        discounts.add(d1);
        discounts.add(d2);
        db.setAllDiscount(discounts);
        r.setDiscountsFromDB(db.getAllDiscount());

        System.out.print(r.toString());

    }

}
