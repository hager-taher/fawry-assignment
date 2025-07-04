package Models;

public class Customer {

    private String name;
    private double balance;

    public Customer(String name, double balance) {
        if (name.isEmpty())
            throw new IllegalArgumentException("Error: Name must not be empty !!");
        if (balance < 0.0)
            throw new IllegalArgumentException("Error: Balance must be a positive number !!");
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void increaseBalance(double balance) {
        if (balance > 0)
            this.balance += balance;
        else
            throw new IllegalArgumentException("Error:Balance must be a positive number !!");
    }

    public void decreaseBalance(double balance) {
        if (balance <= 0)
            throw new IllegalArgumentException("Error:Balance must be a positive number !!");
        else if (this.balance < balance)
            throw new IllegalArgumentException("Error: Insufficient operation !! ");
        else {
            this.balance -= balance;

        }
    }

}
