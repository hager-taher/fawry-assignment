package Services;

import Interfaces.Shippable;
import java.util.*;

public class ShippingService {
    public void ship(HashMap<Shippable, Integer> items) {
        double totalWeight = 0.0;
        System.out.println("** Shipment notice **");

        for (HashMap.Entry<Shippable, Integer> item : items.entrySet()) {
            String itemName = item.getKey().getName();
            int itemFrequency = item.getValue();
            double itemWeight = item.getKey().getWeight() * itemFrequency;
            totalWeight += itemWeight;

            System.out.printf("%dx %s\t %.1fg%n", itemFrequency, itemName, itemWeight);

        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000);

    }

}
