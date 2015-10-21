/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inte.projekt;

/**
 *
 * @author andre
 */


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountPerProduct implements DiscountInterface{
    List<Product> allProducts  = new ArrayList<>();
    Product p;
    BigDecimal discountAmount;
    Database db;
    
    
    
    public DiscountPerProduct(Product p, BigDecimal discountAmount){
        this.p = p;
        this.discountAmount = discountAmount;
        
    }

    @Override
    public boolean checkDiscount(List<Product> allProducts, boolean isMember) {
        if(isMember){
            this.allProducts = allProducts;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkDiscount(List<Product> allProducts) {
        return false;
    }

    @Override
    public BigDecimal getDiscountSum() {
        
        BigDecimal sum = new BigDecimal(0);
        BigDecimal priceWithDiscount;

        for(Product p : allProducts){
            System.out.println(allProducts);
            priceWithDiscount = p.getPrice().multiply(discountAmount); 
            sum = sum.add(priceWithDiscount);
        }
        
        return sum;
    }
    public BigDecimal getDiscountAmount(){
        return discountAmount;
    }
    
    public BigDecimal getPriceWithDiscount(){
        this.discountAmount = new BigDecimal(1).subtract(discountAmount);
        this.discountAmount = p.getPrice().multiply(this.discountAmount);
        
        return discountAmount.setScale(0,RoundingMode.HALF_UP);
        
    }

    @Override
    public List<Product> getAffectedProducts() {

        return allProducts;
    }
}
