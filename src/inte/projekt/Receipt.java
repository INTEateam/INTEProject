package inte.projekt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicklas on 2015-10-14.
 */
public class Receipt {
    private List<Product> products;

    public Receipt(){
        products = new ArrayList<>();
    }

    public void addProduct(Product p){
        products.add(p);
    }

    public int getProductCount(){
        return products.size();
    }


    public int getPriceSum() {
        return 10;
    }
}
