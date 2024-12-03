import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedBlackTree<Product> rbt = new RedBlackTree<>();
        List<Product> products = new ArrayList<>();
        int insertionCount = 0;

        // Read product data from CSV file and insert into Red-Black Tree
        try (BufferedReader br = new BufferedReader(new FileReader("src/amazon-product-data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data.length > 0 ? data[0] : "";
                String name = data.length > 1 ? data[1] : "";
                String category = data.length > 2 ? data[2] : "";
                String price = data.length > 3 ? data[3] : "";

                Product product = new Product(id, name, category, price);
                if (rbt.search(id) != null) {
                    System.out.println("Error: Product with ID " + id + " already exists. Skipping insertion.");
                } else {
                    products.add(product);
                    // Logging insertion process
                    System.out.println("Inserting #" + insertionCount + " " + product);
                    insertionCount++;
                    long startTime = System.nanoTime();
                    rbt.insert(product);
                    long endTime = System.nanoTime();
                    System.out.println("Insertion time: " + (endTime - startTime) + " ns");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to search or insert a product? (search/insert)");
            String answer = scanner.nextLine();
            if (answer.equals("search")) {
                System.out.println("Type the product id you want to search:");
                String search = scanner.nextLine();
                long startTime = System.nanoTime();
                Product result = rbt.search(search);
                long endTime = System.nanoTime();
                if (result != null) {
                    System.out.println("Found: " + result);
                } else {
                    System.out.println("Not found");
                }
                System.out.println("Search time: " + (endTime - startTime) + " ns");
            } else if (answer.equals("insert")) {
                System.out.println("Type the product id you want to insert:");
                String id = scanner.nextLine();
                System.out.println("Type the product name you want to insert:");
                String name = scanner.nextLine();
                System.out.println("Type the product category you want to insert:");
                String category = scanner.nextLine();
                System.out.println("Type the product price you want to insert:");
                String price = scanner.nextLine();
                Product product = new Product(id, name, category, price);
                if (rbt.search(id) != null) {
                    System.out.println("Error: Product with ID " + id + " already exists. Skipping insertion.");
                } else {
                    long startTime = System.nanoTime();
                    rbt.insert(product);
                    long endTime = System.nanoTime();
                    products.add(product);
                    System.out.println("Product inserted: " + product);
                    System.out.println("Insertion time: " + (endTime - startTime) + " ns");
                }
            } else {
                System.out.println("Invalid input");
            }
            System.out.println("Do you want to search or insert again? (yes/no)");
            answer = scanner.nextLine();
            if (answer.equals("no")) {
                break;
            }
        }
    }
}