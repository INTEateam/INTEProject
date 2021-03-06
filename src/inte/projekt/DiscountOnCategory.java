package inte.projekt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountOnCategory implements DiscountInterface {
    private String DISCOUNT_ID = "Category Discount";
    private String category;
    private BigDecimal amount = BigDecimal.ZERO;
    private boolean onlyMembers;

    public DiscountOnCategory(String category, BigDecimal amount) {
        this.category = category;
        this.amount = amount;
    }

    public DiscountOnCategory(String category, BigDecimal amount, boolean onlyMembers) {
        this.category = category;
        this.amount = amount;
        this.onlyMembers = onlyMembers;
    }

    @Override
    public boolean checkDiscount(List<Product> productsFromReceipt, boolean isMember) {
        if(onlyMembers){
            return isMember;
        }else{
            return true;
        }
    }

    @Override
    public boolean checkDiscount(List<Product> productsFromReceipt) {
        for (Product p : productsFromReceipt) {
            if (p.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BigDecimal getDiscountSum(List<Product> productsFromReceipt) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product p : productsFromReceipt) {
            if (p.getCategory().equals(category)) {
                sum = sum.add(getDiscountAmount());
            }
        }
        return sum;
    }

    @Override
    public List<Product> getAffectedProducts(List<Product> productsFromReceipt) {
        List<Product> l = new ArrayList<>();
        for (Product p : productsFromReceipt) {
            if (p.getCategory().equals(category)) {
                l.add(p);
            }
        }
        return l;
    }

    @Override
    public String getID() {
        return DISCOUNT_ID;
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return getID() +
                ", Category: " + category +
                ", Amount: " + getDiscountAmount().toString() +
                ", Only Members: " + onlyMembers;
    }
}
