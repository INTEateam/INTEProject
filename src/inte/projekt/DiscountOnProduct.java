package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

public class DiscountOnProduct implements DiscountInterface {

    private int id;
    private BigDecimal discountProcentage;


    public DiscountOnProduct(int id, BigDecimal discountProcentage) {
        if (discountProcentage.compareTo(BigDecimal.ZERO) > 0 && discountProcentage.compareTo(new BigDecimal(0.7)) <= 0) {
            this.discountProcentage = discountProcentage;
            this.id = id;
        } else {
            throw new IllegalArgumentException("ken dum jävel");
        }

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
        BigDecimal temp= new BigDecimal(0);
        for (Product p : productsFromReceipt) {
            if (p.getId() == id) {
                temp = temp.add(p.getPrice().multiply(this.discountProcentage));
                
            }
        }
        return temp.setScale(0, BigDecimal.ROUND_HALF_UP);


    }

    @Override
    public List<Product> getAffectedProducts(List<Product> productsFromReceipt) {
        return productsFromReceipt;
    }
    public BigDecimal getPriceWithDiscount(List<Product> productsFromReceipt) {
        BigDecimal temp;
        for (Product p : productsFromReceipt) {
           if (discountProcentage.equals(BigDecimal.ZERO)) {
            return p.getPrice();
        } else {
            temp = BigDecimal.ONE.subtract(this.discountProcentage);
            temp = p.getPrice().multiply(temp);
            return temp.setScale(0, BigDecimal.ROUND_HALF_UP);
        } 
        }
        
        return null;
    }

}
