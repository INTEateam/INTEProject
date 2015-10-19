package inte.projekt;

import java.math.BigDecimal;

/**
 * Created by Nicklas on 2015-10-14, modified by John on 2015-10-18
 */
public class Product {
	int id;
	BigDecimal price;
	String name;
	String category;

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

		if (price.signum() == 1) {

			checkDecimals(price);

		} else if (price.signum() == 0) {
			throw new IllegalArgumentException("Price cannot be 0");
		} else if (price.signum() == -1) {
			throw new IllegalArgumentException("Price cannot be negative");
		} else if (price.scale() > 2) {
			throw new IllegalArgumentException(
					"No more than two decimals are allowed");
		}

		else
			throw new IllegalArgumentException("Invalid price");

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
		} else
			throw new IllegalArgumentException("Invalid ID");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// Assumption of valid length interval according to some req. specifiation
		if (name.length() > 2 && name.length() <= 50) {

			validateName(name);

		} else if (name.length() < 2) {
			throw new IllegalArgumentException("name too short");
		} else if (name.length() > 50) {
			throw new IllegalArgumentException("name too long");
		} else if (name == null ) {
			throw new NullPointerException("name is not allowed to be null");
		} else
			throw new IllegalArgumentException("Invalid name");
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		// Assumption of valid length interval according to some req. specifiation
		if (category.length() > 2 && category.length() <= 50) {

			validateCategory(category);

		} else if (category.length() < 2) {
			throw new IllegalArgumentException("category too short");
		} else if (category.length() > 50) {
			throw new IllegalArgumentException("category too long");
		} else if (category == null) {
			throw new NullPointerException("category is not allowed to be null");
		} else
			throw new IllegalArgumentException("Invalid category");
	}

	public void checkDecimals(BigDecimal price) {

		if (price.scale() >= 0 && price.scale() <= 2) {
			this.price = price;
		} else
			throw new ArithmeticException(
					"No more than two decimals are allowed");

	}

	public void validateName(String name) {
		// Assumption of valid characters according to some req. specifiation
		if (name.matches("[a-zA-Z_0-9-& ]+")) {
			this.name = name;
		} else
			throw new IllegalArgumentException(
					"name contains invalid characters");

	}

	public void validateCategory(String category) {
		// Assumption of valid characters according to some req. specifiation
		if (category.matches("[a-zA-Z_0-9- ]+")) {
			this.category = category;
		} else
			throw new IllegalArgumentException(
					"category contains invalid characters");

	}

}
