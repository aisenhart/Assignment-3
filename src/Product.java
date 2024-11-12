/**
 * The Product class represents a product with an ID, name, category, and price.
 * It implements the Comparable interface to allow comparison based on the product ID.
 */
public class Product implements Comparable<Product> {
    private String id;           // The unique identifier for the product
    private String productName;  // The name of the product
    private String category;     // The category of the product
    private String price;        // The price of the product

    /**
     * Constructs a new Product with the specified ID, name, category, and price.
     */
    public Product(String id, String productName, String category, String price) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    /**
     * Compares this product to another product based on their IDs.
     */
    @Override
    public int compareTo(Product other) {
        return this.id.compareTo(other.id);
    }

    /**
     * Returns the ID of the product.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Returns the category of the product.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the price of the product.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the product.
     */
    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + productName + ", Category: " + category + ", Price: " + price;
    }
}