package inte.projekt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountOneForFree implements DiscountInterface {
    private String DISCOUNT_ID = "One for free";
    private int numberOfProducts;
    private int numberOfProductsToPay;
    private int affectedProductId;
    private boolean onlyMembers;

    public DiscountOneForFree(int numberOfProducts, int numberOfProductsToPay, int affectedProductId, boolean onlyMembers) {
        this.numberOfProducts = numberOfProducts;
        this.numberOfProductsToPay = numberOfProductsToPay;
        this.affectedProductId = affectedProductId;
        this.onlyMembers = onlyMembers;
    }

    public DiscountOneForFree(int numberOfProducts, int numberOfProductsToPay, int affectedProductId) {
        this(numberOfProducts, numberOfProductsToPay, affectedProductId, false);
    }

    @Override
    public boolean checkDiscount(List<Product> productsFromReceipt, boolean isMember) {
        boolean valid = false;
        if (onlyMembers) {
            if (isMember) {
                valid = productValidForDiscountExists(productsFromReceipt);
            }
        } else {
            valid = productValidForDiscountExists(productsFromReceipt);
        }
        return valid;
    }

    private boolean productValidForDiscountExists(List<Product> productsFromReceipt) {
        return getAffectedProducts(productsFromReceipt).size() > 0;
    }

    @Override
    public boolean checkDiscount(List<Product> productsFromReceipt) {
        return checkDiscount(productsFromReceipt, false);
    }

    @Override
    public BigDecimal getDiscountSum(List<Product> productsFromReceipt) {
        int temp = 0;
        BigDecimal tempValue = new BigDecimal(0);
        for (Product p : productsFromReceipt) {
            if (p.getId() == affectedProductId) {
                temp++;
                if (temp == numberOfProducts) {
                    temp = 0;
                    tempValue = tempValue.add(p.getPrice().multiply((new BigDecimal(numberOfProducts - numberOfProductsToPay))));

                }
            }

        }
        return tempValue;
    }

    @Override
    public List<Product> getAffectedProducts(List<Product> productsFromReceipt) {
        List<Product> l = new ArrayList<>();
        for (Product p : productsFromReceipt) {
            if (p.getId() == affectedProductId) {
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
        return new BigDecimal(numberOfProducts);
    }

    @Override
    public String toString() {
        return  DISCOUNT_ID +
                ", Number of products: " + numberOfProducts +
                ", Number of products to pay: " + numberOfProductsToPay +
                ", Affected Product Id: " + affectedProductId +
                ", Only Members: " + onlyMembers;
    }
}
