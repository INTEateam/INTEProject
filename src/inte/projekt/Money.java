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
        if (notNegative(amountToAdd))
            this.amount = this.amount.add(new BigDecimal(amountToAdd));
        else throw new IllegalArgumentException("Negative Number");
    }

    public void add(double amountToAdd) {
        if (notNegative(amountToAdd))
            this.amount = this.amount.add(new BigDecimal(amountToAdd).setScale(TWO_DECIMALS, RoundingMode.HALF_UP));
        else throw new IllegalArgumentException("Negative Number");
    }

    public void subtract(int amountToSubtract) {
        if (notNegative(amountToSubtract))
            this.amount = this.amount.subtract(new BigDecimal(amountToSubtract));
        else throw new IllegalArgumentException("Negative Number");
    }

    public void subtract(double amountToSubtract) {
        if (notNegative(amountToSubtract))
            this.amount = this.amount.subtract(new BigDecimal(amountToSubtract).setScale(TWO_DECIMALS, RoundingMode.HALF_UP));
        else throw new IllegalArgumentException("Negative Number");
    }

    public void decreasePercent(int percent) {
        BigDecimal decimalPercent = new BigDecimal(percent).movePointLeft(2);
        BigDecimal amountToRemove = this.amount.multiply(decimalPercent, context);
        this.amount = this.amount.subtract(amountToRemove);
    }

    public void increasePercent(int percent) {
        BigDecimal decimalPercent = new BigDecimal(percent, context).movePointLeft(2);
        BigDecimal amountToAdd = this.amount.multiply(decimalPercent, context);
        this.amount = this.amount.add(amountToAdd);
    }

    private boolean notNegative(int num) {
        return num >= 0;
    }

    private boolean notNegative(double num) {
        return num >= 0;
    }

    @Override
    public String toString() {
        return this.amount.setScale(TWO_DECIMALS).toString();
    }
}
