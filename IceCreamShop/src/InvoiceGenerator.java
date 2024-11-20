import java.io.IOException;

public interface InvoiceGenerator {
    void generateInvoice(Order order, String filePath) throws IOException;
}
