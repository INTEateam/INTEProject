package inte.projekt;

import java.math.BigDecimal;
import java.util.List;

public class DiscountOneForFree implements DiscountInterface {
	private List<Product> allProducts;
	private int numberOfProducts;
	private int numberOfProductsToPay; 
	private Product p;
	
	public DiscountOneForFree(int numberOfProducts, int numberOfProductsToPay, Product p){
		this.numberOfProducts=numberOfProducts;
		this.numberOfProductsToPay=numberOfProductsToPay;
		this.p=p;
		
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
		return null;
 
    }
	@Override
    public List<Product> getAffectedProducts(){
    	return allProducts;
    }

}
