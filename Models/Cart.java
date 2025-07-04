package Models;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public HashMap<Product, Integer> getProducts() {
        return items;
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Error: Quantity must be a positive number");
        else if (product.getQuantity() < quantity)
            throw new IllegalArgumentException("Error: Not enough stock for " + product.getName() + "!!");
        else {
            items.put(product, items.getOrDefault(product, 0) + quantity);

        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

}
