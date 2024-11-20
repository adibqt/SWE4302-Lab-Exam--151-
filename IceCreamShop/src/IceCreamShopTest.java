import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IceCreamShopTest {
    // Test 1: Validate Ice Cream Flavor Price
    @Test
    void testIceCreamFlavorPrice() {
        IceCreamFlavor flavor = new IceCreamFlavor("Mint Chocolate Chip", 2.80);
        assertEquals("Mint Chocolate Chip", flavor.getName());
        assertEquals(2.80, flavor.getPrice());
    }

    // Test 2: Validate Ice Cream Topping Price
    @Test
    void testIceCreamToppingPrice() {
        IceCreamTopping topping = new IceCreamTopping("Sprinkles", 0.30);
        assertEquals("Sprinkles", topping.getName());
        assertEquals(0.30, topping.getPrice());
    }

    // Test 3: Validate Subtotal Calculation (No Waffle Cone)
    @Test
    void testOrderSubtotalWithoutWaffleCone() {
        Order order = new Order(false); // No Waffle Cone
        IceCreamFlavor flavor = new IceCreamFlavor("Chocolate Fudge", 3.00);
        IceCreamTopping topping = new IceCreamTopping("Fresh Strawberries", 1.00);

        order.addComponent(flavor, 2); // 2 scoops
        order.addComponent(topping, 1); // 1 topping

        assertEquals(7.00, order.calculateSubtotal());
    }
    // Test 4: Validate Subtotal Calculation (With Waffle Cone)
    @Test
    void testOrderSubtotalWithWaffleCone() {
        Order order = new Order(true); // Waffle Cone
        IceCreamFlavor flavor = new IceCreamFlavor("Chocolate Fudge", 3.00);

        order.addComponent(flavor, 3); // 3 scoops

        // 3 * $3.00 + $5.00 (Waffle Cone)
        assertEquals(14.00, order.calculateSubtotal());
    }

    // Test 5: Validate Total Price with Tax
    @Test
    void testTotalPriceWithTax() {
        Order order = new Order(false); // No Waffle Cone
        IceCreamFlavor flavor = new IceCreamFlavor("Mint Chocolate Chip", 2.80);
        IceCreamTopping topping = new IceCreamTopping("Sprinkles", 0.30);

        order.addComponent(flavor, 2); // 2 scoops
        order.addComponent(topping, 3); // 3 toppings

        double subtotal = order.calculateSubtotal(); // 2 * $2.80 + 3 * $0.30 = $6.80
        double totalWithTax = order.calculateTotalWithTax(); // $6.80 + (8% tax) = $7.34

        assertEquals(6.80, subtotal);
        assertEquals(7.34, totalWithTax, 0.01); // Allowing for floating-point precision
    }


    // Test 6: Validate Invoice Generation


    @Test
    void testInvoiceGeneration() throws IOException {
        // Prepare Order
        Order order = new Order(true); // Waffle Cone
        IceCreamFlavor flavor = new IceCreamFlavor("Strawberry Swirl", 2.75);
        IceCreamTopping topping = new IceCreamTopping("Crushed Oreo", 0.85);

        order.addComponent(flavor, 1); // 1 scoop
        order.addComponent(topping, 2); // 2 toppings

        // Generate Invoice
        TextInvoiceGenerator invoiceGenerator = new TextInvoiceGenerator();
        String filePath = "test_invoice.txt";
        invoiceGenerator.generateInvoice(order, filePath);

        // Read Invoice File
        StringBuilder invoiceContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                invoiceContent.append(line).append("\n");
            }
        }


        String expectedInvoice = """
        Ice Cream Shop Invoice
        Crushed Oreo - 2 times: $1.70
        Strawberry Swirl - 1 time: $2.75
        Subtotal: $9.45
        Tax: $0.76
        Total Amount Due: $10.21
        """;

        assertEquals(expectedInvoice.trim(), invoiceContent.toString().trim());
    }





}
