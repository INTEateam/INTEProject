package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Nicklas on 2015-10-21.
 */
public interface DiscountInterface {
    public boolean checkDiscount(List<Product> allProducts, boolean isMember);
    public boolean checkDiscount(List<Product> allProducts);
    public BigDecimal getDiscountSum();
    public List<Product> getAffectedProducts();
}
