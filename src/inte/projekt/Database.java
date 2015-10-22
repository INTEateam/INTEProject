package inte.projekt;

import java.util.ArrayList;

/**
 * Created by Nicklas on 2015-10-21.
 */
public class Database {
    ArrayList<Product> allProducts;
    ArrayList<DiscountInterface> allDiscount;

    Database() {
        this.allDiscount = new ArrayList<>();
        this.allProducts = new ArrayList<>();
    }

    public ArrayList<Product> getAllProducts() {
        return new ArrayList<Product>(allProducts);
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public ArrayList<DiscountInterface> getAllDiscount() {
        return new ArrayList<DiscountInterface>(allDiscount);
    }

    public void setAllDiscount(ArrayList<DiscountInterface> allDiscount) {
        this.allDiscount = allDiscount;
    }


}
