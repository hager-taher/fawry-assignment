package Models;
import Interfaces.Shippable;
import java.time.LocalDateTime;


public class ShippableExpirableProduct extends ExpirableProduct implements Shippable {
    private double weight; 

    public ShippableExpirableProduct(String name, double price, int quantity, double weight, LocalDateTime expirationDate) {
        super(name, price, quantity, expirationDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}