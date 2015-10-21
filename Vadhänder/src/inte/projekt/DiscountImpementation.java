package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Nicklas on 2015-10-21.
 */
public class DiscountImpementation implements DiscountInterface{
    List<Product> allProducts;
    private BigDecimal discountAmount = new BigDecimal(0.10).setScale(2, BigDecimal.ROUND_HALF_UP);

    @Override
    public boolean checkDiscount(List<Product> allProducts, boolean isMember) {
        if(isMember){
            this.allProducts = allProducts;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkDiscount(List<Product> allProducts) {
        return false;
    }

    @Override
    public BigDecimal getDiscountSum() {
        BigDecimal sum = new BigDecimal(0);
        for(Product p : allProducts){

            sum = sum.add(p.getPrice());
        }
        sum = sum.multiply(this.discountAmount);
        return sum;
    }

    @Override
    public List<Product> getAffectedProducts() {

        return allProducts;
    }
}
