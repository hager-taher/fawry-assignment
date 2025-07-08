import java.time.LocalDateTime;

import Models.*;
import Services.*;

public class Main {
    public static void main(String[] args) {

        // --- Valid process ---
        System.out.println("=================================================================================");
        System.out.println("                         Valid Process: All items in stock, sufficient balance, and not expired.");
        System.out.println("=================================================================================");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, 400, LocalDateTime.now().plusDays(1)); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight

            Customer customer = new Customer("Hager", 20000.0);

            Cart cart = new Cart();
            cart.addProduct(cheese, 2);
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);

            CheckoutService checkoutService = new CheckoutService(customer, cart);
            checkoutService.checkout();
            System.out.println("Checkout completed successfully for valid process.");
        } catch (Exception ex) {
            System.out.println("Error during valid process: " + ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------");


        // --- Valid data with no shippable products ---
        System.out.println("\n=================================================================================");
        System.out.println("             Valid Process: No shippable products in the cart.");
        System.out.println("=================================================================================");
        try {
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight

            Customer customer = new Customer("Hager", 80000.0);

            Cart cart = new Cart();
            cart.addProduct(scratchCard, 2);

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService(customer, cart);
            checkoutService.checkout();
            System.out.println("Checkout completed successfully for no shippable products.");
        } catch (Exception ex) {
            System.out.println("Error during checkout with no shippable products: " + ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------");


        // --- Not Valid: Insufficient stock ---
        System.out.println("\n=================================================================================");
        System.out.println("                       Not Valid: Insufficient stock scenario.");
        System.out.println("=================================================================================");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, 400, LocalDateTime.now().plusDays(1)); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight

            Customer customer = new Customer("Hager", 4000.0);

            Cart cart = new Cart();
            cart.addProduct(cheese, 11); // Requesting more than available stock
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);

            CheckoutService checkoutService = new CheckoutService(customer, cart);
            checkoutService.checkout();
        } catch (Exception ex) {
            System.out.println("Expected Error: " + ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------");

        // --- Not Valid: Empty Cart ---
        System.out.println("\n=================================================================================");
        System.out.println("                          Not Valid: Empty Cart scenario.");
        System.out.println("=================================================================================");
        try {
            Customer customer = new Customer("Hager", 2000.0);
            Cart cart = new Cart();

            CheckoutService checkoutService = new CheckoutService(customer, cart);
            checkoutService.checkout();
        } catch (Exception ex) {
            System.out.println("Expected Error: " + ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------");


        // --- Not Valid: Insufficient balance ---
        System.out.println("\n=================================================================================");
        System.out.println("                      Not Valid: Insufficient balance scenario.");
        System.out.println("=================================================================================");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, 400, LocalDateTime.now().plusDays(1)); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight

            Customer customer = new Customer("Hager", 100.0); // Insufficient balance

            Cart cart = new Cart();
            cart.addProduct(cheese, 2);
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);

            CheckoutService checkoutService = new CheckoutService(customer, cart);
            checkoutService.checkout();
        } catch (Exception ex) {
            System.out.println("Expected Error: " + ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------");

        // --- Not Valid: Expired product ---
        System.out.println("\n=================================================================================");
        System.out.println("                         Not Valid: Expired product scenario.");
        System.out.println("=================================================================================");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, 400, LocalDateTime.now().minusDays(1)); // Expired
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight

            Customer customer = new Customer("Mustafa", 2000.0);

            Cart cart = new Cart();
            cart.addProduct(cheese, 2);
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);

            CheckoutService checkoutService = new CheckoutService(customer, cart);
            checkoutService.checkout();
        } catch (Exception ex) {
            System.out.println("Expected Error: " + ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------");
    }
}
