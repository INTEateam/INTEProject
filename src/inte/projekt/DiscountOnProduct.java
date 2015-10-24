package inte.projekt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountOnProduct implements DiscountInterface {
    private String DISCOUNT_ID = "Discount on Product";
    private int productId;
    private BigDecimal discountPercentage;
    private boolean onlyMembers;


    public DiscountOnProduct(int productId, BigDecimal discountPercentage) {
        this(productId, discountPercentage, false);
    }

    public DiscountOnProduct(int productId, BigDecimal discountPercentage, boolean onlyMembers) {                 // Added scale and RoundingMode for precision
        if (discountPercentage.compareTo(BigDecimal.ZERO) > 0 && discountPercentage.compareTo(new BigDecimal(0.7).setScale(2, BigDecimal.ROUND_HALF_UP)) <= 0) {
            this.discountPercentage = discountPercentage;
            this.productId = productId;
            this.onlyMembers = onlyMembers;
        } else {
            throw new IllegalArgumentException("ken dum jävel");
        }

    }

    @Override
    public boolean checkDiscount(List<Product> productsFromReceipt, boolean isMember) {
        boolean b = false;
        if (isMember) {
            b = productValidForDiscountExists(productsFromReceipt);

        } else if (!onlyMembers) {
            b = productValidForDiscountExists(productsFromReceipt);
        }
        return b;
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
        BigDecimal temp = new BigDecimal(0);
        for (Product p : productsFromReceipt) {
            if (p.getId() == productId) {
                temp = temp.add(p.getPrice().multiply(this.discountPercentage));
            }
        }

        return temp.setScale(0, BigDecimal.ROUND_HALF_UP);


    }

    @Override
    public List<Product> getAffectedProducts(List<Product> productsFromReceipt) {
        List<Product> l = new ArrayList<>();
        for (Product p : productsFromReceipt) {
            if (p.getId() == productId) {
                l.add(p);
            }
        }
        return l;
    }

    public BigDecimal getPriceWithDiscount(List<Product> productsFromReceipt) {
        BigDecimal temp = BigDecimal.ZERO;
        for (Product p : productsFromReceipt) {
            if (p.getId() == productId) {
                temp = temp.add(p.getPrice().multiply(BigDecimal.ONE.subtract(this.discountPercentage)));
            }
        }

        return temp.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String getID() {
        return DISCOUNT_ID;
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return discountPercentage;
    }

}
