package inte.projekt;

import java.math.BigDecimal;

/**
 * Created by Nicklas on 2015-10-14.
 */
public class Product {
    BigDecimal price;
    String name;
    String type;

    public Product(BigDecimal price, String name, String type){
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
