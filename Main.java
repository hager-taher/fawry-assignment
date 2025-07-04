import java.time.LocalDateTime;

import Models.*;
import Services.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Valid process");
       try{
           ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, 400,  LocalDateTime.now().plusDays(1)); // 1 day expiration
           ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
           Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
           
           Customer customer = new Customer("Hager", 20000.0);
           
           Cart cart = new Cart();
           cart.addProduct(cheese, 2);
           cart.addProduct(tv, 1);
           cart.addProduct(scratchCard, 1);
          
           CheckoutService checkoutService = new CheckoutService(customer, cart);
           checkoutService.checkout();

       }
       catch (Exception ex)
       {
        System.out.println(ex.getMessage());
       }
       

      
        
        System.out.println("Valid data with no shippable products");
        try{

            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
           
            Customer customer = new Customer("Hager", 80000.0);
           
            Cart cart = new Cart();

            cart.addProduct(scratchCard, 2);

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService(customer,cart);
            checkoutService.checkout();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        System.out.println("Not Valid : Insufficient stock !!");
        try{
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 400, LocalDateTime.now().plusDays(1)); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
           
            Customer customer = new Customer("Hager", 4000.0);
            
            Cart cart = new Cart();
            cart.addProduct(cheese, 11);
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);
            
            CheckoutService checkoutService = new CheckoutService(customer,cart);
            checkoutService.checkout();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
       
        System.out.println("Not Valid : Empty Cart !!");
        try{
            
            Customer customer = new Customer("Hager", 2000.0);
            Cart cart = new Cart();

            
            CheckoutService checkoutService = new CheckoutService(customer,cart);
            checkoutService.checkout();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        

        System.out.println("Not Valid : In sufficient balance !!");
        try{
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 400, LocalDateTime.now().plusDays(1)); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            
            Customer customer = new Customer("Hager", 100.0);
           
            Cart cart = new Cart();
            cart.addProduct(cheese, 2);
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);

            CheckoutService checkoutService = new CheckoutService(customer,cart);
            checkoutService.checkout();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        //Test 7 ( Expired:Invalid)

        System.out.println("Not Valid : Expired !!");
        try{
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 400,  LocalDateTime.now().minusDays(1)); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            
            Customer customer = new Customer("Mustafa", 2000.0);
            
            Cart cart = new Cart();
            cart.addProduct(cheese, 2);
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);

            
            CheckoutService checkoutService = new CheckoutService(customer,cart);
            checkoutService.checkout();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }


    }
}
