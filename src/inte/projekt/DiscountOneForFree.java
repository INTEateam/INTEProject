package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

public class DiscountOneForFree implements DiscountInterface {
    private List<Product> allProducts;
    private int numberOfProducts;
    private int numberOfProductsToPay;
    private int id;

    public DiscountOneForFree(int numberOfProducts, int numberOfProductsToPay, int id) {
        this.numberOfProducts = numberOfProducts;
        this.numberOfProductsToPay = numberOfProductsToPay;
        this.id = id;

    }

    @Override
    public boolean checkDiscount(List<Product> productsFromReceipt, boolean isMember) {
        return false;

    }

    @Override
    public boolean checkDiscount(List<Product> productsFromReceipt) {
        return true;

    }

    @Override
    public BigDecimal getDiscountSum(List<Product> productsFromReceipt) {
        int temp = 0;
        BigDecimal tempValue = new BigDecimal(0);
        for (Product p : productsFromReceipt) {
            if (p.getId() == id) {
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
        return allProducts;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return null;
    }


}
