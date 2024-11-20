import java.io.IOException;

public class IceCreamShop {
    public static void main(String[] args) throws IOException{

        InvoiceGenerator invoiceGenerator = new TextInvoiceGenerator();

        IceCreamFlavor mint = new IceCreamFlavor("Mint Chocolate Chip", 2.80);
        IceCreamFlavor chocolate = new IceCreamFlavor("Chocolate Fudge", 3.00);
        IceCreamTopping sprinkles = new IceCreamTopping("Sprinkles", 0.30);
        IceCreamTopping strawberries = new IceCreamTopping("Fresh Strawberries", 1.00);

        Order order = new Order(true); // Waffle Cone selected
        order.addComponent(mint, 2);
        order.addComponent(chocolate, 1);
        order.addComponent(sprinkles, 1);
        order.addComponent(strawberries, 2);

        invoiceGenerator.generateInvoice(order, "invoice.txt");

        System.out.println("Subtotal: $" + String.format("%.2f", order.calculateSubtotal()));
        System.out.println("Total (with Tax): $" + String.format("%.2f", order.calculateTotalWithTax()));



    }

}
