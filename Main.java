import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private String size;
    private String color;

    public Product(String name, String size, String color) {
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

class User {
    private String name;
    private String bodySize;
    private List<Product> wishlist;

    public User(String name, String bodySize) {
        this.name = name;
        this.bodySize = bodySize;
        this.wishlist = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getBodySize() {
        return bodySize;
    }

    public void addToWishlist(Product product) {
        wishlist.add(product);
    }

    public void showWishlist() {
        System.out.println("Wishlist for " + name + ":");
        for (Product item : wishlist) {
            System.out.println("- " + item.getName() + " (" + item.getColor() + ")");
        }
    }
}

class ARSystem {

    public void tryOn(Product product, User user) {
        System.out.println("User " + user.getName() + " is trying on " + product.getName() + "...");

        if (product.getName().equals("Shirt") && product.getColor().equals("Blue")) {
            System.out.println("The Blue Shirt fits perfectly!");
            proceedWithWishlistOrShare(product, user);
        } else if (product.getSize().equals(user.getBodySize())) {
            System.out.println("The " + product.getName() + " fits perfectly!");
            proceedWithWishlistOrShare(product, user);
        } else {
            System.out.println("The " + product.getName() + " doesn't fit well.");
            if (product.getName().equals("Hat") || product.getName().equals("Pants")) {
                askForAnotherColor(product, user);
            }
        }

        System.out.println("Color: " + product.getColor());
    }

    private void askForAnotherColor(Product product, User user) {
        Scanner scanner = new Scanner(System.in);

        
        List<String> colorOptions = new ArrayList<>();
        if (product.getName().equals("Hat")) {
            colorOptions.add("Blue");
            colorOptions.add("Green");
        } else if (product.getName().equals("Pants")) {
            colorOptions.add("Gray");
            colorOptions.add("White");
        }

        System.out.print("The " + product.getName() + " doesn't fit well. What color do you want to try? Options: " + String.join(", ", colorOptions) + ": ");
        String newColor = scanner.nextLine();
        product.setColor(newColor);

        
        System.out.println("User " + user.getName() + " is trying on " + product.getName() + " in " + newColor + "...");
        System.out.println("The " + product.getName() + " fits well!");

        
        proceedWithWishlistOrShare(product, user);
    }

    private void proceedWithWishlistOrShare(Product product, User user) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Do you want to add this to your wishlist? (yes/no): ");
        String addToWishlist = scanner.nextLine();

        if (addToWishlist.equalsIgnoreCase("yes")) {
            saveToWishlist(product, user);
        }

        
        System.out.print("Do you want to share this on social media? (yes/no): ");
        String shareOnSocialMedia = scanner.nextLine();

        if (shareOnSocialMedia.equalsIgnoreCase("yes")) {
            shareOnSocialMedia(user, product);
        }

        
        user.showWishlist();
    }

    public void saveToWishlist(Product product, User user) {
        user.addToWishlist(product);
        System.out.println(product.getName() + " added to your wishlist.");
    }

    public void shareOnSocialMedia(User user, Product product) {
        System.out.println(user.getName() + " shared " + product.getName() + " on social media!");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter your body size (e.g., Small, Medium, Large): ");
        String userBodySize = scanner.nextLine();

        
        User user = new User(userName, userBodySize);

        
        Product shirt = new Product("Shirt", "Medium", "Blue");
        Product hat = new Product("Hat", "Large", "Red");
        Product pants = new Product("Pants", "Medium", "Black");

        
        ARSystem arSystem = new ARSystem();

        
        Product selectedProduct = null;
        while (selectedProduct == null) {
            try {
                System.out.println("Select a product to try on:");
                System.out.println("1. Shirt");
                System.out.println("2. Hat");
                System.out.println("3. Pants");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        selectedProduct = shirt;
                        break;
                    case 2:
                        selectedProduct = hat;
                        break;
                    case 3:
                        selectedProduct = pants;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }

        
        scanner.nextLine(); 
        arSystem.tryOn(selectedProduct, user);

        scanner.close();
    }
}
