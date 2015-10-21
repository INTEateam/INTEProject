package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Nicklas on 2015-10-21.
 */
public interface DiscountInterface {
    public boolean checkDiscount(List<Product> productsFromReceipt, boolean isMember);
    public boolean checkDiscount(List<Product> productsFromReceipt);
    public BigDecimal getDiscountSum(List<Product> productsFromReceipt);
    public List<Product> getAffectedProducts(List<Product> productsFromReceipt);
}
