import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class TextInvoiceGenerator implements InvoiceGenerator {
    @Override
    public void generateInvoice(Order order, String filePath) throws IOException {
        // Group flavors and toppings by name
        Map<String, Double> componentPrices = new HashMap<>();
        Map<String, Integer> componentCounts = new HashMap<>();

        for (IceCreamComponent component : order.getComponents()) {
            String name = component.getName();
            componentPrices.put(name, component.getPrice());
            componentCounts.put(name, componentCounts.getOrDefault(name, 0) + 1);
        }

        // Write grouped data to the invoice file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Ice Cream Shop Invoice\n");

            for (Map.Entry<String, Integer> entry : componentCounts.entrySet()) {
                String name = entry.getKey();
                int count = entry.getValue();
                double price = componentPrices.get(name) * count;

                // Use singular/plural format based on the component type
                if (name.contains("scoop")) {
                    writer.write(name + " - " + count + " scoop" + (count > 1 ? "s" : "") + ": $" + String.format("%.2f", price) + "\n");
                } else {
                    writer.write(name + " - " + count + " time" + (count > 1 ? "s" : "") + ": $" + String.format("%.2f", price) + "\n");
                }
            }

            // Add subtotal, tax, and total
            double subtotal = order.calculateSubtotal();
            double tax = subtotal * 0.08;
            double total = subtotal + tax;

            writer.write("Subtotal: $" + String.format("%.2f", subtotal) + "\n");
            writer.write("Tax: $" + String.format("%.2f", tax) + "\n");
            writer.write("Total Amount Due: $" + String.format("%.2f", total) + "\n");
        }
    }
}
