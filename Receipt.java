import java.io.FileNotFoundException;

public interface Receipt {
    void createOrderReceipt() throws FileNotFoundException;
    void createRefundReceipt() throws FileNotFoundException;
}