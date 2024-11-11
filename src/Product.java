//File: Product.java
public class Product implements Comparable<Product> {
    private String id;
    private String productName;
    private String category;
    private String price;

    public Product(String id, String productName, String category, String price) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    @Override
    public int compareTo(Product other) {
        return this.id.compareTo(other.id);
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }
}