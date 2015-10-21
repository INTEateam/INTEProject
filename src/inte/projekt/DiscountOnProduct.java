package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

public class DiscountOnProduct implements DiscountInterface {
	private List<Product> allProducts;
	private int id;
	private BigDecimal discountAmount;
	
	
	public DiscountOnProduct(int id, BigDecimal discountAmount, List<Product> allProducts) {
		 if (discountAmount.compareTo(BigDecimal.ZERO) > 0 && discountAmount.compareTo(new BigDecimal(0.7)) <= 0) {
	            this.discountAmount = new BigDecimal(1).subtract(discountAmount);
	            this.id=id;
	    		this.allProducts=allProducts;
	        } else {
	            throw new IllegalArgumentException("ken dum jävel");
	        }

	}
	@Override
    public boolean checkDiscount(List<Product> allProducts, boolean isMember){
		return false;
    	
    }
	@Override
    public boolean checkDiscount(List<Product> allProducts){
		return true;
    	
    }
	@Override
    public BigDecimal getDiscountSum(){
		 BigDecimal temp;
		for(Product p : allProducts){
			if(p.getId()==id){
			            temp = p.getPrice().multiply(this.discountAmount);
			            return temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			        }
			
			}
		return null;

		
    
      
 
    }
	@Override
    public List<Product> getAffectedProducts(){
    	return allProducts;
    }

}
