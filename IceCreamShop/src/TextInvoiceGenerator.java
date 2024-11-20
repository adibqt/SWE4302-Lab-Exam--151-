import java.io.FileWriter;
import java.io.IOException;

public class TextInvoiceGenerator implements InvoiceGenerator {
    @Override
    public void generateInvoice(Order order, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Ice Cream Shop Invoice\n");
            for (IceCreamComponent component : order.getComponents()) {
                writer.write(component.getName() + ": $" + String.format("%.2f", component.getPrice()) + "\n");
            }
            if (order.hasWaffleCone()) {
                writer.write("Waffle Cone: $5.00\n");
            }
            double subtotal = order.calculateSubtotal();
            writer.write("Subtotal: $" + String.format("%.2f", subtotal) + "\n");
            writer.write("Tax: $" + String.format("%.2f", subtotal * 0.08) + "\n");
            writer.write("Total Amount Due: $" + String.format("%.2f", order.calculateTotalWithTax()) + "\n");
        }
    }
}
