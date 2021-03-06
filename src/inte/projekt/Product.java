package inte.projekt;

import java.math.BigDecimal;

/**
 * Created by Nicklas on 2015-10-14, modified by John on 2015-10-23
 */
public class Product {
    private static final int CATEGORY_MAX_LENGHT = 50;
    private static final int CATEGORY_MIN_LENGHT = 2;
    private static final int NAME_MAX_LENGHT = 50;
    private static final int NAME_MIN_LENGHT = 2;

    private BigDecimal price;
    private int id;
    private String name;
    private String category;

    public Product(int id, BigDecimal price, String name, String category) {

        setId(id);
        setPrice(price);
        setName(name);
        setCategory(category);
    }

    public BigDecimal getPrice() {

        return price;

    }

    public void setPrice(BigDecimal price) {
        if (price != null) {
            if (price.signum() == 1) {

                checkDecimals(price);

            } else if (price.signum() == 0) {
                throw new IllegalArgumentException("Price cannot be 0");
            } else if (price.signum() == -1) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
        } else {
            throw new NullPointerException("Price null!");
        }
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        if (id == 0) {
            throw new IllegalArgumentException("ID is not allowed to be 0");
        } else if (id < 0) {
            throw new IllegalArgumentException(
                    "ID is not allowed to be negative");
        } else if (id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Assumption of valid length interval according to some req. specifiation
        if (name != null) {
            if (name.length() > 2 && name.length() <= 50) {

                validateName(name);

            } else if (name.length() < NAME_MIN_LENGHT) {
                throw new IllegalArgumentException("name too short");
            } else if (name.length() > NAME_MAX_LENGHT) {
                throw new IllegalArgumentException("name too long");
            }
        } else {
            throw new NullPointerException("name is not allowed to be null");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        // Assumption of valid length interval according to some req. specifiation
        if (category != null) {
            if (category.length() > 2 && category.length() <= 50) {

                validateCategory(category);

            } else if (category.length() < CATEGORY_MIN_LENGHT) {
                throw new IllegalArgumentException("category too short");
            } else if (category.length() > CATEGORY_MAX_LENGHT) {
                throw new IllegalArgumentException("category too long");
            }
        } else {
            throw new NullPointerException("category is not allowed to be null");
        }
    }

    public void checkDecimals(BigDecimal price) {

        if (price.scale() >= 0 && price.scale() <= 2) {
            this.price = price;
        } else
            throw new IllegalArgumentException("No more than two decimals are allowed");

    }

    public void validateName(String name) {
        // Assumption of valid characters according to some req. specifiation
        if (name.matches("[a-zA-Z_0-9-& ]+")) {
            this.name = name;
        } else
            throw new IllegalArgumentException("name contains invalid characters");

    }

    public void validateCategory(String category) {
        // Assumption of valid characters according to some req. specifiation
        if (category.matches("[a-zA-Z_0-9- ]+")) {
            this.category = category;
        } else
            throw new IllegalArgumentException("category contains invalid characters");

    }

}
