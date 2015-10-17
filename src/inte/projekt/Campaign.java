package inte.projekt;

import java.math.BigDecimal;

public class Campaign {

	Receipt r;
	BigDecimal totalDiscount;

	public Campaign(Receipt r, BigDecimal totalDiscount) {
		if (totalDiscount.compareTo(BigDecimal.ZERO) > 0
				&& totalDiscount.compareTo(new BigDecimal(0.5)) <= 0) {
			this.totalDiscount = new BigDecimal(1).subtract(totalDiscount);
			this.r = r;

		} else {
			throw new IllegalArgumentException("ken dum jävel");
		}

	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public BigDecimal getPriceByDiscount() {
		BigDecimal temp;
		temp = r.getPriceSum().multiply(totalDiscount);
		return temp.setScale(0, BigDecimal.ROUND_HALF_UP);

	}

}
