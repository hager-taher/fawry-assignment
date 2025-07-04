package Models;

public class Product {

    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        if (name.isEmpty())
            throw new IllegalArgumentException("Error: Name must not be empty !!");
        if (price < 0.0)
            throw new IllegalArgumentException("Error: Price must be a positive number !!");
        if (quantity < 0)
            throw new IllegalArgumentException("Error: Quantity must be a positive number !!");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        if (quantity > 0)
            this.quantity += quantity;
        else
            throw new IllegalArgumentException("Error:Quantity must be a positive number !!");

    }

    public void decreaseQuantity(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Error:Quantity must be a positive number !!");
        else if (this.quantity < quantity)
            throw new IllegalArgumentException("Error: Insufficient operation !! ");
        else {
            this.quantity -= quantity;

        }
    }
}

