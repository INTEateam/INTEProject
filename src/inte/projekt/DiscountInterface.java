package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Nicklas on 2015-10-21.
 */
public interface DiscountInterface {
    boolean checkDiscount(List<Product> productsFromReceipt, boolean isMember);

    boolean checkDiscount(List<Product> productsFromReceipt);

    BigDecimal getDiscountSum(List<Product> productsFromReceipt);

    List<Product> getAffectedProducts(List<Product> productsFromReceipt);

    String getID();
}
