package inte.projekt;

import java.math.BigDecimal;

/**
 * Created by Nicklas on 2015-10-24.
 */
public class Money {
    private final static int TWO_DECIMALS = 2;
    private BigDecimal amount;

    Money(int amount){
        this.amount = new BigDecimal(amount).setScale(TWO_DECIMALS, BigDecimal.ROUND_HALF_UP);
    }

    public void add(int amountToAdd){
        this.amount = this.amount.add(new BigDecimal(amountToAdd));
    }

    public void subtract(int amountToSubtract){
        this.amount = this.amount.subtract(new BigDecimal(amountToSubtract));
    }

    public void decreasePercent(int percent){
        this.amount = this.amount.multiply(new BigDecimal(percent).movePointLeft(2));
    }

    @Override
    public String toString() {
        return this.amount.toString();
    }
}
