package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

public class Campaign {

    private Receipt r;
    private BigDecimal totalDiscount;

    public Campaign(Receipt r) {

        this.r = r;

    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public BigDecimal getPriceByDiscount() {
        BigDecimal temp;
        temp = r.getPriceSum().multiply(totalDiscount);
        return temp.setScale(0, BigDecimal.ROUND_HALF_UP);

    }

    @SuppressWarnings("unchecked")
    public void setThreeForTwoDiscount(int id) {

        int counter = 0;
        List<Product> products;
        //noinspection unchecked,unchecked
        products = r.getProductList();

        for (Product product : products) {

            if (id == product.getId()) {
                counter = counter + 1;

            }

        }
        if (counter == 3) {
            r.removeProduct(id);

        }

    }

    public void setPercentageDiscount(BigDecimal discountAmount) {
        if (discountAmount.compareTo(BigDecimal.ZERO) > 0
                && discountAmount.compareTo(new BigDecimal(0.5)) <= 0) {
            discountAmount = new BigDecimal(1).subtract(discountAmount);
            this.totalDiscount = discountAmount;

        } else {
            throw new IllegalArgumentException();
        }

    }
}
