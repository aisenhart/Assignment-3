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

        // Read product data from CSV file and insert into Red-Black Tree
        try (BufferedReader br = new BufferedReader(new FileReader("src/amazon-product-data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    // Create product with category
                    Product product = new Product(data[0], data[1], data[2], data[3]);
                    products.add(product);
                    //Logging insertion process
                    System.out.println("Inserting: " + product);
                    rbt.insert(product);





                } else if (data.length == 3) {
                    // [Noticed some Products did not have category] Create product without category
                    Product product = new Product(data[0], data[1], "", data[2]);
                    products.add(product);
                    rbt.insert(product);
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
                Product result = rbt.search(search);
                if (result != null) {
                    System.out.println("Found: " + result);
                } else {
                    System.out.println("Not found");
                }
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
                rbt.insert(product);
                products.add(product);
                System.out.println("Product inserted: " + product);
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