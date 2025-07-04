package Models;

import java.time.LocalDateTime;

import Interfaces.Expirable;

public class ExpirableProduct  extends Product implements Expirable{
    private LocalDateTime expirationDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDateTime expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
   public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
