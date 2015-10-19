package inte.projekt;

import java.math.BigDecimal;
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


    public BigDecimal getPriceSum() {
        BigDecimal sum = new BigDecimal(0);
        for(Product p : products){
            sum = sum.add(p.getPrice());
        }
        return sum;

    }
    public List getProductList(){
        return products;
    }

    public boolean removeProduct(int id){
        Product pRem = null;
        for(Product p : products){
            if(p.getId() == id){
                pRem = p;
                break;
            }
        }
        return products.remove(pRem);
    }

}
