package Services;

import java.util.HashMap;
import Models.*;
import Interfaces.*;

public class CheckoutService {
    private Customer customer;
    private Cart cart;
    private double shippingFeePerKg = 50;

    public CheckoutService(final Customer customer, final Cart cart) {
        this.customer = customer;
        this.cart = cart;
    }

    public void checkout() {

        try {
            if (cart.isEmpty()) {
                throw new IllegalArgumentException("Error: Cart is empty !!");

            }

            double subtotal = 0;
            double shippingFees = 0;
            HashMap<Shippable, Integer> shippableProducts = new HashMap<>();

            // --------processing for the cart---------
            for (var entry : cart.getProducts().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();

                // -------- Checking the expiration and stock of products -------------
                if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                    System.out.println("Error: Product " + product.getName() + " is expired !!");
                    return;
                }
                if (product.getQuantity() < quantity) {
                    System.out.println("Error: Not enough stock for " + product.getName() + " !!");
                    return;
                }

                // -------- Subtotal Calculation --------------
                subtotal += product.getPrice() * quantity;
                product.decreaseQuantity(quantity);

                // ------------Checking for shipping and process this--------------------
                if (product instanceof Shippable) {
                    shippableProducts.put((Shippable) product,
                            shippableProducts.getOrDefault((Shippable) product, 0) + quantity);

                }
            }

            // -------Shipping Fees Calculations--------------
            if (!shippableProducts.isEmpty()) {
                double totalWeight = shippableProducts.entrySet().stream()
                        .mapToDouble(entry -> entry.getKey().getWeight() * entry.getValue()).sum() / 1000;
                shippingFees = totalWeight * shippingFeePerKg;
            }

            double totalAmount = subtotal + shippingFees;

            // ------ Check for customer balance ----
            if (customer.getBalance() < totalAmount) {
                System.out.println("Error: Insufficient balance !!");
                return;
            }
            // ----- Shipping products with ship fees receipt----------------
            ShippingService shippingService = new ShippingService();
            shippingService.ship(shippableProducts);

            // ------Decreasing the customer balance-----
            double oldBalance = customer.getBalance();
            customer.decreaseBalance(totalAmount);
            printReceipt(subtotal, shippingFees, totalAmount, customer.getBalance(), oldBalance);
        } catch (Exception ex) {
            throw ex;
        }

    }

    private void printReceipt(double subtotal, double shippingFees, double totalAmount, double customerBalance,
            double oldBalance) {
        System.out.println("** Checkout receipt **");
        System.out.printf("Customer Balance before process        %.2f\n", oldBalance);
        System.out.printf("Subtotal         %.2f\n", subtotal);
        System.out.printf("Shipping         %.2f\n", shippingFees);
        System.out.printf("Total Amount     %.2f\n", totalAmount);
        System.out.printf("Customer Balance after process        %.2f\n", customerBalance);
    }

}
