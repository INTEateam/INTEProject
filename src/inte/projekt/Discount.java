/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inte.projekt;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author andre
 */
public class Discount {

    Product p;
    BigDecimal discountAmount;

    public Discount(BigDecimal discountAmount, Product p) {
        if (discountAmount.compareTo(BigDecimal.ZERO) > 0 && discountAmount.compareTo(new BigDecimal(0.7)) <= 0) {
            this.discountAmount = new BigDecimal(1).subtract(discountAmount);
            this.p = p;

        } else {
            throw new IllegalArgumentException("ken dum jävel");
        }

    }

    public BigDecimal getPriceWithDiscount() {
        BigDecimal temp;

        if (discountAmount.equals(BigDecimal.ZERO)) {
            return p.getPrice();
        } else {
            temp = p.getPrice().multiply(this.discountAmount);
            return temp.setScale(0, BigDecimal.ROUND_HALF_UP);
        }

    }

    public BigDecimal getDiscountAmount() {

        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        if (discountAmount.equals(BigDecimal.ZERO)) {
            this.discountAmount = BigDecimal.ZERO;
        } else {
            this.discountAmount = new BigDecimal(1).subtract(discountAmount);
        }

    }

    public void resetDiscountAmount() {
        
        this.discountAmount = BigDecimal.ZERO;

    }

}
