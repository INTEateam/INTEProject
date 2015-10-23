package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Nicklas on 2015-10-21.
 */
public class DiscountOnAllProducts implements DiscountInterface {
    final private String DISCOUNTNAME = "Sales discount";
    private BigDecimal discountAmount;
    private boolean onlyMembers;


    public DiscountOnAllProducts(BigDecimal decimalPercent, boolean onlyMembers) {
        discountAmount = decimalPercent.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.onlyMembers = onlyMembers;
    }

    public DiscountOnAllProducts(BigDecimal decimalPercent) {
        discountAmount = decimalPercent.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.onlyMembers = false;
    }

    @Override
    public boolean checkDiscount(List<Product> allProducts, boolean isMember) {
        if (onlyMembers) {
            return isMember;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkDiscount(List<Product> allProducts) {
        return !onlyMembers;
    }

    @Override
    public BigDecimal getDiscountSum(List<Product> productsFromReceipt) {
        if (checkDiscount(productsFromReceipt)) {
            BigDecimal sum = new BigDecimal(0);
            for (Product p : productsFromReceipt) {

                sum = sum.add(p.getPrice());
            }
            sum = sum.multiply(this.discountAmount).divide(new BigDecimal(100));
            return sum;
        }
        return new BigDecimal(0);
    }

    @Override
    public List<Product> getAffectedProducts(List<Product> productsFromReceipt) {
        return productsFromReceipt;
    }

    @Override
    public String getID() {
        return DISCOUNTNAME;
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public String toString() {
        return getID() + "\t" + getDiscountAmount() + "%\n";
    }


}
