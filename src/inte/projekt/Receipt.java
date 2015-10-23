package inte.projekt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicklas on 2015-10-14.
 */
public class Receipt {
    private List<Product> products;

    private ArrayList<DiscountInterface> discounts = new ArrayList<>();

    private Customer customer;

    public Receipt() {
        products = new ArrayList<>();
    }

    public void setDiscountsFromDB(ArrayList<DiscountInterface> discounts) {
        this.discounts = discounts;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public int getProductCount() {
        return products.size();
    }

    private BigDecimal getTotalDiscount() {
        BigDecimal total = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
        for (DiscountInterface d : discounts) {
            if (d.checkDiscount(products)) {
                total = total.add(d.getDiscountSum(products));
            }
        }
        return total.setScale(2,BigDecimal.ROUND_HALF_UP);
    }


    public BigDecimal getPriceSum() {
        BigDecimal sum = new BigDecimal(0);
        for (Product p : products) {
            sum = sum.add(p.getPrice());
        }
        return sum.subtract(getTotalDiscount()).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public List getProductList() {
        //TODO deep copy?
        return new ArrayList<>(products);
    }

    public boolean removeProduct(int id) {
        //TODO iterator
        Product pRem = null;
        for (Product p : products) {
            if (p.getId() == id) {
                pRem = p;
                break;
            }
        }

        return products.remove(pRem);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String toString() {
        String receipt = "PRODUCTS\n";
        for (int i = 0; i < products.size(); i++) {
            receipt += products.get(i).getName() + "\t" + products.get(i).getPrice() + "kr \n";
        }
        receipt += "----------------------";
        receipt += "\n" + "Total: " + getPriceSum() + "kr";
        receipt += "\n\nAPPLIED DISCOUNTS\n";
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).checkDiscount(products)) {
                receipt += discounts.get(i).toString();
            }
        }
        receipt += "----------------------";
        receipt += "\n" + "Total: " + getTotalDiscount() + "kr\n";
        if (customer != null) {
            receipt += "\nDELIVERY ADDRESS";
            receipt += customer.toString();
        }

        return receipt;
    }
}
