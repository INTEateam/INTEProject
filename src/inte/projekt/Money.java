package inte.projekt;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Nicklas on 2015-10-24.
 */
public class Money {
    private final static int TWO_DECIMALS = 2;
    private final static MathContext context = new MathContext(TWO_DECIMALS, RoundingMode.HALF_UP);
    private BigDecimal amount;

    Money(int amount) {
        this.amount = new BigDecimal(amount, context);
    }

    public void add(int amountToAdd) {
        this.amount = this.amount.add(new BigDecimal(amountToAdd));
    }

    public void add(double amountToAdd) {
        this.amount = this.amount.add(new BigDecimal(amountToAdd).setScale(TWO_DECIMALS, RoundingMode.HALF_UP));
    }

    public void subtract(int amountToSubtract) {
        this.amount = this.amount.subtract(new BigDecimal(amountToSubtract));
    }

    public void subtract(double amountToSubtract) {
        this.amount = this.amount.subtract(new BigDecimal(amountToSubtract).setScale(TWO_DECIMALS, RoundingMode.HALF_UP));
    }

    public void decreasePercent(int percent) {
        BigDecimal decimalPercent = new BigDecimal(10).movePointLeft(2);
        BigDecimal amountToRemove = this.amount.multiply(decimalPercent, context);
        this.amount = this.amount.subtract(amountToRemove, context);
    }

    @Override
    public String toString() {
        return this.amount.setScale(TWO_DECIMALS).toString();
    }
}
