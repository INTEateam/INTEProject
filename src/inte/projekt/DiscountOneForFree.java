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
    public boolean checkDiscount(List<Product> productsFromReceipt, boolean isMember){
		return false;
    	
    }
	@Override
    public boolean checkDiscount(List<Product> productsFromReceipt){
		return true;
    	
    }
	@Override
    public BigDecimal getDiscountSum(List<Product> productsFromReceipt){
		return null;
 
    }
	@Override
    public List<Product> getAffectedProducts(List<Product> productsFromReceipt){
    	return allProducts;
    }

}
