import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<IceCreamComponent> components = new ArrayList<>();
    private final boolean waffleCone;
    public Order(boolean waffleCone) {
        this.waffleCone = waffleCone;
    }
    public void addComponent(IceCreamComponent component, int quantity) {
        for (int i = 0; i < quantity; i++) {
            components.add(component);
        }
    }

    public double calculateSubtotal() {
        double subtotal = waffleCone ? 5.0 : 0; // Waffle cone price
        for (IceCreamComponent component : components) {
            subtotal += component.getPrice();
        }
        return subtotal;
    }

    public double calculateTotalWithTax() {
        double subtotal = calculateSubtotal();
        return subtotal + (subtotal * 0.08);
    }

    public List<IceCreamComponent> getComponents() {
        return components;
    }

    public boolean hasWaffleCone() {
        return waffleCone;
    }


}
